import javax.swing.*;
import java.awt.*;

public class RegistrationFormFrame extends JFrame {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;

    public RegistrationFormFrame() {
        setTitle("Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
    }

    public static void main(String[] args) {
        RegistrationFormFrame frame = new RegistrationFormFrame();
        Container cp = frame.getContentPane();
        JTabbedPane tabbedPane = new JTabbedPane();

        String[] formSectionNames = {"Personal", "Contact", "Health", "Membership"};
        for (String name : formSectionNames) {
            frame.createPanelAndAddToTabbedPane(name, tabbedPane);
        }

        tabbedPane.setComponentAt(0, new PersonalRegistrationFormPanel());

        cp.add(tabbedPane);
        frame.setVisible(true);
    }

    private void createPanelAndAddToTabbedPane(String panelTitle, JTabbedPane tabbedPane) {
        JPanel panel = new JPanel();
        tabbedPane.addTab(panelTitle, null, panel, panelTitle);
    }
}
