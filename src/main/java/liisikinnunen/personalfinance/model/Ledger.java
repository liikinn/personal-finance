package liisikinnunen.personalfinance.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "ledger")
public class Ledger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ledger_id")
    private int ledgerId;

    @Column(name = "title")
    private String title;

    @Column(name = "created")
    private Date created;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "ledger_user",
            joinColumns = { @JoinColumn(name = "ledger_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private Set<User> users = new HashSet<>();
}
