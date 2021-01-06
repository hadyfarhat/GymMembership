import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class PersonalRegistrationFormPanel extends RegistrationFormPanel {

    public PersonalRegistrationFormPanel() {
        super();

        List<JPanel> panels = Arrays.asList(
                new FormTextFieldPanel("First Name"),
                new FormTextFieldPanel("Last Name"),
                new FormDateFieldPanel("Date", "dd/MM/yy"),
                new FormRadioButtonsPanel("Gender", "Male", "Female")
        );

        addPanels(panels);
    }
}
