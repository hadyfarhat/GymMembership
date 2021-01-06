import javax.swing.*;
import java.awt.*;

public class FormComboBoxFieldPanel extends FormFieldPanel {
    public FormComboBoxFieldPanel(String name, String[] list) {
        super(name);
        JComboBox comboBox = createComboBoxField(name, list);
        RegistrationForm.components.put(name, comboBox);
        this.add(comboBox);
    }

    private JComboBox createComboBoxField(String name, String[] list) {
        JComboBox comboBox = new JComboBox(list);
        comboBox.setMaximumSize(new Dimension(
                this.getMaximumSize().width / 2, this.getMaximumSize().height));
        comboBox.setName(name);
        return comboBox;
    }
}
