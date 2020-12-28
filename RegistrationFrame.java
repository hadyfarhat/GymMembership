import javax.swing.*;
import java.awt.*;

public class RegistrationFrame extends JFrame {
    public RegistrationFrame() {
        setTitle("Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
    }

    public static void main(String[] args) {
        RegistrationFrame frame = new RegistrationFrame();
        Container cp = frame.getContentPane();
        JTabbedPane tabbedPane = new JTabbedPane();

        frame.createPanelAndAddToTabbedPane("Personal", tabbedPane);
        frame.createPanelAndAddToTabbedPane("Contact", tabbedPane);
        frame.createPanelAndAddToTabbedPane("Health", tabbedPane);
        frame.createPanelAndAddToTabbedPane("Membership", tabbedPane);

        cp.add(tabbedPane);
        frame.setVisible(true);
    }

    private void createPanelAndAddToTabbedPane(String panelTitle, JTabbedPane tabbedPane) {
        JPanel panel = new JPanel();
        tabbedPane.addTab(panelTitle, null, panel, panelTitle);
    }
}
