public class Demo03 {
    public static void main(String[] args) throws InterruptedException {
        final int[] result = {0};
        Thread t = new Thread(() -> result[0] = getResult());
        t.start();
        //循环检查结果是否返回
        while (result[0]==0) {
            System.out.println("等待");
            Thread.sleep(100);
        }
        System.out.println("结果为："+ result[0]);
    }

    private static int getResult() {
        return 1;
    }
}
