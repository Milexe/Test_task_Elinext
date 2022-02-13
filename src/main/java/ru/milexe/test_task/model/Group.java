package ru.milexe.test_task.model;

import ru.milexe.test_task.entity.GroupEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Group {
    private Long id;
    private String speciality;
    private long number;
    private List<Student> students = new ArrayList<Student>();

    public static Group toModel(GroupEntity group){
        Group model = new Group();
        model.setId(group.getId());
        model.setNumber(group.getNumber());
        model.setSpeciality(group.getSpeciality());
        if(group.getStudents() != null)
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

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

}
