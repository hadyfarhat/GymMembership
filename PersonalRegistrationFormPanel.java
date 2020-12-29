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


        JPanel namePanel = new FormTextFieldPanel("Name", textComponentDimensions,10);
        JPanel datePanel = new FormDateFieldPanel(
                "Date", textComponentDimensions, 10, "dd/MM/yyyy");

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
