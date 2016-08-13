package com.ssm.mapper;

import java.util.List;
import java.util.Map;

public interface BaseMapper<T> {
    int insert(T obj);

    int update(T obj);

    int delete(long id);

    T getById(long id);

    /**
     * 
     * @param param
     * put("sort", "id desc");
       put("offset", 0);
       put("size", 2);
     * @return
     */
    List<T> list(Map<String, Object> param);

    int count(Map<String, Object> param);
    
    List<T> list();
    
    int count();
}
