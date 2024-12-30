package com.template.template.api.mysql.service;

import com.template.template.api.mysql.mapper.SupplierManagementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SupplierManagementService {
    @Autowired
    SupplierManagementMapper supplierManagementMapper;

    public class Supplier {
        private String supplier_id = "";
        private String password = "";
        private String supplier_name = "";
        private String short_name = "";

        public String getSupplier_id() {
            return supplier_id;
        }

        public void setSupplier_id(String supplier_id) {
            this.supplier_id = supplier_id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSupplier_name() {
            return supplier_name;
        }

        public void setSupplier_name(String supplier_name) {
            this.supplier_name = supplier_name;
        }

        public String getShort_name() {
            return short_name;
        }

        public void setShort_name(String short_name) {
            this.short_name = short_name;
        }
    }

    public List<Supplier> getListSupplier(HashMap<String, Object> input) {
        List<Supplier> result = new ArrayList<>();
        List<HashMap<String, Object>> data = supplierManagementMapper.getListSupplier(input);
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                Supplier item = new Supplier();
                item.setSupplier_id(data.get(i).get("SUPPLIER_ID").toString());
                item.setPassword(data.get(i).get("PASSWORD").toString());
                item.setSupplier_name(data.get(i).get("SUPPLIER_NAME").toString());
                item.setShort_name(data.get(i).get("SHORT_NAME").toString());
                result.add(item);
            }
        } else {

        }
        return result;
    }
}
