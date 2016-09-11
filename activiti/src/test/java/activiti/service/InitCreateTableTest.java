package activiti.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.spring.ProcessEngineFactoryBean;
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
public class InitCreateTableTest {

    @Resource
    ProcessEngineFactoryBean processEngine;
    @Resource
    RepositoryService repositoryService;
    @Resource
    IdentityService identityService;
    @Resource
    RuntimeService runtimeService;
    @Resource
    Test1Mapper test1Mapper;
    @Resource
    TaskService taskService;

    // @Test
    public final void test1CreateTableAndAutoDeploy() {
        /**
         * DB_SCHEMA_UPDATE_TRUE：如果不存在表就创建表，存在就直接使用
         * DB_SCHEMA_UPDATE_FALSE：如果不存在表就抛出异常
         * DB_SCHEMA_UPDATE_CREATE_DROP：每次都先删除表，再创建新的表
         */
        assertNotNull(processEngine);
        assertNotNull(repositoryService);
        long count = repositoryService.createProcessDefinitionQuery().count();
        assertEquals(1, count);
        System.out.println("test1");
    }

    // @Test
    public void test2Test1() {
        Test1 baoxiao = test1Mapper.selectById(1l);
        assertEquals(1, baoxiao.getId());
        test1Mapper.updateProcess(baoxiao.getId(), "100");
        Test1 baoxiao2 = test1Mapper.selectById(1l);
        assertEquals("100", baoxiao2.getProcessId());
    }

//    @Test
    public void test2StartProcess() {
        Test1 baoxiao = test1Mapper.selectById(1l);
        identityService.setAuthenticatedUserId("fuwei2");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess", String.valueOf(baoxiao.getId()));
        test1Mapper.updateProcess(baoxiao.getId(), processInstance.getProcessInstanceId());

        List<Task> taskLst = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).list();
        for (Task task : taskLst) {
            System.out.println(task.getId() + " " + task.getName());
            task.setCategory("zhuanyuan");
            taskService.saveTask(task);
        }
    }

    @Test
    public void test3GetTask() {
        List<Task> taskLst = taskService.createTaskQuery().taskAssignee("wanglin").list();
        for (Task task : taskLst) {
            System.out.println(task.toString());
//            taskService.claim(task.getId(), "wanglin");
          taskService.complete(task.getId(), null);
        }
//        List<Task> taskLst = taskService.createTaskQuery().taskCategory("zhuanyuan").list();
//        for (Task task : taskLst) {
//            System.out.println(task.toString());
//            taskService.claim(task.getId(), "xiaonvhai");
//            taskService.complete(task.getId(), null);
//        }
    }
    // 246 添加意见
    // 295 签收
}
