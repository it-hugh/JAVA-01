import java.util.concurrent.*;

public class Demo08 {
    public static void main(String[] args) {
        final int parties = 1;
        final int[] result = {0};
        final boolean[] ok = {false};
        CyclicBarrier cyclicBarrier = new CyclicBarrier(parties,() -> ok[0] = true);
        Thread t = new Thread(() -> {
            result[0] = getResult();
            try {
                cyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        while (!ok[0]) {
           //等待CyclicBarrier所有线程调用完毕
        }
        System.out.println("结果为："+result[0]);
    }

    private static int getResult() {
        return 1;
    }
}
