package com.oop.online.service;

import com.oop.online.model.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@org.springframework.stereotype.Service
public class ServiceService {

    private static final String FILE_PATH = "src/main/resources/data/services.txt";
    private final List<Service> services = new ArrayList<>();

    public ServiceService() {
        loadServices();
    }

    private void loadServices() {
        services.clear();
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
            for (String line : lines) {
                if (!line.trim().isEmpty()) {
                    services.add(Service.fromString(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveServices() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Service service : services) {
                writer.write(service.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Service> getAllServices() {
        return new ArrayList<>(services);
    }

    public void addService(Service service) {
        services.add(service);
        saveServices();
    }

    public void updateService(String id, Service updatedService) {
        for (int i = 0; i < services.size(); i++) {
            if (services.get(i).getId().equals(id)) {
                services.set(i, updatedService);
                saveServices();
                return;
            }
        }
    }

    public void deleteService(String id) {
        services.removeIf(s -> s.getId().equals(id));
        saveServices();
    }

    public Service getServiceById(String id) {
        return services.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
