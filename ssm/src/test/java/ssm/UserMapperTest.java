package ssm;


import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssm.entity.User;
import com.ssm.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/spring-dao.xml" })
public class UserMapperTest {
    @Resource
    UserMapper userMapper;

    // @Test
    // public final void testInsert() {
    // User u = new User("fuwei", "123");
    // userMapper.insert(u);
    // }
    //
    // @Test
    public void testUpdate() {
        User u = new User("fuwei1", "fuwei123");
        u.setId(1l);
        userMapper.update(u);
    }

    //
    // @Test
    // public void testDelete() {
    // userMapper.delete(2l);
    // User u = userMapper.getById(2l);
    // Assert.isNull(u);
    // }
    //
    @Test
    public void testGetById() {
        User u = userMapper.getById(2l);
        System.out.println(u.getId() + " " + u.getName() + " " + u.getPwd());
        System.out.println(u.getUserExt().getAccountNo() + " " + new DateTime(u.getUserExt().getBirthDate()).toString("yyyy-MM-dd HH:mm:ss"));
    }

    // @Test
    public void testListUser() {
        List<User> list = userMapper.list(new HashMap<String, Object>() {
            private static final long serialVersionUID = 4265469637185981657L;
            {
                put("sort", "id desc");
                put("offset", 0);
                put("size", 2);
            }
        });
        for (User u : list) {
            System.out.println(u.getName());
        }
    }

    // @Test
    public void testListAll() {
        List<User> list = userMapper.list();
        for (User u : list) {
            System.out.println(u.getName());
        }
    }

    // @Test
    public void testCount() {
        int count = userMapper.count(new HashMap<String, Object>() {
            private static final long serialVersionUID = 4265469637185981657L;
            {
                put("name", "fuwei");
            }
        });
        System.out.println(count);
    }
}
