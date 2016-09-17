package com.activiti.listener;

import org.activiti.engine.delegate.BpmnError;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

/**
 * 请假会签任务监听器，当会签任务完成时统计同意的数量
 *
 * @author henryyan
 */
@Component
public class ApartmentApproveCompleteListener implements TaskListener {

    private static final long serialVersionUID = 1L;

    @Override
    public void notify(DelegateTask delegateTask) {
        String reject = (String) delegateTask.getVariable("reject");
        if ("1".equals(reject)) {
            System.out.println(">>>>>>>>>>>>>部门领导驳回");
        }
//        throw new BpmnError("oa_err");
    }

}
