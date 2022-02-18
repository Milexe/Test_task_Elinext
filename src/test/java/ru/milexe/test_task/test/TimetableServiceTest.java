package ru.milexe.test_task.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ru.milexe.test_task.entity.GroupEntity;
import ru.milexe.test_task.entity.LectureEntity;
import ru.milexe.test_task.entity.TimetableEntity;
import ru.milexe.test_task.exception.DataNotFoundException;
import ru.milexe.test_task.model.Lecture;
import ru.milexe.test_task.model.Timetable;
import ru.milexe.test_task.repository.GroupRepo;
import ru.milexe.test_task.repository.LectureRepo;
import ru.milexe.test_task.repository.TimetableRepo;
import ru.milexe.test_task.service.TimetableService;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TimetableServiceTest {
    @InjectMocks
    private TimetableService timetableService;
    @Mock
    private TimetableRepo timetableRepo;
    @Mock
    private GroupRepo groupRepo;
    @Mock
    private LectureRepo lectureRepo;

    @Test
    public void getTimetablesTest(){
        GroupEntity group = new GroupEntity();
        group.setNumber(1L);
        LectureEntity lecture = new LectureEntity();
        TimetableEntity timetable = new TimetableEntity(group, lecture);
        Mockito.when(timetableRepo.findAll()).thenReturn(Arrays.asList(
                timetable,
                timetable,
                timetable
        ));

        List<Timetable> allTimetables = timetableService.getTimetables();

        assertEquals(3, allTimetables.size());
    }
    @Test
    public void getTimetablesForGroupTest() throws DataNotFoundException {
        GroupEntity group = new GroupEntity(1,"math", 1);
        LectureEntity lecture = new LectureEntity();
        TimetableEntity timetable = new TimetableEntity(group, lecture);
        timetable.setDay("monday");
        Mockito.when(groupRepo.findByNumber(group.getNumber())).thenReturn(group);
        Mockito.when(timetableRepo.findAllByGroupAndDay(group,timetable.getDay())).thenReturn(Arrays.asList(
                timetable,
                timetable
        ));

        List<Timetable> allTimetables = timetableService.getTimetablesForGroup(group.getId(),timetable.getDay());

        assertEquals(2, allTimetables.size());
    }

    @Test
    public void createTimetableTest() throws Exception {
        LectureEntity lecture = new LectureEntity();
        lecture.setSubject("math");
        lecture.setTeacher("me");
        GroupEntity group = new GroupEntity(1,"math", 1);
        TimetableEntity timetable = new TimetableEntity(group, lecture);
        timetable.setDay("monday");
        Mockito.when(lectureRepo.findById(lecture.getId())).thenReturn(Optional.of(lecture));
        Mockito.when(groupRepo.findById(group.getId())).thenReturn(Optional.of(group));
        Mockito.when(timetableRepo.findById(timetable.getId())).thenReturn(Optional.empty());
        Mockito.when(timetableRepo.save(timetable)).thenReturn(timetable);

        Timetable res = timetableService.createTimetable(timetable, group.getId(), lecture.getId());

        assertEquals(Timetable.toModel(timetable).getId(), res.getId());
    }

    @Test
    public void deleteTimetableTest() throws Exception {
        LectureEntity lecture = new LectureEntity();
        lecture.setSubject("math");
        lecture.setTeacher("me");
        GroupEntity group = new GroupEntity(1,"math", 1);
        TimetableEntity timetable = new TimetableEntity(group, lecture);
        timetable.setDay("monday");
        Mockito.when(timetableRepo.findById(timetable.getId())).thenReturn(Optional.of(timetable));

        Timetable res = timetableService.deleteTimetable(timetable.getId());

        assertEquals(Timetable.toModel(timetable).getId(), res.getId());
    }


    @Test
    public void updateTimetableTest() throws Exception {
        LectureEntity lecture = new LectureEntity();
        lecture.setSubject("math");
        lecture.setTeacher("me");
        GroupEntity group = new GroupEntity(1,"math", 1);
        TimetableEntity timetable = new TimetableEntity(group, lecture);
        timetable.setDay("monday");
        Mockito.when(timetableRepo.findById(timetable.getId())).thenReturn(Optional.of(timetable));
        Mockito.when(lectureRepo.findById(lecture.getId())).thenReturn(Optional.of(lecture));
        Mockito.when(groupRepo.findByNumber(group.getNumber())).thenReturn(group);
        Mockito.when(timetableRepo.save(timetable)).thenReturn(timetable);

        Timetable res = timetableService.updateTimetable(Timetable.toModel(timetable));

        assertEquals(Timetable.toModel(timetable).getId(), res.getId());
    }
}
