import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class MembershipForm extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;
    public static final int TEXT_FIELD_COLUMNS = 10;
    public static final int FIELD_RIGID_AREA_HEIGHT = 5;
    public static final Dimension FIELD_DIMENSIONS = new Dimension(WIDTH - 100, 30);

    public static HashMap<String, Component> components = new HashMap<String, Component>();

    public MembershipForm() {
        setTitle("Club Membership");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
    }

    private void createPanelAndAddToTabbedPane(String panelTitle, JTabbedPane tabbedPane) {
        JPanel panel = new JPanel();
        tabbedPane.addTab(panelTitle, null, panel, panelTitle);
    }

    /**
     * Creates the registration form panel.
     * The panel will include a tabbed pane which will include the different sections of the form.
     * The different sections are:
     * - Personal
     * - Contact
     * - Health
     * - Membership
     *
     * @return registration form panel
     */
    public JPanel createRegistrationForm() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setMaximumSize(new Dimension(
                MembershipForm.WIDTH / 2, MembershipForm.HEIGHT / 2));

        String[] formSectionNames = {"Personal", "Contact", "Health", "Membership"};
        for (String name : formSectionNames) {
            createPanelAndAddToTabbedPane(name, tabbedPane);
        }

        tabbedPane.setComponentAt(0, new PersonalRegistrationFormPanel());
        tabbedPane.setComponentAt(1, new ContactRegistrationFormPanel());
        tabbedPane.setComponentAt(3, new MembershipRegistrationForm());

        JButton button = new JButton("Register");
        button.addActionListener(new RegistrationFormSubmissionActionListener());

        JPanel registrationForm = new JPanel();
        registrationForm.setLayout(new BorderLayout());
        registrationForm.add(tabbedPane, BorderLayout.CENTER);
        registrationForm.add(button, BorderLayout.SOUTH);

        return registrationForm;
    }

    /**
     * Creates the check in form panel which contains an ID text field and a sign in button
     *
     * @return check in form panel
     */
    public JPanel createCheckInForm() {
        JPanel signInIDPanel = new SignInIDPanel();

        JButton button = new JButton("Sign in");
        button.addActionListener(new CheckInFormActionListener());

        JPanel checkInForm = new JPanel();
        checkInForm.setLayout(new BorderLayout());
        checkInForm.add(signInIDPanel, BorderLayout.CENTER);
        checkInForm.add(button, BorderLayout.SOUTH);

        return checkInForm;
    }
}
