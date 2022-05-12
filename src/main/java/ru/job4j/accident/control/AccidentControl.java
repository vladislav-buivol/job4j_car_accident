package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentTypeService;

import java.util.List;

@Controller
public class AccidentControl {
    private final AccidentService accidents;
    private final AccidentTypeService accidentTypes;

    public AccidentControl(AccidentService accidents,
                           AccidentTypeService accidentTypes) {
        this.accidents = accidents;
        this.accidentTypes = accidentTypes;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidentTypes.getAll());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        if (accident.getType() != null) {
            accident.setType(accidentTypes.findById(accident.getType().getId()));
        }
        accidents.save(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        Accident accident = accidents.findById(id);
        model.addAttribute("accident", accident);
        List<AccidentType> types = accidentTypes.getAll();
        types.remove(accident.getType());
        model.addAttribute("types", types);
        return "accident/update";
    }
}