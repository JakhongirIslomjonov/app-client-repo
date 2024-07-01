package org.example.appchekingefficiency.controller;

import lombok.RequiredArgsConstructor;
import org.example.appchekingefficiency.entity.Client;
import org.example.appchekingefficiency.entity.User;
import org.example.appchekingefficiency.repo.ClientRepository;
import org.example.appchekingefficiency.repo.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class BasicController {
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;

    @GetMapping("/admin")
    public ModelAndView getAll() {
        List<Client> clients = clientRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("admin");
        modelAndView.addObject("clients", clients);
        return modelAndView;
    }

    @GetMapping("/client")
    public String getClientPage() {
        return "client";
    }

    @GetMapping("/use")
    public ModelAndView getUsers(@RequestParam Integer id) {
        List<User> users = userRepository.findByClientId(id);
        ModelAndView modelAndView = new ModelAndView("use");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/join")
    public ModelAndView getLink(String link) {
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("link", link);
        return modelAndView;
    }

    @PostMapping("/client")
    public String saveClient(String name, String phoneNumber) {
        Client client = Client.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .link("http://localhost:8080/api/" + name)
                .build();
        clientRepository.save(client);
        return "redirect:/api/admin";
    }

    @PostMapping("users")
    public String saeUser(String name, String phoneNumber, String link) {
        Client client = clientRepository.findByLink(link);
        User user = User.builder()
                .phoneNumber(phoneNumber)
                .name(name)
                .client(client)
                .build();
        userRepository.save(user);
        return "saved";
    }


}
