import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Создаю потоки...");
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        int result = 0;
        List<MyCallable> futureList = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            futureList.add(new MyCallable(i));
        }
        try {
            List<Future<Integer>> futures = pool.invokeAll(futureList);
            for (Future<Integer> future : futures) {
                try {
                    result = result + future.get();
                } catch (ExecutionException ee) {
                    ee.printStackTrace();
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        System.out.println("Выведенно сообщений по всем задачам " + result);

        try {
            result = pool.invokeAny(futureList);
        } catch (
                ExecutionException ee) {
            ee.printStackTrace();
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Выведенно сообщений по самой быстрой задаче " + result);
        System.out.println("Завершаю все потоки...");
        pool.shutdown();
    }
}