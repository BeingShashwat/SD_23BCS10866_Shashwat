class AccountDetails {
    private String username;
    private double balance;

    public AccountDetails(String username, double balance) {
        this.username = username;
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    public String getDetails() {
        return "username: " + username + "     ||      balance: " + balance;
    }

    public AccountDetails withUpdatedBalance(double newBalance) {
        return new AccountDetails(username, newBalance);
    }
}

class TransactionService {
    public AccountDetails deposit(AccountDetails account, double amount) {
        validateAmount(amount);
        return account.withUpdatedBalance(account.getBalance() + amount);
    }

    public AccountDetails withdraw(AccountDetails account, double amount) {
        validateAmount(amount);

        if (amount > account.getBalance()) {
            System.out.println("insufficient balance");
            return account;
        }

        return account.withUpdatedBalance(account.getBalance() - amount);
    }

    private void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("enter amount > 0");
        }
    }
}

public class BankApplication {
    public static void main(String[] args) {
        AccountDetails account = new AccountDetails("Shashwat", 5000.00);
        TransactionService transactionService = new TransactionService();

        System.out.println("initial detail :");
        System.out.println(account.getDetails());

        account = transactionService.deposit(account, 1500.00);
        System.out.println();
        System.out.println("after deposit:");
        System.out.println(account.getDetails());

        account = transactionService.withdraw(account, 2000.00);
        System.out.println();
        System.out.println("after withdrawal:");
        System.out.println(account.getDetails());
    }
}
