import javax.swing.*;
import java.awt.*;

public abstract class FormFieldPanel extends JPanel {
    public FormFieldPanel(String name, Dimension dimension) {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setMaximumSize(dimension);
        add(new JLabel(name));
        add(Box.createHorizontalGlue());
    }
}
