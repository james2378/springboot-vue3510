package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *管理层任务管理：(ManagementTaskManagement)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ManagementTaskManagement")
public class ManagementTaskManagement implements Serializable {

    //ManagementTaskManagement编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "management_task_management_id")
    private Integer management_task_management_id;
   // 任务标题
   @Basic
    private String task_title;
   // 管理员工号
   @Basic
    private Integer management_employee_number;
   // 姓名
   @Basic
    private String full_name;
   // 任务状态
   @Basic
    private String task_status;
   // 截止日期
   @Basic
    private Timestamp closing_date;
   // 任务详情
   @Basic
    private String task_details;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
