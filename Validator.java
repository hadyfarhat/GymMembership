public class Validator {
    public static boolean idIsValid(int id) {
        return Membership.idExists(id);
    }
}
