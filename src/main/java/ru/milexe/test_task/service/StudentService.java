package ru.milexe.test_task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.milexe.test_task.entity.GroupEntity;
import ru.milexe.test_task.entity.StudentEntity;
import ru.milexe.test_task.model.Group;
import ru.milexe.test_task.model.Student;
import ru.milexe.test_task.repository.GroupRepo;
import ru.milexe.test_task.repository.StudentRepo;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private GroupRepo groupRepo;

    public Student createStudent(StudentEntity student, Long groupId){
        GroupEntity group = groupRepo.findById(groupId).get();
        student.setGroup(group);
        studentRepo.save(student);
        return Student.toModel(studentRepo.save(student));
    }

    public Student getStudent(Long id) throws Exception {
        if(!studentRepo.findById(id).isPresent())
            throw new Exception("студент не найден");
        return Student.toModel(studentRepo.findById(id).get());
    }

    public List<Student> getStudents(){
        List<StudentEntity> list = (List<StudentEntity>) studentRepo.findAll();
        List<Student> resultList = list.stream().map(Student::toModel).collect(Collectors.toList());
        return resultList;
    }

    public Student deleteStudent(Long id){
        Student deletedStudent = Student.toModel(studentRepo.findById(id).get());
        studentRepo.deleteById(id);
        return deletedStudent;
    }

    public Student updateStudent(Student student){
        StudentEntity updatedStudent = studentRepo.findById(student.getId()).get();
        updatedStudent.setGroup(groupRepo.findByNumber(student.getGroup()));
        updatedStudent.setName(student.getName());
        return Student.toModel(studentRepo.save(updatedStudent));
    }
}
