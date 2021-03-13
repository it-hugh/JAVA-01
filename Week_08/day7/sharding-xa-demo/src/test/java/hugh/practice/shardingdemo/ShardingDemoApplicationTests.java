package hugh.practice.shardingdemo;

import hugh.practice.shardingdemo.service.XAOrderService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
class ShardingDemoApplicationTests {
    @Autowired
    XAOrderService orderService;

    @Test
    void contextLoads() throws SQLException {
        orderService.addOrder();
    }

}
