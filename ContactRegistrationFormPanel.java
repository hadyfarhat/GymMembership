import javax.swing.*;
import java.awt.*;

public class ContactRegistrationFormPanel extends RegistrationFormPanel {

    public ContactRegistrationFormPanel() {
        super();

        JPanel postalAddress = new FormTextFieldPanel("Postal Address");
        JPanel telephoneNumber = new FormTextFieldPanel("Telephone Number");

        addPanelWithRigidArea(postalAddress);
        add(telephoneNumber);
    }
}
