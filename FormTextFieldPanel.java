import javax.swing.*;
import java.awt.*;

public class FormTextFieldPanel extends JPanel {
    public FormTextFieldPanel(String name, Dimension dimension, int textFieldColumns) {
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setMaximumSize(dimension);
        this.add(new JLabel(name));
        this.add(Box.createHorizontalGlue());
        this.add(this.createTextField(textFieldColumns));
    }

    private JTextField createTextField(int columns) {
        JTextField textField = new JTextField(columns);
        textField.setMaximumSize(new Dimension(
                this.getMaximumSize().width / 2, this.getMaximumSize().height));
        return textField;
    }
}
