import javax.swing.*;
import java.awt.*;

public class FormTextFieldPanel extends FormFieldPanel {
    public FormTextFieldPanel(String name, Dimension dimension, int textFieldColumns) {
        super(name, dimension);
        this.add(this.createTextField(textFieldColumns));
    }

    public FormTextFieldPanel(String name) {
        super(name);
        this.add(this.createTextField(RegistrationFormFrame.TEXT_FIELD_COLUMNS));
    }

    private JTextField createTextField(int columns) {
        JTextField textField = new JTextField(columns);
        textField.setMaximumSize(new Dimension(
                this.getMaximumSize().width / 2, this.getMaximumSize().height));
        return textField;
    }
}
