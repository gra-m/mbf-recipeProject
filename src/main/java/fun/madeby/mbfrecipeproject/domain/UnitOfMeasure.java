package fun.madeby.mbfrecipeproject.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Gra_m on 2022 04 04
 */
@Data
@Entity
public class UnitOfMeasure {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

}
