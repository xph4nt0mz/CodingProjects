/**
 * A class that verifies a strong password. Not asscoiated to class work.
 * @author Ahman Evans
 * @version 1.0
 */
import java.util.Scanner;
public class PasswordChecker {
    static Scanner scanner = new Scanner (System.in);
    public static void main(String[] args) {
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (!Characters(password)) {
            System.out.println("Password does not meet the criteria. You must enter at least 8 characters.");
            display();
            password = scanner.nextLine();
        }
        if (!SpecialCharacters(password)) {
            System.out.println("Password does not meet criteria. You must enter at least 1 speical character.");
            display();
            password = scanner.nextLine();
        }
        if (!Numbers(password)) {
            System.out.println("Password does not meeet criteria. You must enter at least 1 number.");
            display();
            password = scanner.nextLine();
        }
        if (!lowerCase(password)) {
            System.out.println("Password does not meeet criteria. You must enter at least 1 lower case letter.");
            display();
            password = scanner.nextLine();
        }
        else {
            System.out.println("You've created a strong password.");
        }
    }

    /**
     * @param password
     * @return
     */
    public static boolean Characters(String password) {
        int corrLength = 8;
        return password.length() >= corrLength;
    }

    /**
     * @param password
     * @return
     */
    public static boolean SpecialCharacters(String password) {
        char[] specialChar = {'!', '@', '#', '$', '%', '^', '&', '*', '(', ')'};

        for  (int j = 0; j < specialChar.length; j++) {
            for (int i = 0; i < password.length(); i++) {
                if (password.charAt(i) == specialChar[j]) {
                    return true;
                    }
                }
            }
            return false;
        }
    
        /**
         * @param password
         * @return
         */
    public static boolean Numbers(String password) {
        char[] numChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        for (int j = 0; j < numChar.length; j++) {
            for (int i = 0; i < password.length(); i++) {
                if (password.charAt(i) == numChar[j]) {
                    return true;
                    }
                }
            }
            return false;
        }

        /**
         * @param password
         * @return
         */
    public static boolean lowerCase(String password) {
        char[] lowerCase = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l','m', 
                            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        
        for (int j = 0; j < lowerCase.length; j++) {
            for (int i = 0; i < password.length(); i++) {
                if (password.charAt(i) == lowerCase[j]) {
                    return true;
                    }
                }
            }
        return false;
        }
    
    /**
     * @param password
     * @return
     */
    public static boolean upperCase(String password) {
        char[] upperCase = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        for (int j = 0; j < upperCase.length; j++) {
            for (int i = 0; i < password.length(); i++) {
                if (password.charAt(i) == upperCase[j]) {
                    return true;
                    }
                }
            }
            return false;
        }
    
    public static void display() {
        System.out.print("Enter password: ");
        }

    
    }