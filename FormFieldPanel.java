import javax.swing.*;
import java.awt.*;

public abstract class FormFieldPanel extends JPanel {
    public FormFieldPanel(String name, Dimension dimension) {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setMaximumSize(dimension);
        add(new JLabel(name));
        add(Box.createHorizontalGlue());
    }

    public FormFieldPanel(String name) {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setMaximumSize(RegistrationFormFrame.FIELD_DIMENSIONS);
        add(new JLabel(name));
        add(Box.createHorizontalGlue());
    }
}
