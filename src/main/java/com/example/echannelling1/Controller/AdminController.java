package com.example.echannelling1.Controller;

import com.medicalstore.model.Medicine;
import com.medicalstore.model.Order;
import com.medicalstore.service.MedicineService;
import com.medicalstore.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final MedicineService medicineService = new MedicineService();
    private final OrderService orderService = new OrderService();

    @GetMapping("/medicines")
    public String viewMedicines(Model model) throws IOException {
        List<Medicine> medicines = medicineService.getAllMedicines();
        model.addAttribute("medicines", medicines);
        return "admin-dashboard";
    }

    @GetMapping("/medicines/add")
    public String showAddForm(Model model) {
        model.addAttribute("medicine", new Medicine());
        return "medicine-form";
    }

    @PostMapping("/medicines/add")
    public String addMedicine(@ModelAttribute Medicine medicine) throws IOException {
        medicineService.addMedicine(medicine);
        return "redirect:/admin/medicines";
    }

    @GetMapping("/medicines/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) throws IOException {
        Medicine medicine = medicineService.getMedicineById(id);
        if (medicine != null) {
            model.addAttribute("medicine", medicine);
            return "medicine-form";
        }
        return "redirect:/admin/medicines";
    }

    @PostMapping("/medicines/edit")
    public String updateMedicine(@ModelAttribute Medicine medicine) throws IOException {
        medicineService.updateMedicine(medicine);
        return "redirect:/admin/medicines";
    }

    @GetMapping("/medicines/delete/{id}")
    public String deleteMedicine(@PathVariable String id) throws IOException {
        medicineService.deleteMedicine(id);
        return "redirect:/admin/medicines";
    }

    @GetMapping("/orders")
    public String viewAllOrders(Model model) throws IOException {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "all-orders";
    }
}
