import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FormDateFieldPanel extends FormFieldPanel {
    public FormDateFieldPanel(String name, Dimension dimension, int dateFieldColumns, String pattern) {
        super(name, dimension);
        add(this.createDateField(name, dateFieldColumns, pattern));
    }

    public FormDateFieldPanel(String name, String pattern) {
        super(name);
        JFormattedTextField field = this.createDateField(name, RegistrationForm.TEXT_FIELD_COLUMNS, pattern);
        RegistrationForm.components.add(field);
        add(field);
    }

    private JFormattedTextField createDateField(String name, int columns, String pattern) {
        DateFormat format = new SimpleDateFormat(pattern);
        JFormattedTextField dateField = new JFormattedTextField(format);
        dateField.setMaximumSize(new Dimension(
                this.getMaximumSize().width / 2, this.getMaximumSize().height));
        dateField.setColumns(columns);
        dateField.setName(name.toLowerCase());
        return dateField;
    }
}
