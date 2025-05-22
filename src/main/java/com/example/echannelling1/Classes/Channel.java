package com.example.echannelling1.Classes;

public class Channel {
    private String id;
    private String hospital;
    private String doctorName;
    private String specialization;
    private String date;
    private String time;
    private String patientName;
    private String patientAge;
    private String patientGender;
    private String patientNIC;
    private String patientPhone;
    private String patientEmail;
    private String patientAddress;
    private String medicalHistory;
    private String currentMedications;
    private String allergies;
    private String paymentMethod;

    public Channel(String id, String hospital, String doctorName, String specialization, String date, String time,
                  String patientName, String patientAge, String patientGender, String patientNIC, String patientPhone,
                  String patientEmail, String patientAddress, String medicalHistory, String currentMedications,
                  String allergies, String paymentMethod) {
        this.id = id;
        this.hospital = hospital;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.date = date;
        this.time = time;
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.patientGender = patientGender;
        this.patientNIC = patientNIC;
        this.patientPhone = patientPhone;
        this.patientEmail = patientEmail;
        this.patientAddress = patientAddress;
        this.medicalHistory = medicalHistory;
        this.currentMedications = currentMedications;
        this.allergies = allergies;
        this.paymentMethod = paymentMethod;
    }

    public String getId() { return id; }

    public String getDoctorName() { return doctorName; }

    public String getPatientName() { return patientName; }


    public String toFileString() {
        return String.join(",", id, hospital, doctorName, specialization, date, time,
                patientName, patientAge, patientGender, patientNIC, patientPhone,
                patientEmail, patientAddress, medicalHistory, currentMedications,
                allergies, paymentMethod);
    }

    public static Channel fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length != 17) {
            throw new IllegalArgumentException("Invalid channel record: " + line);
        }
        return new Channel(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5],
                parts[6], parts[7], parts[8], parts[9], parts[10], parts[11],
                parts[12], parts[13], parts[14], parts[15], parts[16]);
    }
}
