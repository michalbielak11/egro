package pl.coderslab.egro.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import pl.coderslab.egro.entity.User;
import pl.coderslab.egro.repository.UserRepository;
import pl.coderslab.egro.service.UserService;

import javax.validation.Valid;


@Controller
public class LoginController {

    private final UserService userService;
    private final UserRepository userRepository;

    public LoginController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }
    @GetMapping("/login")
    public String login() {return "login";}
    @RequestMapping(value = {"/403"}, method = RequestMethod.GET)
    public String error403() {
        return "admin/403";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = {"/add-user-sample"}, method = RequestMethod.GET)
    public void createUser() {
        User user = new User();
        user.setPassword("user1");
        user.setUsername("user1");
        userService.saveUser(user);
    }

    @RequestMapping(value = {"/add-user"}, method = RequestMethod.GET)
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }


    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public String perform(@ModelAttribute @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "add-user";
        }
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/admin/list")
    public String list(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        userRepository.delete(userRepository.findUserById(id));
        return "redirect:/admin/list";
    }

    @GetMapping("/update/{id}")
    public String showForm(Model model, @PathVariable long id) {
        User b = userRepository.findUserById(id);
        model.addAttribute("user", b);
        return "admin/update";
    }

    @PostMapping("/update")
    public String performUpdate(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/admin/list";
    }


}
