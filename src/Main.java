import Dao.UserDao;
import Dao.impl.ProductDaoImpl;
import Dao.impl.UserDaoImpl;
import Service.ProductService;
import Service.UserService;
import Service.impl.ProductServiceImpl;
import Service.impl.UserServiceimpl;
import config.Validation;
import enam.Role;
import models.User;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        ProductDaoImpl productDao = new ProductDaoImpl();
        ProductService productService = new ProductServiceImpl(productDao);

        UserDao userDao = new UserDaoImpl();
        UserService userService = new UserServiceimpl(userDao);

        Scanner scanForStr = new Scanner(System.in);
        outerLoop:
        while (true) {
            System.out.println("""
                    Press to 1: sing-up
                    """);


            switch (checkValidCommand()) {
                case 1 ->{
                    System.out.println("Register:");
                    String validEmail = isValidData(" email: ", Validation.emailPattern());
                    System.out.println(validEmail);

                    String validPassword = isValidData(" password: ", Validation.passwordPattern());
                    System.out.println(validPassword);
                    System.out.print("Enter name: ");

                    String result = userService.sainUp(new User(validEmail, validPassword, scanForStr.nextLine(), Role.CLIENT));
                    System.out.println(result);

                }
                case 2 -> {
                    System.out.println("All users: ");
                    for (User user : userService.findAll()) {
                        System.out.println(user);
                    }
                }
                default -> {
                    System.out.println("invalid command. Enter valid command: ");
                }
            }
        }

    }

    public static String isValidData(String label, String pattern) {
        System.out.printf("Enter the %s", label);
        String data;

        do {
            data = new Scanner(System.in).nextLine();
            if (!data.matches(pattern)) {
                System.out.printf("invalid %s. Enter valid %s: ", label, label);
            }

        } while (!data.matches(pattern));
        return data;
    }

    public static int checkValidCommand() {
        int command = 0;
        boolean invalidCommand;
        System.out.print("write command: ");
        do {
            try {
                command = new Scanner(System.in).nextInt();
                invalidCommand = false;
            } catch (InputMismatchException e) {
                invalidCommand = true;
                System.out.print("invalid command. Enter valid command: ");
            }
        } while (invalidCommand);
        return command;
    }
}