import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class RegistrationFormPanel extends JPanel {

    public RegistrationFormPanel() {
        setSize(RegistrationForm.WIDTH, RegistrationForm.HEIGHT);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    private void addPanelWithRigidArea(JPanel panel, int rigidAreaHeight) {
        add(panel);
        add(Box.createRigidArea(new Dimension(RegistrationForm.WIDTH, rigidAreaHeight)));
    }

    private void addPanelWithRigidArea(JPanel panel) {
        add(panel);
        add(Box.createRigidArea(new Dimension(RegistrationForm.WIDTH, RegistrationForm.FIELD_RIGID_AREA_HEIGHT)));
    }

    public void addPanels(List<JPanel> panels) {
        if (panels.size() > 0) {
            for (int i = 0; i < panels.size() - 1; ++i) {
                addPanelWithRigidArea(panels.get(i));
            }
            add(panels.get(panels.size() - 1));
        }
    }
}
