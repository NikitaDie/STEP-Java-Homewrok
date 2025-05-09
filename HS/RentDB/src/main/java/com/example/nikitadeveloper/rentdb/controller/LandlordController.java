package com.example.nikitadeveloper.rentdb.controller;

import com.example.nikitadeveloper.rentdb.entity.Landlord;
import com.example.nikitadeveloper.rentdb.service.LandlordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/landlords")
public class LandlordController {
    private final LandlordService landlordService;

    public LandlordController(LandlordService landlordService) {
        this.landlordService = landlordService;
    }

    @GetMapping
    public String listLandlords(Model model) {
        List<Landlord> landlords = landlordService.findAll();
        model.addAttribute("landlords", landlords);
        return "landlord/index";
    }

    @GetMapping("/{id}/apartments")
    public String listLandlordApartments(@PathVariable Long id, Model model) {
        Landlord landlord = landlordService.findById(id);
        model.addAttribute("landlord", landlord);
        model.addAttribute("apartments", landlord.getApartments());
        return "landlord/landlord-apartments";
    }

    @GetMapping("/new")
    public String showNewLandlordForm(Model model) {
        model.addAttribute("landlord", new Landlord());
        return "landlord/landlord-form";
    }

    @PostMapping("/new")
    public String createLandlord(@ModelAttribute("landlord") Landlord landlord,
                                 Model model,
                                 RedirectAttributes redirectAttributes)
    {
        try {
            landlordService.save(landlord);
            redirectAttributes.addFlashAttribute("message", "Landlord created successfully!");
            return "redirect:/landlords";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to create landlord: " + e.getMessage());
            return "landlord/landlord-form";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteLandlord(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            landlordService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Landlord deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete landlord: " + e.getMessage());
        }
        return "redirect:/landlords";
    }
}