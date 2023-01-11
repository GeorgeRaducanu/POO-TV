package input;

public class CredentialsIn {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private int balance;

    public CredentialsIn() {

    }

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final String getPassword() {
        return password;
    }

    public final void setPassword(final String password) {
        this.password = password;
    }

    public final String getAccountType() {
        return accountType;
    }

    public final void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    public final String getCountry() {
        return country;
    }

    public final void setCountry(final String country) {
        this.country = country;
    }

    public final int getBalance() {
        return balance;
    }

    public final void setBalance(final int balance) {
        this.balance = balance;
    }

    @Override
    public final String toString() {
        return "CredentialsIn{"
                +
                "name='" + name + '\''
                +
                ", password='" + password + '\''
                +
                ", accountType='" + accountType + '\''
                +
                ", country='" + country + '\''
                +
                ", balance=" + balance
                +
                '}';
    }
}
