public class Demo01 {
    public static void main(String[] args) {
        final int[] result = {0};
        Thread t = new Thread(() -> result[0] = getResult());
        t.start();
        try {
            //使用join方法阻塞当前线程，等待t线程执行完毕
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结果为："+ result[0]);
    }

    private static int getResult() {
        return 1;
    }
}
