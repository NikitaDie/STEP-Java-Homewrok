package com.example.nikitadeveloper.rentdb.controller;

import com.example.nikitadeveloper.rentdb.entity.Client;
import com.example.nikitadeveloper.rentdb.entity.dto.ClientSearchDTO;
import com.example.nikitadeveloper.rentdb.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String listClients(Model model) {
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("clientSearchDTO", new ClientSearchDTO());
        return "client/list";
    }

    @GetMapping("/new")
    public String newClient(Model model) {
        model.addAttribute("client", new Client());
        return "client/new";
    }

    @PostMapping
    public String saveClient(@ModelAttribute Client client) {
        clientService.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/edit/{id}")
    public String editClient(@PathVariable Long id, Model model) {
        model.addAttribute("client", clientService.findById(id));
        return "client/new";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientService.delete(id);
        return "redirect:/clients";
    }

    @GetMapping("/search")
    public String searchClients(@ModelAttribute ClientSearchDTO searchDTO, Model model) {
        model.addAttribute("clients", clientService.search(searchDTO));
        model.addAttribute("clientSearchDTO", searchDTO);
        return "client/list";
    }

    @GetMapping("/search/recent")
    public String searchRecentRentals(Model model) {
        model.addAttribute("clients", clientService.findByRecentRentals());
        model.addAttribute("clientSearchDTO", new ClientSearchDTO());
        return "client/list";
    }

    @GetMapping("/search/upcoming")
    public String searchUpcomingEndRentals(Model model) {
        model.addAttribute("clients", clientService.findByUpcomingEndRentals());
        model.addAttribute("clientSearchDTO", new ClientSearchDTO());
        return "client/list";
    }

    @GetMapping("/search/short")
    public String searchShortAverageRental(Model model) {
        model.addAttribute("clients", clientService.findByShortAverageRental());
        model.addAttribute("clientSearchDTO", new ClientSearchDTO());
        return "client/list";
    }

    @GetMapping("/search/long")
    public String searchLongAverageRental(Model model) {
        model.addAttribute("clients", clientService.findByLongAverageRental());
        model.addAttribute("clientSearchDTO", new ClientSearchDTO());
        return "client/list";
    }
}
