import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Member {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public static HashMap<Integer, HashMap<String, String>> getAllMembers() {
        HashMap<Integer, HashMap<String, String>> members = new HashMap<Integer, HashMap<String, String>>();
        HashMap<String, String> temp;
        try (BufferedReader br = new BufferedReader(new FileReader("customerlist.csv"))) {
            String line;
            temp = new HashMap<String, String>();
            String[] lineRecords;
            while((line = br.readLine()) != null) {
                lineRecords = line.split(",");
                // temp.put("")
            }
            // members.put()
        } catch (IOException e) {
            e.printStackTrace();
        }

        return members;
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
        generateMissingIds();
    }

    private int id;
    private String name;
    private LocalDate dob;
    private String gender;
    private String address;
    private String telephoneNumber;
    private int age;
}