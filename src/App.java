import java.util.*;
import java.sql.*;

public class App{

    public static void main(String[] args){
    
        DBHelper db = new DBHelper();
        db.activate();
       
        while(true){
            System.out.println();
            System.out.println("********************LMS DBMS*********************");
            System.out.println("Main Menu : \n1 - Student\n2 - Professor\n0 - Exit application");
            System.out.print("Enter your choice : ");
            
            Scanner sc = new Scanner(System.in); 
            int role = sc.nextInt();

            // Student Menu
            if(role == 1){
                    System.out.print("Enter your student-ID (1 or 2) : ");
                    int sid = sc.nextInt();
                    int valid = db.checkID(sid);
                    
                    // Invalid student-ID
                    if(valid != 0){
                        System.out.println("Invalid student-ID");
                        break;
                    }
                    // Valid student-ID
                    else if(valid == 0){
                        System.out.println("Student Menu :  ");
                        System.out.println("1 - Download slides");
                        System.out.println("2 - Attempt an exam");
                        System.out.println("0 - Back to main menu");
                        System.out.print("Enter your choice : ");

                        int choice = sc.nextInt();
                        
                        // Download slides
                        if(choice == 1){
                            // From student's enrollment, display the course
                            db.displayCourses(sid);
                            System.out.print("Enter your choice of course : ");
                            int course = sc.nextInt();
                            db.downloadSlides(course);
                            System.out.println();
                            
                        }

                        // Attempt Exam
                        else if (choice == 2){
                            // Display available exams
                            db.displayExams(sid);
                            System.out.print("Enter the exam-id of the exam you want to attempt : ");
                            int examID = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Enter the response file name : ");
                            String response = sc.nextLine();
                            db.makeResponse(examID, response);
                        }

                        // Back to main menu
                        else if(choice == 0){
                            System.out.println("Returning to main menu...");
                            //break;
                        }
                    }

            }
            
            // Professor Menu
            else if(role == 2){
                    System.out.print("Enter your professor-ID : (21 or 22)");
                    int pid = sc.nextInt();
                    int valid = db.checkPID(pid);
                    
                    // Invalid professor-ID
                    if(valid != 0){
                        System.out.println("Invalid professor-ID");
                        break;
                    }
                    // Valid professor-ID
                    else if(valid == 0){
                        System.out.println("Professor Menu :  ");
                        System.out.println("1 - Upload slides");
                        System.out.println("2 - Conduct an exam");
                        System.out.println("0 - Back to main menu");
                        System.out.print("Enter your choice : ");

                        int choice = sc.nextInt();

                        // Upload slides
                        if(choice == 1){
                            // For professors, display the course
                            db.displayProfCourses(pid);
                            System.out.print("Enter your choice of course : ");
                            int courseID = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Enter the slides folder name : ");
                            String slide;
                            slide = sc.nextLine();
                            db.uploadSlides(courseID, slide);
                            System.out.println();
                            
                        }

                        // Conduct exam
                        else if (choice == 2){
                            db.displayProfCourses(pid);
                            System.out.print("Enter your choice of course to conduct an exam : ");
                            int courseID = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Enter exam name : ");
                            String examName = sc.nextLine();
                            db.uploadExam(courseID, examName);                            
                        }

                        // Back to main menu
                        else if(choice == 0){
                            System.out.println("Returning to main menu...");
                            //break;
                        }
            
                    }
            }
            
            else if (role == 0)
                break;
        }
        
        
            
            
        db.deactivate();        
    }
}

