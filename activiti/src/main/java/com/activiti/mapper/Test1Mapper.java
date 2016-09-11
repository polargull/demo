package com.activiti.mapper;

import org.apache.ibatis.annotations.Param;

import com.activiti.entity.Test1;

public interface Test1Mapper {
    int insert(Test1 test);

    Test1 selectById(long id);

    void updateProcess(@Param("id") long id, @Param("processId") String processId);
    
    void updateStatus(@Param("id") long id, @Param("status") String status);
    
    Test1 selectByProcessId(String processId);
}