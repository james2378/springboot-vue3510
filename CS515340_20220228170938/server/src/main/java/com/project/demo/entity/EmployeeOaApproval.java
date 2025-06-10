package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *职员OA审批：(EmployeeOaApproval)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "EmployeeOaApproval")
public class EmployeeOaApproval implements Serializable {

    //EmployeeOaApproval编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_oa_approval_id")
    private Integer employee_oa_approval_id;
   // 工号
   @Basic
    private Integer job_number;
   // 审批类型
   @Basic
    private String approval_type;
   // 申请人
   @Basic
    private String applicant;
   // 审批情况
   @Basic
    private String approval_status;
   // 审批人回复
   @Basic
    private String approver_reply;
   // 审批人
   @Basic
    private String approver;
   // 日期
   @Basic
    private Timestamp date;
   // 审批内容
   @Basic
    private String approval_content;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
