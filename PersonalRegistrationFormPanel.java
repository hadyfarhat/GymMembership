import javax.swing.*;
import java.awt.*;

public class PersonalRegistrationFormPanel extends JPanel {
    private Dimension fieldDimensions;

    public PersonalRegistrationFormPanel() {
        setSize(RegistrationFormFrame.WIDTH, RegistrationFormFrame.HEIGHT);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        fieldDimensions = new Dimension(this.getWidth() / 2, 30);


        JPanel namePanel = new FormTextFieldPanel("Name", fieldDimensions,10);
        JPanel datePanel = new FormDateFieldPanel(
                "Date", fieldDimensions, 10, "dd/MM/yyyy");
        JPanel genderPanel = new FormRadioButtonsPanel(
                "Gender", fieldDimensions, "Male", "Female");


        add(namePanel);
        add(Box.createRigidArea(new Dimension(this.getWidth(), 5)));
        add(datePanel);
        add(Box.createRigidArea(new Dimension(this.getWidth(), 5)));
        add(genderPanel);
    }
}
