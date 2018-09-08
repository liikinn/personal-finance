package liisikinnunen.personalfinance.Entity;

import lombok.Data;

import java.util.Date;

@Data
public class Income {
    private int incomeId;
    private String type;
    private double amount;
    private Date date;
    private int ledgerId;
}
