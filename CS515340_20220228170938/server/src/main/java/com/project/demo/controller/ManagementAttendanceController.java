package com.project.demo.controller;

import com.project.demo.entity.ManagementAttendance;
import com.project.demo.service.ManagementAttendanceService;
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
 *管理层考勤：(ManagementAttendance)表控制层
 *
 */
@RestController
@RequestMapping("/management_attendance")
public class ManagementAttendanceController extends BaseController<ManagementAttendance,ManagementAttendanceService> {

    /**
     *管理层考勤对象
     */
    @Autowired
    public ManagementAttendanceController(ManagementAttendanceService service) {
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
