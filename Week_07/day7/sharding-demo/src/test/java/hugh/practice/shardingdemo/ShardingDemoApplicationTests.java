package hugh.practice.shardingdemo;

import hugh.practice.shardingdemo.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ShardingDemoApplicationTests {
    @Autowired
    OrderService orderService;

    @Test
    void contextLoads() {
        orderService.addOrder();
        orderService.addAndGet();
        orderService.getOrder();
        orderService.getOrder();
        orderService.getOrder();
    }

}
