package polsl.sklepinternetowy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import polsl.sklepinternetowy.model.Item;
import polsl.sklepinternetowy.repository.ItemRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ItemRepository itemRepo;

    @Autowired
    public AdminController(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    @GetMapping
    private String AdminPage(){
        return "adminview/addItem";
    }

    @PostMapping
    private String AddItem(Item item) {
        itemRepo.save(item);
        //HomeController.items.add(item);
        return "redirect:/";
    }
}
