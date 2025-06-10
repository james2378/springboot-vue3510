package com.project.demo.controller;

import com.project.demo.entity.EmployeeUser;
import com.project.demo.service.EmployeeUserService;
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
 *职员用户：(EmployeeUser)表控制层
 *
 */
@RestController
@RequestMapping("/employee_user")
public class EmployeeUserController extends BaseController<EmployeeUser,EmployeeUserService> {

    /**
     *职员用户对象
     */
    @Autowired
    public EmployeeUserController(EmployeeUserService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        Map<String, String> mapjob_number = new HashMap<>();
        mapjob_number.put("job_number",String.valueOf(paramMap.get("job_number")));
        List listjob_number = service.select(mapjob_number, new HashMap<>()).getResultList();
        if (listjob_number.size()>0){
            return error(30000, "字段工号内容不能重复");
        }
        this.addMap(paramMap);
        return success(1);
    }

}
