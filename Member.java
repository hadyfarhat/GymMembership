import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.*;

public class Member {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public int getAge() {
        return age;
    }

    private void calculateAge() {
        LocalDate today = LocalDate.now();
        LocalDate september1stOfCurrentYear = LocalDate.of(today.getYear(), Month.SEPTEMBER, 1);
        // if today is before september the 1st of the current year
        if (today.compareTo(september1stOfCurrentYear) < 0) {
            LocalDate september1stOfLastYear = LocalDate.of(today.getYear() - 1, Month.SEPTEMBER, 1);
            Period interval = Period.between(this.getDob(), september1stOfLastYear);
            this.age = interval.getYears();
        } else {
            Period interval = Period.between(this.getDob(), september1stOfCurrentYear);
            this.age = interval.getYears();
        }
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

            while((line = br.readLine()) != null) {
                temp = new HashMap<>();
                memberData = Arrays.asList(line.split(","));
                int count = 1;
                while (count < 7) {
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


    public void addToFile() throws IOException {
        FileWriter fw = new FileWriter("customerlist.csv", true);
        fw.append(Integer.toString(generateUniqueId()));
        fw.append(',');
        fw.append(this.getFirstName());
        fw.append(',');
        fw.append("");
        fw.append(',');
        fw.append(this.getDob().toString());
        fw.append(',');
        fw.append(this.getGender());
        fw.append(',');
        fw.append(this.getAddress());
        fw.append(',');
        fw.append(this.getTelephoneNumber());
        fw.append('\n');

        fw.flush();
        fw.close();
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

    public static void main(String[] args) {
        // HashMap<Integer, HashMap<String, String>> members = getAllMembers();
        // for (Map.Entry<Integer, HashMap<String, String>> member : members.entrySet()) {
        //     System.out.println("Id: " + member.getKey());
        //     for (Map.Entry<String, String> memberData : member.getValue().entrySet()) {
        //         System.out.println(memberData.getKey() + " " + memberData.getValue());
        //     }
        // }
        HashMap<Integer, HashMap<String, String>> members = getAllMembers();
        System.out.println(members.size());
        for (Map.Entry<Integer, HashMap<String, String>> memberData : members.entrySet()) {
            for (Map.Entry<String, String> data : memberData.getValue().entrySet()) {
                System.out.println(data.getKey() + " " + data.getValue());
            }
            System.out.println();
        }
    }

    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String gender;
    private String address;
    private String telephoneNumber;
    private int age;
}