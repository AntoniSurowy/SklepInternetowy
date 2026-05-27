package polsl.sklepinternetowy.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import polsl.sklepinternetowy.model.Item;
import polsl.sklepinternetowy.repository.ItemRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    private final ItemRepository itemRepo;

    @Autowired
    public HomeController(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    @GetMapping("/")
    //@ResponseBody
    public String home(Model model, HttpSession session)
    {
        //List<Item> items = itemRepo.findAll();
        BigDecimal fullPrice = (BigDecimal) session.getAttribute("fullPrice");
        model.addAttribute("fullPrice", fullPrice);
        model.addAttribute("items", itemRepo.findAll());
        return "home";
        //return new Item("Ołówek", new BigDecimal("12.3"), "https://dentaltree.pl/userdata/public/gfx/874f9fee9a846664a4b56c870c57fbbc.jpg");
    }

    @GetMapping("/add/{itemId}")
    public String addItemToCart(@PathVariable("itemId") Long itemId, Model model, HttpSession session) {
        @SuppressWarnings("unchecked")
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        BigDecimal fullPrice = (BigDecimal) session.getAttribute("fullPrice");
        if (cart == null)
            cart = new ArrayList<>();
        if (fullPrice == null)
            fullPrice = BigDecimal.ZERO;
        Optional<Item> oItem = itemRepo.findById(itemId);
        if (oItem.isPresent()) {
            Item item = oItem.get();
            cart.add(item);
            fullPrice = fullPrice.add(item.getPrice());
            session.setAttribute("cart", cart);
            session.setAttribute("fullPrice", fullPrice);
        }

        model.addAttribute("items", itemRepo.findAll());
        return "redirect:/";
    }


}
