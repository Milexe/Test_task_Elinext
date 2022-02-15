package ru.milexe.test_task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.milexe.test_task.entity.GroupEntity;
import ru.milexe.test_task.entity.StudentEntity;
import ru.milexe.test_task.exception.DataAlreadyExistsException;
import ru.milexe.test_task.exception.DataNotFoundException;
import ru.milexe.test_task.repository.GroupRepo;
import ru.milexe.test_task.service.GroupService;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupService groupService;

    @PostMapping
    public ResponseEntity addGroup(@RequestBody GroupEntity group){
        try{
            return ResponseEntity.ok(groupService.createGroup(group));
        }
        catch(DataAlreadyExistsException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body("произошла ошибка при добавлении группы:" + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getGroup(@RequestParam Long id){
        try{
            return ResponseEntity.ok(groupService.getGroup(id));
        }
        catch(DataNotFoundException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body("произошла ошибка при получении группы:" + e.getMessage());
        }
    }

    @GetMapping("/groups")
    public ResponseEntity getGroups(){
        try{
            return ResponseEntity.ok(groupService.getGroups());
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body("произошла ошибка при получении групп:" + e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity deleteGroup(@RequestParam Long id){
        try{
            return ResponseEntity.ok(groupService.deleteGroup(id));
        }
        catch(DataAlreadyExistsException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(DataNotFoundException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("произошла ошибка при удалении группы:" + e.getMessage());
        }
    }
    @PutMapping
    public ResponseEntity updateGroup(@RequestBody GroupEntity group){
        try{
            return ResponseEntity.ok(groupService.updateGroup(group));
        }
        catch(DataNotFoundException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(DataAlreadyExistsException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body("произошла ошибка при обновлении группы:" + e.getMessage());
        }
    }
}
