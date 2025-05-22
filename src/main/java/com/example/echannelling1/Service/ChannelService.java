package com.example.echannelling1.Service;

import com.example.echannelling1.Classes.Channel;

import java.io.*;
import java.util.*;

public class ChannelService {
    private final List<Channel> channelList = new ArrayList<>();
    private final String FILE_PATH = "channel.txt";

    public ChannelService() {
        loadFromFile();
    }

    public void addChannel(Channel c) {
        channelList.add(c);
        saveToFile();
    }



    public boolean deleteChannel(String id) {
        boolean removed = channelList.removeIf(c -> c.getId().equals(id));
        if (removed) saveToFile();
        return removed;
    }

    public List<Channel> getAllChannels() {
        return channelList;
    }

    public Queue<Channel> getProcessingQueue() {
        return new LinkedList<>(channelList);
    }

    public void sortChannelsByDoctorName() {
        channelList.sort(Comparator.comparing(Channel::getDoctorName));
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Channel c : channelList) {
                writer.write(c.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(",");
                    if (parts.length == 6) {
                        // Old format: id,doctorName,specialization,date,time,patientName
                        channelList.add(new Channel(
                            parts[0], // id
                            "Not provided", // hospital
                            parts[1], // doctorName
                            parts[2], // specialization
                            parts[3], // date
                            parts[4], // time
                            parts[5], // patientName
                            "Not provided", // patientAge
                            "Not provided", // patientGender
                            "Not provided", // patientNIC
                            "Not provided", // patientPhone
                            "Not provided", // patientEmail
                            "Not provided", // patientAddress
                            "Not provided", // medicalHistory
                            "Not provided", // currentMedications
                            "Not provided", // allergies
                            "Not provided"  // paymentMethod
                        ));
                    } else if (parts.length == 17) {
                        // New format with all fields
                        channelList.add(Channel.fromFileString(line));
                    } else {
                        System.err.println("Skipping invalid line: " + line);
                    }
                } catch (Exception e) {
                    System.err.println("Error processing line: " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
