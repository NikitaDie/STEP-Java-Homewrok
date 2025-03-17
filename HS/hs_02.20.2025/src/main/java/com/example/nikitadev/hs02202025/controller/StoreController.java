package com.example.nikitadev.hs02202025.controller;

import com.example.nikitadev.hs02202025.model.Store;
import com.example.nikitadev.hs02202025.repository.StoreRepository;
import com.example.nikitadev.hs02202025.service.FileUploadService;
import com.example.nikitadev.hs02202025.service.StoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService; // Interface type
    private final FileUploadService fileUploadService;

    public StoreController(StoreService storeService, FileUploadService fileUploadService) {
        this.storeService = storeService;
        this.fileUploadService = fileUploadService;
    }

    @GetMapping
    public String listStores(Model model, @RequestParam(required = false) String search) {
        Iterable<Store> stores = (search != null && !search.isEmpty())
            ? storeService.searchStores(search)
            : storeService.getAllStores();

        model.addAttribute("stores", stores);
        return "index";
    }

    @GetMapping("/new")
    public String newStoreForm(Model model) {
        model.addAttribute("store", new Store());
        return "store-form";
    }

    @PostMapping
    public String saveStore(@Valid @ModelAttribute Store store, @RequestParam("image") MultipartFile image)
        throws IOException
    {
        String imagePath = fileUploadService.uploadFile(image);
        if (imagePath != null) {
            store.setImagePath(imagePath);
        }

        storeService.saveStore(store);
        return "redirect:/stores";
    }

    @GetMapping("/{id}")
    public String viewStore(@PathVariable UUID id, Model model) {
        Store store = storeService.getStoreById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid store Id:" + id));

        model.addAttribute("store", store);
        return "store-view";
    }

    @GetMapping("/{id}/edit")
    public String editStoreForm(@PathVariable UUID id, Model model) {
        Store store = storeService.getStoreById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid store Id:" + id));

        model.addAttribute("store", store);
        return "store-form";
    }

    @PostMapping("/{id}")
    public String updateStore(@PathVariable UUID id, @Valid @ModelAttribute Store store,
                              @RequestParam MultipartFile image)
    {
        store.setId(id);
        Store existingStore = storeService.getStoreById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid store Id:" + id));

        try {
            String imagePath = fileUploadService.uploadFile(image, existingStore.getImagePath());

            if (imagePath != null) {
                store.setImagePath(imagePath);
            }
        } catch (IOException e) {
            return "redirect:/stores/" + id + "/store-form";
        }


        storeService.saveStore(store);

        return "redirect:/stores";
    }

    @PostMapping("/{id}/delete")
    public String deleteStore(@PathVariable UUID id) {
        storeService.deleteStore(id);
        return "redirect:/stores";
    }

}
