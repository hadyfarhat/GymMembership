import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckInFormActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField idField = (JTextField) MembershipForm.components.get("ID");

        int id = Integer.parseInt(idField.getText());
        System.out.println("ID: " + id);
        System.out.println("ID exists? : " + Validator.idIsValid(id));
    }
}