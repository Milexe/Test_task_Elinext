package ru.milexe.test_task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.milexe.test_task.entity.GroupEntity;
import ru.milexe.test_task.entity.StudentEntity;
import ru.milexe.test_task.repository.GroupRepo;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupRepo groupRepo;

    @PostMapping
    public ResponseEntity addGroup(@RequestBody GroupEntity group){
        try{
            groupRepo.save(group);
            return ResponseEntity.ok("группа добавлена");
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body("произошла ошибка");
        }
    }
}
