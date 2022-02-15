package ru.milexe.test_task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.milexe.test_task.entity.LectureEntity;
import ru.milexe.test_task.entity.StudentEntity;
import ru.milexe.test_task.exception.DataAlreadyExistsException;
import ru.milexe.test_task.exception.DataNotFoundException;
import ru.milexe.test_task.model.Student;
import ru.milexe.test_task.service.LectureService;
import ru.milexe.test_task.service.StudentService;

@RestController
@RequestMapping("/lectures")
public class LectureController {

    @Autowired
    LectureService lectureService;

    @PostMapping
    public ResponseEntity addLecture(@RequestBody LectureEntity lecture){
        try{
            return ResponseEntity.ok(lectureService.createLecture(lecture));
        }
        catch(DataAlreadyExistsException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body("ошибка при добавлении лекции:" + e.getMessage());
        }
    }

    @GetMapping("/lecture")
    public ResponseEntity getLecture(@RequestParam Long id){
        try{
            return ResponseEntity.ok(lectureService.getLecture(id));

        }
        catch(DataNotFoundException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body("ошибка при получении лекции:" + e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity getLectures(){
        try{
            return ResponseEntity.ok(lectureService.getLectures());
        } catch(Exception e)
        {
            return ResponseEntity.badRequest().body("ошибка при получении лекций:" + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity deleteLecture(@RequestParam Long id){
        try{
            return ResponseEntity.ok(lectureService.deleteLecture(id));
        }
        catch(DataNotFoundException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(DataAlreadyExistsException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("произошла ошибка при удалении лекции:" + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity updateLecture(@RequestBody LectureEntity lecture){
        try{
            return ResponseEntity.ok(lectureService.updateLecture(lecture));
        }
        catch(DataNotFoundException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body("произошла ошибка при обновлении студента:" + e.getMessage());
        }
    }
}
