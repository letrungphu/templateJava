package com.template.template.api.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.List;

@Mapper
@Qualifier("sqlSessionFactory2")
public interface SupplierManagementMapper {
    List<HashMap<String, Object>> getListSupplier(HashMap<String, Object> input);
}
