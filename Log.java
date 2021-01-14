import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Responsible for logging members and visitors into log.csv
 */
public class Log {
    /**
     * Logs gym members (Individual and Family)
     * Format: id, date time
     *
     * @param id id of membership
     */
    public static void log(int id) throws IOException {
        String todaysDate = getTodaysDateAndTime();

        FileWriter fw = new FileWriter("logs.csv", true);
        fw.append(Integer.toString(id));
        fw.append(',');
        fw.append(todaysDate);
        fw.append('\n');

        fw.flush();
        fw.close();
    }

    /**
     * Logs visitors with an id of VISITOR
     */
    public static void log() throws IOException {
        String todaysDate = getTodaysDateAndTime();

        FileWriter fw = new FileWriter("logs.csv", true);
        fw.append("VISITOR");
        fw.append(',');
        fw.append(todaysDate);
        fw.append('\n');

        fw.flush();
        fw.close();
    }

    /**
     * Get's todays date and time in string format: dd/MM/yyyy HH:mm:ss
     *
     * @return string format of todays date and time
     */
    private static String getTodaysDateAndTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
