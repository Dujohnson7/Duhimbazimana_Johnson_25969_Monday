package Test;
import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class ExceptionWork {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nChoose an example to run:\n");
            System.out.println("1. IOException (Read a Non-Existent File)");
            System.out.println("2. FileNotFoundException (Delete a File)");
            System.out.println("3. EOFException (Read Beyond File Content)");
            System.out.println("4. SQLException (Invalid Database Connection)");
            System.out.println("5. ClassNotFoundException (Load a Missing Class)");
            System.out.println("6. ArithmeticException (Divide by Zero)");
            System.out.println("7. NullPointerException (Access a Null Object)");
            System.out.println("8. ArrayIndexOutOfBoundsException (Access Invalid Index)");
            System.out.println("9. ClassCastException (Invalid Casting)");
            System.out.println("10. IllegalArgumentException (Invalid Argument)");
            System.out.println("11. NumberFormatException (Invalid String to Number)");
            System.out.println("12. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> ioExceptionExample(scanner);
                case 2 -> fileNotFoundExceptionExample(scanner);
                case 3 -> eofExceptionExample();
                case 4 -> sqlExceptionExample(scanner);
                case 5 -> classNotFoundExceptionExample(scanner);
                case 6 -> arithmeticExceptionExample(scanner);
                case 7 -> nullPointerExceptionExample();
                case 8 -> arrayIndexOutOfBoundsExceptionExample();
                case 9 -> classCastExceptionExample();
                case 10 -> illegalArgumentExceptionExample();
                case 11 -> numberFormatExceptionExample(scanner);
                case 12 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void ioExceptionExample(Scanner scanner) {
        System.out.println("\n");
        System.out.print("Enter the file name to open: ");
        String fileName = scanner.nextLine();

        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            int data;
            while ((data = fileInputStream.read()) != -1) {
                System.out.print((char) data);
            }
        } catch (IOException e) {
            System.out.println("Error: Unable to open the file - " + e.getMessage());
        }
    }

    private static void fileNotFoundExceptionExample(Scanner scanner) {
        System.out.println("\n");
        System.out.print("Enter the file name to delete: ");
        String fileName = scanner.nextLine();

        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Error: File not found.");
        } else {
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Error: File could not be deleted.");
            }
        }
    }

    private static void eofExceptionExample() {
        System.out.println("\n");
        String fileName = "data.bin";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject("Hello");
            oos.writeObject("World");
        } catch (IOException e) {
            System.out.println("Error: Unable to write data.");
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            while (true) {
                System.out.println(ois.readObject());
            }
        } catch (EOFException e) {
            System.out.println("Reached end of file.");
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void sqlExceptionExample(Scanner scanner) {
        System.out.println("\n");
        System.out.print("Enter database URL: ");
        String dbUrl = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {
            System.out.println("Database connection successful!");
        } catch (SQLException e) {
            System.out.println("Error: Could not establish a database connection.");
        }
    }

    private static void classNotFoundExceptionExample(Scanner scanner) {
        System.out.println("\n");
        System.out.print("Enter the class name to load: ");
        String className = scanner.nextLine();

        try {
            Class<?> clazz = Class.forName(className);
            System.out.println("Class loaded: " + clazz.getName());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
    }

    private static void arithmeticExceptionExample(Scanner scanner) {
        System.out.println("\n");
        System.out.print("Enter the numerator: ");
        int numerator = scanner.nextInt();
        System.out.print("Enter the denominator: ");
        int denominator = scanner.nextInt();

        try {
            int result = numerator / denominator;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Division by zero is not allowed.");
        }
    }

    private static void nullPointerExceptionExample() {
        System.out.println("\n");
        String[] data = null;

        try {
            System.out.println("Data length: " + data.length);
        } catch (NullPointerException e) {
            System.out.println("Error: Attempt to access a null reference.");
        }
    }

    private static void arrayIndexOutOfBoundsExceptionExample() {
        System.out.println("\n");
        int[] array = {5, 10, 15, 20};

        try {
            System.out.println("Array element at index 5: " + array[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Array index out of bounds.");
        }
    }

    private static void classCastExceptionExample() {
        System.out.println("\n");
        Object obj = new String("Java");

        try {
            Integer num = (Integer) obj;
        } catch (ClassCastException e) {
            System.out.println("Error: Cannot cast String to Integer.");
        }
    }

    private static void illegalArgumentExceptionExample()
    {
        System.out.println("\n");
        try {
            Thread.sleep(-500);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid sleep time provided.");
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted.");
        }
    }

    private static void numberFormatExceptionExample(Scanner scanner) {
        System.out.println("\n");
        System.out.print("Enter a number: ");
        String input = scanner.nextLine();

        try {
            double number = Double.parseDouble(input);
            System.out.println("Parsed number: " + number);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input, not a number.");
        }
    }
}
