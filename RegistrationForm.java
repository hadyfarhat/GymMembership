import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class RegistrationForm extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    public static final int TEXT_FIELD_COLUMNS = 10;
    public static final int FIELD_RIGID_AREA_HEIGHT = 5;
    public static final Dimension FIELD_DIMENSIONS = new Dimension(WIDTH - 100, 30);

    public static HashMap<String, Component> components = new HashMap<String, Component>();

    public RegistrationForm() {
        setTitle("Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
    }

    public static void main(String[] args) {
        RegistrationForm frame = new RegistrationForm();
        Container cp = frame.getContentPane();
        JTabbedPane tabbedPane = new JTabbedPane();

        String[] formSectionNames = {"Personal", "Contact", "Health", "Membership"};
        for (String name : formSectionNames) {
            frame.createPanelAndAddToTabbedPane(name, tabbedPane);
        }

        tabbedPane.setComponentAt(0, new PersonalRegistrationFormPanel());
        tabbedPane.setComponentAt(1, new ContactRegistrationFormPanel());
        tabbedPane.setComponentAt(3, new MembershipRegistrationForm());

        JButton button = new JButton("Submit");
        button.addActionListener(new RegistrationFormSubmissionActionListener());
        cp.add(tabbedPane, BorderLayout.CENTER);
        cp.add(button, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void createPanelAndAddToTabbedPane(String panelTitle, JTabbedPane tabbedPane) {
        JPanel panel = new JPanel();
        tabbedPane.addTab(panelTitle, null, panel, panelTitle);
    }
}
