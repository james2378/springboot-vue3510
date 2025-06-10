package com.project.demo.controller;

import com.project.demo.entity.ManagementTaskManagement;
import com.project.demo.service.ManagementTaskManagementService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *管理层任务管理：(ManagementTaskManagement)表控制层
 *
 */
@RestController
@RequestMapping("/management_task_management")
public class ManagementTaskManagementController extends BaseController<ManagementTaskManagement,ManagementTaskManagementService> {

    /**
     *管理层任务管理对象
     */
    @Autowired
    public ManagementTaskManagementController(ManagementTaskManagementService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
