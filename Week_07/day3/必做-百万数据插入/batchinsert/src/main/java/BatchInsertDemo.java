import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class BatchInsertDemo {
    private static final String URL = "jdbc:mysql://localhost:3306/demo_db?serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "youmeng521";

    public static void main(String[] args) throws SQLException {
        System.out.println("---start---");
        //分50次每次插入20000条 本机测试约12s-13s
//        addMany1(20000,50); //测试请打开注释
        //分50次每次插入20000条 使用PreparedStatement 本机测试约12s-13s
//        addMany2(20000,50); //测试请打开注释
        //多线程调用方法2 本机测试约8s-9s
        addMany3(); //测试请打开注释
    }

    /**
     * 拼接sqlvalues后的部分
     * @param length 数据个数
     * @return
     */
    private static String getValuesPart(int length) {
        String val = "(1,2,3,4,5,'2021-03-03 12:00:00',0,'')";
        StringBuilder sb = new StringBuilder(val);
        for(int i = 0;i<length-1;i++) {
            sb.append(",");
            sb.append(val);
        }
        return sb.toString();
    }

    /**
     * 插入一捆数据
     * @param length  一捆包含的数据条数
     * @return
     * @throws SQLException
     */
    public static int addChunk(int length,Connection conn) throws SQLException {
        String sql = "insert into t_order (f_user_id, f_product_id, f_order_num, f_product_count, f_total_price,f_create_time, f_status, f_remarks) values"+getValuesPart(length);
//        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
            try (Statement statement = conn.createStatement()) {
                return statement.executeUpdate(sql);
            }
//        }
    }



    /**
     * 批量添加-1
     * 分50次插入
     * @throws SQLException
     */
    public static void addMany1(int chunkCap,int chunkNum) throws SQLException {
        long startMs = System.currentTimeMillis();
        Connection conn = getConnection();
        try {
           for(int i = 0;i<chunkNum;i++) {
               addChunk(chunkCap,conn);
           }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        long endMs = System.currentTimeMillis();
        System.out.println("addMany1执行完毕耗时："+(endMs-startMs)/1000.0);
    }

    /**
     * 批量添加-2
     * 分50次插入 使用PreparedStatement
     * @throws SQLException
     */
    public static void addMany2(int chunkCap,int chunkNum) throws SQLException {
        long startMs = System.currentTimeMillis();
        int length = chunkCap;
        String sql = "insert into t_order (f_user_id, f_product_id, f_order_num, f_product_count, f_total_price,f_create_time, f_status, f_remarks) values"+getValuesPart(length);
        //获取连接
        Connection conn = getConnection();
        try {
            conn.setAutoCommit(false);
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for(int i = 0;i<chunkNum;i++) {
                    ps.addBatch();
                }
                ps.executeBatch();
                conn.commit();
            }
        }finally {
            conn.setAutoCommit(true);
            conn.close();
        }
        long endMs = System.currentTimeMillis();
        System.out.println("addMany2执行完毕耗时："+(endMs-startMs)/1000.0);
    }

    /**
     * 批量添加-3
     * 多线程调用方法2
     * @throws SQLException
     */
    public static void addMany3(){
        long startMs = System.currentTimeMillis();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2,() -> {
            long endMs = System.currentTimeMillis();
            System.out.println("addMany3执行完毕耗时："+(endMs-startMs)/1000.0);
        });
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Runnable runnable = () -> {
            try {
                addMany2(10000,50);
                cyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        };
        executorService.submit(runnable);
        executorService.submit(runnable);
        executorService.shutdown();
    }

    /**
     * 获取连接
     * @return
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }
    /**
     * 使用Hikari获取连接
     * @return
     */
    public static Connection getHikariConnection() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(URL);
        hikariConfig.setUsername(USER_NAME);
        hikariConfig.setPassword(PASSWORD);
        hikariConfig.setAutoCommit(true);
        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "256");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "512");
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        try {
            return hikariDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
