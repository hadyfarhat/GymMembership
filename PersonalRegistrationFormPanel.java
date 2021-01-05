import javax.swing.*;

public class PersonalRegistrationFormPanel extends RegistrationFormPanel {

    public PersonalRegistrationFormPanel() {
        super();

        JPanel firstNamePanel = new FormTextFieldPanel("First Name");
        JPanel lastNamePanel = new FormTextFieldPanel("Last Name");
        JPanel datePanel = new FormDateFieldPanel("Date", "dd/MM/yy");
        JPanel genderPanel = new FormRadioButtonsPanel("Gender", "Male", "Female");

        addPanelWithRigidArea(firstNamePanel);
        addPanelWithRigidArea(lastNamePanel);
        addPanelWithRigidArea(datePanel);
        add(genderPanel);
    }
}
