package hugh.practice.dynamicds;

import hugh.practice.dynamicds.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class DynamicDsApplicationTests {
    @Autowired
    OrderService orderService;

    @Test
    void contextLoads() {
        orderService.addOrder();
        orderService.getOrder();
    }

}
