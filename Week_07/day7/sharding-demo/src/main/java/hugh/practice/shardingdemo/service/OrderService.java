package hugh.practice.shardingdemo.service;

import hugh.practice.shardingdemo.mapper.OrderMapper;
import hugh.practice.shardingdemo.model.Order;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;

    public Long addOrder() {
        //通过日志可以看出插入数据默认使用了主库ds0
        Order order = new Order();
        order.setUserId(1L);
        orderMapper.addOrder(order);
        System.out.println("addOrder ===> "+order.getId());
        return order.getId();
    }

    public Order getOrder() {
        //通过日志可以看出查询数据轮询了配置文件中的ds1和ds2
        Order order = orderMapper.getOrder(1L);
        System.out.println("getOrder ===> "+order);
        return order;
    }

    public Order addAndGet() {
        //强制使用主库 避免写完读问题
        HintManager.getInstance().setPrimaryRouteOnly();
        Long orderId = addOrder();
        Order order = orderMapper.getOrder(orderId);
        System.out.println("addAndGet ===> "+order);
        HintManager.clear();
        HintManager.getInstance().close();
        return order;
    }
}
