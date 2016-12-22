package pk.edu.nust.seecs.gradebook;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import pk.edu.nust.seecs.gradebook.dao.CloDao;
import pk.edu.nust.seecs.gradebook.dao.ContentDao;
import pk.edu.nust.seecs.gradebook.dao.CourseDao;
import pk.edu.nust.seecs.gradebook.dao.GradeDao;
import pk.edu.nust.seecs.gradebook.dao.StudentDao;
import pk.edu.nust.seecs.gradebook.dao.TeacherDao;
import pk.edu.nust.seecs.gradebook.entity.Clo;
import pk.edu.nust.seecs.gradebook.entity.Content;
import pk.edu.nust.seecs.gradebook.entity.Course;
import pk.edu.nust.seecs.gradebook.entity.Grade;
import pk.edu.nust.seecs.gradebook.entity.Student;
import pk.edu.nust.seecs.gradebook.entity.Teacher;

/**
 * My main App. 
 * <p>
 This executes everything.
 */

public class App {
    
    private static CloDao cloDao = new CloDao();
    private static ContentDao contentDao = new ContentDao();
    private static CourseDao courseDao = new CourseDao();
    private static GradeDao gradeDao = new GradeDao();
    private static StudentDao studentDao = new StudentDao();
    private static TeacherDao teacherDao = new TeacherDao();
    
    public static int menu() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println(" 1 - Add Course");
        System.out.println(" 2 - Update Course");
        System.out.println(" 3 - Add Teacher");
        System.out.println(" 4 - Update Course");
        System.out.println(" 5 - Add Student");
        System.out.println(" 6 - Update Student");
        System.out.println(" 7 - Add Content");
        System.out.println(" 8 - Update Content");
        System.out.println(" 9 - Add CLO");
        System.out.println("10 - Update CLO");
        System.out.println("11 - Add Grade");
        System.out.println("12 - Update Grade");
        System.out.println("13 - Print All Data"); 
        System.out.println();
        System.out.println("Enter your option: ");
        int option = sc.nextInt();
        
