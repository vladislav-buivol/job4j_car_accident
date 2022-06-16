package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.User;
import ru.job4j.accident.service.UserService;

@Controller
public class RegControl {
    private final UserService userService;

    public RegControl(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        userService.save(user, "ROLE_USER");
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}