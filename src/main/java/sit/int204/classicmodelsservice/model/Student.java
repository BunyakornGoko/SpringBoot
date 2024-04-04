package sit.int204.classicmodelsservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name = "students")
public class Student {
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "score", nullable = false)
    private Integer score;
    private String grade;
    public void calculateGrade(){
        if(score >= 80){
            grade = "A";
        }
        else if(score >= 70){
            grade = "B";
        }
        else if(score >= 60){
            grade = "c";
        }
        else if(score >= 50){
            grade = "D";
        }
        else{
            grade = "F";
        }

    }
}
