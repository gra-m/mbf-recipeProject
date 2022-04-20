package fun.madeby.mbfrecipeproject.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Gra_m on 2022 04 04
 */
//@EqualsAndHashCode(exclude = {"recipe"})
@Data
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob // CharacterLargeObject: JPA limit is 255 for a string
    private String recipeNote;

    public Note() {
    }

    public Note(String recipeNote, Recipe recipe) {
        this.recipeNote = recipeNote;
    }
}
