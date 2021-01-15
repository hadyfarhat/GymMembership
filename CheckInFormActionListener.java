import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Represents the Action Listener for the Check In form button.
 * Its main responsibilities are to validate ids and memberships
 */
public class CheckInFormActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField idField = (JTextField) MembershipForm.components.get("ID");


        // Check if it's a Visitor
        if (idField.getText().equals("VISITOR")) {
            logVisitor(idField);
        } else {
            // If it's not a visitor then verify and log member
            boolean idAndMembershipAreValid = validateIdAndMembership(idField);
            if (idAndMembershipAreValid) {
                // after the above validations succeed -> sign and log in member
                logMember(idField);
            }
        }
    }

    /**
     * Logs visitor, displays successful message and clears id text field
     *
     * @param idField id field of the check in form
     */
    private void logVisitor(JTextField idField) {
        try {
            Log.log();
            JOptionPane.showMessageDialog(idField, "Visitor has been logged successfully");
            idField.setText("");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * Logs member, displays successful message and clear id text field
     *
     * @param idField id field of the check in form
     */
    private void logMember(JTextField idField) {
        int id = Integer.parseInt(idField.getText());

        try {
            Log.log(id);
            JOptionPane.showMessageDialog(idField, "#" + id + " has checked in successfully");
            idField.setText("");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * Validates if id exists in the database and if membership is not expired.
     * If either validation fails, it will display the relevant message.
     *
     * @param idField id field of the check in form
     * @return
     */
    private boolean validateIdAndMembership(JTextField idField) {
        boolean isValid = true;

        try {
            int id = Integer.parseInt(idField.getText());

            if (!Validator.idIsValid(id)) {
                isValid = false;
                JOptionPane.showMessageDialog(idField, "ID was not found in the database");
            } else if (!Membership.membershipIsValid(id)) {
                isValid = false;
                JOptionPane.showMessageDialog(idField, "Membership has expired");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(idField, "Invalid ID");
            isValid = false;
        }

        return isValid;
    }
}