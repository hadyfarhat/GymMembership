import javax.swing.*;
import java.awt.*;

public abstract class RegistrationFormPanel extends JPanel {

    public RegistrationFormPanel() {
        setSize(RegistrationForm.WIDTH, RegistrationForm.HEIGHT);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    public void addPanelWithRigidArea(JPanel panel, int rigidAreaHeight) {
        add(panel);
        add(Box.createRigidArea(new Dimension(RegistrationForm.WIDTH, rigidAreaHeight)));
    }

    public void addPanelWithRigidArea(JPanel panel) {
        add(panel);
        add(Box.createRigidArea(new Dimension(RegistrationForm.WIDTH, RegistrationForm.FIELD_RIGID_AREA_HEIGHT)));
    }
}
