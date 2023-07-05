import java.util.*;

public class ATM {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Bank bank = new Bank();

        // Два юзера с айди и пин-кодом
        AccountHolder user1 = new AccountHolder("Aizhan", 211110, 1234);
        AccountHolder user2 = new AccountHolder("Aidana", 567880, 9876);

        // Добавили их в банк
        bank.addAccountHolder(user1);
        bank.addAccountHolder(user2);

        // Процесс входа в аккаунт
        AccountHolder loggedInAccountHolder = login(bank);
        if (loggedInAccountHolder != null) {
            performTransactions(loggedInAccountHolder, bank);
        } else {
            System.out.println("Login failed. Invalid ID or PIN.");
        }
    }

    private static AccountHolder login(Bank bank) {
        System.out.print("Enter your 6-digit account ID: ");
        int id = scanner.nextInt();

        System.out.print("Enter your 4-digit PIN: ");
        int pin = scanner.nextInt();

        AccountHolder accountHolder = bank.findAccountHolder(id);
        if (accountHolder != null && pin == accountHolder.getPin()) {
            System.out.println("Welcome, " + accountHolder.getName() + "!");
            return accountHolder;
        } else {
            return null;
        }
    }

    private static void performTransactions(AccountHolder accountHolder, Bank bank) {
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add to deposit");
            System.out.println("2. Withdraw money");
            System.out.println("3. See transaction history");
            System.out.println("4. Transfer money to another user");
            System.out.println("5. Check balance");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice. Please try again.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    performDeposit(accountHolder);
                    break;
                case 2:
                    performWithdrawal(accountHolder);
                    break;
                case 3:
                    accountHolder.printTransactionHistory();
                    break;
                case 4:
                    performTransfer(accountHolder, bank);
                    break;
                case 5:
                    checkBalance(accountHolder, bank);
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void performDeposit(AccountHolder accountHolder) {
        System.out.print("Enter the amount to deposit: ");
        int amount = scanner.nextInt();

        if (accountHolder.deposit(amount)) {
            System.out.println("Successfully added " + amount + " KZT to the deposit.");
        }
    }

    private static void performWithdrawal(AccountHolder accountHolder) {
        System.out.print("Enter the amount to withdraw: ");
        int amount = scanner.nextInt();

        if (accountHolder.withdraw(amount)) {
            System.out.println("Successfully withdrew " + amount + " KZT.");
        }
    }

    private static void performTransfer(AccountHolder accountHolder, Bank bank) {
        System.out.print("Enter the recipient's account number: ");
        int recipientId = scanner.nextInt();
        scanner.nextLine();

        AccountHolder recipient = bank.findAccountHolder(recipientId);
        if (recipient == null) {
            System.out.println("Recipient's account not found.");
            return;
        }

        System.out.print("Enter the amount to transfer: ");
        int amount = scanner.nextInt();
        scanner.nextLine();

        if (accountHolder.transfer(recipient, amount)) {
            System.out.println("Successfully transferred " + amount + " KZT to Account Number " + recipient.getId());
        }
    }

    private static void checkBalance(AccountHolder loggedInAccountHolder, Bank bank) {
        System.out.println("Choose whose balance to check:");
        System.out.println("1. Your own account");
        System.out.println("2. Recipient's account");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                checkOwnBalance(loggedInAccountHolder);
                break;
            case 2:
                checkRecipientBalance(bank);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void checkOwnBalance(AccountHolder accountHolder) {
        System.out.println("Your current balance is: " + accountHolder.getBalance() + "KZT.");
    }

    private static void checkRecipientBalance(Bank bank) {
        System.out.print("Enter the recipient's 6-digit account ID: ");
        int recipientId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the recipient's 4-digit PIN: ");
        int recipientPin = scanner.nextInt();
        scanner.nextLine();

        AccountHolder recipient = bank.findAccountHolder(recipientId);
        if (recipient != null && recipient.getPin() == recipientPin) {
            System.out.println("Recipient's current balance is: $" + recipient.getBalance());
        } else {
            System.out.println("Invalid recipient ID or PIN.");
        }
    }

}
