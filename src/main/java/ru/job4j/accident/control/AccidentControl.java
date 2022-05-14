package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentTypeService;
import ru.job4j.accident.service.RuleService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AccidentControl {
    private final AccidentService accidents;
    private final AccidentTypeService accidentTypes;
    private final RuleService rules;

    public AccidentControl(AccidentService accidents,
                           AccidentTypeService accidentTypes,
                           RuleService rules) {
        this.accidents = accidents;
        this.accidentTypes = accidentTypes;
        this.rules = rules;
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<AccidentType> types = new ArrayList<>();
        types.add(AccidentType.of(1, "Две машины"));
        types.add(AccidentType.of(2, "Машина и человек"));
        types.add(AccidentType.of(3, "Машина и велосипед"));
        model.addAttribute("types", types);
        model.addAttribute("rules", rules.getAll());
        model.addAttribute("types", accidentTypes.getAll());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        if (accident.getType() != null) {
            accident.setType(accidentTypes.findById(accident.getType().getId()));
        }
        if (ids != null) {
            for (String rId : ids) {
                accident.addRule(rules.findById(Integer.parseInt(rId)));
            }
        }
        accidents.save(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        Accident accident = accidents.findById(id);
        model.addAttribute("accident", accident);
        addTypesToModel(model, accident);
        addRulesToModel(model, accident);
        return "accident/update";
    }

    private void addTypesToModel(Model model, Accident accident) {
        List<AccidentType> types = accidentTypes.getAll();
        types.remove(accident.getType());
        model.addAttribute("types", types);
    }

    private void addRulesToModel(Model model, Accident accident) {
        List<Rule> allRule = rules.getAll();
        allRule.removeAll(accident.getRules());
        model.addAttribute("rules", allRule);
        model.addAttribute("selectedrule", accident.getRules());
    }
}