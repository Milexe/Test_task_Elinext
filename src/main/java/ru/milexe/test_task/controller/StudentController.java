package ru.milexe.test_task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.milexe.test_task.entity.GroupEntity;
import ru.milexe.test_task.entity.StudentEntity;
import ru.milexe.test_task.repository.GroupRepo;
import ru.milexe.test_task.repository.StudentRepo;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private GroupRepo groupRepo;

    @PostMapping
    public ResponseEntity addStudent(@RequestBody StudentEntity student,@RequestParam Long groupId){
        try{
            GroupEntity group = groupRepo.findById(groupId).get();
            student.setGroup(group);
            studentRepo.save(student);
            return ResponseEntity.ok("студент добавлен");
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body("произошла ошибка");
        }
    }

    @GetMapping("")
    public ResponseEntity getStudents(){
        try{
            return ResponseEntity.ok("server works!");
        } catch(Exception e)
        {
            return ResponseEntity.badRequest().body("Exception has been occurred");
        }
    }
}

