package pl.coderslab.egro.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.egro.entity.Item;
import pl.coderslab.egro.repository.ItemRepository;
import pl.coderslab.egro.service.ItemServiceImpl;

import javax.validation.Valid;

@Controller
@RequestMapping("/item")
public class ItemController {
    private final ItemRepository itemRepository;
    private final ItemServiceImpl itemService;

    public ItemController(ItemRepository itemRepository, ItemServiceImpl itemService) {
        this.itemRepository = itemRepository;
        this.itemService = itemService;
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String showForm(Model model) {
        model.addAttribute("item", new Item());
        return "item/add";
    }



    @RequestMapping(value = "/add", method = RequestMethod.POST)

    public String perform(@ModelAttribute @Valid Item item, BindingResult result) {
        if (result.hasErrors()) {
            return "item/add";
        }
        itemService.saveItem(item);
        return "redirect:/item/list";
    }
    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("items", itemRepository.findAll());
        return "item/list";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        itemRepository.delete(itemRepository.findItemById(id));
        return "redirect:/item/list";
    }
    @GetMapping("/update/{id}")
    public String showForm(Model model, @PathVariable long id) {
        Item b = itemRepository.findItemById(id);
        model.addAttribute("item", b);
        return "item/update";
    }
    @PostMapping("/update")
    public String performUpdate(@ModelAttribute Item item) {
        itemRepository.save(item);
        return "redirect:/item/list";
    }
    @GetMapping("/search")
    public String searchResult(@RequestParam String param, Model model) {
        model.addAttribute("items", itemRepository.findItemsContaining(param));
        return "item/list";
    }
    @GetMapping("/asc")
    public String itemsAsc(Model model) {
        model.addAttribute("items", itemRepository.itemsSortedByPriceAsc());
        return "item/list";
    }
    @GetMapping("/desc")
    public String itemsDesc(Model model) {
        model.addAttribute("items", itemRepository.itemsSortedByPriceDasc());
        return "item/list";
    }
    @GetMapping("/name-asc")
    public String itemsNameAsc(Model model) {
        model.addAttribute("items", itemRepository.itemsSortedByNameAsc());
        return "item/list";
    }
    @GetMapping("/name-desc")
    public String itemsNameDesc(Model model) {
        model.addAttribute("items", itemRepository.itemsSortedByPriceDasc());
        return "item/list";
    }


}
