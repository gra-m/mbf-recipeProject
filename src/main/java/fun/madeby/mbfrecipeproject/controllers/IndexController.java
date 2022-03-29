package fun.madeby.mbfrecipeproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Gra_m on 2022 03 28
 */

@Controller
public class IndexController {

    @RequestMapping({"", "/", "/index"})
    public String getIndex(){
        return "index";
    }
}
