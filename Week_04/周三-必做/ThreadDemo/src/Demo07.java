import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo07 {
    public static void main(String[] args) {
        final int[] result = {0};
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            result[0] = getResult();
            latch.countDown();
        });
        executorService.shutdown();
        try {
            //通过CountDownLatch等待线程执行完毕
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结果为："+result[0]);
    }

    private static int getResult() {
        return 1;
    }
}
