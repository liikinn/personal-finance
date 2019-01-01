package liisikinnunen.personalfinance.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "expense_id")
    private Long expenseId;

    @Column(name = "type_id")
    private Long typeId;

    @Column(name = "date")
    private Date date;

    @Column(name = "amount")
    private double amount;

    @Column(name = "ledger_id")
    private Long ledgerId;

    @Column(name = "comment")
    private String comment;
}
