package activiti.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
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
    @Resource
    HistoryService historyService;
    final String PROCESS_DEF_KEY = "baoxiao2";

    /**
     * 启动部署流程定义
     */
    // @Test
    public final void test1CreateTableAndAutoDeploy() {
        assertNotNull(processEngine);
        assertNotNull(repositoryService);
        long count = repositoryService.createProcessDefinitionQuery().count();
        assertEquals(1, count);
        System.out.println("test1");
    }

    /**
     * 申请提交启动流程
     */
    // @Test
    public void test2StartProcess() {
        // 设置启动人
        identityService.setAuthenticatedUserId("fuwei1");
        // 启动流程和业务绑定
        Map<String, Object> vars = new HashMap<String, Object>();
        List<String> users = Arrays.asList("si.cong", "zhang.lin");
        vars.put("users", users);
        Test1 baoxiao = test1Mapper.selectById(1l);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_DEF_KEY, String.valueOf(baoxiao.getId()), vars);
        String processInsId = processInstance.getProcessInstanceId();
        test1Mapper.updateProcess(baoxiao.getId(), processInsId);
        setWaitApprove(processInsId);
    }

    /**
     * 查询待审批任务
     */
    // @Test
    public void test3GetWaitApproveTask() {
        List<Task> siCongtaskLst = taskService.createTaskQuery().taskAssignee("si.cong").list();
        for (Task task : siCongtaskLst) {
            System.out.println("si.cong:" + task.toString());
            complete(task);
        }
        List<Task> zLinTaskList = taskService.createTaskQuery().taskAssignee("zhang.lin").list();
        for (Task task : zLinTaskList) {
            System.out.println("zhang.lin:" + task.toString());
            complete(task);
        }
        // List<Task> taskLst =
        // taskService.createTaskQuery().taskCategory("zhuanyuan").list();
        // for (Task task : taskLst) {
        // System.out.println(task.toString());
        // taskService.claim(task.getId(), "xiaonvhai");
        // taskService.complete(task.getId(), null);
        // }
    }

    /**
     * 查询已审批任务
     */
    @Test
    public void test4GetAlreadyApproveTask() {
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey(PROCESS_DEF_KEY).singleResult();
        List<HistoricTaskInstance> lst = historyService.createHistoricTaskInstanceQuery().processDefinitionId(pd.getId()).taskDeleteReason("completed").taskAssignee("si.cong")
                .list();
        for (HistoricTaskInstance hiTask : lst) {
            System.out.println(hiTask.getProcessDefinitionId() + " >> " + hiTask.getProcessInstanceId() + " >> " + hiTask.toString() + " >> " + hiTask.getAssignee() + " >> "
                    + hiTask.getDeleteReason());
        }
    }

    // 246 添加意见
    // 295 签收

    private void complete(Task task) {
        taskService.complete(task.getId());
        String processInsId = task.getProcessInstanceId();
        setWaitApprove(processInsId);
    }

    private void setWaitApprove(String processInsId) {
        Test1 test = test1Mapper.selectByProcessId(processInsId);
        Task nextTask = taskService.createTaskQuery().processInstanceId(processInsId).singleResult();
        String status = "审批通过";
        if (nextTask != null) {
            status = "待" + nextTask.getAssignee() + "审批";
        }
        test1Mapper.updateStatus(test.getId(), status);
    }
}
