import java.*

public class Course
{
    private : int courseID;
              int profID;
              string courseName;

    public :
              // Getters
              int get_courseID(){ return courseID;}
              int get_profID(){ return profID; }
              string get_courseName(){ return courseName; }
              
              // Setters
              void set_courseID(int cid){ courseID = cid; }
              void set_profID(int pid){ profID = pid; }
              void set_courseName(string name){ courseName = name; }
              
}
