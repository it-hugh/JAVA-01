import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Demo04 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //使用Callable获取线程返回结果
        FutureTask futureTask = new FutureTask(() -> getResult());
        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println("结果为："+futureTask.get());
    }

    private static int getResult() {
        return 1;
    }

}
