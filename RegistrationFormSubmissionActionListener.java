import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the action listener for the submit button on the Registration Form.
 * Once this button is clicked, this class gets called and the function actionPerformed() gets executed.
 * This class acts as a controller between the form (UI), the validators and the database (model).
 */
public class RegistrationFormSubmissionActionListener implements ActionListener {
    private Component parentComponent;

    /**
     * Class constructor which initialises the parent component reference.
     * This reference is mainly used when displaying message dialogs.
     *
     * @param parentComponent reference to the registration form that contains the button of this action listener
     */
    public RegistrationFormSubmissionActionListener(Component parentComponent) {
        this.parentComponent = parentComponent;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        Member member = new Member();
        Membership membership = new Membership();

        for (Map.Entry<String, Component> entry : MembershipForm.components.entrySet()) {
            String fieldName = entry.getKey();
            Component field = entry.getValue();

            switch (fieldName) {
                case "First Name":
                    member.setFirstName(getTextFromTextField(field));
                    break;
                case "Last Name":
                    member.setLastName(getTextFromTextField(field));
                    break;
                case "DOB":
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

        membership.setMember(member);

        boolean membershipIsValid = Validator.membershipIsValid(membership);
        if (!membershipIsValid){
            String validationMessage = Validator.getValidationMessage(membership);
            JOptionPane.showMessageDialog(parentComponent, validationMessage);
        } else {
            try {
                int id = membership.addToFile();
                MembershipForm.clearFormComponents();
                MembershipManagementPanel.refreshTableData();
                JOptionPane.showMessageDialog(parentComponent,
                        "Member has been registered. Please use the following ID to check in: " + id);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
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
