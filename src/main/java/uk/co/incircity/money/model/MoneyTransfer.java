package uk.co.incircity.money.model;

import java.math.BigDecimal;

public class MoneyTransfer {

    private Integer fromAccountNumber;
    private Integer toAccountNumber;
    private BigDecimal amount;

    public Integer getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(Integer fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public Integer getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(Integer toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public static class MoneyTransferBuilder {

        private Integer fromAccountNumber;
        private Integer toAccountNumber;
        private BigDecimal amount;

        public MoneyTransferBuilder fromAccountNumber(Integer fromAccountNumber) {
            this.fromAccountNumber = fromAccountNumber;
            return this;
        }
        public MoneyTransferBuilder toAccountNumber(Integer toAccountNumber) {
            this.toAccountNumber = toAccountNumber;
            return this;
        }
        public MoneyTransferBuilder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }
        public MoneyTransfer build() {
            MoneyTransfer moneyTransfer = new MoneyTransfer();
            moneyTransfer.setFromAccountNumber(fromAccountNumber);
            moneyTransfer.setToAccountNumber(toAccountNumber);
            moneyTransfer.setAmount(amount);
            return moneyTransfer;
        }
    }
}
