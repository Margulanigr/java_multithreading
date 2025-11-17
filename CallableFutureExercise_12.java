import java.util.concurrent.*;

public class CallableFutureExercise_12 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future < String > future = executor.submit(new Task());


        try {
            String result = future.get();

            System.out.println("Task result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }

    static class Task implements Callable < String > {
        @Override
        public String call() throws Exception {
            Thread.sleep(2000);
            return "Task completed!";
        }
    }
}
