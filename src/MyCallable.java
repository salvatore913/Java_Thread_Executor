import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {

    private final int name;

    public MyCallable(int name) {
        this.name = name;
    }

    public int getName() {
        return name;
    }

    @Override
    public Integer call() throws InterruptedException {
        int count = 0;
        while (count <= 3) {
            Thread.sleep(2000);
            System.out.println("Я поток " + getName() + ". " + "Всем привет!");
            count++;
        }
        return count;
    }
}