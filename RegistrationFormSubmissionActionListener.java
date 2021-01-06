import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

public class RegistrationFormSubmissionActionListener implements ActionListener {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        Member member = new Member();
        Membership membership = new Membership();

        for (Map.Entry<String, Component> entry : RegistrationForm.components.entrySet()) {
            String fieldName = entry.getKey();
            Component field = entry.getValue();

            switch (fieldName) {
                case "First Name":
                    member.setFirstName(getTextFromTextField(field));
                    break;
                case "Last Name":
                    member.setLastName(getTextFromTextField(field));
                    break;
                case "Date":
                    LocalDate date = LocalDate.parse(getTextFromTextField(field), Member.dobFormatter);
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
                case "Individual":
                    if (radioButtonIsSelected(field)) membership.setType("Individual");
                    break;
                case "Family":
                    if (radioButtonIsSelected(field)) membership.setType("Family");
                    break;
                case "Start Date":
                    LocalDate startDate = LocalDate.parse(getTextFromTextField(field), Membership.dateFormatter);
                    membership.setStartDate(startDate);
                    break;
                case "Duration":
                    String duration = getSelectedItemInComboBox((JComboBox) field);
                    int durationInt = convertDurationIntoInt(duration);
                    membership.calculateEndDate(durationInt);
                    membership.calculatePrice(durationInt);
                    break;
                default:
                    System.out.println("Field not recognised");
                    System.out.println(field.getName());
            }
        }

        System.out.println("Member details");
        System.out.println("First Name: " + member.getFirstName());
        System.out.println("Last Name: " + member.getLastName());
        System.out.println("Date of birth: " + member.getFormattedDob());
        System.out.println("Telephone Number: " + member.getTelephoneNumber());
        System.out.println("Address: " + member.getAddress());
        System.out.println("Gender: " + member.getGender());

        System.out.println("Membership details");
        System.out.println("Type: " + membership.getType());
        System.out.println("Start Date: " + membership.getFormattedStartDate());
        System.out.println("End Date: " + membership.getFormattedEndDate());
        System.out.println("Price: £" + membership.getPrice());

        try {
            member.addToFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private String getTextFromTextField(Component component) {
        return ((JTextField) component).getText();
    }

    private boolean radioButtonIsSelected(Component component) {
        JRadioButton button = (JRadioButton) component;
        return button.isSelected();
    }

    private String getSelectedItemInComboBox(JComboBox comboBox) {
        return (String) comboBox.getSelectedItem();
    }

    private int convertDurationIntoInt(String duration) {
        int durationInt;
        switch(duration) {
            case "1 month":
                durationInt = 1;
                break;
            case "2 months":
                durationInt = 2;
                break;
            case "3 months":
                durationInt = 3;
                break;
            case "4 months":
                durationInt = 4;
                break;
            case "5 months":
                durationInt = 5;
                break;
            case "6 months":
                durationInt = 6;
                break;
            default:
                durationInt = 0;
        }

        return durationInt;
    }
}
