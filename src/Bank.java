import java.util.*;
public class Bank {
    private final List<AccountHolder> accountHolders;

    public Bank() {
        this.accountHolders = new ArrayList<>();
    }

    public void addAccountHolder(AccountHolder accountHolder) {
        accountHolders.add(accountHolder);
    }

    public AccountHolder findAccountHolder(int id) {
        for (AccountHolder accountHolder : accountHolders) {
            if (accountHolder.getId() == id) {
                return accountHolder;
            }
        }
        return null;
    }
}
