package com.example.nikitadeveloper.rentdb.controller;

import com.example.nikitadeveloper.rentdb.entity.Apartment;
import com.example.nikitadeveloper.rentdb.service.ApartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/apartments")
public class ApartmentController {
    private final ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping
    public String listApartments(@RequestParam(required = false) String district,
                                 @RequestParam(required = false) Integer rooms,
                                 @RequestParam(required = false) Double price,
                                 @RequestParam(defaultValue = "all") String status,
                                 Model model) {
        List<Apartment> apartments = apartmentService.findApartments(district, rooms, price, status);
        model.addAttribute("apartments", apartments);
        model.addAttribute("district", district);
        model.addAttribute("rooms", rooms);
        model.addAttribute("price", price);
        model.addAttribute("status", status);
        return "apartment/index";
    }

    @GetMapping("/new")
    public String showNewApartmentForm(Model model) {
        model.addAttribute("apartment", new Apartment());
        return "apartment/apartment-form";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteApartment(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            apartmentService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Apartment deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete apartment: " + e.getMessage());
        }
        return "redirect:/apartments";
    }
}
