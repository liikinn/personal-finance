package liisikinnunen.personalfinance.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "income")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "income_id")
    private int incomeId;

    @Column(name = "type_id")
    private int typeId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "date")
    private Date date;

    @Column(name = "ledger_id")
    private int ledgerId;
}