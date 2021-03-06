package hugh.practice.shardingdemo.mapper;

import hugh.practice.shardingdemo.model.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderMapper {
    @Insert("insert into t_order (f_user_id, f_product_id, f_order_num, f_product_count, f_total_price,f_create_time, f_status, f_remarks) "+
            "values "+
            "(#{order.userId},2,3,4,5,'2021-03-03 12:00:00',0,'')")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void addOrder(@Param("order") Order order);

    @Select("select * from t_order where id = #{id}")
    @Results({
            @Result(column="id", property="id"),
            @Result(column="f_user_id", property="userId")
    })
    Order getOrder(@Param("id") Long id);
}
