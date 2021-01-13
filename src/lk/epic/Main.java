package lk.epic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer(); //Printer Object for Students to Print
        printer.start();

        Thread Student1 = new Thread(new Student(printer));
        Student1.setName("S1");
        Student1.start();

        Thread Student2 = new Thread(new Student(printer));
        Student1.setName("S2");
        Student1.start();

        Thread Student3= new Thread(new Student(printer));
        Student1.setName("S3");
        Student1.start();

        Thread Student4 = new Thread(new Student(printer));
        Student1.setName("S4");
        Student1.start();

        Thread Student5 = new Thread(new Student(printer));
        Student1.setName("S5");
        Student1.start();


    }
}
