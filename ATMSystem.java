import java.util.*;

public class ATMSystem {

    public static class User {
        private final String userId;
        private String pin;
        private double balance;
        private ArrayList<String> transactionHistory;

        public User(String userId, String name, String dob, String phoneNumber, String pin) {
            this.userId = userId;
            this.pin = pin;
            this.balance = 0.0;
            this.transactionHistory = new ArrayList<>();
        }
        public User(String userId, String pin) {
            this.userId = userId;
            this.pin = pin;
            this.balance = 0.0;
            this.transactionHistory = new ArrayList<>();
        }

        public String getUserId() { return userId; }
        public String getPin() { return pin; }
        public double getBalance() { return balance; }
        public ArrayList<String> getTransactionHistory() { return transactionHistory; }


        public void deposit(double amount) {
            balance += amount;
            transactionHistory.add("Deposited: Rs." + amount);
        }

        public boolean withdraw(double amount) {
            if (amount > balance) return false;
            balance -= amount;
            transactionHistory.add("Withdrew: Rs." + amount);
            return true;
        }

        public boolean transfer(User receiver, double amount) {
            if (amount > balance) return false;
            balance -= amount;
            receiver.balance += amount;
            transactionHistory.add("Transferred Rs." + amount + " to " + receiver.userId);
            receiver.transactionHistory.add("Received Rs." + amount + " from " + this.userId);
            return true;
        }


    }
    private static ArrayList<User> users = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Sample users
        users.add(new User("user1", "1234"));
        users.add(new User("user2", "5678"));
        users.add(new User("user3", "9111"));
        users.add(new User("user4", "4566"));
        users.add(new User("user5", "5458"));

        System.out.println("Welcome to the ATM!");

        User currentUser = login();

        if (currentUser != null) {
            showMenu(currentUser);
        } else {
            System.out.println("Login failed.");
        }
    }

    private static User login() {
        System.out.print("Enter User ID: ");
        String inputId = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String inputPin = scanner.nextLine();

        for (User user : users) {
            if (user.getUserId().equals(inputId) && user.getPin().equals(inputPin)) {
                System.out.println("Login successful!");
                return user;
            }
        }

        return null;
    }

    private static void showMenu(User user) {
        int choice;

        do {
            System.out.println("\n===== ATM Menu =====");
            System.out.println("1. Transaction History");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    for (String record : user.getTransactionHistory()) {
                        System.out.println(record);

                    }
                    System.out.println("Balance : Rs."+user.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = Double.parseDouble(scanner.nextLine());
                    user.deposit(depositAmount);
                    System.out.println("Deposit successful.");
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = Double.parseDouble(scanner.nextLine());
                    if (user.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Insufficient balance.");
                    }
                    break;
                case 4:
                    System.out.print("Enter recipient user ID: ");
                    String recipientId = scanner.nextLine();
                    User receiver = findUserById(recipientId);
                    if (receiver != null && receiver != user) {
                        System.out.print("Enter amount to transfer: ");
                        double transferAmount = Double.parseDouble(scanner.nextLine());
                        if (user.transfer(receiver, transferAmount)) {
                            System.out.println("Transfer successful.");
                        } else {
                            System.out.println("Insufficient balance.");
                        }
                    } else {
                        System.out.println("Invalid recipient.");
                    }
                    break;
                    case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }

        } while (choice != 5);
    }

    private static User findUserById(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
}