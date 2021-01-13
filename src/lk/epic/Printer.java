package lk.epic;

public class Printer extends Thread {

    public static volatile boolean IS_ALLOCATED = false;
    public int paperCount = 250; //number of papers that tray can hold
    public int inkCount = 50; //number of cartridges that tray can hold
    public volatile int printedPapers = 0; //printed paper count
    public volatile int printedInk = 0; //used ink cartridges
    private volatile boolean isPaperFilled = false;
    private volatile boolean isInkFilled = false;
    private int printCount = 250; //size of print (number of pages to be print)

    private CartridgesRefill cartridgesRefill;
    private PaperRefill paperRefill;

    public Printer() {
        this.cartridgesRefill = new CartridgesRefill(this);
        this.paperRefill = new PaperRefill(this);
        this.cartridgesRefill.start();
        this.paperRefill.start();
    }

    public void paperRefill() {
        synchronized (this) {
            IS_ALLOCATED = true;
            printedPapers = 0; //reset printed paper count
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Papers Refilled ");
            isPaperFilled = true;
            this.notify();
        }
    }

    public void cartridgeRefill() {
        synchronized (this) {
            IS_ALLOCATED = true;
            printedInk = 0;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Cartridge Refilled");
            isInkFilled = true;
            this.notify();
        }
    }


    //printing
    public void print() {
        synchronized (this) {
            for (int i = 1; i <= printCount; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + i + " Page Printing."); // show Current Thread and Number of Print
                printedPapers++; // increment print count
                if (i % 10 == 0) {
                    printedInk++; // increment every 10 pages print
                }
                if (printedInk == inkCount | printedPapers == paperCount) {
                    if (printedInk == inkCount) {
                        //call cartridges refill Thread
                        try {
                            isInkFilled = false;
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    if (printedPapers == paperCount) {
                        //call papers refill Thread
                        try {
                            isPaperFilled = false;
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    Thread.sleep(100); // sleep 1s before print another paper
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (isPaperFilled || isInkFilled) {
                IS_ALLOCATED = false;
            }
        }

    }

}
