import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class MembershipRegistrationForm extends RegistrationFormPanel {
    public MembershipRegistrationForm() {
        super();

        String[] durationPeriods = {
                "1 month", "2 months", "3 months", "4 months", "5 months", "6 months"
        };

        List<JPanel> panels = Arrays.asList(
                new FormRadioButtonsPanel("Membership Type", "Individual", "Family"),
                new FormDateFieldPanel("Start Date", "dd/MM/yy"),
                new FormComboBoxFieldPanel("Duration", durationPeriods)
        );

        addPanels(panels);
    }
}
