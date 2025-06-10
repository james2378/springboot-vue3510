package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *笔记心得：(NotesAndExperience)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "NotesAndExperience")
public class NotesAndExperience implements Serializable {

    //NotesAndExperience编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notes_and_experience_id")
    private Integer notes_and_experience_id;
   // 类型
   @Basic
    private String type;
   // 分享内容
   @Basic
    private String share_content;
   // 分享人
   @Basic
    private String sharer;
   // 附件
   @Basic
    private String enclosure;
   // 备注
   @Basic
    private String remarks;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
