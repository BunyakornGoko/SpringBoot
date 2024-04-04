package sit.int204.classicmodelsservice.serviceclass;

import org.springframework.stereotype.Service;
import sit.int204.classicmodelsservice.model.Student;
@Service
public class StudentGradeService {
    public Student calculateGrade(Student student){
        if (student.getScore() == null || student.getScore() > 100){
            return null;
        }
        student.calculateGrade();
        return student;
    }
}
