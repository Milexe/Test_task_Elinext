package ru.milexe.test_task.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ru.milexe.test_task.entity.GroupEntity;
import ru.milexe.test_task.entity.StudentEntity;
import ru.milexe.test_task.model.Student;
import ru.milexe.test_task.repository.GroupRepo;
import ru.milexe.test_task.repository.StudentRepo;
import ru.milexe.test_task.service.StudentService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {
    @InjectMocks
    private StudentService studentService;
    @Mock
    private StudentRepo studentRepo;
    @Mock
    private GroupRepo groupRepo;

    @Test
    public void getStudentsTest(){
        GroupEntity group = new GroupEntity(1,"math", 1);
        Mockito.when(studentRepo.findAll()).thenReturn(Arrays.asList(
                new StudentEntity(1,"roma",group),
                new StudentEntity(2,"roma",group),
                new StudentEntity(3,"roma",group)
        ));

        List<Student> allStudents = studentService.getStudents();

        assertEquals(3, allStudents.size());
    }

    @Test
    public void getStudentTest() throws Exception {
        GroupEntity group = new GroupEntity(1,"math", 1);
        StudentEntity student = new StudentEntity(2, "roma 2", group);
        Mockito.when(studentRepo.findById(student.getId())).thenReturn(Optional.of(student));

        Student res = studentService.getStudent(student.getId());

        assertEquals("roma 2", res.getName());
    }

    @Test
    public void createStudentTest() throws Exception {
        GroupEntity group = new GroupEntity(1,"math", 1);
        StudentEntity student = new StudentEntity(2, "roma 2", group);
        Mockito.when(studentRepo.findById(student.getId())).thenReturn(Optional.empty());
        Mockito.when(groupRepo.findById(group.getId())).thenReturn(Optional.of(group));
        Mockito.when(studentRepo.save(student)).thenReturn(student);

        Student res = studentService.createStudent(student, group.getId());

        assertEquals(Student.toModel(student).getName(), res.getName());
    }

    @Test
    public void deleteStudentTest() throws Exception {
        GroupEntity group = new GroupEntity(1,"math", 1);
        StudentEntity student = new StudentEntity(2, "roma 2", group);
        Mockito.when(studentRepo.findById(student.getId())).thenReturn(Optional.of(student));

        Student res = studentService.deleteStudent(student.getId());

        assertEquals(Student.toModel(student).getName(), res.getName());
    }

    @Test
    public void updateStudentTest() throws Exception {
        GroupEntity group = new GroupEntity(1,"math", 1);
        StudentEntity student = new StudentEntity(2, "roma 2", group);
        Mockito.when(studentRepo.findById(student.getId())).thenReturn(Optional.of(student));
        Mockito.when(groupRepo.findByNumber(group.getNumber())).thenReturn(group);
        Mockito.when(studentRepo.save(student)).thenReturn(student);

        Student res = studentService.updateStudent(Student.toModel(student));

        assertEquals(Student.toModel(student).getName(), res.getName());
    }
}
