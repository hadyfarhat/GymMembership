import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Membership {
    public LocalDate getStartDate() {
        return startDate;
    }

    public String getFormattedStartDate() {
        return startDate.format(dateFormatter);
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getFormattedEndDate() {
        return endDate.format(dateFormatter);
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void calculateEndDate(int duration) {
        LocalDate startDate = getStartDate();
        LocalDate endDate = startDate.plusMonths(duration);
        this.setEndDate(endDate);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void calculatePrice(int duration) {
        int price = 0;
        if (type.equals("Individual")) {
            price = 36 * duration;
        } else if (type.equals("Family")) {
            price = 60 * duration;
        }
        setPrice(price);
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    /**
     * Appends membership to csv file
     *
     * @return id the newly generated id for the new member
     * @throws IOException
     */
    public int addToFile() throws IOException {
        int id = generateUniqueId();

        FileWriter fw = new FileWriter("customerlist.csv", true);
        fw.append(Integer.toString(id));
        fw.append(',');
        fw.append(member.getFirstName());
        fw.append(',');
        fw.append(member.getLastName());
        fw.append(',');
        fw.append(member.getFormattedDob());
        fw.append(',');
        fw.append(member.getGender());
        fw.append(',');
        fw.append(member.getAddress());
        fw.append(',');
        fw.append(member.getTelephoneNumber());
        fw.append(',');
        fw.append(this.getType());
        fw.append(',');
        fw.append(this.getFormattedStartDate());
        fw.append(',');
        fw.append(this.getFormattedEndDate());
        fw.append(',');
        fw.append(Integer.toString(this.getPrice()));
        fw.append('\n');

        fw.flush();
        fw.close();

        return id;
    }

    /**
     * Checks if the end date of the membership is >= today's date
     * If it's not, then the membership is not valid.
     *
     * @param id id of the membership to be checked
     * @return whether the membership is valid or not
     */
    public static boolean membershipIsValid(int id) {
        Membership membership = getMembershipFromDatabase(id);
        LocalDate endDate = membership.getEndDate();
        // if end date is equal to null then either the id wasn't found or the start date wasn't set
        if (endDate != null) {
            LocalDate todaysDate = LocalDate.now();
            // if today's date is before end date
            if (todaysDate.compareTo(endDate) < 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * Gets the membership details based on its id from the customer list csv file.
     * It loops through all the records until it finds the provided id. It then fills in the membership details
     * based on the found record and returns it.
     *
     * @param id id of the membership to be checked
     * @return membership
     */
    public static Membership getMembershipFromDatabase(int id) {
        HashMap<Integer, HashMap<String, String>> allMembers = getAllMembers();
        Membership membership = new Membership();

        for (Map.Entry<Integer, HashMap<String, String>> memberRow : allMembers.entrySet()) {
            int rowId = memberRow.getKey();
            if (rowId == id) {
                HashMap<String, String> memberData = memberRow.getValue();
                    Member member = new Member();
                    member.setFirstName(memberData.get("firstName"));
                    member.setLastName(memberData.get("lastName"));
                    if (!memberData.get("dob").equals("")) {
                        LocalDate dob = LocalDate.parse(memberData.get("dob"), Member.dobFormatter);
                        member.setDob(dob);
                    }
                    member.setGender(memberData.get("gender"));
                    member.setAddress(memberData.get("address"));
                    member.setTelephoneNumber(memberData.get("telephoneNumber"));

                    membership.setMember(member);

                    membership.setType(memberData.get("type"));
                    if (!memberData.get("startDate").equals("") && !memberData.get("endDate").equals("")) {
                        LocalDate startDate = LocalDate.parse(memberData.get("startDate"), Membership.dateFormatter);
                        membership.setStartDate(startDate);
                        LocalDate endDate = LocalDate.parse(memberData.get("endDate"), Membership.dateFormatter);
                        membership.setEndDate(endDate);
                    }
                    if (!memberData.get("price").equals("")) {
                        membership.setPrice(Integer.parseInt(memberData.get("price")));
                    }
            }
        }

        return membership;
    }

    /**
     * Generate a random number between 10,000 and 100,000.
     * It then loops through the members csv file to check if this number (id) is already taken.
     * The method will keep on looping until the id is unique
     * @return - unique generated id
     */
    public static int generateUniqueId() {
        while (true) {
            // 10,000 <= randomId <= 100,000
            int randomId = (int) (Math.random() * ((100000 - 10000) + 1)) + 10000;
            boolean idFound = false;

            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("customerlist.csv"), StandardCharsets.UTF_8))) {
                String line;
                String[] lineRecords;
                while((line = br.readLine()) != null) {
                    lineRecords = line.split(",");
                    try {
                        if (!lineRecords[0].trim().isEmpty()) {
                            int lineId = Integer.parseInt(lineRecords[0]);
                            if (lineId == randomId) {
                                idFound = true;
                                break;
                            }
                        }
                    } catch (NumberFormatException e) { };
                }

                if (!idFound) return randomId;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Since the id field is mandatory and important, every member should have one.
     * This method will loop through each member, and if any member was found
     * without an id, the function will generate an id and assign it to that member.
     * This function will only be called once and never be used again.
     */
    public static void generateMissingIds() {
        // get all data from csv
        List<List<String>> members = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("customerlist.csv"))) {
            String line;
            List<String> memberData;
            while((line = br.readLine()) != null) {
                memberData = Arrays.asList(line.split(","));
                members.add(memberData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // write data to file
        try (PrintWriter writer = new PrintWriter(new File("customerlist.csv"))) {
            for (List<String> member : members) {
                // if id is empty
                if (member.get(0).isEmpty()) {
                    String uniqueId = Integer.toString(generateUniqueId());
                    member.set(0, uniqueId);
                }
                writer.write(String.join(",", member));
                writer.write("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the data of the file customerlist.csv and returns it in the form of a HashMap.
     * The key of the hashmap is the id of the member. Each key has its own hashmap which stores all
     * the data of the member except for id. For ex:
     * {
     *   1: {"firstName": "Cristiano", "lastName": "Ronaldo", ....},
     *   2: {"firstName": "Robert", "lastName": "Lewandowski", ....}
     * }
     *
     * @return
     */
    public static HashMap<Integer, HashMap<String, String>> getAllMembers() {
        HashMap<Integer, HashMap<String, String>> members = new HashMap<>();
        HashMap<String, String> temp;
        try (BufferedReader br = new BufferedReader(new FileReader("customerlist.csv"))) {
            String line;
            List<String> memberData = new ArrayList<>();

            HashMap<Integer, String> lineFormat = new HashMap<>();
            lineFormat.put(1, "lastName");
            lineFormat.put(2, "firstName");
            lineFormat.put(3, "dob");
            lineFormat.put(4, "gender");
            lineFormat.put(5, "address");
            lineFormat.put(6, "telephoneNumber");
            lineFormat.put(7, "type");
            lineFormat.put(8, "startDate");
            lineFormat.put(9, "endDate");
            lineFormat.put(10, "price");

            while((line = br.readLine()) != null) {
                temp = new HashMap<>();
                memberData = Arrays.asList(line.split(","));
                int count = 1;
                while (count < 11) {
                    try {
                        temp.put(lineFormat.get(count), memberData.get(count));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        temp.put(lineFormat.get(count), "");
                    };
                    count++;
                }
                try {
                    members.put(Integer.parseInt(memberData.get(0)), temp);
                } catch (NumberFormatException e) {};
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return members;
    }


    /**
     * Updates the current customerlist.csv file by overwriting it with the data provided as parameter.
     * This function is mainly used when the user is editing data through {@link MembershipTableModel}.
     * Each row of the data list will have the following columns:
     * "ID", "First Name", "Last Name", "Date of Birth", "Gender", "Address", "Telephone Number",
     * "Type", "Start Date", "End Date", "Price"
     *
     * @param data data to be overwritten into the customerlist.csv file
     */
    public static void updateData(List<List<String>> data) throws IOException {
        FileWriter fw = new FileWriter("customerlist.csv", false);
        for (int row = 0; row < data.size(); ++row) {
            // write all columns except for the last one
            for (int col = 0; col < data.get(row).size() - 1; ++col) {
                fw.append(data.get(row).get(col));
                fw.append(",");
            }
            // write last column
            fw.append(data.get(row).get(data.get(row).size() - 1));
            fw.append('\n');
        }

        fw.flush();
        fw.close();
    }

    /**
     * Loops over the ids of the customer list to check if the passed id parameter is equal to one of them.
     * @param id id to be checked against the ids in the customer list
     * @return whether the id exists or not
     */
    public static boolean idExists(int id) {
        boolean found = false;

        HashMap<Integer, HashMap<String, String>> allMembers = getAllMembers();
        for (Map.Entry<Integer, HashMap<String, String>> member : allMembers.entrySet()) {
            if (id == member.getKey()) {
                found = true;
                break;
            }
        }

        return found;
    }

    public static void main(String[] args) {
        generateMissingIds();
    }

    private LocalDate startDate;
    private LocalDate endDate;
    public static DateTimeFormatter dateFormatter =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String type;
    private int price;
    private Member member;
}
