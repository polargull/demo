package activiti.mapper;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.activiti.entity.Test1;
import com.activiti.mapper.Test1Mapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/spring-dao.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test1Test {
    @Resource
    Test1Mapper test1Mapper;

    @Test
    public void test2Test1() {
        Test1 baoxiao = test1Mapper.selectById(1l);
        assertEquals(1, baoxiao.getId());
        test1Mapper.updateProcess(baoxiao.getId(), "100");
        Test1 baoxiao2 = test1Mapper.selectById(1l);
        assertEquals("100", baoxiao2.getProcessId());
    }

}
