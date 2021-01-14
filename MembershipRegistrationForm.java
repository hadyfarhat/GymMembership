import javax.swing.*;
import java.util.Arrays;
import java.util.List;

/**
 * Represents the membership section of the registration form.
 * It has the following fields:
 * <ul>
 *     <li>Membership Type - Radio Buttons (Individual or Family)</li>
 *     <li>Start Date - Date Field</li>
 *     <li>Duration - Combobox Field ('1 month', '2 months', ... '6 months')</li>
 * </ul>
 *
 */
public class MembershipRegistrationForm extends RegistrationFormPanel {
    public MembershipRegistrationForm() {
        super();

        String[] durationPeriods = {
                "1 month", "2 months", "3 months", "4 months", "5 months", "6 months"
        };

        List<JPanel> panels = Arrays.asList(
                new FormRadioButtonsPanel("Membership Type", "Individual", "Family"),
                new FormDateFieldPanel("Start Date", "dd/MM/yyyy"),
                new FormComboBoxFieldPanel("Duration", durationPeriods)
        );

        addPanels(panels);
    }
}
