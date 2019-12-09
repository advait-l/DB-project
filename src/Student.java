import java.*

public class Student
{
    private : int studentID;
              string studentName;

    public :
              // Constructor
              Student(id, name)
              {
                  studentid = id;
                  studentName = name;
              }

              // Getters
              int get_studentID(){ return studentID; }
              string get_studentName(){ return studentName; }

              // Setters
              void set_studentName(string name){ studentName = name; }
              void set_studentID(int sid){ studentID = sid; }
              
}
