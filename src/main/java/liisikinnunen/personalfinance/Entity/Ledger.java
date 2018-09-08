package liisikinnunen.personalfinance.Entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Ledger {
    private int ledgerId;
    private List<User> users;
    private String name;
    private Date created;
}
