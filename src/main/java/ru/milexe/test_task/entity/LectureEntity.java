package ru.milexe.test_task.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class LectureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String subject;
    private String teacher;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lecture")
    private List<TimetableEntity> timetables;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public List<TimetableEntity> getTimetables() {
        return timetables;
    }

    public void setTimetables(List<TimetableEntity> timetables) {
        this.timetables = timetables;
    }

    public LectureEntity() {
    }
}
