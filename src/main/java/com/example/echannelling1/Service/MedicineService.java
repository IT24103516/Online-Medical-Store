package com.example.echannelling1.Service;

import com.medicalstore.model.Medicine;
import com.medicalstore.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MedicineService {
    private static final String FILE_PATH = "data/medicines.txt";

    public List<Medicine> getAllMedicines() throws IOException {
        FileUtil.ensureFileExists(FILE_PATH);
        List<String> lines = FileUtil.readLines(FILE_PATH);
        List<Medicine> medicines = new ArrayList<>();
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                medicines.add(Medicine.fromString(line));
            }
        }
        return medicines;
    }

    public Medicine getMedicineById(String id) throws IOException {
        return getAllMedicines().stream()
                .filter(med -> med.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void addMedicine(Medicine medicine) throws IOException {
        FileUtil.appendLine(FILE_PATH, medicine.toFileString());
    }

    public void updateMedicine(Medicine updatedMedicine) throws IOException {
        List<Medicine> medicines = getAllMedicines();
        List<String> lines = new ArrayList<>();
        for (Medicine med : medicines) {
            if (med.getId().equals(updatedMedicine.getId())) {
                lines.add(updatedMedicine.toFileString());
            } else {
                lines.add(med.toFileString());
            }
        }
        FileUtil.writeLines(FILE_PATH, lines);
    }

    public void deleteMedicine(String id) throws IOException {
        List<Medicine> medicines = getAllMedicines();
        List<String> lines = new ArrayList<>();
        for (Medicine med : medicines) {
            if (!med.getId().equals(id)) {
                lines.add(med.toFileString());
            }
        }
        FileUtil.writeLines(FILE_PATH, lines);
    }
}
