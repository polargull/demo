package ssm.unit.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssm.entity.SubOrder;
import com.ssm.mapper.order.SubOrderMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/spring-dao.xml" })
public class SubOrderMapperTest {
    @Resource
    SubOrderMapper subOrderMapper;

    @Test
    public void testGetById() {
        SubOrder subOrder = subOrderMapper.getById(2l);
        System.out.println("id:" + subOrder.getId() + " pid:" + subOrder.getProductId() + " orderId:" + subOrder.getOrderId());
        System.out.println(subOrder.getOrder().getTotalPrice() + " " + subOrder.getOrder().getUser().getId());
    }

}
