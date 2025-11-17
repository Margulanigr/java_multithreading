import java.util.concurrent.Semaphore;

class SharedResource {
    private final Semaphore semaphore = new Semaphore(2);

    public void useResource() {
        try {
            semaphore.acquire(); // захват разрешения
            System.out.println(Thread.currentThread().getName() + " получил доступ");

            Thread.sleep(300);

            System.out.println(Thread.currentThread().getName() + " освобождает ресурс");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release(); // освобождение разрешения
        }
    }
}

public class Semaphore_4 {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Runnable task = () -> {
            for (int i = 0; i < 3; i++) {
                resource.useResource();
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");
        Thread t3 = new Thread(task, "Thread-3");
        Thread t4 = new Thread(task, "Thread-4");
        Thread t5 = new Thread(task, "Thread-5");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
