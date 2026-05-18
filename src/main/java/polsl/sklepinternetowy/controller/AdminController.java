package polsl.sklepinternetowy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import polsl.sklepinternetowy.model.Item;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    private String AdminPage(){
        return "adminview/addItem";
    }

    @PostMapping
    private String AddItem(Item item) {
        HomeController.items.add(item);
        return "redirect:/";
    }
}
