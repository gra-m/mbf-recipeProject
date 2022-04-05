package fun.madeby.mbfrecipeproject.controllers;

import fun.madeby.mbfrecipeproject.domain.Category;
import fun.madeby.mbfrecipeproject.domain.UnitOfMeasure;
import fun.madeby.mbfrecipeproject.repositories.CategoryRepository;
import fun.madeby.mbfrecipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Created by Gra_m on 2022 03 28
 */

@Controller
public class IndexController {

    private final CategoryRepository CATEGORY_REPOSITORY;
    private final UnitOfMeasureRepository UNIT_OF_MEASURE_REPOSITORY;

    public IndexController (
            CategoryRepository categoryRepository,
            UnitOfMeasureRepository unitOfMeasureRepository) {
        this.CATEGORY_REPOSITORY = categoryRepository;
        this.UNIT_OF_MEASURE_REPOSITORY = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndex(){
        Optional<Category> categoryOptional = CATEGORY_REPOSITORY.findCategoryByDescription("American");
        Optional<UnitOfMeasure> uOMOptional = UNIT_OF_MEASURE_REPOSITORY.findUnitOfMeasureByDescription("Pinch");
        System.out.println("CAT_ID: " + categoryOptional.get().getId());
        System.out.println("UOM_ID: " + uOMOptional.get().getId());

        return "index";
    }
}
