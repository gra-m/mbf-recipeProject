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

    public Ingredient() {
    }

    public Ingredient(
            String description,
            BigDecimal amount,
            UnitOfMeasure uom,
            Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
        this.recipe = recipe;
    }

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

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public UnitOfMeasure getUom() {
        return uom;
    }

    public void setUom(UnitOfMeasure uom) {
        this.uom = uom;
    }
}
