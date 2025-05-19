package com.example.echannelling1.Controller;

import com.example.echannelling1.Classes.Channel;
import com.example.echannelling1.Service.ChannelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Queue;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/channel")
public class ChannelController {
    private final ChannelService service = new ChannelService();

    @PostMapping("/add")
    public Map<String, String> addChannel(@RequestBody Channel c) {
        Map<String, String> response = new HashMap<>();
        service.addChannel(c);
        response.put("message", "Channel added successfully.");
        return response;
    }

    @GetMapping("/all")
    public List<Channel> getAll() {
        return service.getAllChannels();
    }

    @PutMapping("/update/{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Channel updated) {
        Map<String, String> response = new HashMap<>();
        boolean success = service.updateChannel(id, updated);
        response.put("message", success ? "Updated" : "Channel not found");
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, String> delete(@PathVariable String id) {
        Map<String, String> response = new HashMap<>();
        boolean success = service.deleteChannel(id);
        response.put("message", success ? "Deleted" : "Not Found");
        return response;
    }

    @GetMapping("/processQueue")
    public Map<String, String> processQueue() {
        Queue<Channel> queue = service.getProcessingQueue();
        StringBuilder result = new StringBuilder();
        while (!queue.isEmpty()) {
            Channel c = queue.poll();
            result.append("Processing: ").append(c.getPatientName()).append("\n");
        }
        Map<String, String> response = new HashMap<>();
        response.put("message", result.toString());
        return response;
    }

    @GetMapping("/sort")
    public List<Channel> sortByDoctorName() {
        service.sortChannelsByDoctorName();
        return service.getAllChannels();
    }
}
