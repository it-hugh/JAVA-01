package hugh.practice.shardingdemo.service;

import hugh.practice.shardingdemo.mapper.OrderMapper;
import hugh.practice.shardingdemo.model.Order;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class XAOrderService {

    @Autowired
    OrderMapper orderMapper;

    @Transactional(rollbackFor = Exception.class)
    @ShardingTransactionType(TransactionType.XA)
    public void addOrder() throws SQLException {
        //先插入成功一条到ds0
        Order order1 = new Order();
        order1.setUserId(1L);
        orderMapper.addOrder(order1);
        System.out.println(order1.toString());
        //再插入一条到ds1
        Order order2 = new Order();
        order2.setUserId(2L);
        orderMapper.addOrder(order2);
        System.out.println(order2.toString());
        //模拟抛出异常查看是否都回滚
        throw new SQLException("mock transaction failed");
        //测试可看到两条数据均未插入
    }

}
