import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class MembershipRegistrationForm extends RegistrationFormPanel {
    public MembershipRegistrationForm() {
        super();

        List<JPanel> panels = Arrays.asList(
                new FormRadioButtonsPanel("Membership Type", "Individual", "Family"),
                new FormDateFieldPanel("Start Date", "dd/MM/yy"),
                new FormDateFieldPanel("End Date", "dd/MM/yy"),
                new FormTextFieldPanel("Price")
        );

        addPanels(panels);
    }
}
