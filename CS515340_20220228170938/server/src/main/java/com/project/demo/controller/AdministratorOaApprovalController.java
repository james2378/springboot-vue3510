package com.project.demo.controller;

import com.project.demo.entity.AdministratorOaApproval;
import com.project.demo.service.AdministratorOaApprovalService;
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
 *管理员OA审批：(AdministratorOaApproval)表控制层
 *
 */
@RestController
@RequestMapping("/administrator_oa_approval")
public class AdministratorOaApprovalController extends BaseController<AdministratorOaApproval,AdministratorOaApprovalService> {

    /**
     *管理员OA审批对象
     */
    @Autowired
    public AdministratorOaApprovalController(AdministratorOaApprovalService service) {
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
