package fun.madeby.mbfrecipeproject.domain;

import javax.persistence.*;

/**
 * Created by Gra_m on 2022 04 04
 */
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob // CharacterLargeObject: JPA limit is 255 for a string
    private String recipeNote;

    @OneToOne
    private Recipe recipe;

    public Note() {

    }

    public Note(String recipeNote, Recipe recipe) {
        this.recipeNote = recipeNote;
        this.recipe = recipe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNote() {
        return recipeNote;
    }

    public void setNote(String notes) {
        this.recipeNote = notes;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
