package ru.milexe.test_task.model;

import ru.milexe.test_task.entity.GroupEntity;
import ru.milexe.test_task.entity.StudentEntity;

public class Student {
    private long id;
    private String name;
    private Long group;


    public static Student toModel(StudentEntity student){
        Student model = new Student();
        model.setId(student.getId());
        model.setGroup(student.getGroup().getNumber());
        model.setName(student.getName());
        return model;
    }
    public Student() {
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGroup() {
        return group;
    }

    public void setGroup(Long group) {
        this.group = group;
    }
}
