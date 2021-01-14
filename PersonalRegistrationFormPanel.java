import javax.swing.*;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the personal section of the registration form.
 * It has the following fields:
 * <ul>
 *     <li>First Name - Text Field</li>
 *     <li>Last Name - Text Field</li>
 *     <li>Date - Date Field</li>
 *     <li>Gender - Radio Buttons (Male or Female)</li>
 * </ul>
 */
public class PersonalRegistrationFormPanel extends RegistrationFormPanel {

    public PersonalRegistrationFormPanel() {
        super();

        List<JPanel> panels = Arrays.asList(
                new FormTextFieldPanel("First Name"),
                new FormTextFieldPanel("Last Name"),
                new FormDateFieldPanel("Date", "dd/MM/yyyy"),
                new FormRadioButtonsPanel("Gender", "Male", "Female")
        );

        addPanels(panels);
    }
}
