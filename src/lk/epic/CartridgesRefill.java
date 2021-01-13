package lk.epic;

public class CartridgesRefill extends Thread {

    private Printer printer;

    public CartridgesRefill(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        while (true) {
            if (printer.printedInk == printer.inkCount) {
                printer.cartridgeRefill();
            }
        }
    }

}
