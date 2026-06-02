package polsl.sklepinternetowy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import polsl.sklepinternetowy.Cart;
import polsl.sklepinternetowy.model.Item;
import polsl.sklepinternetowy.repository.ItemRepository;

import java.util.Optional;

@Controller
public class HomeController {

    private final ItemRepository itemRepository;
    private Cart cart;

    @Autowired
    public HomeController(ItemRepository itemRepository, Cart cart) {
        this.itemRepository = itemRepository;
        this.cart = cart;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        return "home";
    }

    @GetMapping("/add/{itemId}")
    public String addItem(@PathVariable Long itemId) {
        Optional<Item> oItem = itemRepository.findById(itemId);

        oItem.ifPresent(item -> cart.addItem(item));

        return "redirect:/";
    }


}
