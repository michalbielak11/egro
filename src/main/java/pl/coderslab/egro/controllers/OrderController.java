package pl.coderslab.egro.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.egro.entity.Item;

import pl.coderslab.egro.entity.OrderItem;
import pl.coderslab.egro.repository.ItemRepository;
import pl.coderslab.egro.service.OrderUtility;
import pl.coderslab.egro.service.ItemServiceImpl;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final ItemRepository itemRepository;
    private final ItemServiceImpl itemService;
    private final OrderUtility cartUtility;

    public OrderController(ItemRepository itemRepository, ItemServiceImpl itemService, OrderUtility cartUtility) {

        this.itemRepository = itemRepository;
        this.itemService = itemService;
        this.cartUtility = cartUtility;
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("orders", itemRepository.findAll());
        return "order/list";
    }

    @GetMapping("/add/{id}")
    public String add(@PathVariable long id){
        Item i = itemRepository.findOne(id);
        cartUtility.addToCart(i);
        return "redirect:/order/summary";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable long id){
        Item i = itemRepository.findOne(id);
        cartUtility.remove(i);
        return "redirect:/order/summary";
    }


    @GetMapping("/summary")
    public String summary(Model model){
        model.addAttribute("orderItems", cartUtility.getOrderItemMap().values());
        return "order/summary";
    }
    @GetMapping("/plus/{id}")
    public String plus(@PathVariable long id){
        Item i = itemRepository.findOne(id);
        cartUtility.plus(i);
        return "redirect:/order/summary";
    }
    @GetMapping("/minus/{id}")
    public String minus(@PathVariable long id){
        Item i = itemRepository.findOne(id);
        cartUtility.minus(i);
        return "redirect:/order/summary";
    }
//    @GetMapping("/save/{id}")
//    public String save(@PathVariable long id, Model model){
//        Item i = itemRepository.findOne(id);
//        model.addAttribute("orderedItems", new OrderItem());
//        cartUtility.save(i);
//        return "redirect:/order/order";
//    }
}
