import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PersonalRegistrationFormPanel extends JPanel {
    private Dimension textComponentDimensions;

    public PersonalRegistrationFormPanel() {
        setSize(RegistrationFormFrame.WIDTH, RegistrationFormFrame.HEIGHT);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        textComponentDimensions = new Dimension(this.getWidth() / 2, 30);

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.LINE_AXIS));
        namePanel.setMaximumSize(new Dimension(this.getWidth() / 2, 30));
        JLabel nameLabel = new JLabel("Name");
        JTextField name = new JTextField(10);
        name.setMaximumSize(new Dimension(namePanel.getMaximumSize().width / 2, namePanel.getMaximumSize().height));
        namePanel.add(nameLabel);
        namePanel.add(Box.createHorizontalGlue());
        namePanel.add(name);

        // JPanel namePanel = new FormTextFieldPanel("Name", textComponentDimensions,10);

        // Date
        // Setting up a panel
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.LINE_AXIS));
        datePanel.setMaximumSize(new Dimension(this.getWidth() / 2, 30));
        // Label
        JLabel dateLabel = new JLabel("Date");
        // Formatted Text Field
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        JFormattedTextField dob = new JFormattedTextField(df);
        dob.setColumns(10);
        dob.setMaximumSize(new Dimension(datePanel.getMaximumSize().width / 2, datePanel.getMaximumSize().height));
        // Add
        datePanel.add(dateLabel);
        datePanel.add(Box.createHorizontalGlue());
        datePanel.add(dob);

        // Gender
        // Setting up a panel
        JPanel genderPanel = new JPanel();
        genderPanel.setLayout(new BoxLayout(genderPanel, BoxLayout.LINE_AXIS));
        genderPanel.setMaximumSize(new Dimension(this.getWidth() / 2, 30));
        // Label
        JLabel genderLabel = new JLabel("Gender");
        // Radio Elements
        JRadioButton male = new JRadioButton("Male");
        JRadioButton female = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        // Add
        genderPanel.add(genderLabel);
        genderPanel.add(Box.createHorizontalGlue());
        genderPanel.add(male);
        genderPanel.add(female);


        add(namePanel);
        add(Box.createRigidArea(new Dimension(this.getWidth(), 5)));
        add(datePanel);
        add(Box.createRigidArea(new Dimension(this.getWidth(), 5)));
        add(genderPanel);
    }
}
