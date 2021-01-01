import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegistrationFormSubmissionActionListener implements ActionListener {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        for (Component c : RegistrationForm.components) {
            if (c instanceof JTextField) {
                // System.out.println(((JTextField) c).getText());
                // System.out.println(((JTextField) c).getName());
                System.out.println(c.getName());
            } else if (c instanceof JRadioButton) {
                System.out.println(c.getName());
                System.out.println(((JRadioButton) c).isSelected());
            }
        }
    }
}
