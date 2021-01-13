package lk.epic;

public class PaperRefill extends Thread {

    private Printer printer;

    public PaperRefill(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        while (true) {
            if (printer.printedPapers == printer.paperCount) {
                printer.paperRefill();
            }
        }
    }

}
