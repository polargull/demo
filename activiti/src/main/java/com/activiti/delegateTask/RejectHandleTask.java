package com.activiti.delegateTask;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import com.activiti.entity.Test1;
import com.activiti.mapper.Test1Mapper;

@Component
public class RejectHandleTask implements JavaDelegate {
    @Resource
    Test1Mapper test1Mapper;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println(">>驳回处理>>, 流程id: " + execution.getProcessInstanceId());
        Test1 test = test1Mapper.selectByProcessId(execution.getProcessInstanceId());
        System.out.println(">>驳回处理>>, test: " + test.toString());
    }

}
