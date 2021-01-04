import javax.swing.*;

public class PersonalRegistrationFormPanel extends RegistrationFormPanel {

    public PersonalRegistrationFormPanel() {
        super();

        JPanel namePanel = new FormTextFieldPanel("Name");
        JPanel datePanel = new FormDateFieldPanel("Date", "dd/MM/yy");
        JPanel genderPanel = new FormRadioButtonsPanel("Gender", "Male", "Female");

        addPanelWithRigidArea(namePanel);
        addPanelWithRigidArea(datePanel);
        add(genderPanel);
    }
}
