import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.HashMap;

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
     * It then loop through the members csv file to check if this number (id) is already taken.
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
                // temp.put()
                System.out.println("Id: " + lineRecords[0]);
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
     * without an id, the function will generate an id and assign it to that member
     */
    public static void generateMissingIds() {

    }

    public static void main(String[] args) {
        System.out.println(generateUniqueId());
    }

    private int id;
    private String name;
    private LocalDate dob;
    private String gender;
    private String address;
    private String telephoneNumber;
    private int age;
}