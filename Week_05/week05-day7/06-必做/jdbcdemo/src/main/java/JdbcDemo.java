import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;

public class JdbcDemo {
    private static final String URL = "jdbc:mysql://localhost:3306/demo_db?serverTimezone=Asia/Shanghai";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "111111";

    public static void main(String[] args) throws SQLException {
        add("user01");
        query("user01");

        update("user01","user02");
        query("user01");
        query("user02");

        delete("user02");
        query("user02");

        addAndUpdate();
        addMany();

    }

    public static int add(String userName) throws SQLException {
        String sql = "INSERT INTO user (name) VALUES ('" + userName + "')";
        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
            try (Statement statement = conn.createStatement()) {
                return statement.executeUpdate(sql);
            }
        }
    }

    public static int delete(String userName) throws SQLException {
        String sql = "DELETE FROM `user` WHERE name = '"+userName+"'";
        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
            try (Statement statement = conn.createStatement()) {
                return statement.executeUpdate(sql);
            }
        }
    }

    public static int update(String oldName,String newName)throws SQLException {
        String sql = "UPDATE `user` SET name='"+ newName + "' WHERE name = '"+oldName+"'";
        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
            try (Statement statement = conn.createStatement()) {
                return statement.executeUpdate(sql);
            }
        }
    }

    public static void query(String userName) throws SQLException {
        String sql = "SELECT * FROM `user` WHERE name LIKE '%" + userName + "%'";
        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
            try (Statement statement = conn.createStatement()) {
                try (ResultSet rs = statement.executeQuery(sql)) {
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        String uname = rs.getString(2);
                        System.out.println(id+"-"+uname);
                    }
                }
            }
        }
    }


    /**
     * 使用事务对增加和更新操作优化
     * @throws SQLException
     */
    public static void addAndUpdate() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        try {
            conn.setAutoCommit(false);
            add("newUser");
            update("newUser","updatedUser");
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
    }

    /**
     * 批量添加
     * @throws SQLException
     */
    public static void addMany() throws SQLException {
        String sql = "INSERT INTO `user`(name) VALUES (?)";
        //使用Hikari获取连接
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                for(int i=0;i<5;i++) {
                    ps.setString(1, "user" + i);
                    ps.addBatch();
                }
                ps.executeBatch();
            }
        }
    }

    /**
     * 使用Hikari获取连接
     * @return
     */
    public static Connection getConnection() {
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