        return option;
    }
    
    public static void executioner() throws ParseException {
        int id;
        String name, description, plo, btLevel;
        
        String classTitle;
        String strDate;
        Date startsOn, endsOn;
        int creditHours;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        
        Set<Course> courses = new HashSet<>(0);
        
        String title;
        Set<Student> students;
        Course course;
        Set<Grade> grades;
        List<Clo> clos;
        
        Content content;
        int score;
        
        Scanner sc = new Scanner(System.in);
        
        while(true) {
            switch(menu()) {
                case 1:
                    System.out.print("Enter Class Title: ");
                    classTitle = sc.nextLine();
                    System.out.print("Enter Description: ");
                    description = sc.nextLine();
                    System.out.print("Enter Starting Date: ");
                    strDate = sc.nextLine();
                    startsOn = format.parse(strDate);
                    System.out.print("Enter Ending Date: ");
                    strDate = sc.nextLine();
                    endsOn = format.parse(strDate);
                    System.out.print("Enter Credit Hours: ");
                    creditHours = sc.nextInt();
                    if(addCourse(classTitle, startsOn, endsOn, creditHours))
                        System.out.println("Course added");
                    break;
                case 2:
                    System.out.print("Enter Id: ");
                    id = sc.nextInt();
                    System.out.print("Enter Class Title: ");
                    classTitle = sc.nextLine();
                    System.out.print("Enter Description: ");
                    description = sc.nextLine();
                    System.out.print("Enter Starting Date: ");
                    strDate = sc.nextLine();
                    startsOn = format.parse(strDate);
                    System.out.print("Enter Ending Date: ");
                    strDate = sc.nextLine();
                    endsOn = format.parse(strDate);
                    System.out.print("Enter Credit Hours: ");
                    creditHours = sc.nextInt();
                    if(updateCourse(id, classTitle, startsOn, endsOn, creditHours))
                        System.out.println("Course Updated");
                    break;    
                case 3:
                    System.out.print("Enter Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Courses: ");
                    courses = selectCourses();
                    if(addTeacher(name, courses))
                        System.out.println("Teacher added");
                    break;
                case 4:
                    System.out.print("Enter Id: ");
                    id = sc.nextInt();                    
                    System.out.print("Enter Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Courses: ");
                    courses = selectCourses();
                    if(updateTeacher(id, name, courses))
                        System.out.println("Teacher Updated");
                    break;    
                case 5:
                    System.out.print("Enter Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Courses: ");
                    courses = selectCourses();
                    if(addStudent(name, courses))
                        System.out.println("Student added");
                    break;
                case 6:
                    System.out.print("Enter Id: ");
                    id = sc.nextInt();  
                    System.out.print("Enter Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Courses: ");
                    courses = selectCourses();
                    if(updateStudent(id, name, courses))
                        System.out.println("Student Updated");
                    break;
                case 7:
                    System.out.print("Enter Title: ");
                    title = sc.nextLine();
                    System.out.print("Enter Description: ");
                    description = sc.nextLine();
                    System.out.print("Enter Starting Date: ");
                    strDate = sc.nextLine();
                    startsOn = format.parse(strDate);
                    System.out.print("Enter Ending Date: ");
                    strDate = sc.nextLine();
                    endsOn = format.parse(strDate);
                    students = selectStudents();
                    course = selectCourse();
                    grades = selectGrades();
                    clos = selectClos();
                    if(addContent(title, description, startsOn, endsOn, students, course, grades, clos))
                        System.out.println("Content added");
                    break;
                case 8:
                    System.out.print("Enter Id: ");
                    id = sc.nextInt();
                    System.out.print("Enter Title: ");
                    title = sc.nextLine();
                    System.out.print("Enter Description: ");
                    description = sc.nextLine();
                    System.out.print("Enter Starting Date: ");
                    strDate = sc.nextLine();
                    startsOn = format.parse(strDate);
                    System.out.print("Enter Ending Date: ");
                    strDate = sc.nextLine();
                    endsOn = format.parse(strDate);
                    students = selectStudents();
                    course = selectCourse();
                    grades = selectGrades();
                    clos = selectClos();
                    if(updateContent(id, title, description, startsOn, endsOn, students, course, grades, clos))
                        System.out.println("Content Updated");
                    break;
                case 9:
                    System.out.print("Enter Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Description: ");
                    description = sc.nextLine();
                    System.out.print("Enter Plo: ");
                    plo = sc.nextLine();
                    System.out.print("Enter btLevel: ");
                    btLevel = sc.nextLine();
                    if(addClo(name, description, plo, btLevel))
                        System.out.println("CLO added");
                    break;
                case 10:
                    System.out.print("Enter Id: ");
                    id = sc.nextInt();
                    System.out.print("Enter Name: ");
                    sc.nextLine();
                    name = sc.nextLine();
                    System.out.print("Enter Description: ");
                    description = sc.nextLine();
                    System.out.print("Enter PLO: ");
                    plo = sc.nextLine();
                    System.out.print("Enter btLevel: ");
                    btLevel = sc.nextLine();
                    if(updateClo(id, name, description, plo, btLevel))
                        System.out.println("CLO Updated");
                    break;
                case 11:
                    System.out.print("Enter Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Score: ");
                    score = sc.nextInt();
                    content = selectContent();
                    if(addGrade(name, score, content))
                        System.out.println("Grade added");
                    break;
                case 12:
                    System.out.print("Enter Id: ");
                    id = sc.nextInt();
                    System.out.print("Enter Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Score: ");
                    score = sc.nextInt();
                    content = selectContent();
                    if(updateGrade(id, name, score, content))
                        System.out.println("Grade Updated");
                    break;
                case 13:
                    printAll();
                    break;
            }    
        }
    }
    
    public static boolean addClo(String name, String description, String plo, String btLevel) {        
        Clo clo = new Clo(name, description, plo, btLevel);
        cloDao.addClo(clo);
        return true;
    }
    
    public static boolean updateClo(int cloId, String name, String description, String plo, String btLevel) {
        Clo clo = cloDao.getCloById(cloId);
        
        clo.setName(name);
        clo.setDescription(description);
        clo.setPlo(plo);
        clo.setBtLevel(btLevel);
        
        cloDao.updateClo(clo);
        return true;
    }
    
    public static boolean addCourse(String classtitle, Date starttime, Date endtime, int creditHours) {        
        Course course = new Course(classtitle, starttime, endtime, creditHours);
        courseDao.addCourse(course);
        return true;
    }
    
    public static boolean updateCourse(int courseId, String classtitle, Date starttime, Date endtime, int creditHours) {
        Course course = courseDao.getCourseById(courseId);
        
        course.setClasstitle(classtitle);
        course.setStartsOn(starttime);
        course.setEndsOn(endtime);
        course.setCreditHours(creditHours);
        
        courseDao.updateCourse(course);
        return true;
    }    
    
    public static boolean addTeacher(String name, Set<Course> Courses) {
        Teacher teacher = new Teacher(name);
        teacher.setCourses(Courses);
        
        teacherDao.addTeacher(teacher);
        return true;
    }
    
    public static boolean updateTeacher(int teacherId, String name, Set<Course> Courses) {
        Teacher teacher = teacherDao.getTeacherById(teacherId);
        
        teacher.setName(name);
        teacher.setCourses(Courses);
        
        teacherDao.updateTeacher(teacher);
        return true;
    }
    
    public static boolean addStudent(String name, Set<Course> Courses) {
        Student student = new Student(name);
        student.setCourses(Courses);
        
        studentDao.addStudent(student);
        return true;
    } 
    
    public static boolean updateStudent(int studentId, String name, Set<Course> Courses) {
        Student student = studentDao.getStudentById(studentId);
        
        student.setName(name);
        student.setCourses(Courses);
        
        studentDao.updateStudent(student);
        return true;
    }
    
    public static boolean addContent(String title, String description, Date starttime, Date endtime, Set<Student> students, Course course, Set<Grade> grades, List<Clo> clo) {
        Content content = new Content();
        
        content.setTitle(title);
        content.setDescription(description);
        content.setStarttime(starttime);
        content.setEndtime(endtime);
        content.setStudents(students);
        content.setCourse(course);
        content.setClo(clo);  
        
        contentDao.addContent(content);
        return true;
    }
    
    public static boolean updateContent(int contentId, String title, String description, Date starttime, Date endtime, Set<Student> students, Course course, Set<Grade> grades, List<Clo> clo) {
        Content content = contentDao.getContentById(contentId);
        
        content.setTitle(title);
        content.setDescription(description);
        content.setStarttime(starttime);
        content.setEndtime(endtime);
        content.setStudents(students);
        content.setCourse(course);
        content.setClo(clo);
        
        contentDao.updateContent(content);
        return true;
    }
    
    public static boolean addGrade(String name, int score, Content content) {
        Grade grade = new Grade();
        
        grade.setName(name);
        grade.setScore(score);
        grade.setContentItem(content);
        
        gradeDao.addGrade(grade);
        return true;        
    }
    
    public static boolean updateGrade(int gradeId, String name, int score, Content content) {
        Grade grade = gradeDao.getGradeById(gradeId);
        
        grade.setName(name);
        grade.setScore(score);
        grade.setContentItem(content);
        
        gradeDao.updateGrade(grade);
        return true;        
    }
    
    public static Set<Course> selectCourses() {
        Set<Course> Courses = new HashSet<>(0);
        Scanner sc = new Scanner(System.in);
        int option = 0;
        
        System.out.println("Showing all Courses");
        for (Course iter : courseDao.getAllCourses()) {
            System.out.println(iter.getCourseid() + " - " + iter.getClasstitle());
            }
        System.out.println("Enter your options (one per line) or 0 to stop: ");
        while(option != 0) {
            option = sc.nextInt();
            Courses.add(courseDao.getCourseById(option));
        }
        
        return Courses;
    }
    
    public static Course selectCourse() {
        Course course = new Course();
        Scanner sc = new Scanner(System.in);
        int option = 0;
        
        System.out.println("Showing all Courses");
        for (Course iter : courseDao.getAllCourses()) {
            System.out.println(iter.getCourseid() + " - " + iter.getClasstitle());
            }
        System.out.println("Enter your option: ");
            option = sc.nextInt();
            course = (courseDao.getCourseById(option));
        
        return course;
    }
    
    public static Set<Student> selectStudents() {
        Set<Student> students = new HashSet<>(0);
        Scanner sc = new Scanner(System.in);
        int option = 0;
        
        System.out.println("Showing all Students");
        for (Student iter : studentDao.getAllStudents()) {
            System.out.println(iter.getStudentId()+ " - " + iter.getName());
            }
        System.out.println("Enter your options (one per line) or 0 to stop: ");
        while(option != 0) {
            option = sc.nextInt();
            students.add(studentDao.getStudentById(option));
        }
        
        return students;
    }

    public static Set<Grade> selectGrades() {
        Set<Grade> grades = new HashSet<>(0);
        Scanner sc = new Scanner(System.in);
        int option = 0;
        
        System.out.println("Showing all Grades");
        for (Grade iter : gradeDao.getAllGrades()) {
            System.out.println(iter.getGradeId()+ " - " + iter.getName());
            }
        System.out.println("Enter your options (one per line) or 0 to stop: ");
        while(option != 0) {
            option = sc.nextInt();
            grades.add(gradeDao.getGradeById(option));
        }
        
        return grades;
    }  
    
    public static List<Clo> selectClos() {
        List<Clo> clos = new ArrayList<Clo>();
        Scanner sc = new Scanner(System.in);
        int option = 0;
        
        System.out.println("Showing all CLOs");
        for (Clo iter : cloDao.getAllClos()) {
            System.out.println(iter.getCloId()+ " - " + iter.getName());
            }
        System.out.println("Enter your options (one per line) or 0 to stop: ");
        while(option != 0) {
            option = sc.nextInt();
            clos.add(cloDao.getCloById(option));
        }
        
        return clos;
    }  
    
    public static Content selectContent() {
        Content content;
        Scanner sc = new Scanner(System.in);
        int option = 0;
        
        System.out.println("Showing all Content");
        for (Clo iter : cloDao.getAllClos()) {
            System.out.println(iter.getCloId()+ " - " + iter.getName());
            }
        System.out.println("Enter your option: ");
            option = sc.nextInt();
            content = (contentDao.getContentById(option));
        
        return content;
    } 
    
    public static void printAll() {
        System.out.println("List of Students: ");
        for (Student iter : studentDao.getAllStudents()) {
            System.out.println("Student Name: " + iter.getName());
        }
        System.out.println("List of CLOs: ");
        for (Clo iter : cloDao.getAllClos()) {
            System.out.println("CLO Title: " + iter.getName() + "CLO Description: " + iter.getDescription());
        }
        System.out.println("List of Content: ");
        for (Content iter : contentDao.getAllContents()) {
            System.out.println("CLO Title: " + iter.getTitle() + "Content Description: " + iter.getDescription());
        }
        System.out.println("List of Courses: ");
        for (Course iter : courseDao.getAllCourses()) {
            System.out.println("Course Title: " + iter.getClasstitle());
        }
        System.out.println("List of Teachers: ");
        for (Teacher iter : teacherDao.getAllTeachers()) {
            System.out.println("Teacher Name: " + iter.getName());
        }
    }

    public static void main(String[] args) {
        try {
            executioner();
        } catch (ParseException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

}