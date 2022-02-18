package ru.milexe.test_task.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ru.milexe.test_task.entity.LectureEntity;
import ru.milexe.test_task.entity.TimetableEntity;
import ru.milexe.test_task.model.Lecture;
import ru.milexe.test_task.repository.LectureRepo;
import ru.milexe.test_task.service.LectureService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LectureServiceTest {
    @InjectMocks
    private LectureService lectureService;
    @Mock
    private LectureRepo lectureRepo;

    @Test
    public void getLecturesTest(){
        LectureEntity group = new LectureEntity();
        Mockito.when(lectureRepo.findAll()).thenReturn(Arrays.asList(
                new LectureEntity(),
                new LectureEntity(),
                new LectureEntity()
        ));

        List<Lecture> allLectures = lectureService.getLectures();

        assertEquals(3, allLectures.size());
    }

    @Test
    public void getLectureTest() throws Exception {
        LectureEntity lecture = new LectureEntity();
        lecture.setSubject("math");
        Mockito.when(lectureRepo.findById(lecture.getId())).thenReturn(Optional.of(lecture));

        Lecture res = lectureService.getLecture(lecture.getId());

        assertEquals("math", res.getSubject());
    }

    @Test
    public void createLectureTest() throws Exception {
        LectureEntity lecture = new LectureEntity();
        lecture.setSubject("math");
        lecture.setTeacher("me");
        Mockito.when(lectureRepo.findById(lecture.getId())).thenReturn(Optional.empty());
        Mockito.when(lectureRepo.save(lecture)).thenReturn(lecture);

        Lecture res = lectureService.createLecture(lecture);

        assertEquals(Lecture.toModel(lecture).getTeacher(), res.getTeacher());
    }

    @Test
    public void deleteLectureTest() throws Exception {
        LectureEntity lecture = new LectureEntity();
        lecture.setSubject("math");
        lecture.setTeacher("me");
        lecture.setTimetables(new ArrayList<TimetableEntity>());
        Mockito.when(lectureRepo.findById(lecture.getId())).thenReturn(Optional.of(lecture));

        Lecture res = lectureService.deleteLecture(lecture.getId());

        assertEquals(Lecture.toModel(lecture).getTeacher(), res.getTeacher());
    }

    @Test
    public void updateStudentTest() throws Exception {
        LectureEntity lecture = new LectureEntity();
        lecture.setSubject("math");
        lecture.setTeacher("me");
        Mockito.when(lectureRepo.findById(lecture.getId())).thenReturn(Optional.of(lecture));
        Mockito.when(lectureRepo.save(lecture)).thenReturn(lecture);

        Lecture res = lectureService.updateLecture(lecture);

        assertEquals(Lecture.toModel(lecture).getTeacher(), res.getTeacher());
    }
}
