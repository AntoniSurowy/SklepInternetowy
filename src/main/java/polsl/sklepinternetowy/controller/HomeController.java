package polsl.sklepinternetowy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import polsl.sklepinternetowy.model.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    static List<Item> items = new ArrayList<>();
    static {
        items.add(new Item("Ołówek", new BigDecimal("12.3"), "https://dentaltree.pl/userdata/public/gfx/874f9fee9a846664a4b56c870c57fbbc.jpg"));
        items.add(new Item("Ołówek2", new BigDecimal("13.3"), "https://wallbeing.com/2272-thickbox_default/grafika-joker.jpg"));
        items.add(new Item("Ołówek3", new BigDecimal("18.3"), "https://dentaltree.pl/userdata/public/gfx/874f9fee9a846664a4b56c870c57fbbc.jpg"));
    }

    @GetMapping("/")
    //@ResponseBody
    public String home(Model model)
    {
        model.addAttribute("items", items);
        return "home";
        //return new Item("Ołówek", new BigDecimal("12.3"), "https://dentaltree.pl/userdata/public/gfx/874f9fee9a846664a4b56c870c57fbbc.jpg");
    }


}
