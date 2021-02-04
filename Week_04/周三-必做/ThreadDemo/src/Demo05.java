import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Demo05 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //和04类似，但此处为向线程池中提交Callable
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future future = executorService.submit(() -> getResult());
        System.out.println("结果为："+future.get());
        executorService.shutdown();
    }

    private static int getResult() {
        return 1;
    }

}
