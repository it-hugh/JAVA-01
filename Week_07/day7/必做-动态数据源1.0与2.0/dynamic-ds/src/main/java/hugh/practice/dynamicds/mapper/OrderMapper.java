package hugh.practice.dynamicds.mapper;

import hugh.practice.dynamicds.model.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderMapper {
    @Insert("insert into t_order (f_user_id, f_product_id, f_order_num, f_product_count, f_total_price,f_create_time, f_status, f_remarks) values (10,2,3,4,5,'2021-03-03 12:00:00',0,'')")
    void addOrder();

    @Select("select * from t_order where id = 1")
    @Results({
            @Result(column="id", property="id"),
            @Result(column="f_user_id", property="userId")
    })
    Order getOrder();
}
