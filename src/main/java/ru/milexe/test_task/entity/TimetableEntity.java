package ru.milexe.test_task.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class TimetableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date day_of_week;

    @ManyToOne
    @JoinColumn(name="group_id")
    private GroupEntity group;

    @ManyToOne
    @JoinColumn(name="lecture_id")
    private LectureEntity lecture;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(Date day_of_week) {
        this.day_of_week = day_of_week;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public LectureEntity getLecture() {
        return lecture;
    }

    public void setLecture(LectureEntity lecture) {
        this.lecture = lecture;
    }

    public TimetableEntity() {
    }
}
