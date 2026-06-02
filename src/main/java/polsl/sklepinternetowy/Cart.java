package polsl.sklepinternetowy;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import polsl.sklepinternetowy.model.Item;
import polsl.sklepinternetowy.repository.ItemRepository;

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
    ItemRepository itemRepository;

    private Optional<CartItem> getCartItemByItem(Item item) {
        return cartItems.stream()
                .filter(cartItem -> cartItem.isEquals(item))
                .findFirst();
    }

    public void addItem(Item item) {
        getCartItemByItem(item).ifPresentOrElse(
                CartItem::increaseCounter,
                () -> cartItems.add(new CartItem(item)));

        recalculatePriceAdnCounter();
    }

    public void removeItem(Item item) {
        Optional<CartItem> oCartItem = getCartItemByItem(item);

        if (oCartItem.isPresent()) {
            CartItem cartItem = oCartItem.get();
            cartItem.decreaseCounter();

            if (cartItem.hasZeroItems())
                cartItems.remove(cartItem);

            recalculatePriceAdnCounter();
        }
    }

    private void recalculatePriceAdnCounter() {
        sum = cartItems.stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        counter = cartItems.stream()
                .map(CartItem::getCounter)
                .reduce(0, Integer::sum);
    }
}
