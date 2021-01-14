/**
 * Responsible for validating data on the form.
 */
public class Validator {
    /**
     * Determines whether the provided id exists in the database or not
     *
     * @param id id to be checked
     * @return id is found in the database or not
     */
    public static boolean idIsValid(int id) {
        return Membership.idExists(id);
    }

    /**
     * If the member has an individual type of membership, it checks if he/she is older than 12 yo.
     * It the member has a family type of membership, it checks if he/she is older than 18 yo.
     *
     * @param membership member's membership data
     * @return whether the above conditions are valid or not
     */
    public static boolean membershipIsValid(Membership membership) {
        if (membership.getType().equals("Individual")) {
            if (membership.getMember().getAge() < 12) {
                return false;
            }
        } else if (membership.getType().equals("Family")) {
            if (membership.getMember().getAge() < 18) {
                return false;
            }
        }

        return true;
    }

    /**
     * Used with membershipIsValid function to get the validation message for it to be displayed to the user
     *
     * @param membership member's membership
     * @return Validation message
     */
    public static String getValidationMessage(Membership membership) {
        String message = "";
        if (membership.getType().equals("Individual")) {
            if (membership.getMember().getAge() < 12) {
                message = "Individual age should be greater than or equal to 12";
            }
        } else if (membership.getType().equals("Family")) {
            if (membership.getMember().getAge() < 18) {
                message =  "Family member age should be greater than or equal to 18";
            }
        }

        return message;
    }
}
