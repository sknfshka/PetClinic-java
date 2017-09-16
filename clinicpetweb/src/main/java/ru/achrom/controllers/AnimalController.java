package ru.achrom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.achrom.models.Animal;
import ru.achrom.store.Storages;

/**
 * Контроллер животного
 */
@Controller
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private Storages storages;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createGet(@RequestParam("clientId") String clientId, ModelMap model) {
        model.addAttribute("userId", clientId);
        return "clinic/AddAnimal";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createPost(@RequestParam("userId") String userId, @ModelAttribute Animal animal) {
        animal.setOwner(this.storages.clientStorage.get(Integer.parseInt(userId)));
        this.storages.animalStorage.add(animal);
        return "redirect:/client/edit?id=" + userId;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editGet(@RequestParam("clientId") String clientId, @RequestParam("id") String id, ModelMap model) {
        model.addAttribute("userId", clientId);
        model.addAttribute("animal", this.storages.animalStorage.get(Integer.parseInt(id)));
        return "clinic/EditAnimal";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(@RequestParam("userId") String userId, @ModelAttribute Animal animal) {
        animal.setOwner(this.storages.clientStorage.get(Integer.parseInt(userId)));
        this.storages.animalStorage.edit(animal);
        return "redirect:/client/edit?id=" + userId;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") String id, @RequestParam("clientId") String clientId) {
        Animal animal = this.storages.animalStorage.get(Integer.parseInt(id));
        this.storages.animalStorage.delete(animal);
        return "redirect:/client/edit?id=" + clientId;
    }
}
