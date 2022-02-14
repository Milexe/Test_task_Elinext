package ru.milexe.test_task.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class TimetableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String day;

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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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
