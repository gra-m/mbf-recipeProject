package fun.madeby.mbfrecipeproject.domain;

/**
 * Created by Gra_m on 04/04/2022
 */

public enum Difficulty {
    EASY("Easy"),
    MODERATE("Moderate"),
    HARD("Hard");


    public final String label;

    Difficulty(String label) {
        this.label = label;
    }

}
