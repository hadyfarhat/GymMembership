import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents the Action Listener for the Check In form button.
 * It's main responsibilities are to validate ids and memberships
 */
public class CheckInFormActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField idField = (JTextField) MembershipForm.components.get("ID");

        int id = Integer.parseInt(idField.getText());

        boolean idIsValid = Validator.idIsValid(id);
        if (!idIsValid) {
            JOptionPane.showMessageDialog(idField, "ID was not found in the database");
        } else if (!Membership.membershipIsValid(id)) {
            JOptionPane.showMessageDialog(idField, "Membership has expired");
        } else {
            JOptionPane.showMessageDialog(idField, "#" + id + " has checked in successfully");
        }
    }
}