import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

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
