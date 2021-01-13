import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents the Membership Management section of the form
 * It's responsible for setting up the table and handling user actions.
 * Such actions could be deleting a row from the table
 */
public class MembershipManagementPanel extends JPanel implements ActionListener {
    private static JTable membershipTable;

    /**
     * Constructor of the class which sets the layout of the table and adds an action listener to it
     *
     * @param membershipTable table representing the membership management
     */
    public MembershipManagementPanel(JTable membershipTable) {
        MembershipManagementPanel.membershipTable = membershipTable;
        JScrollPane tableScrollPane = new JScrollPane(membershipTable);
        setLayout(new BorderLayout());
        add(tableScrollPane, BorderLayout.CENTER);
        JButton button = new JButton("Delete Row");
        button.addActionListener(this);
        add(button, BorderLayout.SOUTH);
    }

    /**
     * Fires when the button under the membership management table is clicked
     *
     * @param e action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int column = 0;
            int row = membershipTable.getSelectedRow();
            String value = membershipTable.getModel().getValueAt(row, column).toString();
            ((MembershipTableModel) membershipTable.getModel()).removeRow(row);
        } catch (ArrayIndexOutOfBoundsException ae) {
            System.out.println("Please select a row");
        }
    }

    /**
     * Refreshes the data of the table.
     * This method is usually called after inserting a new row into the database (csv file)
     */
    public static void refreshTableData() {
        ((MembershipTableModel) membershipTable.getModel()).refreshData();
    }
}
