package ru.milexe.test_task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.milexe.test_task.entity.StudentEntity;
import ru.milexe.test_task.entity.TimetableEntity;
import ru.milexe.test_task.model.Student;
import ru.milexe.test_task.model.Timetable;
import ru.milexe.test_task.service.TimetableService;

import java.sql.Date;

@RestController
@RequestMapping("/timetable")
public class TimetableController {

    @Autowired
    private TimetableService timetableService;

    @PostMapping
    public ResponseEntity addTimetable(@RequestBody TimetableEntity timetable, @RequestParam Long groupId, @RequestParam Long lectureId){
        try{
            return ResponseEntity.ok(timetableService.createTimetable(timetable, groupId, lectureId));
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body("ошибка при добавлении занятия");
        }
    }

    @GetMapping("/timetables")
    public ResponseEntity getTimetables(){
        try{
            return ResponseEntity.ok(timetableService.getTimetables());
        } catch(Exception e)
        {
            return ResponseEntity.badRequest().body("ошибка при получении занятий");
        }
    }

    @GetMapping()
    public ResponseEntity getTimetablesForGroup(@RequestParam Long groupNumber, @RequestParam String date){
        try{
            return ResponseEntity.ok(timetableService.getTimetablesForGroup(groupNumber, date));
        } catch(Exception e)
        {
            return ResponseEntity.badRequest().body("ошибка при получении занятий для группы");
        }
    }

    @DeleteMapping
    public ResponseEntity deleteTimetable(@RequestParam Long id){
        try{
            return ResponseEntity.ok(timetableService.deleteTimetable(id));
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("произошла ошибка при удалении занятия");
        }
    }

    @PutMapping
    public ResponseEntity updateTimetable(@RequestBody Timetable timetable){
        try{
            return ResponseEntity.ok(timetableService.updateTimetable(timetable));
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body("произошла ошибка при обновлении занятия");
        }
    }
}
