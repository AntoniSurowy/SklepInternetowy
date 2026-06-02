package polsl.sklepinternetowy;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import polsl.sklepinternetowy.model.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
public class Cart {
    private List<CartItem> cartItems = new ArrayList<>();
    private int counter = 0;
    private BigDecimal sum = BigDecimal.ZERO;

    public void addItem(Item item) {
        boolean found = false;
        for (CartItem cartItem : cartItems) {
            if (cartItem.getItem().getId().equals(item.getId())) {
                cartItem.increaseCounter();
                found = true;
                break;
            }
        }

        if(!found)
            cartItems.add(new CartItem(item));

        recalculatePriceAdnCounter();
    }

    public void removeItem(Item item) {
        Optional<CartItem> itemToRemove = cartItems.stream().filter(ci -> ci.getItem().getId().equals(item.getId())).findFirst();

        if (itemToRemove.isPresent()) {
            CartItem cartItem = itemToRemove.get();
            cartItem.decreaseCounter();

            if (cartItem.hasZeroItems())
                cartItems.remove(cartItem);

            recalculatePriceAdnCounter();
        }
    }

    private void recalculatePriceAdnCounter() {
        sum = BigDecimal.ZERO;
        counter = 0;

        for (CartItem cartItem : cartItems) {
            sum = sum.add(cartItem.getPrice());
            counter += cartItem.getCounter();
        }
    }
}
