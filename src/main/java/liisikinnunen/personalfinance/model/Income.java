package liisikinnunen.personalfinance.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "incomes")
public class Income implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "income_id")
    private Long incomeId;

    @Column(name = "type_id")
    private Long typeId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "date")
    private Date date;

    @Column(name = "ledger_id")
    private Long ledgerId;
}
