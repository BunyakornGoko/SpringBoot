package sit.int204.classicmodelsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int204.classicmodelsservice.model.Office;
import sit.int204.classicmodelsservice.model.Student;
import sit.int204.classicmodelsservice.serviceclass.StudentGradeService;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class StudentController {
    @Autowired
    private StudentGradeService service;
    @GetMapping("")
    public Student getAllStudent(@RequestBody Student score) {
        return service.calculateGrade(score);
    }

}
