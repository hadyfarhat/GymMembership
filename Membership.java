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
     * @throws IOException
     */
    public void addToFile() throws IOException {
        FileWriter fw = new FileWriter("customerlist.csv", true);
        fw.append(Integer.toString(generateUniqueId()));
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
        System.out.println("ID exists: " + idExists(416100));
    }

    private LocalDate startDate;
    private LocalDate endDate;
    public static DateTimeFormatter dateFormatter =  DateTimeFormatter.ofPattern("dd/MM/yy");
    private String type;
    private int price;
    private Member member;
}
