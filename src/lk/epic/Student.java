package lk.epic;

public class Student implements Runnable {

    private Printer printer;

    public Student(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
            while (Printer.IS_ALLOCATED) {

            }
            Printer.IS_ALLOCATED = true;
            printer.print(); // call print method in printer

    }

}
