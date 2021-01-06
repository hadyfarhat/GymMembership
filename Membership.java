import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    private LocalDate startDate;
    private LocalDate endDate;
    public static DateTimeFormatter dateFormatter =  DateTimeFormatter.ofPattern("dd/MM/yy");
    private String type;
    private int price;
}
