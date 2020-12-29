import javax.swing.*;
import java.awt.*;

public class FormTextFieldPanel extends FormTextComponentFieldPanel {
    public FormTextFieldPanel(String name, Dimension dimension, int textFieldColumns) {
        super(name, dimension);
        this.add(this.createTextField(textFieldColumns));
    }

    private JTextField createTextField(int columns) {
        JTextField textField = new JTextField(columns);
        textField.setMaximumSize(new Dimension(
                this.getMaximumSize().width / 2, this.getMaximumSize().height));
        return textField;
    }
}
