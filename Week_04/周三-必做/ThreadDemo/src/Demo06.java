import java.util.concurrent.CompletableFuture;

public class Demo06 {
    public static void main(String[] args) {
        Integer result = CompletableFuture.supplyAsync(() -> getResult()).join();
        System.out.println("结果为："+result);
    }

    private static int getResult() {
        return 1;
    }
}
