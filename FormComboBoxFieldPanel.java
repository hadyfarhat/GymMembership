import javax.swing.*;
import java.awt.*;

/**
 * Represents the Combobox field of a form
 */
public class FormComboBoxFieldPanel extends FormFieldPanel {
    /**
     * Class constructor specifying the name of the label and list of values for the combobox
     * It creates a combobox field and sets its name to the provided name parameter. It also sets its options to
     * the provided list parameter
     * It then adds the combobox field to the components list in {@link RegistrationForm}
     * It then adds the combobox field to this panel
     *
     * @param name name of the label
     * @param list option values for the combobox
     */
    public FormComboBoxFieldPanel(String name, String[] list) {
        super(name);
        JComboBox comboBox = createComboBoxField(name, list);
        RegistrationForm.components.put(name, comboBox);
        this.add(comboBox);
    }

    /**
     * Creates a JComboBox that will then be added to this panel
     * Sets its width to half the width of this panel
     * Sets its height to this panel
     * Sets its name to the provided name parameter
     *
     * @param name name of the combobox
     * @param list option values for the combobox
     * @return combobox with its name and options set
     */
    private JComboBox createComboBoxField(String name, String[] list) {
        JComboBox comboBox = new JComboBox(list);
        comboBox.setMaximumSize(new Dimension(
                this.getMaximumSize().width / 2, this.getMaximumSize().height));
        comboBox.setName(name);
        return comboBox;
    }
}
