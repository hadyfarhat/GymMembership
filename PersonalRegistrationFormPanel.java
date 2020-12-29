import javax.swing.*;
import java.awt.*;

public class PersonalRegistrationFormPanel extends RegistrationFormPanel {

    public PersonalRegistrationFormPanel() {
        super();

        JPanel namePanel = new FormTextFieldPanel("Name", RegistrationFormFrame.FIELD_DIMENSIONS,10);
        JPanel datePanel = new FormDateFieldPanel(
                "Date", RegistrationFormFrame.FIELD_DIMENSIONS, 10, "dd/MM/yyyy");
        JPanel genderPanel = new FormRadioButtonsPanel(
                "Gender", RegistrationFormFrame.FIELD_DIMENSIONS, "Male", "Female");

        addPanelWithRigidArea(namePanel, RegistrationFormFrame.FIELD_RIGID_AREA_HEIGHT);
        addPanelWithRigidArea(datePanel, RegistrationFormFrame.FIELD_RIGID_AREA_HEIGHT);
        add(genderPanel);
    }
}
