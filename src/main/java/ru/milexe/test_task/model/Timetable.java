package ru.milexe.test_task.model;

import ru.milexe.test_task.entity.LectureEntity;
import ru.milexe.test_task.entity.TimetableEntity;

import java.sql.Date;

public class Timetable {
    private long id;
    private String day;
    private Long lectureId;
    private Long group;

    public Long getLectureId() {
        return lectureId;
    }

    public void setLectureId(Long lectureId) {
        this.lectureId = lectureId;
    }

    public static Timetable toModel(TimetableEntity entity){
        Timetable model = new Timetable();
        model.setId(entity.getId());
        model.setDay(entity.getDay());
        model.setGroup(entity.getGroup().getNumber());
        model.setLectureId(entity.getLecture().getId());
        return model;
    }

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

    public Long getGroup() {
        return group;
    }

    public void setGroup(Long group) {
        this.group = group;
    }

    public Timetable() {
    }
}
