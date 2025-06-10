package com.project.demo.controller;

import com.project.demo.entity.NotesAndExperience;
import com.project.demo.service.NotesAndExperienceService;
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
 *笔记心得：(NotesAndExperience)表控制层
 *
 */
@RestController
@RequestMapping("/notes_and_experience")
public class NotesAndExperienceController extends BaseController<NotesAndExperience,NotesAndExperienceService> {

    /**
     *笔记心得对象
     */
    @Autowired
    public NotesAndExperienceController(NotesAndExperienceService service) {
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
