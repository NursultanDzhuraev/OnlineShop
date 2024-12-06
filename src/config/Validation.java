package config;

public class Validation {
    public static boolean checkEmail(String email) {
        return email.matches(emailPattern());
    }

    public static String emailPattern() {
        return "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    }

    public static boolean checkPassword(String password) {
        return password.matches(passwordPattern());
    }

    public static String passwordPattern() {
        return "^(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&+=!]).{8,}$";
    }
}

