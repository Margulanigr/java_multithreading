import java.util.concurrent.locks.StampedLock;


public class StampedLockExercise_15 {

    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                int value = sharedResource.getValue();
                System.out.println("Reader thread: " + Thread.currentThread().getName() + ", value: " + value);
            }).start();
        }

        new Thread(() -> {
            sharedResource.setValue(42);
            System.out.println("Writer thread: " + Thread.currentThread().getName() + " set value to 42");
        }).start();
    }

    static class SharedResource {
        private int value;
        private final StampedLock lock = new StampedLock();

        public int getValue() {
            long stamp = lock.tryOptimisticRead();

            int currentValue = value;

            if (!lock.validate(stamp)) {
                stamp = lock.readLock();
                try {
                    currentValue = value;
                } finally {
                    lock.unlockRead(stamp);
                }
            }

            return currentValue;
        }

        public void setValue(int value) {
            long stamp = lock.writeLock();

            try {
                this.value = value;
            } finally {
                lock.unlockWrite(stamp);
            }
        }
    }
}