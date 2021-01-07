import javax.swing.*;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the contact details section of the registration form.
 * It has the following fields:
 * <ul>
 *     <li>Postal Address - Text Field</li>
 *     <li>Telephone Number - Text Field</li>
 * </ul>
 */
public class ContactRegistrationFormPanel extends RegistrationFormPanel {

    public ContactRegistrationFormPanel() {
        super();

        List<JPanel> panels = Arrays.asList(
            new FormTextFieldPanel("Postal Address"),
            new FormTextFieldPanel("Telephone Number")
        );

        addPanels(panels);
    }
}
