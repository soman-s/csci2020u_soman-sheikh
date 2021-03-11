package sample;

public class StudentRecord{

    String StudentID;
    float assignments;
    float midterm;
    float finalExam;
    char letterGrade;
    float finalMark;

    public StudentRecord(String StudentID, float assignments, float midterm, float finalExam){
        this.StudentID = StudentID;
        this.assignments = assignments;
        this.midterm = midterm;
        this.finalExam = finalExam;
    }

    public String getStudentID(){
        return this.StudentID;
    }

    public float getAssignments(){
        return this.assignments;
    }

    public float getMidterm(){
        return this.midterm;
    }

    public float getFinalExam(){
        return this.finalExam;
    }

    public float getFinalMark(){
        return this.assignments*0.20f + this.midterm*0.30f + this.finalExam*0.50f;
    }

    public char getLetterGrade(){
        float mark = this.getFinalMark();

        if (mark >= 80){
            return 'A';
        }
        else if(mark >= 70){
            return 'B';
        }
        else if(mark >= 60){
            return 'C';
        }
        else if(mark >= 50){
            return 'D';
        }
        else{
            return 'F';
        }
    }
}
