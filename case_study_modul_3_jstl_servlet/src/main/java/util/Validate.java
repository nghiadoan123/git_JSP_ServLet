package util;

public class Validate {

    public static boolean validatePhonenumber(String string) {
        String regex ="(^09[01]\\d{7}$)||(\\(\\^84\\)\\+9[01]\\d{7}$)";
        return string.matches(regex);
    }


    public static boolean validatePersonalId(String string) {
        String regex = "(^\\d{9}$)||(^\\d{12}$)";
        return string.matches(regex);
    }

    public static boolean validateEmail(String string) {
        String regex = "[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)";
        return string.matches(regex);
    }

    public static boolean validateIdCustomer(String string) {
        String regex = "^KH-\\d{4}$";
        return string.matches(regex);
    }


    public static boolean validateIdFacility(String string) {
        String regex = "^DV-\\d{4}$";
        return string.matches(regex);
    }
}
