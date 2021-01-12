/**
 * Represents the Sign in ID section of the check in form.
 * It contains only an ID text field in which the user will enter his/her id to check in
 */
public class SignInIDPanel extends RegistrationFormPanel {
    public SignInIDPanel() {
        super();

        FormTextFieldPanel id = new FormTextFieldPanel("ID");
        addPanelWithRigidArea(id);
    }
}
