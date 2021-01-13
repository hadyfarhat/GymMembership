import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MembershipForm extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 500;
    public static final int TEXT_FIELD_COLUMNS = 10;
    public static final int FIELD_RIGID_AREA_HEIGHT = 5;
    public static final Dimension FIELD_DIMENSIONS = new Dimension(WIDTH / 2, 30);

    public static HashMap<String, Component> components = new HashMap<String, Component>();

    public MembershipForm() {
        setTitle("Club Membership");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
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
        button.addActionListener(new RegistrationFormSubmissionActionListener(tabbedPane));

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

    /**
     * Creates the membership management panel which contains an editable table of all member data
     *
     * @return membership management panel
     */
    public JPanel createMembershipManagementPanel() {
        JTable membershipTable = new JTable(new MembershipTableModel());
        JPanel membershipManagementPanel = new MembershipManagementPanel(membershipTable);

        return membershipManagementPanel;
    }

    /**
     * This will clear all the data found inside form components.
     * It will loop through each component in the components map and will
     * clear its data depending on its type
     */
    public static void clearFormComponents() {
        for (Map.Entry<String, Component> entry : MembershipForm.components.entrySet()) {
            Component field = entry.getValue();
            String fieldClassName = field.getClass().getName();
            switch (fieldClassName) {
                case "javax.swing.JFormattedTextField":
                    JFormattedTextField jFormattedTextField = (JFormattedTextField) field;
                    jFormattedTextField.setText("");
                    break;
                case "javax.swing.JTextField":
                    JTextField jTextField = (JTextField) field;
                    jTextField.setText("");
                    break;
                case "javax.swing.JRadioButton":
                    JRadioButton jRadioButton = (JRadioButton) field;
                    // This doesn't work. Only way to clear radio button selection is by using is button group
                    jRadioButton.setSelected(false);
                    break;
                case "javax.swing.JComboBox":
                    JComboBox jComboBox = (JComboBox) field;
                    jComboBox.setSelectedIndex(0);
                    break;
                default:
                    System.out.println("Component not recognised");
            }
        }
    }
}
