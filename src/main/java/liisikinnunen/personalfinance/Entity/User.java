package liisikinnunen.personalfinance.Entity;

import lombok.Data;

@Data
public class User {
    private int userId;
    private String username;
    private String firstName;
    private String lastName;
}