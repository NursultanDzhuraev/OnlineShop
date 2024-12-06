import Dao.UserDao;
import Dao.impl.ProductDaoImpl;
import Dao.impl.UserDaoImpl;
import Service.ProductService;
import Service.UserService;
import Service.impl.ProductServiceImpl;
import Service.impl.UserServiceimpl;
import config.Validation;
import enam.Role;
import enam.Size;
import exceptions.NotFoundException;
import models.Product;
import models.User;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    static Scanner scanForStr = new Scanner(System.in);
    static Scanner scanForNumber = new Scanner(System.in);
    static ProductDaoImpl productDao = new ProductDaoImpl();
    static ProductService productService = new ProductServiceImpl(productDao);

    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        UserService userService = new UserServiceimpl(userDao);

        userService.savedDefaultAdmin();
        Size[] sizes = Size.values();
        System.out.println(Arrays.toString(sizes));
        User current = null;
        outerLoop:
        while (true) {
            System.out.println("""
                    Press to 1: sing-up
                    Press to 2: sing-in
                    Press to 3: users
                    
                    """);


            switch (checkValidCommand()) {
                case 1 -> {
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
                    System.out.println("sing-in");
                    String validEmail = isValidData(" email: ", Validation.emailPattern());
                    String validPassword = isValidData(" password: ", Validation.passwordPattern());

                    try {
                        current = userService.singIn(validEmail, validPassword);
                        System.out.println(current);
                    } catch (NotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    if (current.getRole().equals(Role.CLIENT)) {
                        System.out.println("""
                                
                                """);
                        switch (checkValidCommand()) {


                        }


                    } else if (current.getRole().equals(Role.ADMIN)) {
                        boolean loop = true;
                        while (loop) {
                            System.out.println("""
                                  
                                    1: addProduct
                                    2: getAllProduct
                                    3: updateProduct
                                    4: deleteProduct
                                    5: Exit
                                    """);
                            switch (checkValidCommand()) {
                                case 1 -> {
                                    Product product = addProductScan(new Product());
                                    productService.addProduct(product);
                                }
                                case 2 -> {
                                    Product[] allProduct = productDao.findAllProduct();
                                    System.out.println(Arrays.toString(allProduct));
                                }
                                case 3 -> {
                                    System.out.println("Enter product id: ");
                                    int productId = scanForNumber.nextInt();
                                    productService.updateProduct(productId, addProductScan(new Product()));
                                }
                                case 4 ->{
                                    System.out.println("Enter product id: ");
                                    int productId = scanForNumber.nextInt();
                                    productService.deleteProduct(productId);
                                }
                                case 5->{
                                    loop = false;
                                }default -> {
                                    System.out.println("default number ");
                                }


                            }
                        }
                        } else{
                            System.out.println("There is no such role");
                    }
                }
                case 3 -> {
                    System.out.println("All users: ");
                    for (User user : userService.findAll()) {
                        System.out.println(user);
                    }
                }
                case 4 -> {

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

    public static Product addProductScan(Product product) {
        System.out.print("Enter product name: ");
        product.setName(scanForStr.nextLine());
        System.out.print("Enter product price: ");
        Scanner scanner = new Scanner(System.in);
        product.setPrice(scanner.nextBigDecimal());
        System.out.print("Enter product color: ");
        product.setColor(scanForStr.nextLine());
        System.out.print("Enter product imegeUrl: ");
        product.setImegeUrl(scanForStr.nextLine());

        System.out.print("Enter product size: ");
        String sizeFromScan = scanForStr.nextLine();

        Size[] sizes = Size.values();
        for (Size size : sizes) {
//            if (sizeFromScan.equals(size.name())) {
//                product.setSizes(new Size[]{Size.valueOf(sizeFromScan.toUpperCase())});
//            }
            if (sizeFromScan.toUpperCase().equalsIgnoreCase(size.toString())){
                product.setSizes(new Size[]{size});
            }
        }

        return product;

    }
}