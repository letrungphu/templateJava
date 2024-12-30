package com.template.template.auth.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface AuthMapper {
    List<HashMap<String, String>> getUserAndRole(String user_name);
}
