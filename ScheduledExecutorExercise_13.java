import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorExercise_13 {

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        executor.schedule(new Task(), 2, TimeUnit.SECONDS);

        executor.scheduleAtFixedRate(new Task(), 3, 5, TimeUnit.SECONDS);

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("Task executed at: " + new Date());
        }
    }
}