import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FormDateFieldPanel extends JPanel {
    public FormDateFieldPanel(String name, Dimension dimension, int dateFieldColumns, String pattern) {
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setMaximumSize(dimension);
        this.add(new JLabel(name));
        this.add(Box.createHorizontalGlue());
        this.add(this.createDateField(dateFieldColumns, pattern));
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
