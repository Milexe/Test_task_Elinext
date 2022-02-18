package ru.milexe.test_task.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String speciality;
    private long number;

    public GroupEntity(long id, String speciality, long number) {
        this.id = id;
        this.speciality = speciality;
        this.number = number;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private List<StudentEntity> students;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private List<TimetableEntity> timetables;

    public List<TimetableEntity> getTimetables() {
        return timetables;
    }

    public void setTimetables(List<TimetableEntity> timetables) {
        this.timetables = timetables;
    }

    public GroupEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public List<StudentEntity> getStudents() {
        return students;
    }

    public void setStudents(List<StudentEntity> students) {
        this.students = students;
    }
}
