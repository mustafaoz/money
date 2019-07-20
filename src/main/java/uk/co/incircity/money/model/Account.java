package uk.co.incircity.money.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {

    private Integer accountNumber;
    private Integer ownerId;
    private String ownerName;
    private BigDecimal balance;
    private String currency;


    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() { return ownerName; }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() { return currency; }

    public void setCurrency(String currency) { this.currency = currency; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountNumber.equals(account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, currency);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", ownerId=" + ownerId +
                ", ownerName='" + ownerName + '\'' +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                '}';
    }

    public static class AccountBuilder {

        private Integer accountNumber;
        private Integer ownerId;
        private String ownerName;
        private BigDecimal balance;
        private String currency;

        public AccountBuilder ownerName(String ownerName) {
            this.ownerName = ownerName;
            return this;
        }
        public AccountBuilder accountNumber(Integer accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }
        public AccountBuilder ownerId(Integer ownerId) {
            this.ownerId = ownerId;
            return this;
        }
        public AccountBuilder balance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }
        public AccountBuilder currency(String currency) {
            this.currency = currency;
            return this;
        }
        public Account build() {
            Account account = new Account();
            account.setAccountNumber(accountNumber);
            account.setOwnerName(ownerName);
            account.setOwnerId(ownerId);
            account.setBalance(balance);
            account.setCurrency(currency);
            return account;
        }
    }
}
