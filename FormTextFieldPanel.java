import javax.swing.*;
import java.awt.*;

public class FormTextFieldPanel extends FormFieldPanel {
    public FormTextFieldPanel(String name, Dimension dimension, int textFieldColumns) {
        super(name, dimension);
        this.add(this.createTextField(name, textFieldColumns));
    }

    public FormTextFieldPanel(String name) {
        super(name);
        JTextField field = this.createTextField(name, RegistrationForm.TEXT_FIELD_COLUMNS);
        RegistrationForm.components.put(name, field);
        this.add(field);
    }

    private JTextField createTextField(String name, int columns) {
        JTextField textField = new JTextField(columns);
        textField.setMaximumSize(new Dimension(
                this.getMaximumSize().width / 2, this.getMaximumSize().height));
        textField.setName(name.toLowerCase());
        return textField;
    }
}
