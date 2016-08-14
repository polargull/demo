package ssm.unit.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssm.entity.Order;
import com.ssm.entity.SubOrder;
import com.ssm.mapper.order.OrderMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/spring-dao.xml" })
public class OrderMapperTest {
    @Resource
    OrderMapper orderMapper;

    @Test
    public void testGetById() {
        Order order = orderMapper.getById(1l);
        System.out.println("id:" + order.getId() + " pid:" + order.getTotalPrice());
        System.out.println(order.getSubLst().size());
        for (SubOrder so : order.getSubLst()) {
            System.out.println("suborderId:"+so.getId() + " suborderNums:" + so.getNums() + " subOrderProductId:" + so.getProductId());
        }
    }

}
