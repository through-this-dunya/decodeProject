import java.util.*;
public class Transactions {
    private List<String> transactionHistory;

    public Transactions() {
        this.transactionHistory = new ArrayList<>();
    }

    public void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}
