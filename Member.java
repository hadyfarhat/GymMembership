import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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

    public String getFormattedDob() {
        return dob.format(dobFormatter);
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
        calculateAge();
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

    /**
     * Calculates age based on the 1st of September of the current year
     */
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


    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    public static DateTimeFormatter dobFormatter =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String gender;
    private String address;
    private String telephoneNumber;
    private int age;
}