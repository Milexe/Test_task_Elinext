package ru.milexe.test_task.model;

import ru.milexe.test_task.entity.GroupEntity;

import java.util.List;
import java.util.stream.Collectors;

public class Group {
    private Long id;
    private String speciality;
    private long course;
    private long number;
    private List<Student> students;

    public static Group toModel(GroupEntity group){
        Group model = new Group();
        model.setId(group.getId());
        model.setCourse(group.getCourse());
        model.setNumber(group.getNumber());
        model.setSpeciality(group.getSpeciality());
        model.setStudents(group.getStudents().stream().map(Student::toModel).collect(Collectors.toList()));
        return model;
    }
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public long getCourse() {
        return course;
    }

    public void setCourse(long course) {
        this.course = course;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

}
