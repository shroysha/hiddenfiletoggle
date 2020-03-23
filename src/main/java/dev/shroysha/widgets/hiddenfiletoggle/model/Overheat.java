package dev.shroysha.widgets.hiddenfiletoggle.model;


public class Overheat {

    public static void main(String[] args) {
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            new OverheatThread(i).start();
        }
    }

    private static class OverheatThread extends Thread {
        private final int num;

        public OverheatThread(int num) {
            this.num = num;
        }

        public void run() {
            long i = 0;
            //noinspection InfiniteLoopStatement
            while (true) {
                i++;
                System.out.println("Worker " + num + " says " + i);
            }
        }
    }
}
