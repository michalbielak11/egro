package pl.coderslab.egro.service;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import pl.coderslab.egro.entity.Item;


import java.util.HashMap;
import java.util.Map;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class OrderUtility {


    private Map<Long, Item> orderItemMap = new HashMap<>();

    public void addToCart(Item item) {
        if (orderItemMap.containsKey(item.getId())) {
            orderItemMap.get(item.getId()).setQuantity(orderItemMap.get(item.getId()).getQuantity() + 1);
        } else {
            orderItemMap.put(item.getId(), item);
        }
    }

    public void remove(Item cartItem) {
        this.orderItemMap.remove(cartItem.getId());
    }

    public void plus(Item item) {
        orderItemMap.get(item.getId()).setQuantity(orderItemMap.get(item.getId()).getQuantity() + 1);;
    }


    public void minus(Item item) {
        if (orderItemMap.get(item.getId()).getQuantity() == 1) {
            this.orderItemMap.remove(item.getId());
        } else {
            orderItemMap.get(item.getId()).setQuantity(orderItemMap.get(item.getId()).getQuantity() - 1);;
        }
    }

//    public void save(Item item) {
//
//        User user = currentUser.getUser();
//        orderItem.getOrder().setId(user.getOrder().getId());
//        orderItem.setQuantity(item.getQuantity());
//        orderItem.setDescription(item.getDescription());
//        orderItem.setName(item.getName());
//        orderItem.setPrice(item.getPrice() * item.getQuantity());
//        orderItem.setSerialNumber(item.getSerialNumber());
//
//    }

    public Map<Long, Item> getOrderItemMap() {
        return orderItemMap;
    }

    public void setOrderItemMap(Map<Long, Item> orderItemMap) {
        this.orderItemMap = orderItemMap;
    }
}
