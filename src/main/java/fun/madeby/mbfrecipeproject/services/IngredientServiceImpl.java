package fun.madeby.mbfrecipeproject.services;

import fun.madeby.mbfrecipeproject.commands.IngredientCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by Gra_m on 2022 04 20
 */

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService{

    @Override
    public IngredientCommand getIngredientById(Long id) {
        return null;
    }
}
