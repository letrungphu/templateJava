package com.template.template.api.oracle.controller;

import com.template.template.api.oracle.service.UserManagementService;
import com.template.template.config.BizRequest;
import com.template.template.config.BizResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userManagement")
public class UserManagementController {
    @Autowired
    UserManagementService userManagementService;

    @RequestMapping(value = "getListUser", method = RequestMethod.POST)
    @ResponseBody
    public BizResponse getUser(@RequestBody BizRequest request) throws Exception {
        BizResponse response = new BizResponse();
        try {
            response.addList(userManagementService.getListUser(request.getData()));
        } catch (Exception e) {

        }
        return response;
    }
}
