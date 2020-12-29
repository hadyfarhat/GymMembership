import javax.swing.*;
import java.awt.*;

public abstract class RegistrationFormPanel extends JPanel {

    public RegistrationFormPanel() {
        setSize(RegistrationFormFrame.WIDTH, RegistrationFormFrame.HEIGHT);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    public void addPanelWithRigidArea(JPanel panel, int rigidAreaHeight) {
        add(panel);
        add(Box.createRigidArea(new Dimension(RegistrationFormFrame.WIDTH, rigidAreaHeight)));
    }
}
