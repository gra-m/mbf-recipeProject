package fun.madeby.mbfrecipeproject.domain;

import javax.persistence.*;

/**
 * Created by Gra_m on 2022 04 04
 */
@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob // CharacterLargeObject: JPA limit is 255 for a string
    private String recipeNotes;

    @OneToOne
    private Recipe recipe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNotes() {
        return recipeNotes;
    }

    public void setNotes(String notes) {
        this.recipeNotes = notes;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
