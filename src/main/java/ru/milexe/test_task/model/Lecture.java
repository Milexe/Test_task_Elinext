package ru.milexe.test_task.model;

import ru.milexe.test_task.entity.LectureEntity;

import java.util.stream.Collectors;

public class Lecture{
        private long id;
        private String subject;
        private String teacher;

    public static Lecture toModel(LectureEntity lecture)
        {
            Lecture model = new Lecture();
            model.setId(lecture.getId());
            model.setSubject(lecture.getSubject());
            model.setTeacher(lecture.getTeacher());
            return model;
        }

    public Lecture() {
    }

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

}
