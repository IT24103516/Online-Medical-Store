package com.example.echannelling1.Controller;

import com.medicalstore.model.Medicine;
import com.medicalstore.service.MedicineService;
import com.medicalstore.service.QuickSort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    private final MedicineService medicineService = new MedicineService();

    @GetMapping("/user/medicines")
    public String viewMedicines(Model model) throws IOException {
        List<Medicine> medicines = medicineService.getAllMedicines();
        QuickSort.sort(medicines);
        model.addAttribute("medicines", medicines);
        return "user-dashboard";
    }
}
