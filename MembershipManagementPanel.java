import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Represents the Membership Management section of the form
 * It's responsible for setting up the table and handling user actions.
 * Such actions could be deleting a row from the table
 */
public class MembershipManagementPanel extends JPanel implements ActionListener, KeyListener {
    private static JTable membershipTable;
    private final JTextField search;
    private final TableRowSorter<MembershipTableModel> sorter;

    /**
     * Constructor of the class which sets the layout of the table and adds an action listener to it
     *
     * @param membershipTable table representing the membership management
     */
    public MembershipManagementPanel(JTable membershipTable) {
        setLayout(new BorderLayout());
        MembershipManagementPanel.membershipTable = membershipTable;

        MembershipTableModel model = ((MembershipTableModel) membershipTable.getModel());
        sorter = new TableRowSorter<MembershipTableModel>(model);
        membershipTable.setRowSorter(sorter);

        search = new JTextField(20);
        search.addKeyListener(this);
        add(search, BorderLayout.NORTH);

        JScrollPane tableScrollPane = new JScrollPane(membershipTable);
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

    /**
     * Gets called once a Uni-Code key gets pressed in the search field. It will then search through the table
     * using the search string entered
     *
     * @param e key event which is used to get the character of the key being pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        String searchString = this.search.getText() + e.getKeyChar();
        if (searchString.trim().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter(searchString));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
