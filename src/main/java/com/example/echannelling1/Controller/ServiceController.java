package com.oop.online.controller;

import com.oop.online.model.Service;
import com.oop.online.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/view")
    public String viewServices(Model model) {
        List<Service> services = serviceService.getAllServices();
        model.addAttribute("services", services);
        return "more-services";
    }

    @GetMapping("/admin")
    public String adminServices(Model model) {
        List<Service> services = serviceService.getAllServices();
        model.addAttribute("services", services);
        return "admin-more-services";
    }

    @PostMapping("/add")
    public String addService(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam String category,
                             @RequestParam String contact,
                             @RequestParam String location,
                             @RequestParam String price,
                             @RequestParam("image") MultipartFile image) {

        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        String imageId = UUID.randomUUID().toString();
        String savedName = imageId + "_" + fileName;

        try {
            String uploadPath = "src/main/resources/static/images/uploads/";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();
            image.transferTo(new File(uploadPath + savedName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Service service = new Service(UUID.randomUUID().toString(), name, description, category, contact, location, price, savedName);
        serviceService.addService(service);
        return "redirect:/services/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteService(@PathVariable String id) {
        serviceService.deleteService(id);
        return "redirect:/services/admin";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        Service service = serviceService.getServiceById(id);
        model.addAttribute("service", service);
        return "admin-edit-service";
    }

    @PostMapping("/update")
    public String updateService(@ModelAttribute Service service) {
        serviceService.updateService(service.getId(), service);
        return "redirect:/services/admin";
    }
    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "✅ Spring Boot is working!";
    }

}
