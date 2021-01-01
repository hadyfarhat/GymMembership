import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Map;

public class RegistrationFormSubmissionActionListener implements ActionListener {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        Member member = new Member();

        for (Map.Entry<String, Component> entry : RegistrationForm.components.entrySet()) {
            String fieldName = entry.getKey();
            Component field = entry.getValue();

            switch (fieldName) {
                case "Name":
                    member.setName(getTextFromTextField(field));
                    break;
                case "Date":
                    LocalDate date = LocalDate.parse(getTextFromTextField(field));
                    member.setDob(date);
                    break;
                case "Telephone Number":
                    member.setTelephoneNumber(getTextFromTextField(field));
                    break;
                case "Postal Address":
                    member.setAddress(getTextFromTextField(field));
                    break;
                case "Female":
                    if (radioButtonIsSelected(field)) member.setGender("Female");
                    break;
                case "Male":
                    if (radioButtonIsSelected(field)) member.setGender("Male");
                    break;
                default:
                    System.out.println("Field not recognised");
                    System.out.println(field.getName());
            }
        }

        System.out.println("Member details");
        System.out.println("Name: " + member.getName());
        System.out.println("Date of birth: " + member.getDob().toString());
        System.out.println("Telephone Number: " + member.getTelephoneNumber());
        System.out.println("Address: " + member.getAddress());
        System.out.println("Gender: " + member.getGender());
    }

    private String getTextFromTextField(Component component) {
        return ((JTextField) component).getText();
    }

    private boolean radioButtonIsSelected(Component component) {
        JRadioButton button = (JRadioButton) component;
        return button.isSelected();
    }
}
