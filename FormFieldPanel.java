import javax.swing.*;
import java.awt.*;

/**
 * An abstract class which all form field panels extend from.
 * Form field panels have a certain naming convention: Form_Field_Name_Panel
 * where _Field_Name_ is the name of the form field that will extend this class.
 * An example of this is:
 * - {@link FormDateFieldPanel} which represents a date field
 * - {@link FormTextFieldPanel} which represents a text field
 *
 * Because all fields in the form have a corresponding label, the constructors will automatically add a label to this
 * panel. Then, once this is extended, a field (ex: {@link FormTextFieldPanel}) could be added without having to create
 * a new label.
 */
public abstract class FormFieldPanel extends JPanel {
    /**
     * Class constructor specifying label name and maximum size dimension
     * Sets the layout of this panel to Box Layout with a horizontal (line axis) orientation
     * Sets the maximum size of this panel to the provided Dimension parameter
     * Adds a label to this panel and sets its name to the provded name parameter
     * Adds a horizontal glue next to the label in order to organise it next to the field that will be added later
     *
     * @param name the name of the label
     * @param dimension the dimension that will be used to set the max size of this panel
     */
    public FormFieldPanel(String name, Dimension dimension) {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setMaximumSize(dimension);
        add(new JLabel(name));
        add(Box.createHorizontalGlue());
    }

    /**
     * Class constructor specifying label name and default maximum size dimension
     * Sets the layout of this panel to Box Layout with a horizontal (line axis) orientation
     * Sets the maximum size of this panel to the FIELD_DIMENSIONS constant found in {@link MembershipForm}
     * Adds a label to this panel and sets its name to the provded name parameter
     * Adds a horizontal glue next to the label in order to organise it next to the field that will be added later
     *
     * @param name the name of the label
     */
    public FormFieldPanel(String name) {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setMaximumSize(MembershipForm.FIELD_DIMENSIONS);
        add(new JLabel(name));
        add(Box.createHorizontalGlue());
    }
}
