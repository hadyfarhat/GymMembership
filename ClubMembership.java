import javax.swing.*;
import java.awt.*;

public class ClubMembership {
    public static void main(String[] args) {
        MembershipForm membershipForm = new MembershipForm();
        Container contentPane = membershipForm.getContentPane();

        JPanel registrationForm = membershipForm.createRegistrationForm();
        JPanel checkInForm = membershipForm.createCheckInForm();
        JPanel membershipManagementForm = membershipForm.createMembershipManagementPanel();

        JTabbedPane mainPane = new JTabbedPane();
        mainPane.addTab("Registration Form", null, registrationForm, "Registration Form");
        mainPane.addTab("Check In Form", null, checkInForm, "Check In Form");
        mainPane.addTab("Membership Management", null, membershipManagementForm, "Membership Management");


        contentPane.add(mainPane, BorderLayout.CENTER);

        membershipForm.setLocationRelativeTo(null);
        membershipForm.setVisible(true);
    }
}
