package hugh.practice.dynamicds.service;

import hugh.practice.dynamicds.annotation.DataSource;
import hugh.practice.dynamicds.mapper.OrderMapper;
import hugh.practice.dynamicds.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;

    /**
     * 添加
     *
     * 使用注解默认将数据插入到master
     */
    @DataSource
    public void addOrder() {
       orderMapper.addOrder();
    }

    /**
     * 查询
     * @return
     *
     * 使用注解指定到slave查询 没有注解则随机一个数据源
     */
//    @DataSource("slave")
    public Order getOrder() {
        Order order = orderMapper.getOrder();
        System.out.println("getOrder ===> "+order);
        return order;
    }
}
