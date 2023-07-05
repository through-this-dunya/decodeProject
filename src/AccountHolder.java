public class AccountHolder {
    private final String name;
    private final int id;
    private final int pin;
    private final Account account;
    private final Transactions transactions;

    public AccountHolder(String name, int id, int pin) {
        this.name = name;
        this.id = id;
        this.account = new Account(id, 0);
        this.transactions = new Transactions();
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public boolean deposit(int amount) {
        if (amount > 0) {
            account.deposit(amount);
            transactions.addTransaction("Deposit: +" + amount + " KZT.");
            return true;
        } else {
            System.out.println("Invalid deposit amount.");
            return false;
        }
    }

    public boolean withdraw(int amount) {
        if (account.getBalance() >= amount) {
            account.withdraw(amount);
            transactions.addTransaction("Withdrawal: -" + amount + " KZT.");
            return true;
        } else {
            System.out.println("Insufficient balance for withdrawal.");
            return false;
        }
    }

    public boolean transfer(AccountHolder recipient, int amount) {
        if (account.getBalance() >= amount) {
            account.withdraw(amount);
            recipient.getAccount().deposit(amount);
            transactions.addTransaction("Transfer: -" + amount + " KZT to Account Number " + recipient.getId());
            return true;
        } else {
            System.out.println("Insufficient balance for transfer.");
            return false;
        }
    }

    public void printTransactionHistory() {
        transactions.printTransactionHistory();
    }

    public int getPin() {
        return pin;
    }

    public int getBalance() {
        return account.getBalance();
    }
}
