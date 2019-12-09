import java.util.*;
import java.sql.*;

public class DBHelper{
    // Set JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost/lmsdb";

    // Database credentials
    static final String USER = "user";
    static final String PASS = "pass";

    static Connection conn = null;
    static Statement stmt = null;
    static ResultSet rs;

    // Activate the DB connection
    public void activate() {
        try{
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    public int checkID(int id){
        try{
            String sql;
            sql = "select name,studentID from Student ";
            
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
               int sid = rs.getInt("studentID");
               String sname = rs.getString("name");
               // Valid
               if(sid == id){
                   System.out.println("Valid Student-ID");
                   System.out.println("Welcome " + sname);
                   return 0; 
               }
               else
                   continue;
            }
           //Invalid
            return 1;
        }
        
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return -1; 
    }

    public int checkPID(int pid){
        try{
            String sql;
            sql = "select name,profID from Professor ";
            
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
               int p = rs.getInt("profID");
               String pname = rs.getString("name");
               // Valid
               if(pid == p){
                   System.out.println("Valid Professor-ID");
                   System.out.println("Welcome " + pname);
                   return 0; 
               }
               else
                   continue;
            }
           //Invalid
            return 1;
        }
        
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return -1; 
    }

    public void displayCourses(int cid){
        try{
            String sql;
            sql = "select courseID, courseName from Course where courseID in (select courseID from Enrollment where studentID = " + String.valueOf(cid) + ")";
            
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int i = rs.getInt("courseID");
                String name = rs.getString("courseName");
                System.out.println("Course-ID : " + String.valueOf(i) + ", Course-Name : " + name);
            }
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        
    }

    public void displayProfCourses(int pid){
        try{
            String sql;
            sql = "select courseID, courseName from Course where profID = " + String.valueOf(pid);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int i = rs.getInt("courseID");
                String name = rs.getString("courseName");
                System.out.println("Course-ID : " + String.valueOf(i) + ", Course-Name : " + name);
            }
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }
    public void uploadSlides(int cid, String slide){
        try{
            String sql;
            sql = "update Course set slides = '" + slide + "' where courseID = " + String.valueOf(cid);

            stmt.executeUpdate(sql);
            System.out.println("Slides uploaded");
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }

    }

    public void downloadSlides(int course){
        try{
            String sql;
            sql = "select slides from Course where courseID = " + String.valueOf(course);

            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String slides = rs.getString("slides");
                System.out.println("Slides downloaded : " + slides);
            }
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    public void uploadExam(int courseID, String examName){
        try{

            String sql = "insert into Exam(examName, courseID) VALUES('" + examName + "','" + String.valueOf(courseID) + "')";  
            stmt.executeUpdate(sql);
            System.out.println("Exam uploaded");
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    public void displayExams(int sid){
        try{
            String sql = "select examID, examName from Exam where courseID in (select courseID from Enrollment where studentID = " + String.valueOf(sid) + ")";
            
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                int e = rs.getInt("examID");
                String n = rs.getString("examName");
                System.out.println("Exam-ID : " + String.valueOf(e) + ", Exam-name : " + n);
            }
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    public void makeResponse(int examID, String Response){
        try{
            String sql = "insert into Exam_Response(examID, Response) VALUES('" + String.valueOf(examID) + "','" + Response + "')";
            stmt.executeUpdate(sql);
            System.out.println("Response Recorded");
        }
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    // Deactivate the DB connection
    public void deactivate(){
        try{
            if(stmt!=null){
                System.out.println("Closing statement...");
                stmt.close();
            }
        }catch(SQLException se2){}// nothing we can do
        
        try{
            if(conn!=null){
                System.out.println("Closing connection...");
                conn.close();
            }
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
} 
