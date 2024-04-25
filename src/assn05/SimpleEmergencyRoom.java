package assn05;

import java.util.ArrayList;
import java.util.List;

public class SimpleEmergencyRoom {
    private List<Patient> patients;

    public SimpleEmergencyRoom() {
        patients = new ArrayList<>();
    }

    // TODO (Task 1): dequeue
    public Patient dequeue() {
        if (patients.isEmpty()) {
            return null;
        }

        Patient highestPriorityPatient = patients.get(0);
        int highestPriorityIndex = 0;

        for (int i = 1; i < patients.size(); i++) {
            Patient currentPatient = patients.get(i);
            if (currentPatient != null && currentPatient.getPriority() != null &&
                    currentPatient.getPriority().compareTo(highestPriorityPatient.getPriority()) > 0) {
                highestPriorityPatient = currentPatient;
                highestPriorityIndex = i;
            }
        }

        patients.remove(highestPriorityIndex);
        return highestPriorityPatient;
    }

    public <V, P> void addPatient(V value, P priority) {
        Patient patient = new Patient(value, (Integer) priority);
        patients.add(patient);
    }

    public <V> void addPatient(V value) {
        Patient patient = new Patient(value);
        patients.add(patient);
    }

    public List getPatients() {
        return patients;
    }

    public int size() {
        return patients.size();
    }

}
