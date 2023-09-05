package org.example.model;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankAccount implements Model {
    private Long id;
    private String accountNumber;
    private Client accountOwner;
    private Bank bankOwner;
    private String currency;
    private LocalDate openDate;
    private Long accountBalance;

    public void updateBalance(Long transferAmount) {
        this.accountBalance += transferAmount;
    }

    @Override
    public String toString() {
        return String.format("Account ID = %-3d | accountNumber = %-10s | accountOwner = %-15s | bankOwner = %-15s | currency = %-10s | openDate = %-10s | accountBalance =  %d%n",
                id, accountNumber, accountOwner, bankOwner, currency, openDate, accountBalance);
    }
}
