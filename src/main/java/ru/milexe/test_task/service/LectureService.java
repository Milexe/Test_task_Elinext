package ru.milexe.test_task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.milexe.test_task.entity.GroupEntity;
import ru.milexe.test_task.entity.LectureEntity;
import ru.milexe.test_task.entity.StudentEntity;
import ru.milexe.test_task.model.Lecture;
import ru.milexe.test_task.model.Student;
import ru.milexe.test_task.repository.GroupRepo;
import ru.milexe.test_task.repository.LectureRepo;
import ru.milexe.test_task.repository.StudentRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureService {
    @Autowired
    private LectureRepo lectureRepo;

    public Lecture createLecture(LectureEntity lecture){
        return Lecture.toModel(lectureRepo.save(lecture));
    }

    public Lecture getLecture(Long id) throws Exception {
        if(!lectureRepo.findById(id).isPresent())
            throw new Exception("лекция не найдена");
        return Lecture.toModel(lectureRepo.findById(id).get());
    }

    public List<Lecture> getLectures(){
        List<LectureEntity> list = (List<LectureEntity>) lectureRepo.findAll();
        List<Lecture> resultList = list.stream().map(Lecture::toModel).collect(Collectors.toList());
        return resultList;
    }

    public Lecture deleteLecture(Long id){
        Lecture deletedLecture = Lecture.toModel(lectureRepo.findById(id).get());
        lectureRepo.deleteById(id);
        return deletedLecture;
    }

    public Lecture updateLecture(LectureEntity lecture){
        // add lectureNotFound
        return Lecture.toModel(lectureRepo.save(lecture));
    }
}
