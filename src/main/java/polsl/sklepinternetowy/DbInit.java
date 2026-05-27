package polsl.sklepinternetowy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import polsl.sklepinternetowy.model.Item;
import polsl.sklepinternetowy.repository.ItemRepository;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DbInit implements CommandLineRunner {
    private final ItemRepository itemRepo;

    @Autowired
    public DbInit(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        itemRepo.saveAll(List.of(
            new Item("Ołówek", new BigDecimal("12.3"), "https://dentaltree.pl/userdata/public/gfx/874f9fee9a846664a4b56c870c57fbbc.jpg"),
            new Item("Ołówek2", new BigDecimal("13.3"), "https://wallbeing.com/2272-thickbox_default/grafika-joker.jpg"),
            new Item("Ołówek3", new BigDecimal("18.3"), "https://dentaltree.pl/userdata/public/gfx/874f9fee9a846664a4b56c870c57fbbc.jpg")
        ));
    }
}
