import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;

public class RegistrationFormSubmissionActionListener implements ActionListener {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        for (Map.Entry<String, Component> entry : RegistrationForm.components.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println();
        }
    }
}
