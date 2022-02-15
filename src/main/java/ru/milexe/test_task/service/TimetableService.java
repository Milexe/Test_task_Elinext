package ru.milexe.test_task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.milexe.test_task.entity.GroupEntity;
import ru.milexe.test_task.entity.LectureEntity;
import ru.milexe.test_task.entity.StudentEntity;
import ru.milexe.test_task.entity.TimetableEntity;
import ru.milexe.test_task.exception.DataAlreadyExistsException;
import ru.milexe.test_task.exception.DataNotFoundException;
import ru.milexe.test_task.model.Lecture;
import ru.milexe.test_task.model.Student;
import ru.milexe.test_task.model.Timetable;
import ru.milexe.test_task.repository.GroupRepo;
import ru.milexe.test_task.repository.LectureRepo;
import ru.milexe.test_task.repository.TimetableRepo;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimetableService {
    @Autowired
    private TimetableRepo timetableRepo;

    @Autowired
    private GroupRepo groupRepo;

    @Autowired
    private LectureRepo lectureRepo;

    public Timetable createTimetable(TimetableEntity timetable,Long groupId,Long lectureId) throws DataNotFoundException, DataAlreadyExistsException {
        if(timetableRepo.findById(timetable.getId()).isPresent())
            throw new DataAlreadyExistsException("расписание уже существует");
        if(!groupRepo.findById(groupId).isPresent())
            throw new DataNotFoundException("группа не найдена");
        if(!lectureRepo.findById(lectureId).isPresent())
            throw new DataNotFoundException("лекция не найдена");
        GroupEntity group = groupRepo.findById(groupId).get();
        timetable.setGroup(group);
        LectureEntity lecture = lectureRepo.findById(lectureId).get();
        timetable.setLecture(lecture);
        return Timetable.toModel(timetableRepo.save(timetable));
    }

    public Timetable deleteTimetable(Long id) throws DataNotFoundException {
        if(!timetableRepo.findById(id).isPresent())
            throw new DataNotFoundException("расписание не найдено");
        TimetableEntity entity = timetableRepo.findById(id).get();
        Timetable deletedTimetable = Timetable.toModel(entity);
        timetableRepo.deleteById(id);
        return deletedTimetable;
    }

    public List<Timetable> getTimetables(){
        List<TimetableEntity> list = (List<TimetableEntity>) timetableRepo.findAll();
        List<Timetable> resultList = list.stream().map(Timetable::toModel).collect(Collectors.toList());
        return resultList;
    }

    public List<Timetable> getTimetablesForGroup(Long groupNumber, String date) throws DataNotFoundException {
        if(groupRepo.findByNumber(groupNumber)==null)
            throw new DataNotFoundException("группа не найдена");
        List<TimetableEntity> list = (List<TimetableEntity>) timetableRepo.findAllByGroupAndDay(groupRepo.findByNumber(groupNumber), date);
        List<Timetable> resultList = list.stream().map(Timetable::toModel).collect(Collectors.toList());
        return resultList;
    }

    public Timetable updateTimetable(Timetable timetable) throws DataNotFoundException {
        if(!timetableRepo.findById(timetable.getId()).isPresent())
            throw new DataNotFoundException("расписание не найдено");
        TimetableEntity updatedTimetable = timetableRepo.findById(timetable.getId()).get();
        updatedTimetable.setDay(timetable.getDay());
        updatedTimetable.setLecture(lectureRepo.findById(timetable.getId()).get());
        updatedTimetable.setGroup(groupRepo.findByNumber(timetable.getGroup()));
        return Timetable.toModel(timetableRepo.save(updatedTimetable));
    }
}
