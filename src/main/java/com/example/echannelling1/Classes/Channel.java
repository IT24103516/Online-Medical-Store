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
    public void setId(String id) { this.id = id; }

    public String getHospital() { return hospital; }
    public void setHospital(String hospital) { this.hospital = hospital; }

    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getPatientAge() { return patientAge; }
    public void setPatientAge(String patientAge) { this.patientAge = patientAge; }

    public String getPatientGender() { return patientGender; }
    public void setPatientGender(String patientGender) { this.patientGender = patientGender; }

    public String getPatientNIC() { return patientNIC; }
    public void setPatientNIC(String patientNIC) { this.patientNIC = patientNIC; }

    public String getPatientPhone() { return patientPhone; }
    public void setPatientPhone(String patientPhone) { this.patientPhone = patientPhone; }

    public String getPatientEmail() { return patientEmail; }
    public void setPatientEmail(String patientEmail) { this.patientEmail = patientEmail; }

    public String getPatientAddress() { return patientAddress; }
    public void setPatientAddress(String patientAddress) { this.patientAddress = patientAddress; }

    public String getMedicalHistory() { return medicalHistory; }
    public void setMedicalHistory(String medicalHistory) { this.medicalHistory = medicalHistory; }

    public String getCurrentMedications() { return currentMedications; }
    public void setCurrentMedications(String currentMedications) { this.currentMedications = currentMedications; }

    public String getAllergies() { return allergies; }
    public void setAllergies(String allergies) { this.allergies = allergies; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

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
