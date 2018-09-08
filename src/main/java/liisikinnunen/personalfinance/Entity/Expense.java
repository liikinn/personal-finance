package liisikinnunen.personalfinance.Entity;

import lombok.Data;

import java.util.Date;

@Data
public class Expense {
    private int expenseId;
    private String type;
    private Date date;
    private double amount;
    private int ledgerId;
    private String comment;
}
