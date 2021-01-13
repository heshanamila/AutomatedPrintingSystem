package lk.epic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer(); //Printer Object for Students to Print
        printer.start();

        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 5; i++) {
            Thread student = new Thread(new Student(printer));
            student.setName("Student " + i); //set Thread Name
            pool.execute(student);
        }
        pool.shutdown();
    }
}
