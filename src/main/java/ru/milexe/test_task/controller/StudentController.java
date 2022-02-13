package ru.milexe.test_task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.milexe.test_task.entity.GroupEntity;
import ru.milexe.test_task.entity.StudentEntity;
import ru.milexe.test_task.model.Student;
import ru.milexe.test_task.repository.GroupRepo;
import ru.milexe.test_task.repository.StudentRepo;
import ru.milexe.test_task.service.GroupService;
import ru.milexe.test_task.service.StudentService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping
    public ResponseEntity addStudent(@RequestBody StudentEntity student,@RequestParam Long groupId){
        try{
            return ResponseEntity.ok(studentService.createStudent(student,groupId));
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body("ошибка при добавлении студента");
        }
    }

    @GetMapping("/student")
    public ResponseEntity getStudent(@RequestParam Long id){
        try{
            return ResponseEntity.ok(studentService.getStudent(id));
        } catch(Exception e)
        {
            return ResponseEntity.badRequest().body("ошибка при получении студента");
        }
    }

    @GetMapping()
    public ResponseEntity getStudents(){
        try{
            return ResponseEntity.ok(studentService.getStudents());
        } catch(Exception e)
        {
            return ResponseEntity.badRequest().body("ошибка при получении студентов");
        }
    }

    @DeleteMapping
    public ResponseEntity deleteStudent(@RequestParam Long id){
        try{
            return ResponseEntity.ok(studentService.deleteStudent(id));
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("произошла ошибка при удалении студента");
        }
    }

    @PutMapping
    public ResponseEntity updateStudent(@RequestBody Student student){
        try{
            return ResponseEntity.ok(studentService.updateStudent(student));
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body("произошла ошибка при обновлении студента");
        }
    }
}

