import java.util.concurrent.Phaser;

public class PhaserExercise_10 {

    public static void main(String[] args) {
        Phaser phaser = new Phaser(4);

        Thread thread1 = new Thread(new Worker(phaser, "Thread 1"));
        Thread thread2 = new Thread(new Worker(phaser, "Thread 2"));
        Thread thread3 = new Thread(new Worker(phaser, "Thread 3"));
        Thread thread4 = new Thread(new Worker(phaser, "Thread 4"));
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        phaser.awaitAdvance(phaser.getPhase());

        System.out.println("All threads have completed their tasks.");
    }

    static class Worker implements Runnable {
        private final Phaser phaser;
        private final String name;

        public Worker(Phaser phaser, String name) {
            this.phaser = phaser;
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name + " starting phase 1");
            phaser.arriveAndAwaitAdvance();

            System.out.println(name + " performing phase 1 tasks");

            phaser.arriveAndAwaitAdvance();

            System.out.println(name + " starting phase 2");
            phaser.arriveAndAwaitAdvance();

            System.out.println(name + " performing phase 2 tasks");

            phaser.arriveAndAwaitAdvance();

            System.out.println(name + " completed all phases");
            phaser.arriveAndDeregister();
        }
    }
}