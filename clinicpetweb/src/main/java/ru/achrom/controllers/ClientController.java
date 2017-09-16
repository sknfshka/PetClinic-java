package ru.achrom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.achrom.models.Client;
import ru.achrom.store.Storages;

/**
 * Контроллер клиента клиники
 */
@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private Storages storages;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute Client client) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        //current user
//        String login = auth.getName();
        this.storages.clientStorage.add(client);
        return "redirect:/clinic/view";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editGet(@RequestParam("id") String id, ModelMap model) {
        model.addAttribute("client", this.storages.clientStorage.get(Integer.parseInt(id)));
        model.addAttribute("animals", this.storages.animalStorage.findUserAnimals(Integer.parseInt(id)));
        return "clinic/EditClient";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(@ModelAttribute Client client) {
        this.storages.clientStorage.edit(client);
        return "redirect:/clinic/view";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") String id) {
        Client client = this.storages.clientStorage.get(Integer.parseInt(id));
        this.storages.clientStorage.delete(client);
        return "redirect:/clinic/view";
    }
}
