package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *管理层考勤：(ManagementAttendance)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ManagementAttendance")
public class ManagementAttendance implements Serializable {

    //ManagementAttendance编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "management_attendance_id")
    private Integer management_attendance_id;
   // 管理员工号
   @Basic
    private Integer management_employee_number;
   // 姓名
   @Basic
    private String full_name;
   // 签到
   @Basic
    private Timestamp sign_in;
   // 签退
   @Basic
    private Timestamp sign_out;
   // 出勤状态
   @Basic
    private String attendance_status;
   // 日期
   @Basic
    private Timestamp date;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
