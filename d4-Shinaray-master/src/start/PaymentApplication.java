package start;

import RoleManagement.Role;
import controllers.PaymentController;

import java.util.Scanner;

public class PaymentApplication {
    private final PaymentController paymentController;
    private final Scanner scanner;

    public PaymentApplication(PaymentController paymentController) {
        this.paymentController = paymentController;
        this.scanner = new Scanner(System.in);
    }


    public void start(Role role) {
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if(role == Role.ADMIN || role == Role.COSTUMER) {
                        createPayment();
                    }else{
                        System.out.println("Invalid option, This option work only available for ADMIN and COSTUMER.");
                    }
                    break;
                case 2:
                    if(role == Role.MANAGER || role == Role.COSTUMER) {
                        viewPayment();
                    }else {
                        System.out.println("Invalid option, This option work only available for MANAGER and COSTUMER.");
                    }
                    break;
                case 3:
                    if(role == Role.MANAGER) {
                        listAllPayments();
                    }else {
                        System.out.println("Invalid option, This option work only available for MANAGER.");
                    }
                    break;
                case 4:
                    if(role == Role.MANAGER) {
                        updatePaymentStatus();
                    } else {
                        System.out.println("Invalid option, This option work only available for MANAGER.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting the application...");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n--- Payment Application ---");
        System.out.println("1. Create Payment");
        System.out.println("2. View Payment by ID");
        System.out.println("3. List All Payments");
        System.out.println("4. Update Payment Status");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    private void createPayment() {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        System.out.print("Enter payment amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        String result = paymentController.createPayment(userId, amount);
        System.out.println(result);
    }


    private void viewPayment() {
        System.out.print("Enter payment ID: ");
        int paymentId = scanner.nextInt();
        scanner.nextLine();

        String result = paymentController.getPaymentById(paymentId);
        System.out.println(result);
    }


    private void listAllPayments() {
        String result = paymentController.getAllPayments();
        System.out.println(result);
    }

    private void updatePaymentStatus() {
        System.out.print("Enter payment ID: ");
        int paymentId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new status: ");
        String status = scanner.nextLine();

        String result = paymentController.updatePaymentStatus(paymentId, status);
        System.out.println(result);
    }
}
