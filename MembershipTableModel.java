import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the Table Model for Membership Management table
 * It gets the data from customer list csv file and populates the table.
 * It enables editing of table cells.
 */
public class MembershipTableModel extends AbstractTableModel {
    private final String[] columnNames = {
            // Member details
            "ID", "First Name", "Last Name", "Date of Birth", "Gender", "Address", "Telephone Number",
            // Membership details
            "Start Date", "End Date", "Type", "Price"
    };
    List<List<String>> data = getData();

    @Override
    public int getRowCount() {
        return this.data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex).get(columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data.get(rowIndex).set(columnIndex, (String) aValue);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return data.get(0).get(columnIndex).getClass();
    }

    /**
     * Determines whether the cell at the specified row and column indices is editable or not.
     * If set to true, the user will be able to change the content of the cell.
     *
     * @param rowIndex row index of the cell being selected
     * @param columnIndex column index of the cell being selected
     * @return whether the cell can be edited or not
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    /**
     * Gets the data from the customer list csv file and appends it to the data list of the table
     *
     * @return 2D list of customer data
     */
    private List<List<String>> getData() {
        List<List<String>> data = new ArrayList<>();

        HashMap<Integer, HashMap<String, String>> allMembers = Membership.getAllMembers();
        List<String> row;
        for (Map.Entry<Integer, HashMap<String, String>> member : allMembers.entrySet()) {
            row = new ArrayList<>();
            HashMap<String, String> memberData = member.getValue();

            row.add(0, Integer.toString(member.getKey()));
            row.add(1, memberData.get("firstName"));
            row.add(2, memberData.get("lastName"));
            row.add(3, memberData.get("dob"));
            row.add(4, memberData.get("gender"));
            row.add(5, memberData.get("address"));
            row.add(6, memberData.get("telephoneNumber"));
            row.add(7, memberData.get("type"));
            row.add(8, memberData.get("startDate"));
            row.add(9, memberData.get("endDate"));
            row.add(10, memberData.get("price"));

            data.add(row);
        }

        return data;
    }

    /**
     * Used for testing purposes
     */
    private void printDate() {
        for (int row = 0; row < data.size(); ++row) {
            for (int col = 0; col < data.get(row).size(); ++col) {
                System.out.print(data.get(row).get(col) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MembershipTableModel model = new MembershipTableModel();
        System.out.println("Rows: " + model.getRowCount());
        System.out.println("Cols: " + model.getColumnCount());

    }
}
