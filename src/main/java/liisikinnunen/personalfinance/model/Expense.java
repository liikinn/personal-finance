package liisikinnunen.personalfinance.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "expense_id")
    private int expenseId;

    @Column(name = "type_id")
    private int typeId;

    @Column(name = "date")
    private Date date;

    @Column(name = "amount")
    private double amount;

    @Column(name = "ledger_id")
    private int ledgerId;

    @Column(name = "comment")
    private String comment;
}
