package com.template.template.api.oracle.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface UserManagementMapper {
    List<HashMap<String, Object>> getListUser(HashMap<String, Object> input);
}
