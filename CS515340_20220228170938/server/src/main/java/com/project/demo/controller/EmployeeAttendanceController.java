package com.project.demo.controller;

import com.project.demo.entity.EmployeeAttendance;
import com.project.demo.service.EmployeeAttendanceService;
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
 *职员考勤：(EmployeeAttendance)表控制层
 *
 */
@RestController
@RequestMapping("/employee_attendance")
public class EmployeeAttendanceController extends BaseController<EmployeeAttendance,EmployeeAttendanceService> {

    /**
     *职员考勤对象
     */
    @Autowired
    public EmployeeAttendanceController(EmployeeAttendanceService service) {
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
