package com.project.demo.controller;

import com.project.demo.entity.EmployeeOaApproval;
import com.project.demo.service.EmployeeOaApprovalService;
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
 *职员OA审批：(EmployeeOaApproval)表控制层
 *
 */
@RestController
@RequestMapping("/employee_oa_approval")
public class EmployeeOaApprovalController extends BaseController<EmployeeOaApproval,EmployeeOaApprovalService> {

    /**
     *职员OA审批对象
     */
    @Autowired
    public EmployeeOaApprovalController(EmployeeOaApprovalService service) {
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
