import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FormDateFieldPanel extends FormTextComponentFieldPanel {
    public FormDateFieldPanel(String name, Dimension dimension, int dateFieldColumns, String pattern) {
        super(name, dimension);
        add(this.createDateField(dateFieldColumns, pattern));
    }

    private JFormattedTextField createDateField(int columns, String pattern) {
        DateFormat format = new SimpleDateFormat(pattern);
        JFormattedTextField dateField = new JFormattedTextField(format);
        dateField.setMaximumSize(new Dimension(
                this.getMaximumSize().width / 2, this.getMaximumSize().height));
        dateField.setColumns(columns);
        return dateField;
    }
}
