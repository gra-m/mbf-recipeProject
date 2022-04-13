package fun.madeby.mbfrecipeproject.repositories;

import fun.madeby.mbfrecipeproject.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest Runs, but noticeably faster with @SpringBootTest 383ms vs 192ms
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UnitOfMeasureRepositoryTestIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DirtiesContext
    void findUnitOfMeasureHandful() {
        //given
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findUnitOfMeasureByDescription("handful");

        //when
        String expectedValue = "handful";

        //then
        assertEquals(expectedValue, unitOfMeasureOptional.get().getDescription());
    }

    @Test
    void findUnitOfMeasureCup() {
        //given
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findUnitOfMeasureByDescription("cup");

        //when
        String expectedValue = "cup";

        //then
        assertEquals(expectedValue, unitOfMeasureOptional.get().getDescription());
    }
}