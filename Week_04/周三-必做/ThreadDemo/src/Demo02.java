public class Demo02 {
    public static void main(String[] args) {
        final int[] result = {0};
        Thread t = new Thread(() -> result[0] = getResult());
        t.start();
        try {
            //当前线程sleep一个足够长的时间
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结果为："+ result[0]);
    }

    private static int getResult() {
        return 1;
    }
}
