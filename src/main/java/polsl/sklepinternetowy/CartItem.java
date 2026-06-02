package polsl.sklepinternetowy;

import lombok.Getter;
import polsl.sklepinternetowy.model.Item;

import java.math.BigDecimal;

@Getter
public class CartItem {
    private Item item;
    private int counter;
    private BigDecimal price;

    public CartItem(Item item) {
        this.item = item;
        this.counter = 1;
        this.price = item.getPrice();
    }

    public void increaseCounter() {
        counter++;
        recalculate();
    }

    public void decreaseCounter() {
        counter--;
        recalculate();
    }

    public boolean hasZeroItems() {
        return counter <= 0;
    }

    private void recalculate() {
        price = item.getPrice().multiply(new BigDecimal(counter));
    }
}
