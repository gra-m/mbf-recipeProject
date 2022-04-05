package fun.madeby.mbfrecipeproject.domain;


import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Gra_m on 2022 04 04
 */

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Recipe recipe;
    @Lob
    private String description;
    private BigDecimal amount;
    @OneToOne(fetch = FetchType.EAGER)//Default but we're being explicit.
    private UnitOfMeasure uom;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public UnitOfMeasure getUom() {
        return uom;
    }

    public void setUom(UnitOfMeasure uom) {
        this.uom = uom;
    }
}
