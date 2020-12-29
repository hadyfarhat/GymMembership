import javax.swing.*;
import java.awt.*;

public abstract class FormTextComponentFieldPanel extends JPanel {
    public FormTextComponentFieldPanel(String name, Dimension dimension) {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setMaximumSize(dimension);
        add(new JLabel(name));
        add(Box.createHorizontalGlue());
    }
}
