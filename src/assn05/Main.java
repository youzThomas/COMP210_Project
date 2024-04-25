package assn05;

public class Main {

    public static void main(String[] args) {
        testP1();
        testP2();
        testP3();
        testP4();
    }

    // test Part 1
    public static void testP1(){
        /*
        Part 1 - Write some tests to convince yourself that your code for Part 1 is working
         */
        SimpleEmergencyRoom rm01 = new SimpleEmergencyRoom();

        rm01.addPatient("Thomas", 4);
        rm01.addPatient("Josh", 1);
        rm01.addPatient("Viktoria", 3);

        System.out.println("Dequeued: " + rm01.dequeue().getValue());
        System.out.println("Dequeued: " + rm01.dequeue().getValue());
        System.out.println("Dequeued: " + rm01.dequeue().getValue());
        System.out.println("Dequeued: " + rm01.dequeue());
    }

    // test Part 2
    public static void testP2(){
       /*
        Part 2 - Write some tests to convince yourself that your code for Part 2 is working
         */
        MaxBinHeapER<String, Integer> maxHeap = new MaxBinHeapER<>();

        maxHeap.enqueue("Patient1", 3);
        maxHeap.enqueue("Patient2", 2);
        maxHeap.enqueue("Patient3", 5);
        maxHeap.enqueue("Patient4", 1);
        maxHeap.enqueue("Patient5", 4);
    }

    /*
    Part 3
     */
    public static void testP3(){
        MaxBinHeapER transfer = new MaxBinHeapER(makePatients());
        Prioritized[] arr = transfer.getAsArray();
        for(int i = 0; i < transfer.size(); i++) {
            System.out.println("Value: " + arr[i].getValue()
                    + ", Priority: " + arr[i].getPriority());
        }
    }

    /*
    Part 4
     */
    public static void testP4() {
               /*
        Part 4 - Write some tests to convince yourself that your code for Part 4 is working
         */
        double[] results = compareRuntimes();
        System.out.println("Time taken to dequeue all patients in SimpleEmergencyRoom: " + results[0] + " nanoseconds");
        System.out.println("Average time taken to dequeue a patient in SimpleEmergencyRoom: " + results[1] + " nanoseconds");
        System.out.println("Time taken to dequeue all patients in MaxBinHeapER: " + results[2] + " nanoseconds");
        System.out.println("Average time taken to dequeue a patient in MaxBinHeapER: " + results[3] + " nanoseconds");
    }


    public static void fillER(MaxBinHeapER complexER) {
        for(int i = 0; i < 100000; i++) {
            complexER.enqueue(i);
        }
    }
    public static void fillER(SimpleEmergencyRoom simpleER) {
        for(int i = 0; i < 100000; i++) {
            simpleER.addPatient(i);
        }
    }

    public static Patient[] makePatients() {
        Patient[] patients = new Patient[10];
        for(int i = 0; i < 10; i++) {
            patients[i] = new Patient(i);
        }
        return patients;
    }

    public static double[] compareRuntimes() {
        double[] results = new double[4];

        SimpleEmergencyRoom simplePQ = new SimpleEmergencyRoom();
        fillER(simplePQ);

        long start = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            simplePQ.dequeue();
        }
        long end = System.nanoTime();
        long duration = end - start;

        results[0] = duration;
        results[1] = (double) duration/ 100000.0;

        MaxBinHeapER binHeap = new MaxBinHeapER();
        fillER(binHeap);

        start = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            binHeap.dequeue();
        }
        end = System.nanoTime();
        duration = end- start;
        results[2] = duration;
        results[3] = (double) duration/ 100000.0;

        return results;
    }


}
