import Dao.UserDao;
import Dao.impl.ProductDaoImpl;
import Dao.impl.UserDaoImpl;
import Service.ProductService;
import Service.UserService;
import Service.impl.ProductServiceImpl;
import Service.impl.UserServiceimpl;
import config.Validation;
import enam.Category;
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
                        boolean loop = true;
                        while (loop) {
                            System.out.println("""
                                    1: getAllProduct
                                    2: getProductById
                                    3: getProductByCategory
                                    4: addFavoriteProductId
                                    5: allFavoriteProductId
                                    6: updateFavoriteProductId
                                    7: Exit
                                   
                                    """);
                            switch (checkValidCommand()) {
                                case 1 -> {
                                    Product[] allProduct = productDao.findAllProduct();
                                    System.out.println(Arrays.toString(allProduct));
                                }
                                case 2 -> {
                                    System.out.println("Enter product id: ");
                                    int productId = scanForNumber.nextInt();
                                    Product productByid = productService.getProductByid(productId);
                                    System.out.println(productByid);
                                }
                                case 3->{
                                    System.out.println("Enter product category: ");
                                    String category = new Scanner(System.in).nextLine().toUpperCase();
                                    Product productByCategory = productService.getProductByCategory(category);
                                    System.out.println(productByCategory);
                                }
                                case 4->{

                                }
                                case 5->{

                                }
                                case 7->{
                                    loop = false;
                                }

                            }
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
                                case 4 -> {
                                    System.out.println("Enter product id: ");
                                    int productId = scanForNumber.nextInt();
                                    productService.deleteProduct(productId);
                                }
                                case 5 -> {
                                    loop = false;
                                }
                                default -> {
                                    System.out.println("default number ");
                                }


                            }
                        }
                    } else {
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
        System.out.print("Enter product size (XXS,XS,S,M,XL,XXL): ");
        String sizeFromScan = scanForStr.nextLine();

        Size[] sizes = Size.values();
        for (Size size : sizes) {
            if (sizeFromScan.toUpperCase().equalsIgnoreCase(size.toString())) {
                product.setSizes(new Size[]{size});
            }
        }
        System.out.print("Enter product category( MALE,FEMALE,CHILDREN): ");
        String categoryFromScan = scanForStr.nextLine();
        Category[] categories = Category.values();
        for (Category category : categories) {
            if (categoryFromScan.toUpperCase().equalsIgnoreCase(category.toString())){
                product.setCategories(new Category[]{category});
            }
        }

        return product;

    }
}