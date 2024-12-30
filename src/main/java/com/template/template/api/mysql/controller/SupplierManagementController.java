package com.template.template.api.mysql.controller;

import com.template.template.api.mysql.service.SupplierManagementService;
import com.template.template.config.BizRequest;
import com.template.template.config.BizResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supplierManagement")
public class SupplierManagementController {
    @Autowired
    SupplierManagementService supplierManagementService;

    @RequestMapping(value = "getListSupplier", method = RequestMethod.POST)
    @ResponseBody
    public BizResponse getListSupplier(@RequestBody BizRequest request) throws Exception {
        System.out.println(">>> Start process getListSupplier");
        BizResponse response = new BizResponse();
        try {
            response.addList(supplierManagementService.getListSupplier(request.getData()));
        } catch (Exception e) {

        }
        return response;
    }
}
