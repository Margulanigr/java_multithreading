import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class Worker implements Runnable {
    private final CyclicBarrier barrier;
    private final int workerId;

    public Worker(int id, CyclicBarrier barrier) {
        this.workerId = id;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println("Worker " + workerId + " выполняет фазу 1...");
            Thread.sleep((long) (1000 * Math.random()));

            System.out.println("Worker " + workerId + " ждет остальных у барьера...");
            barrier.await();   // все потоки должны дойти сюда

            System.out.println("Worker " + workerId + " начал фазу 2!");
            Thread.sleep((long) (1000 * Math.random()));

            System.out.println("Worker " + workerId + " завершил работу.");

        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

public class CyclicBarrier_5 {
    public static void main(String[] args) {

        final int THREAD_COUNT = 3;

        CyclicBarrier barrier = new CyclicBarrier(
                THREAD_COUNT,
                () -> System.out.println("=== Все потоки достигли барьера, продолжаем! ===")
        );

        for (int i = 1; i <= THREAD_COUNT; i++) {
            new Thread(new Worker(i, barrier)).start();
        }
    }
}
