package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.*;

////////////////////////////////
// NOTES:
// - add button to edit screens to delete students, courses, and enrollments
// - add confirmation screen for delete buttons (send "*type* *id*" as parameter for message), have two buttons yes, no 


///////////////////////////
// MIDTERM DUE BY MONDAY
//////////////////////////
public class MainController{
	public static int SIDCounter = 0, CIDCounter = 0, EIDCounter = 0;
	public static Highest HighestList;
	public static int studentRecordNumberEditing = 0, courseRecordNumberEditing = 0, enrollmentRecordNumberEditing = 0, departmentRecordNumberEditing = 0;
	public static int instructorRecordNumberEditing = 0;
	@FXML
	private MenuItem addStudentButton,addCourseButton,addEnrollmentButton;
	@FXML
	private MenuItem searchStudentButton,searchCourseButton,searchEnrollmentButton;
	@FXML
	private MenuItem manageGradesButton;
	@FXML
	private Label MenuLabel;
	@FXML
	private VBox addStudentVBOX, postAddStudentVBOX, editStudentVBOX, displayStudentVBOX, searchStudentVBOX, addCourseVBOX;
	@FXML
	private VBox postAddCourseVBOX, editCourseVBOX, displayCourseVBOX, searchCourseVBOX;
	@FXML
	private TextField addStudentAddressTextField, addStudentFirstNameTextField, addStudentLastNameTextField, addStudentStateTextField, addStudentCityTextField;
	@FXML
	private Button createStudentButton, cancelButton, addNewStudentButton, postAddStudentEditButton;
	@FXML
	private Label postAddStudentID, postAddStudentFirstName, postAddStudentLastName, postAddStudentAddress, postAddStudentCity, postAddStudentState;
	@FXML
	private TextField editStudentFirstName, editStudentLastName, editStudentAddress, editStudentCity, editStudentState;
	@FXML
	private Label displayStudentID, displayStudentFirstName, displayStudentLastName, displayStudentAddress, displayStudentCity, displayStudentState;
	@FXML
	private TextField studentSearchID;
	@FXML
	private Button studentSearchButton;
	@FXML
	private ChoiceBox<String> addCourseInstructorChoiceBox, addCourseDepartmentChoiceBox;
	@FXML 
	private TextField addCourseNumber, addCourseName;
	@FXML
	private Label postAddCourseID, postAddCourseNumber, postAddCourseName, postAddCourseInstructor, postAddCourseDepartment;
	@FXML
	private TextField editCourseNumber, editCourseName;
	@FXML
	private ChoiceBox<String> editCourseInstructorChoiceBox, editCourseDepartmentChoiceBox;
	@FXML
	private TextField courseSearchID;
	@FXML
	private Label displayCourseID, displayCourseNumber, displayCourseName, displayCourseInstructor, displayCourseDepartment;
	@FXML
	private VBox searchStudentCreateEnrollmentVBOX;
	@FXML
	private HBox createEnrollmentHBOX;
	@FXML
	private Label createEnrollmentDisplayStudentID, createEnrollmentDisplayStudentFirstName, createEnrollmentDisplayStudentLastName;
	@FXML
	private Label createEnrollmentDisplayStudentAddress, createEnrollmentDisplayStudentCity, createEnrollmentDisplayStudentState;
	@FXML
	private ChoiceBox<String> createEnrollmentCourseChoiceBox, createEnrollmentYearChoiceBox, createEnrollmentSemesterChoiceBox;
	@FXML
	private TextField enrollmentStudentSearchID;
	@FXML
	private VBox postAddEnrollmentVBOX;
	@FXML
	private Label postAddEnrollmentEID, postAddEnrollmentCID, postAddEnrollmentSID, postAddEnrollmentYear, postAddEnrollmentSemester, postAddEnrollmentGrade;
	@FXML
	private HBox editEnrollmentHBOX;
	@FXML
	private Label editEnrollmentDisplayStudentID, editEnrollmentDisplayStudentFirstName, editEnrollmentDisplayStudentLastName;
	@FXML
	private Label editEnrollmentDisplayStudentAddress, editEnrollmentDisplayStudentCity, editEnrollmentDisplayStudentState;
	@FXML
	private ChoiceBox<String> editEnrollmentCourseChoiceBox, editEnrollmentYearChoiceBox, editEnrollmentSemesterChoiceBox;
	@FXML
	private TextField searchEnrollmentStudentID, searchDepartmentTextField, searchInstructorTextField;
	@FXML
	private ChoiceBox<String> searchEnrollmentYear;
	@FXML
	private ChoiceBox<String> searchEnrollmentSemester;
	@FXML
	private VBox displayEnrollmentVBOX;
	@FXML
	private VBox searchEnrollmentGradeManagementVBOX, gradeManagementEditVBOX, searchEnrollmentVBOX;
	@FXML
	private Label gradeManagementDisplayEID, gradeManagementDisplaySID, gradeManagementDisplayCID, gradeManagementDisplayYear;
	@FXML
	private Label gradeManagementDisplaySemester;
	@FXML
	private ChoiceBox<String> gradeManagementGradeChoiceBox;
	@FXML
	private TextField enrollmentSearchID;
	@FXML
	private Label displayEnrollmentEID, displayEnrollmentCID, displayEnrollmentSID, displayEnrollmentYear, displayEnrollmentSemester;
	@FXML
	private Label displayEnrollmentGrade;
	@FXML
	private TextField gradeManagementStudentID, addDepartmentName, editDepartmentName, addInstructorName;
	@FXML
	private ChoiceBox<String> gradeManagementYear, gradeManagementSemester;
	@FXML
	private Label gradeManagementSaveIndicator, displayDepartmentID, displayDepartmentName, postAddDepartmentID, postAddDepartmentName;
	@FXML
	private ChoiceBox<String> searchReportCourseChoiceBox, searchReportYearChoiceBox, addInstructorDepartmentChoiceBox;
	@FXML
	private Button reportSearchButton, addInstructorButton, searchInstructorButton;
	@FXML
	private VBox searchReportVBOX, displayReportVBOX, addDepartmentVBOX, displayDepartmentVBOX, postAddDepartmentVBOX;
	@FXML
	private VBox editDepartmentVBOX, searchDepartmentVBOX, addInstructorVBOX, searchInstructorVBOX, postAddInstructorVBOX;
	@FXML
	private VBox displayInstructorVBOX, editInstructorVBOX, sortStudentsVBOX, sortCoursesVBOX;
	@FXML
	private Label postAddInstructorID, postAddInstructorDepName, postAddInstructorName, displayInstructorID, displayInstructorDepName;
	@FXML
	private Label displayInstructorName, ReportClassName;
	@FXML
	private ChoiceBox<String> editInstructorDepartmentChoiceBox;
	@FXML
	private TextField editInstructorName, gradeManagementCourseID;
	@FXML
	private ListView<String> listOfStudentInfo = new ListView<String>(), listOfStudents = new ListView<String>(), listOfCourses = new ListView<String>();
	
	private int searchStudentRequestedID = 0, searchCourseRequestedID = 0, searchEnrollmentRequestedID = 0;
	private int createEnrollmentRequestedStudentID = 0, searchDepartmentRequestedID = 0, searchInstructorRequestedID = 0;
	private boolean studentJustSearched = false, courseJustSearched = false, enrollmentJustSearched = false;
	private boolean gradeManagementJustSearched = false, departmentJustSearched = false, instructorJustSearched = false;
	private Enrollment searchedEnrollment;
	private List<Integer> listOfReportEnrollmentIds;
	
	private static ObjLinkedList<Student> StudentLinkedList;
	private static ObjLinkedList<Course> CourseLinkedList;
	private static ObjLinkedList<Enrollment> EnrollmentLinkedList;
	private static List<Department> DepartmentList;
	private static List<Instructor> InstructorList;
	
	private static File StudentFile;
	private static File CourseFile;
	private static File EnrollmentFile;
	private static File DepartmentFile;
	private static File InstructorFile;
	private static File HighestFile;
	
	private static FileOutputStream StudentOutput;
	private static ObjectOutputStream ObjectStudentOutput;
	private static FileInputStream StudentInput;
	private static ObjectInputStream ObjectStudentInput;
	
	private static FileOutputStream CourseOutput;
	private static ObjectOutputStream ObjectCourseOutput;
	private static FileInputStream CourseInput;
	private static ObjectInputStream ObjectCourseInput;
	
	private static FileOutputStream EnrollmentOutput;
	private static ObjectOutputStream ObjectEnrollmentOutput;
	private static FileInputStream EnrollmentInput;
	private static ObjectInputStream ObjectEnrollmentInput;
	
	private static FileOutputStream DepartmentOutput;
	private static ObjectOutputStream ObjectDepartmentOutput;
	private static FileInputStream DepartmentInput;
	private static ObjectInputStream ObjectDepartmentInput;
	
	private static FileOutputStream InstructorOutput;
	private static ObjectOutputStream ObjectInstructorOutput;
	private static FileInputStream InstructorInput;
	private static ObjectInputStream ObjectInstructorInput;
	
	private static FileOutputStream HighestOutput;
	private static ObjectOutputStream ObjectHighestOutput;
	private static FileInputStream HighestInput;
	private static ObjectInputStream ObjectHighestInput;
	
	public static String url;
	public static String user;
	public static String pass;
	
	// Use OnMouseClicked for instructor box
	
	public void initialize() throws IOException, ClassNotFoundException{
		LOG("Controller main");
		
		File appconfig = new File("src/application/AppConfig.txt");
		Scanner fileScanner = new Scanner(appconfig);
		if(fileScanner.hasNextLine())
			url = fileScanner.nextLine();
		else
			System.out.println("no url");
		if(fileScanner.hasNextLine())
			user = fileScanner.nextLine();
		else
			System.out.println("no user");
		if(fileScanner.hasNextLine())
			pass = fileScanner.nextLine();
		else
			System.out.println("no pass");
		
		
		
		fileScanner.close();
		
		StudentFile = new File("StudentFile.dat");
		CourseFile = new File("CourseFile.dat");
		EnrollmentFile = new File("EnrollmentFile.dat");
		DepartmentFile = new File("DepartmentFile.dat");
		InstructorFile = new File("InstructorFile.dat");
		HighestFile = new File("HighestFile.dat");
		
		TruncateDB(true);
	
//		addCourseInstructorChoiceBox.getItems().removeAll(addCourseInstructorChoiceBox.getItems());
//		addCourseInstructorChoiceBox.getItems().addAll("Kim", "Jones", "Java", "Pete", "Scott");
//		addCourseDepartmentChoiceBox.getItems().removeAll(addCourseDepartmentChoiceBox.getItems());
//		addCourseDepartmentChoiceBox.getItems().addAll("English", "Science", "Biology", "Math", "Chemistry");
		
		makeAllInvisible();
		MenuLabel.setVisible(true);
	}
	
	private void TruncateDB(boolean doTrucate) throws ClassNotFoundException, IOException {
		SetUpHighestList(doTrucate);
		if(doTrucate) {
			try {
				Connection conn = DriverManager.getConnection(url, user, pass);
				Statement stmnt = conn.createStatement();
				int rows = 0;
				String[] wipes = {"DELETE FROM students", "DELETE FROM courses", 
						"DELETE FROM enrollments", "DELETE FROM instructors",
						"DELETE FROM departments"};
				for(String wipe : wipes)
					rows += stmnt.executeUpdate(wipe);
				
				System.out.println(rows + " total rows deleted\n");
				
				stmnt.close();
				conn.close();
				
				return;
			} catch (SQLException e) {
				System.out.println("Exception: " + e);
			}
		}
		System.out.println("Did not truncate");
	}
	
	private boolean IdIsInDB(int id, String table) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			Statement stmnt = conn.createStatement();
			String type = table.equals("enrollment") ? "EID" : "ID";
			String query = "SELECT * FROM " + table + " WHERE " + type + " = " + id; 
			ResultSet set = stmnt.executeQuery(query);
			conn.close();
			stmnt.close();
			return set.next();
		}
		catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return false;
		}
	}
	
	private int GetHighestID(String objType) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			Statement stmnt = conn.createStatement();
			String ID = objType.equals("enrollments") ? "EID" : "ID";
			String query = "SELECT MAX(" + ID + ") FROM " + objType;
			ResultSet set = stmnt.executeQuery(query);
			conn.close();
			stmnt.close();
			return set.getInt(ID);
		}
		catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return 0;
		}
	}
	
	private boolean IsEmpty(String type) {
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			Statement stmnt = conn.createStatement();
			ResultSet set = stmnt.executeQuery("SELECT * FROM " + type);
			conn.close();
			stmnt.close();
			return set.next();
		}
		catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return true;
		}
	}
	
	private ResultSet GetHighestFrom(String table) throws SQLException{
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			Statement stmnt = conn.createStatement();
			String type = table.equals("enrollments") ? "EID" : "ID";
			ResultSet set = stmnt.executeQuery("SELECT MAX(" + type + ") FROM " + table);
			set.next();
			int highest = set.getInt(1);
			set = stmnt.executeQuery("SELECT * FROM " + table + " WHERE " + type + " = " + highest);
			Object obj = GenerateObject(table, set);
			conn.close();
			stmnt.close();
			System.out.println(set.isClosed());
			return set;
		}
		catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
	private Object GenerateObject(String table, ResultSet set) throws SQLException {
		set.next();
		if(table.equals("students"))
			return new Student(set.getInt("ID"), set.getString("FirstName"), set.getString("LastName"), set.getString("Address"), set.getString("City"), set.getString("State"));
		else if(table.equals("courses"))
			return new Course(set.getInt("ID"), set.getString("Num"), set.getString("Name"), set.getInt("InstructorID"), set.getInt("DepartmentID"));
	}
	
	private ResultSet GetFrom(String table, int id) throws SQLException{
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			Statement stmnt = conn.createStatement();
			String type = table.equals("enrollments") ? "EID" : "ID";
			ResultSet set = stmnt.executeQuery(("SELECT * FROM " + table + " WHERE " + type + " = " + id));
			set.next();
			conn.close();
			return set;
		}
		catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
	private boolean AddToDatabase(Object obj) throws SQLException {
		Connection conn = DriverManager.getConnection(url, user, pass);
		Statement stmnt = conn.createStatement();
		boolean returner;
		if(obj instanceof Student) {
			Student student = (Student)obj;
			String update = "INSERT INTO students (ID, FirstName, LastName, Address, City, State) VALUES (" + student.getId()
						+ ", '" + student.getFirstName() + "', '" + student.getLastName() + "', '" + student.getAddress()
						+ "', '" + student.getCity() + "', '" + student.getState() + "')";
			stmnt.executeUpdate(update);
			returner = true;
		}
		else if(obj instanceof Course) {
			Course course = (Course)obj;
			String update = "INSERT INTO courses (ID, Num, Name, InstructorID, DepartmentID) VALUES (" + course.getId() 
					+ ", '" + course.getNum() + "', '" + course.getName() + "', '" + course.getInstruct() + "', '" 
					+ course.getDepartment() + "')";
			stmnt.executeUpdate(update);
			returner = true;
		}
		else if(obj instanceof Enrollment) {
			Enrollment enrollment = (Enrollment)obj;
			String update = "INSERT INTO enrollments (EID, SID, CID, Year, Semester, Grade) VALUES (" + enrollment.getEnrollmentId()
			+ ", " + enrollment.getStudentId() + ", " + enrollment.getCourseId() + ", " + enrollment.getYear() + ", '" 
			+ enrollment.getSemester() + "', '" + enrollment.getGrade() + "')";
			stmnt.executeUpdate(update);
			returner = true;
		}
		else if(obj instanceof Department) {
			Department department = (Department)obj;
			String update = "INSERT INTO departments (ID, Name) VALUES (" + department.getId() + ", '" + department.getName() + "')";
			stmnt.executeUpdate(update);
			returner = true;
		}
		else if(obj instanceof Instructor) {
			Instructor instructor = (Instructor)obj;
			String update = "INSERT INTO instructors (ID, Name, DepID) VALUES (" + instructor.getId() + ", '" + 
			instructor.getName() + "', '" + instructor.getDepName() + "')";
			stmnt.executeUpdate(update);
			returner = true;
		}
		else
			returner = false;
		conn.close();
		stmnt.close();
		return returner;
	}
	
	/*
	 * NEEEDDDD TOOO FINISH HEREREERER
	 */
	private boolean EditDatabase(Object obj) throws SQLException{
		Connection conn = DriverManager.getConnection(url, user, pass);
		Statement stmnt = conn.createStatement();
		boolean returner;
		if(obj instanceof Student) {
			Student student = (Student)obj;
			String update = "UPDATE students SET FirstName = '" + student.getFirstName() + "', "
					+ "LastName = '" + student.getLastName() + "', Address = '" + student.getAddress() + "', " 
					+ "City = '" + student.getCity() + "', State = '" + student.getState() + "' WHERE ID = " + student.getId();
			stmnt.executeUpdate(update);
			returner = true;
		}
		else if(obj instanceof Course) {
			Course course = (Course)obj;
			String update = "UPDATE courses (ID, Num, Name, InstructorID, DepartmentID) VALUES (" + course.getId() 
					+ ", '" + course.getNum() + "', '" + course.getName() + "', '" + course.getInstruct() + "', '" 
					+ course.getDepartment() + "')";
			stmnt.executeUpdate(update);
			returner = true;
		}
		else if(obj instanceof Enrollment) {
			Enrollment enrollment = (Enrollment)obj;
			String update = "INSERT INTO enrollments (EID, SID, CID, Year, Semester, Grade) VALUES (" + enrollment.getEnrollmentId()
			+ ", " + enrollment.getStudentId() + ", " + enrollment.getCourseId() + ", " + enrollment.getYear() + ", '" 
			+ enrollment.getSemester() + "', '" + enrollment.getGrade() + "')";
			stmnt.executeUpdate(update);
			returner = true;
		}
		else if(obj instanceof Department) {
			Department department = (Department)obj;
			String update = "INSERT INTO departments (ID, Name) VALUES (" + department.getId() + ", '" + department.getName() + "')";
			stmnt.executeUpdate(update);
			returner = true;
		}
		else if(obj instanceof Instructor) {
			Instructor instructor = (Instructor)obj;
			String update = "INSERT INTO instructors (ID, Name, DepID) VALUES (" + instructor.getId() + ", '" + 
			instructor.getName() + "', '" + instructor.getDepName() + "')";
			stmnt.executeUpdate(update);
			returner = true;
		}
		else
			returner = false;
		conn.close();
		stmnt.close();
		return returner;
	}
	
	private ResultSet GetAllCourses() throws SQLException {
		Connection conn = DriverManager.getConnection(url, user, pass);
		Statement stmnt = conn.createStatement();
		ResultSet set = stmnt.executeQuery("SELECT * FROM courses");
		conn.close();
		stmnt.close();
		return set;
	}
	
	private void makeAllInvisible() {
		MenuLabel.setVisible(false);
		addStudentVBOX.setVisible(false);
		editStudentVBOX.setVisible(false);
		postAddStudentVBOX.setVisible(false);
		displayStudentVBOX.setVisible(false);
		searchStudentVBOX.setVisible(false);
		addCourseVBOX.setVisible(false);
		postAddCourseVBOX.setVisible(false);
		editCourseVBOX.setVisible(false);
		searchCourseVBOX.setVisible(false);
		displayCourseVBOX.setVisible(false);
		searchStudentCreateEnrollmentVBOX.setVisible(false);
		createEnrollmentHBOX.setVisible(false);
		postAddEnrollmentVBOX.setVisible(false);
		editEnrollmentHBOX.setVisible(false);
		searchEnrollmentGradeManagementVBOX.setVisible(false);
		displayEnrollmentVBOX.setVisible(false);
		gradeManagementEditVBOX.setVisible(false);
		searchEnrollmentVBOX.setVisible(false);
		displayReportVBOX.setVisible(false);
		searchReportVBOX.setVisible(false);
		addDepartmentVBOX.setVisible(false);
		displayDepartmentVBOX.setVisible(false);
		postAddDepartmentVBOX.setVisible(false);
		editDepartmentVBOX.setVisible(false);
		searchDepartmentVBOX.setVisible(false);
		addInstructorVBOX.setVisible(false);
		searchInstructorVBOX.setVisible(false);
		postAddInstructorVBOX.setVisible(false);
		displayInstructorVBOX.setVisible(false);
		editInstructorVBOX.setVisible(false);
		sortStudentsVBOX.setVisible(false);
		sortCoursesVBOX.setVisible(false);
	}
	
	// if do not truncate is true and if the file isn't empty, the linked list will be
	// updated with the data stored in the StudentFile
	private static void SetUpStudentLinkedList(boolean DoNotTruncate) throws IOException, ClassNotFoundException{
		if(StudentFile.length() > 10 && DoNotTruncate)
			ReadFromStudentFile();
		else
			StudentLinkedList = new ObjLinkedList<Student>();
	}
	
	// linked list will be updated with the data stored in the StudentFile
	private static void ReadFromStudentFile() throws IOException, ClassNotFoundException{
		StudentInput = new FileInputStream(StudentFile); // false for do-truncate
		ObjectStudentInput = new ObjectInputStream(StudentInput);
		StudentLinkedList = (ObjLinkedList<Student>)(ObjectStudentInput.readObject());
		ObjectStudentInput.close();
		StudentInput.close();
	}
	
	// student linked list will replace the existing linked list in student file
	// by truncating file prior to writing
	private static void WriteToStudentFile() throws IOException{
		StudentOutput = new FileOutputStream(StudentFile);
		ObjectStudentOutput = new ObjectOutputStream(StudentOutput);
		ObjectStudentOutput.writeObject(StudentLinkedList);
		ObjectStudentOutput.close();
		StudentOutput.close();
	}
	
	// same as SetUpStudentLinkedList
	private static void SetUpCourseLinkedList(boolean DoNotTruncate) throws IOException, ClassNotFoundException{
		if(CourseFile.length() > 10 && DoNotTruncate)
			ReadFromCourseFile();
		else
			CourseLinkedList = new ObjLinkedList<Course>();
	}
	
	// same as ReadFromStudentFile
	private static void ReadFromCourseFile() throws IOException, ClassNotFoundException{
		CourseInput = new FileInputStream(CourseFile);
		ObjectCourseInput = new ObjectInputStream(CourseInput);
		CourseLinkedList = (ObjLinkedList<Course>)(ObjectCourseInput.readObject());
		ObjectCourseInput.close();
		CourseInput.close();
	}
	
	// same as WriteToStudentFile 
	private static void WriteToCourseFile() throws IOException{
		CourseOutput = new FileOutputStream(CourseFile);
		ObjectCourseOutput = new ObjectOutputStream(CourseOutput);
		ObjectCourseOutput.writeObject(CourseLinkedList);
		ObjectCourseOutput.close();
		CourseOutput.close();
	}
	
	// same as SetUpStudentLinkedList
	private static void SetUpEnrollmentLinkedList(boolean DoNotTruncate) throws IOException, ClassNotFoundException{
		if(EnrollmentFile.length() > 10 && DoNotTruncate)
			ReadFromEnrollmentFile();
		else
			EnrollmentLinkedList = new ObjLinkedList<Enrollment>();
	}
	
	// same as ReadFromStudentFile
	private static void ReadFromEnrollmentFile() throws IOException, ClassNotFoundException{
		EnrollmentInput = new FileInputStream(EnrollmentFile);
		ObjectEnrollmentInput = new ObjectInputStream(EnrollmentInput);
		EnrollmentLinkedList = (ObjLinkedList<Enrollment>)(ObjectEnrollmentInput.readObject());
		ObjectEnrollmentInput.close();
		EnrollmentInput.close();
	}
	
	// same as WriteToEnrollmentFile
	private static void WriteToEnrollmentFile() throws IOException{
		EnrollmentOutput = new FileOutputStream(EnrollmentFile);
		ObjectEnrollmentOutput = new ObjectOutputStream(EnrollmentOutput);
		ObjectEnrollmentOutput.writeObject(EnrollmentLinkedList);
		ObjectEnrollmentOutput.close();
		EnrollmentOutput.close();
	}
	
	private static void SetUpDepartmentList(boolean DoNotTruncate) throws IOException, ClassNotFoundException{
		if(DepartmentFile.length() > 10 && DoNotTruncate)
			ReadFromDepartmentFile();
		else
			DepartmentList = new ArrayList<Department>();
	}
	
	private static void ReadFromDepartmentFile() throws IOException, ClassNotFoundException{
		DepartmentInput = new FileInputStream(DepartmentFile);
		ObjectDepartmentInput = new ObjectInputStream(DepartmentInput);
		DepartmentList = (List<Department>)(ObjectDepartmentInput.readObject());
		DepartmentInput.close();
		ObjectDepartmentInput.close();
	}
	
	private static void WriteToDepartmentFile() throws IOException{
		DepartmentOutput = new FileOutputStream(DepartmentFile);
		ObjectDepartmentOutput = new ObjectOutputStream(DepartmentOutput);
		ObjectDepartmentOutput.writeObject(DepartmentList);
		DepartmentOutput.close();
		ObjectDepartmentOutput.close();
	}
	
	private static void SetUpInstructorList(boolean DoNotTruncate) throws IOException, ClassNotFoundException{
		if(InstructorFile.length() > 10 && DoNotTruncate)
			ReadFromInstructorFile();
		else
			InstructorList = new ArrayList<Instructor>();
	}
	
	private static void ReadFromInstructorFile() throws IOException, ClassNotFoundException{
		InstructorInput = new FileInputStream(InstructorFile);
		ObjectInstructorInput = new ObjectInputStream(InstructorInput);
		InstructorList = (List<Instructor>)(ObjectInstructorInput.readObject());
		InstructorInput.close();
		ObjectInstructorInput.close();
	}
	
	private static void WriteToInstructorFile() throws IOException{
		InstructorOutput = new FileOutputStream(InstructorFile);
		ObjectInstructorOutput = new ObjectOutputStream(InstructorOutput);
		ObjectInstructorOutput.writeObject(InstructorList);
		InstructorOutput.close();
		ObjectInstructorOutput.close();
	}
	
	private static void UpdateHighestFile() throws IOException{
		HighestOutput = new FileOutputStream(HighestFile);
		ObjectHighestOutput = new ObjectOutputStream(HighestOutput);
		ObjectHighestOutput.writeObject(HighestList);
		HighestOutput.close();
		ObjectHighestOutput.close();
	}
	
	private static void SetUpHighestList(boolean doTruncate) throws IOException, ClassNotFoundException{
		if(HighestFile.length() > 10 && !doTruncate)
			ReadFromHighestFile();
		else
			HighestList = new Highest();
	}
	
	private static void ReadFromHighestFile() throws IOException, ClassNotFoundException{
		HighestInput = new FileInputStream(HighestFile);
		ObjectHighestInput = new ObjectInputStream(HighestInput);
		HighestList = (Highest) ObjectHighestInput.readObject();
		HighestInput.close();
		ObjectHighestInput.close();
	}
	// after the deletion of a student, the linked list will update the ids of
	// the students currently remaining
	// starting at index 0, ending in the total number of students
	// at index 0 (first student), the id will become (index + 1), 1
	// will repeat until final index is reached
	// the StudentFile will then be updated via WritetoStudentFile

	
	// will first remove from linked list, then all enrollments containing the student id
	// ids are updated after procedure
	private static void DeleteStudentFromDatabase(int stuID) throws IOException {
		try{
			Connection conn = DriverManager.getConnection(url, user, pass);
			Statement stmnt = conn.createStatement();
			String deleteQuery = "DELETE * FROM students WHERE SID = " + stuID;
			stmnt.executeUpdate(deleteQuery);
			deleteQuery = "DELETE * FROM enrollments WHERE SID = " + stuID;
			stmnt.executeUpdate(deleteQuery);
			conn.close();
			stmnt.close();
		}
		catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		// enrollmentfile and list updated in deleteall...
	}
	// same as method above
	private static void DeleteCourseFromDatabase(int corID) throws IOException {
		try{
			Connection conn = DriverManager.getConnection(url, user, pass);
			Statement stmnt = conn.createStatement();
			String deleteQuery = "DELETE * FROM courses WHERE ID = " + corID;
			stmnt.executeUpdate(deleteQuery);
			deleteQuery = "DELETE * FROM enrollments WHERE CID = " + corID;
			stmnt.executeUpdate(deleteQuery);
			conn.close();
			stmnt.close();
		}
		catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		// enrollmentfile and list updated in deleteall...
	}
	
	// creates a list of integers to hold enrollment indexes
	// loops through the entire list, the index of an enrollment with matching stu id is appended to list
	// for each index, in reverse order (to avoid out of bounds), remove from enrollmentlinked list
	// update enrollmentfile and update enrollment linked list
	private static void DeleteAllEnrollmentsWithStudentInstance(int stuID) throws IOException {
		try{
			Connection conn = DriverManager.getConnection(url, user, pass);
			Statement stmnt = conn.createStatement();
			String deleteQuery = "DELETE * FROM enrollments WHERE SID = " + stuID;
			stmnt.executeUpdate(deleteQuery);
			conn.close();
			stmnt.close();
		}
		catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	// same as method above
	private static void DeleteAllEnrollmentsWithCourseInstance(int corID) throws IOException{
		try{
			Connection conn = DriverManager.getConnection(url, user, pass);
			Statement stmnt = conn.createStatement();
			String deleteQuery = "DELETE * FROM enrollments WHERE CID = " + corID;
			stmnt.executeUpdate(deleteQuery);
			conn.close();
			stmnt.close();
		}
		catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	// if the file is empty then it wont read (avoids error)
//		private static void setUpLinkedList() throws IOException, ClassNotFoundException {
//			if(file.length() > 50 && DoNotTruncate)
//				readFromFile();
//		}
//		
//		// false indicates that the ting needs to truncate
//		private static void writeToFile() throws IOException {
//			outStream = new FileOutputStream(file, false);
//			objectOutputStream  = new ObjectOutputStream(outStream);
//			objectOutputStream.writeObject(StudentLinked);
//			outStream.close();
//			objectOutputStream.close();
//		}
//		
//		// will read the one and only list in file
//		private static void readFromFile() throws IOException, ClassNotFoundException{
//			inStream = new FileInputStream(file);
//			objectInputStream = new ObjectInputStream(inStream);
//			StudentLinked = (LinkedListBoom<Student>)objectInputStream.readObject();
//			inStream.close();
//			objectInputStream.close();
//		}
	
	private static void LOG(String str) {System.out.println(str);}
	private static void LOG(int number) {System.out.println(number);}
	
	// sets all searched flags to false
	private void allJustSearchedFalse() {
		studentJustSearched = false;
		courseJustSearched = false;
		gradeManagementJustSearched = false;
		enrollmentJustSearched = false;
		departmentJustSearched = false;
		instructorJustSearched = false;
	}
	
	// makes add student panel visible
	public void addStudentButtonListener() {
		LOG("Add Student");
		allJustSearchedFalse();
		makeAllInvisible();
		addStudentVBOX.setVisible(true);
	}
	
	public ResultSet GetAllDepartments() {
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			Statement stmnt = conn.createStatement();
			ResultSet set = stmnt.executeQuery("SELECT * FROM departments");
			conn.close();
			stmnt.close();
			return set;
		} catch (SQLException e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
	// if at least one department and list have been made, add course panel will be visible
	public void addCourseButtonListener() throws SQLException {
		LOG("Add Class");
		
		allJustSearchedFalse();
		makeAllInvisible();
		
		if(IsEmpty("departments")) ErrorMessage("No Departments have been made");
		else if(IsEmpty("instructors")) ErrorMessage("No Instructors have been made");
		// might need to fix this else (no else, action pushed above if)
		else {
			addCourseVBOX.setVisible(true);
			addCourseDepartmentChoiceBox.getItems().removeAll(addCourseDepartmentChoiceBox.getItems());
			addCourseDepartmentChoiceBox.setValue("");
			ResultSet departments = GetAllDepartments();
			while(departments.next())
				addCourseDepartmentChoiceBox.getItems().add(departments.getString("name"));
			addCourseInstructorChoiceBox.getItems().removeAll(addCourseInstructorChoiceBox.getItems());
			addCourseInstructorChoiceBox.setValue("");
		}
	}
	
	// if at least one course and student has been made, enrollment panel will show
	public void addEnrollmentButtonListener() {
		LOG("Add Enrollment");
		allJustSearchedFalse();
		makeAllInvisible();
		if(IsEmpty("students")) ErrorMessage("No Students have been made");
		else if(IsEmpty("courses")) ErrorMessage("No Courses have been made");
		else searchStudentCreateEnrollmentVBOX.setVisible(true);
	}
	
	public void addDepartmentButtonListener() {
		LOG("Add Department");
		allJustSearchedFalse();
		makeAllInvisible();
		addDepartmentVBOX.setVisible(true);
	}
	
	public void addInstructorButtonListener() throws SQLException {
		LOG("Add instructor");
		allJustSearchedFalse();
		makeAllInvisible();
		if(IsEmpty("departments")) ErrorMessage("No Departments have been made");
		else {
			addInstructorVBOX.setVisible(true);
			addInstructorDepartmentChoiceBox.getItems().removeAll(addInstructorDepartmentChoiceBox.getItems());
			ResultSet departments = GetAllDepartments();
			while(departments.next()) {
				String option = departments.getInt("ID") + "\t" + departments.getString("Name");
				addInstructorDepartmentChoiceBox.getItems().add(option);
			}
		}
	}
	
	public void searchStudentButtonListener() throws IOException {
		LOG("Search Student");
		allJustSearchedFalse();
		MenuLabel.setText("Search Student");
		makeAllInvisible();
		if(IsEmpty("students")) ErrorMessage("No Students have been made");
		else searchStudentVBOX.setVisible(true);
		
	}
	
	public void searchCourseButtonListener() {
		LOG("Search Class");
		allJustSearchedFalse();
		MenuLabel.setText("Search Course");
		makeAllInvisible();
		if(IsEmpty("courses")) ErrorMessage("No Courses have been made");
		else searchCourseVBOX.setVisible(true);
	}
	
	public void searchEnrollmentButtonListener() {
		LOG("Search Enrollment");
		allJustSearchedFalse();
		MenuLabel.setText("Search Enrollment");
		makeAllInvisible();
		if(IsEmpty("enrollments")) ErrorMessage("No Enrollments have been made");
		else searchEnrollmentVBOX.setVisible(true);
	}
	
	public void searchDepartmentButtonListener() {
		LOG("Search Department");
		allJustSearchedFalse();
		MenuLabel.setText("Search Department");
		makeAllInvisible();
		if(IsEmpty("departments")) ErrorMessage("No Departments have been made");
		else searchDepartmentVBOX.setVisible(true);
	}
	
	public void searchInstructorButtonListener() {
		LOG("Search Instructor");
		allJustSearchedFalse();
		MenuLabel.setText("Search Instructor");
		makeAllInvisible();
		if(IsEmpty("instructors")) ErrorMessage("No Instructors have been made");
		else searchInstructorVBOX.setVisible(true);
	}
	
	public void manageGradesButtonListener() {
		LOG("Manage Grades");
		allJustSearchedFalse();
		MenuLabel.setText("Manage Grades");
		makeAllInvisible();
		searchEnrollmentGradeManagementVBOX.setVisible(true);
		if(IsEmpty("enrollments"))
			ErrorMessage("No enrollments have been made");
		else {
			gradeManagementYear.getItems().removeAll(gradeManagementYear.getItems());
			gradeManagementYear.getItems().addAll("2019", "2020", "2021", "2022");
			gradeManagementSemester.getItems().removeAll(createEnrollmentSemesterChoiceBox.getItems());
			gradeManagementSemester.getItems().addAll("Spring", "Summer", "Fall", "Winter");
		}
	}
	
	public void reportButtonListener() throws IOException, SQLException{
		LOG("Report");
		allJustSearchedFalse();
		MenuLabel.setText("Report");
		if(IsEmpty("enrollments")) {
			ErrorMessage("No enrollments have been made");
		}
		else {
			makeAllInvisible();
			searchReportVBOX.setVisible(true);
			searchReportCourseChoiceBox.getItems().removeAll(searchReportCourseChoiceBox.getItems());
			ResultSet set = GetAllCourses();
			int id;
			String num;
			String option;
			while(set.next()) {
				id = set.getInt("ID");
				num = set.getString("Num");
				option = id + " " + num;
				searchReportCourseChoiceBox.getItems().add(option);
			}
			
			searchReportYearChoiceBox.getItems().removeAll(searchReportYearChoiceBox.getItems());
			searchReportYearChoiceBox.getItems().addAll("2019","2020","2021","2022");
		}	
	}
	
	public void cancelButtonListener() {
		makeAllInvisible();
		allJustSearchedFalse();
		MenuLabel.setVisible(true);
		MenuLabel.setText("Menu");
		wipeStudentFormInfo();
		wipeCourseFormInfo();
		// wipeEnrollmentFormInfo();
	}
	
	// Student
	
	public String displayAllStudents() {
		String message = "";
		for(int i = 0; i < StudentLinkedList.size(); i++)
			message += StudentLinkedList.get(i) + "\n";
		return message;
	}
	
	public void createStudentButtonListener() throws IOException, SQLException{
		LOG("Create Student");
		
		String firstName,lastName,address,city,state;
		firstName = addStudentFirstNameTextField.getText().trim();
		if(firstName.equals(""))
			return;
		lastName = addStudentLastNameTextField.getText().trim();
		if(lastName.equals(""))
			return;
		address = addStudentAddressTextField.getText().trim();
		if(address.equals(""))
			return;
		city = addStudentCityTextField.getText().trim();
		if(city.equals("")) 
			return;
		state = addStudentStateTextField.getText().trim();
		if(state.equals(""))
			return;
		
		// if list is empty, id is 1 (first), else its the last id + 1
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// int id = (StudentLinkedList.isEmpty()) ? 1 : getHighestStudentID() + 1;
		int id = (IsEmpty("students") || HighestList.getSID() != 0) ? HighestList.getSID() + 1 : 1;
		Student student = new Student(id, firstName, lastName, address, city, state);
		HighestList.setSID(student.getId());
		UpdateHighestFile();
		
		AddToDatabase(student);
		wipeStudentFormInfo();
		postAddStudent();
	}
	
	public void studentSearchButtonListener() throws  SQLException, IOException{
		try {
			searchStudentRequestedID = Integer.parseInt(studentSearchID.getText());
		}
		catch(NumberFormatException e) {
			ErrorMessage("Invalid Search, IDs are numerical");
		}
		
		if(IdIsInDB(searchStudentRequestedID, "students")){
			studentJustSearched = true;
			displaySearchedStudent(searchStudentRequestedID);
		}
		else 
			ErrorMessage("Invalid Search");
		
	}
	
	private void ErrorMessage(String error) {
		makeAllInvisible();
		MenuLabel.setVisible(true);
		MenuLabel.setText(error);
	}
	
	private void postAddStudent() throws SQLException{
		LOG("Post add student");
		
		makeAllInvisible();
		postAddStudentVBOX.setVisible(true);
		
		ResultSet set = GetHighestFrom("students");
		System.out.println(set.isClosed());
		set.next();	
		postAddStudentID.setText(set.getString("ID"));
		postAddStudentFirstName.setText(set.getString("FirstName"));
		postAddStudentLastName.setText(set.getString("LastName"));
		postAddStudentAddress.setText(set.getString("Address"));
		postAddStudentCity.setText(set.getString("City"));
		postAddStudentState.setText(set.getString("State"));
	}
	
	private void wipeStudentFormInfo() {
		addStudentFirstNameTextField.setText("");
		addStudentLastNameTextField.setText("");
		addStudentAddressTextField.setText("");
		addStudentCityTextField.setText("");
		addStudentStateTextField.setText("");
	}
	
	public void postAddStudentEditButtonListener() throws SQLException{
		makeAllInvisible();
		editStudentVBOX.setVisible(true);
		studentRecordNumberEditing = GetHighestID("students");
		ResultSet set = GetHighestFrom("students");
		set.next();	
		editStudentFirstName.setText(set.getString("FirstName"));
		editStudentLastName.setText(set.getString("LastName"));
		editStudentAddress.setText(set.getString("Address"));
		editStudentCity.setText(set.getString("City"));
		editStudentState.setText(set.getString("State"));
	}
	
	public void editStudentSaveChangesButtonListener() throws IOException, SQLException{
		String firstName = editStudentFirstName.getText();
		String lastName = editStudentLastName.getText();
		String address = editStudentAddress.getText();
		String city = editStudentCity.getText();
		String state = editStudentState.getText();
		
		Student student = new Student(studentRecordNumberEditing, firstName, lastName, address,city,state);
		
		EditDatabase(student);
		displaySearchedStudent(studentRecordNumberEditing);
	}
	
	private void displaySearchedStudent(int recordNumber) throws IOException, SQLException{
		ResultSet set = GetFrom("students", recordNumber);
		
		makeAllInvisible();
		displayStudentVBOX.setVisible(true);
		
		set.next();
		displayStudentID.setText(set.getString("ID"));
		displayStudentFirstName.setText(set.getString("FirstName"));
		displayStudentLastName.setText(set.getString("LastName"));
		displayStudentAddress.setText(set.getString("Address"));
		displayStudentCity.setText(set.getString("City"));
		displayStudentState.setText(set.getString("State"));
	}
	
	public void displayStudentEditButtonListener() throws SQLException{
		makeAllInvisible();
		editStudentVBOX.setVisible(true);

		System.out.println(studentJustSearched + " " + studentRecordNumberEditing + " " + searchStudentRequestedID);
		
		studentRecordNumberEditing = (searchStudentRequestedID == 0 || !studentJustSearched) 
									? GetHighestID("students")
									: searchStudentRequestedID;
		
		System.out.println(studentJustSearched + " " + studentRecordNumberEditing + " " + searchStudentRequestedID);
		
		ResultSet set = GetFrom("students", studentRecordNumberEditing);
		
		set.next();
		editStudentFirstName.setText(set.getString("FirstName"));
		editStudentLastName.setText(set.getString("LastName"));
		editStudentAddress.setText(set.getString("Address"));
		editStudentCity.setText(set.getString("City"));
		editStudentState.setText(set.getString("State"));
	}
	
	public void deleteStudentButtonListener() throws IOException {
		int id = studentRecordNumberEditing;
		DeleteStudentFromDatabase(id);
		ErrorMessage("Student Deleted");
	}
	
	/////////////
	// course
	public void createCourseButtonListener() throws IOException{
		LOG("Creating course");
		String courseNumber = addCourseNumber.getText();
		String courseName = addCourseName.getText();
		String courseInstructor = addCourseInstructorChoiceBox.getValue();
		String courseDepartment = addCourseDepartmentChoiceBox.getValue();
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// int id = (CourseLinkedList.isEmpty()) ? 1 : getHighestCourseID() + 1;
		int id = (CourseLinkedList.isEmpty() || HighestList.getCID() != 0) ? HighestList.getCID() + 1 : 1;
		Course course = new Course(id, courseNumber, courseName, courseInstructor, courseDepartment);
		HighestList.setCID(course.getId());
		UpdateHighestFile();
		
		CourseLinkedList.add(course);
		WriteToCourseFile();
		wipeCourseFormInfo();
		postAddCourse();
	}
	
	private void postAddCourse() throws IOException{
		makeAllInvisible();
		postAddCourseVBOX.setVisible(true);
		
		Course course = CourseLinkedList.getLast();
		postAddCourseID.setText(String.valueOf(course.getId()));
		postAddCourseNumber.setText(course.getNum());
		postAddCourseName.setText(course.getName());
		postAddCourseInstructor.setText(course.getInstruct());
		postAddCourseDepartment.setText(course.getDepartment());
	}
	
	private void wipeCourseFormInfo() {
		addCourseNumber.setText("");
		addCourseName.setText("");
		addCourseInstructorChoiceBox.setValue("");
		addCourseDepartmentChoiceBox.setValue("");
	}
	
	public void postAddCourseEditButtonListener() throws IOException{
		makeAllInvisible();
		editCourseVBOX.setVisible(true);
		courseRecordNumberEditing = CourseLinkedList.getLast().getId();
		Course course = CourseLinkedList.getLast();
		
		editCourseNumber.setText(course.getNum());
		editCourseName.setText(course.getName());
		editCourseDepartmentChoiceBox.getItems().removeAll(editCourseDepartmentChoiceBox.getItems());
		editCourseDepartmentChoiceBox.setValue("");
		DepartmentList.forEach(name -> editCourseDepartmentChoiceBox.getItems().add(name.getName()));
		editCourseInstructorChoiceBox.getItems().removeAll(editCourseInstructorChoiceBox.getItems());
		editCourseInstructorChoiceBox.setValue("");
	}
	
	public void updateInstructorListEditCourseChoiceBox() {
		String depName = editCourseDepartmentChoiceBox.getValue();
		editCourseInstructorChoiceBox.getItems().removeAll(editCourseInstructorChoiceBox.getItems());
		if(depName.equals(""))
			editCourseInstructorChoiceBox.getItems().add("No Department Selected");
		else {
			InstructorList.forEach(instructor -> 
			{
				if(instructor.getDepName().equalsIgnoreCase(depName)) 
					editCourseInstructorChoiceBox.getItems().add(instructor.getName());
			});
			editCourseInstructorChoiceBox.setValue("");
		}
	}
	
	public void updateInstructorListAddCourseChoiceBox() {
		String depName = addCourseDepartmentChoiceBox.getValue();
		addCourseInstructorChoiceBox.getItems().removeAll(addCourseInstructorChoiceBox.getItems());
		if(depName.equals(""))
			addCourseInstructorChoiceBox.getItems().add("No Department Selected");
		else {
			InstructorList.forEach(instructor -> 
			{
				if(instructor.getDepName().equalsIgnoreCase(depName)) 
					addCourseInstructorChoiceBox.getItems().add(instructor.getName());
			});
			addCourseInstructorChoiceBox.setValue("");
		}
	
	}
	
	public void editCourseSaveChangesButton() throws IOException{
		String number = editCourseNumber.getText();
		String name = editCourseName.getText();
		String instructor = editCourseInstructorChoiceBox.getValue();
		String department = editCourseDepartmentChoiceBox.getValue();
		
		Course course = new Course(courseRecordNumberEditing, number, name, instructor, department);
		CourseLinkedList.get(course, true).copy(course);
		WriteToCourseFile();
		displaySearchedCourse(courseRecordNumberEditing);
	}
	
	public void courseSearchButtonListener() throws IOException{
		try{
			searchCourseRequestedID = Integer.valueOf(courseSearchID.getText());
		}
		catch(NumberFormatException e) {
			ErrorMessage("Invalid search, IDs are numerical");
		}
		
		boolean isInList = false;
		for(int i = 0; i < CourseLinkedList.size(); i++)
			if(searchCourseRequestedID == CourseLinkedList.get(i).getId()) isInList = true;
		
		if(isInList) {
			courseJustSearched = true;
			displaySearchedCourse(searchCourseRequestedID);
		}
		else 
			ErrorMessage("Invalid Search");
		
	}
	
	private void displaySearchedCourse(int recordNumber) throws IOException{
		Course search = new Course(recordNumber);
		Course course = CourseLinkedList.get(search, true);
		
		makeAllInvisible();
		displayCourseVBOX.setVisible(true);
		
		displayCourseID.setText(String.valueOf(course.getId()));
		displayCourseNumber.setText(course.getNum());
		displayCourseName.setText(course.getName());
		displayCourseInstructor.setText(course.getInstruct());
		displayCourseDepartment.setText(course.getDepartment());
	}
	
	public void displayCourseEditButtonListener() throws IOException{
		makeAllInvisible();
		editCourseVBOX.setVisible(true);
		
		courseRecordNumberEditing = (searchCourseRequestedID == 0 || !courseJustSearched) 
										? CourseLinkedList.getLast().getId()
										: searchCourseRequestedID;
		
		Course search = new Course(courseRecordNumberEditing);
		Course course = CourseLinkedList.get(search, true);
		
		editCourseNumber.setText(course.getNum());
		editCourseName.setText(course.getName());
		editCourseDepartmentChoiceBox.getItems().removeAll(editCourseDepartmentChoiceBox.getItems());
		editCourseDepartmentChoiceBox.setValue("");
		DepartmentList.forEach(name -> editCourseDepartmentChoiceBox.getItems().add(name.getName()));
		editCourseInstructorChoiceBox.getItems().removeAll(editCourseInstructorChoiceBox.getItems());
		editCourseInstructorChoiceBox.setValue("");
	}
	
	public void deleteCourseButtonListener() throws IOException {
		int id = courseRecordNumberEditing;
		DeleteCourseFromDatabase(id);
		ErrorMessage("Course Deleted");
	}
	
	private int getHighestCourseID() {
		int[] ids = new int[CourseLinkedList.size()];
		for(int i = 0; i < CourseLinkedList.size(); i++)
			ids[i] = CourseLinkedList.get(i).getId();
		Arrays.sort(ids);
		return ids[ids.length - 1];
	}
	
	// 10/24 LEFT OFF HERE
	//////
	// enrollment
	public void createEnrollmentStudentSearchButtonListener() throws IOException{
		try {
			createEnrollmentRequestedStudentID = Integer.valueOf(enrollmentStudentSearchID.getText());
		}
		catch(NumberFormatException e) {
			ErrorMessage("Invalid search, IDs are numerical");
		}
		
		boolean isInStuList = false;
		for(int i = 0; i < StudentLinkedList.size(); i++)
			if(createEnrollmentRequestedStudentID == StudentLinkedList.get(i).getId()) isInStuList = true;
		
		if(StudentLinkedList.size() == 0) 
			ErrorMessage("No students have been created");
		else if(!isInStuList)
			ErrorMessage("Invalid Student Search");
		else if(CourseLinkedList.size() == 0)
			ErrorMessage("No courses have been created");
		else
			enrollmentCanBeCreated();
	}
	
	private void enrollmentCanBeCreated() throws IOException{
		makeAllInvisible();
		createEnrollmentHBOX.setVisible(true);
		
		Student search = new Student(createEnrollmentRequestedStudentID);
		Student student = StudentLinkedList.get(search, true);
		
		createEnrollmentDisplayStudentID.setText(String.valueOf(student.getId()));
		createEnrollmentDisplayStudentFirstName.setText(student.getFirstName());
		createEnrollmentDisplayStudentLastName.setText(student.getLastName());
		createEnrollmentDisplayStudentAddress.setText(student.getAddress());
		createEnrollmentDisplayStudentCity.setText(student.getCity());
		createEnrollmentDisplayStudentState.setText(student.getState());
		
		createEnrollmentYearChoiceBox.getItems().removeAll(createEnrollmentYearChoiceBox.getItems());
		createEnrollmentYearChoiceBox.getItems().addAll("2019", "2020", "2021", "2022");
		createEnrollmentSemesterChoiceBox.getItems().removeAll(createEnrollmentSemesterChoiceBox.getItems());
		createEnrollmentSemesterChoiceBox.getItems().addAll("Spring", "Summer", "Fall", "Winter");
		addAllCourseEnrollmentOptions();
		
	}
	
	private void addAllCourseEnrollmentOptions() throws IOException {
		createEnrollmentCourseChoiceBox.getItems().removeAll(createEnrollmentCourseChoiceBox.getItems());
		
		for(int i = 0; i < CourseLinkedList.size(); i++) {
			int id = CourseLinkedList.get(i).getId();
			String num = CourseLinkedList.get(i).getNum();
			String option = String.valueOf(id) + "   " + num;
			
			createEnrollmentCourseChoiceBox.getItems().add(option);
		}
	}
	
	public void createEnrollmentButtonListener() throws IOException{
		LOG("Create enrollment");
		// enrollment id, course id, student id, year, semester, grade
		int courseID = extractID(createEnrollmentCourseChoiceBox.getValue().trim());
		int studentID = Integer.valueOf(createEnrollmentDisplayStudentID.getText().trim());
		String semester = createEnrollmentSemesterChoiceBox.getValue().trim();
		int year = Integer.parseInt(createEnrollmentYearChoiceBox.getValue().trim());
		
		int id = (EnrollmentLinkedList.isEmpty() || HighestList.getEID() != 0) ? HighestList.getEID() + 1 : 1;
		Enrollment enrollment = new Enrollment(id, courseID, studentID, year, semester);
		HighestList.setEID(enrollment.getEnrollmentId());
		UpdateHighestFile();
		
		EnrollmentLinkedList.add(enrollment);
		WriteToEnrollmentFile();
		postAddEnrollment();
	}
	
	private void postAddEnrollment() throws IOException{
		makeAllInvisible();
		postAddEnrollmentVBOX.setVisible(true);
		
		Enrollment enrollment = EnrollmentLinkedList.getLast();
		
		postAddEnrollmentEID.setText(String.valueOf(enrollment.getEnrollmentId()));
		postAddEnrollmentCID.setText(String.valueOf(enrollment.getCourseId()));
		postAddEnrollmentSID.setText(String.valueOf(enrollment.getStudentId()));
		postAddEnrollmentYear.setText(String.valueOf(enrollment.getYear()));
		postAddEnrollmentSemester.setText(enrollment.getSemester());
		postAddEnrollmentGrade.setText(enrollment.getGrade());
	}
	
	public void postAddEnrollmentEditButtonListener() throws IOException{
		makeAllInvisible();
		editEnrollmentHBOX.setVisible(true);
		
		enrollmentRecordNumberEditing = EnrollmentLinkedList.getLast().getEnrollmentId();
		
		Enrollment enrollment = EnrollmentLinkedList.getLast();
		Student search = new Student(enrollment.getStudentId());
		Student student = StudentLinkedList.get(search, true);
		
		editEnrollmentDisplayStudentID.setText(String.valueOf(student.getId()));
		editEnrollmentDisplayStudentFirstName.setText(student.getFirstName());
		editEnrollmentDisplayStudentLastName.setText(student.getLastName());
		editEnrollmentDisplayStudentAddress.setText(student.getAddress());
		editEnrollmentDisplayStudentCity.setText(student.getCity());
		editEnrollmentDisplayStudentState.setText(student.getState());
		
		editEnrollmentYearChoiceBox.getItems().removeAll(createEnrollmentYearChoiceBox.getItems());
		editEnrollmentYearChoiceBox.getItems().addAll("2019", "2020", "2021", "2022");
		editEnrollmentYearChoiceBox.setValue(String.valueOf(enrollment.getYear()));
		editEnrollmentSemesterChoiceBox.getItems().removeAll(createEnrollmentSemesterChoiceBox.getItems());
		editEnrollmentSemesterChoiceBox.getItems().addAll("Spring", "Summer", "Fall", "Winter");
		editEnrollmentSemesterChoiceBox.setValue(enrollment.getSemester());
		setUpEditForEnrollmentCourse(enrollment.getCourseId());
	}
	
	public void gradeManagementSearchButtonListener() throws IOException, SQLException{
		if(IsEmpty("enrollments")) {
			ErrorMessage("No enrollments created");
			return;
		}
		int studentID = 0;
		int courseID = 0;
		int year = 0;
		String semester = "";
		
		try {
			studentID = Integer.parseInt(gradeManagementStudentID.getText().trim());
			courseID = Integer.parseInt(gradeManagementCourseID.getText().trim());
			year = Integer.parseInt(gradeManagementYear.getValue().trim());
			semester = gradeManagementSemester.getValue().trim();
		}
		catch(NumberFormatException e) {
			System.out.println(e.getMessage());
			ErrorMessage("Invalid Search");
			return;
		}
		
		if(findEnrollment(courseID, studentID, year, semester)) {
			gradeManagementJustSearched = true;
			displaySearchedStudentGM();
		}
		else {
			ErrorMessage("Invalid search");
		}
	}
	
	private void displaySearchedStudentGM() throws IOException{
		makeAllInvisible();
		gradeManagementEditVBOX.setVisible(true);
		Enrollment enrollment = searchedEnrollment;
		gradeManagementDisplayEID.setText(String.valueOf(enrollment.getEnrollmentId()));
		gradeManagementDisplayCID.setText(String.valueOf(enrollment.getCourseId()));
		gradeManagementDisplaySID.setText(String.valueOf(enrollment.getStudentId()));
		gradeManagementDisplayYear.setText(String.valueOf(enrollment.getYear()));
		gradeManagementDisplaySemester.setText(enrollment.getSemester());
		gradeManagementGradeChoiceBox.getItems().removeAll(gradeManagementGradeChoiceBox.getItems());
		gradeManagementGradeChoiceBox.getItems().addAll("A", "B", "C", "D", "F");
		gradeManagementGradeChoiceBox.setValue(enrollment.getGrade());
		gradeManagementSaveIndicator.setText("");
	}
	
	private boolean findEnrollment(int CID, int SID, int year, String semester) throws SQLException{
		Connection conn = DriverManager.getConnection(url, user, pass);
		Statement stmnt = conn.createStatement();
		String query = "SELECT * FROM enrollments WHERE CID = " + CID + " AND SID = " + SID + " AND Year = " + year + " AND Semester LIKE '%" + semester + "%'";
		ResultSet set = stmnt.executeQuery(query);
		conn.close();
		stmnt.close();
		return set.next();
	}
	
	private boolean findEnrollment(int course, int year) throws SQLException{
		Connection conn = DriverManager.getConnection(url, user, pass);
		Statement stmnt = conn.createStatement();
		String query = "SELECT * FROM enrollments WHERE CID = " + course + " AND Year = " + year ;
		ResultSet set = stmnt.executeQuery(query);
		conn.close();
		stmnt.close();
		return set.next();
	}
	
	private List<Integer> findEnrollmentIDs(int course, int year) throws SQLException{
		List<Integer> ids = new ArrayList<Integer>();
		Connection conn = DriverManager.getConnection(url, user, pass);
		Statement stmnt = conn.createStatement();
		String query = "SELECT ID FROM enrollments WHERE CID = " + course + " AND Year = " + year ;
		ResultSet set = stmnt.executeQuery(query);
		
		while(set.next())
			ids.add(set.getInt("ID"));
		
		conn.close();
		stmnt.close();
		return ids;
	}
	
	public int getHighestEnrollmentID() throws SQLException {
		Connection conn = DriverManager.getConnection(url, user, pass);
		Statement stmnt = conn.createStatement();
		String query = "SELECT MAX(ID) FROM enrollments";
		ResultSet set = stmnt.executeQuery(query);
		conn.close();
		stmnt.close();
		if(set.next())
			return set.getInt(1);
		return 0;
	}
	
	public void gradeManagementSaveChangesButtonListener() throws IOException, SQLException{
		int enrollmentID = Integer.valueOf(gradeManagementDisplayEID.getText().trim());
		Connection conn = DriverManager.getConnection(url, user, pass);
		Statement stmnt = conn.createStatement();
		String query = "UPDATE enrollments SET Grade = '" + gradeManagementGradeChoiceBox.getValue().trim() + "' WHERE EID = " + enrollmentID;
		stmnt.executeUpdate(query);
		ResultSet updatedSet = stmnt.executeQuery("SELECT * FROM enrollments where EID = " + enrollmentID);
		conn.close();
		stmnt.close();
		updatedSet.next();
		gradeManagementSaveIndicator.setText("Grade Saved: " + updatedSet.getString("Grade"));
	}
	
	public void editEnrollmentSaveChangesButtonListener() throws IOException, SQLException{
		int courseID = extractID(editEnrollmentCourseChoiceBox.getValue().trim());
		int studentID = Integer.valueOf(editEnrollmentDisplayStudentID.getText().trim());
		int enrollmentID = enrollmentRecordNumberEditing;
		String semester = editEnrollmentSemesterChoiceBox.getValue().trim();
		int year = Integer.parseInt(editEnrollmentYearChoiceBox.getValue().trim());
		
		Connection conn = DriverManager.getConnection(url, user, pass);
		Statement stmnt = conn.createStatement();
		// not sure if this is smart but doing multiple queries
		String query = "UPDATE enrollments SET CID = " + courseID + " WHERE EID = " + enrollmentID;
		stmnt.executeUpdate(query);
		query = "UPDATE enrollments SET SID = " + studentID + " WHERE EID = " + enrollmentID;
		stmnt.executeUpdate(query);
		query = "UPDATE enrollments SET Semester = '" + semester + "' WHERE EID = " + enrollmentID;
		stmnt.executeUpdate(query);
		query = "UPDATE enrollments SET Year = " + year + " WHERE EID = " + enrollmentID;
		stmnt.executeUpdate(query);
		conn.close();
		stmnt.close();
		displaySearchedEnrollment(enrollmentRecordNumberEditing);
	}
	
	public void displayEnrollmentEditButtonListener() throws IOException{
		makeAllInvisible();
		editEnrollmentHBOX.setVisible(true);
		
		enrollmentRecordNumberEditing = (searchEnrollmentRequestedID == 0 || !enrollmentJustSearched) 
											? EnrollmentLinkedList.getLast().getEnrollmentId()
											: searchEnrollmentRequestedID;
		
		Enrollment searchE = new Enrollment(enrollmentRecordNumberEditing);
		Enrollment enrollment = EnrollmentLinkedList.get(searchE, true);
		Student searchS = new Student(enrollment.getStudentId());
		Student student = StudentLinkedList.get(searchS, true);
		
		editEnrollmentDisplayStudentID.setText(String.valueOf(student.getId()));
		editEnrollmentDisplayStudentFirstName.setText(student.getFirstName());
		editEnrollmentDisplayStudentLastName.setText(student.getLastName());
		editEnrollmentDisplayStudentAddress.setText(student.getAddress());
		editEnrollmentDisplayStudentCity.setText(student.getCity());
		editEnrollmentDisplayStudentState.setText(student.getState());
		
		editEnrollmentYearChoiceBox.getItems().removeAll(createEnrollmentYearChoiceBox.getItems());
		editEnrollmentYearChoiceBox.getItems().addAll("2019", "2020", "2021", "2022");
		editEnrollmentYearChoiceBox.setValue(String.valueOf(enrollment.getYear()));
		editEnrollmentSemesterChoiceBox.getItems().removeAll(createEnrollmentSemesterChoiceBox.getItems());
		editEnrollmentSemesterChoiceBox.getItems().addAll("Spring", "Summer", "Fall", "Winter");
		editEnrollmentSemesterChoiceBox.setValue(enrollment.getSemester());
		setUpEditForEnrollmentCourse(enrollment.getCourseId());
	}
	
	private void displaySearchedEnrollment(int recordNumber) throws IOException{
		Enrollment searchE = new Enrollment(recordNumber);
		Enrollment enrollment = EnrollmentLinkedList.get(searchE, true);
		makeAllInvisible();
		displayEnrollmentVBOX.setVisible(true);
		
		displayEnrollmentEID.setText(String.valueOf(enrollment.getEnrollmentId()));
		displayEnrollmentCID.setText(String.valueOf(enrollment.getCourseId()));
		displayEnrollmentSID.setText(String.valueOf(enrollment.getStudentId()));
		displayEnrollmentYear.setText(String.valueOf(enrollment.getYear()));
		displayEnrollmentSemester.setText(enrollment.getSemester());
		displayEnrollmentGrade.setText(enrollment.getGrade());		
	}
	
	public void enrollmentSearchButtonListener() throws IOException{
		searchEnrollmentRequestedID = Integer.valueOf(enrollmentSearchID.getText());
		
		if(EnrollmentLinkedList.isEmpty())
			ErrorMessage("No enrollments have been created");
		
		boolean isInList = false;
		for(int i = 0; i < EnrollmentLinkedList.size(); i++)
			if(EnrollmentLinkedList.get(i).getEnrollmentId() == searchEnrollmentRequestedID) isInList = true;
		
		
		if (isInList){
			enrollmentJustSearched = true;
			displaySearchedEnrollment(searchEnrollmentRequestedID);
		}
		else
			ErrorMessage("Invalid search");
	}
	
	private void setUpEditForEnrollmentCourse(int courseID) throws IOException{
		editEnrollmentCourseChoiceBox.getItems().removeAll(editEnrollmentCourseChoiceBox.getItems());
		
		for(int i = 0; i < CourseLinkedList.size(); i++) {
			int id = CourseLinkedList.get(i).getId();
			String num = CourseLinkedList.get(i).getNum();
			String option = String.valueOf(id) + "   " + num;
			
			editEnrollmentCourseChoiceBox.getItems().add(option);
		}
		
		Course search = new Course(courseID);
		String num = CourseLinkedList.get(search, true).getNum();
		String option = String.valueOf(courseID) + "   " + num;
		editEnrollmentCourseChoiceBox.setValue(option);
	}
	////////
	// report
	public void reportSearchButtonListener() throws IOException, SQLException{
		makeAllInvisible();
		displayReportVBOX.setVisible(true);
		listOfStudentInfo.getItems().removeAll(listOfStudentInfo.getItems());
		int CID = extractID(searchReportCourseChoiceBox.getValue().trim());
		int year = Integer.parseInt(searchReportYearChoiceBox.getValue().trim());
		if(findEnrollment(CID, year)) {
			listOfReportEnrollmentIds = findEnrollmentIDs(CID, year);
			if(listOfReportEnrollmentIds.isEmpty()) {
				ErrorMessage("Invalid Search");
				return;
			}
			
			Enrollment searchE = new Enrollment(listOfReportEnrollmentIds.get(0));
			Enrollment enrollment = EnrollmentLinkedList.get(searchE, true);
			
			String header;
			
			Course searchC = new Course(enrollment.getCourseId());
			Course course = CourseLinkedList.get(searchC, true);
			header = "Course: " + course.getName() + " ; " + enrollment.getYear() + " ; " + enrollment.getSemester();
			ReportClassName.setText(header);
			
			String str;
			Student searchS;
			Student student;
			for(int EID : listOfReportEnrollmentIds) {
				searchE = new Enrollment(EID);
				enrollment = EnrollmentLinkedList.get(searchE, true);
				searchS = new Student(enrollment.getStudentId());
				student = StudentLinkedList.get(searchS, true);
				
				str = "Student ID: " + student.getId() + "; Student Name: " + student.getLastName() + ", " + student.getFirstName()
						+ "; Grade: " + enrollment.getGrade();
				listOfStudentInfo.getItems().add(str);
			}
			
		}
		else {
			ErrorMessage("There are no enrollments with those details.");
		}
	}
	
	private int extractID(String string) {
		String str = "";
		for(int i = 0; i < string.length(); i++) {
			try {
				Integer.parseInt(String.valueOf(string.charAt(i)));
			}
			catch(NumberFormatException e) {
				break;
			}
			str += string.charAt(i);
		}
		System.out.println(str);
		return Integer.parseInt(str);
	}
	

	
	// static Department department;
	// static DepartmentFile departmentFile;
	
	// static Instructor instructor;
	// static InstructorFile instructorFile;
	
	///////////////
	// department
	public void createDepartmentButtonListener() throws IOException {
		String name = addDepartmentName.getText();
		Department dep = new Department(DepartmentList.size() + 1, name);
		DepartmentList.add(dep);
		WriteToDepartmentFile();
		wipeDepartmentFormInfo();
		postAddDepartment();
		
	}
	
	public void departmentSearchButtonListener() throws IOException{
		try {
			searchDepartmentRequestedID = Integer.parseInt(searchDepartmentTextField.getText());
		}
		catch(NumberFormatException e) {
			ErrorMessage("Invalid Search, IDs are numerical");
		}
		
		if(searchDepartmentRequestedID < 1 || searchDepartmentRequestedID > DepartmentList.size())
			ErrorMessage("Invalid Search");
		else {
			departmentJustSearched = true;
			displaySearchedDepartment(searchDepartmentRequestedID);
		}
	}
	
	public void postAddDepartment() throws IOException{
		LOG("Post add Department");
		
		makeAllInvisible();
		postAddDepartmentVBOX.setVisible(true);
		
		Department dep = DepartmentList.get(DepartmentList.size() - 1);
		postAddDepartmentID.setText(String.valueOf(dep.getId()));
		postAddDepartmentName.setText(dep.getName());
	}
	
	private void wipeDepartmentFormInfo() {
		addDepartmentName.setText("");
	}
	
	public void postAddDepartmentEditButtonListener() throws IOException{
		makeAllInvisible();
		editDepartmentVBOX.setVisible(true);
		departmentRecordNumberEditing = DepartmentList.size();
		Department dep = DepartmentList.get(departmentRecordNumberEditing - 1);
		editDepartmentName.setText(dep.getName());
	}
	
	public void editDepartmentSaveChangesButtonListener() throws IOException{
		String name = editDepartmentName.getText();
		Department dep = new Department(departmentRecordNumberEditing, name);
		
		DepartmentList.get(departmentRecordNumberEditing - 1).copy(dep);
		WriteToDepartmentFile();
		displaySearchedDepartment(departmentRecordNumberEditing);
	}
	
	private void displaySearchedDepartment(int recordNumber) throws IOException{
		LOG(recordNumber);
		Department dep = DepartmentList.get(recordNumber - 1);
		
		makeAllInvisible();
		displayDepartmentVBOX.setVisible(true);
		
		displayDepartmentID.setText(String.valueOf(dep.getId()));
		displayDepartmentName.setText(dep.getName());
	}
	
	public void displayDepartmentEditButtonListener() throws IOException{
		makeAllInvisible();
		editDepartmentVBOX.setVisible(true);
		
		departmentRecordNumberEditing = (searchDepartmentRequestedID == 0 || !departmentJustSearched)
											? DepartmentList.size()
											: searchDepartmentRequestedID;
		
		Department dep = DepartmentList.get(departmentRecordNumberEditing - 1);
		
		editDepartmentName.setText(dep.getName());
	}
	
	///////////////////
	// instructor
	public void createInstructorButtonListener() throws IOException{
		String name = addInstructorName.getText();
		Department dep = DepartmentList.get(extractID(addInstructorDepartmentChoiceBox.getValue()) - 1); // get id from line
		Instructor instruct = new Instructor(InstructorList.size() + 1, name, dep);
		InstructorList.add(instruct);
		WriteToInstructorFile();
		wipeInstructorInfo();
		postAddInstructor();
	}
	
	private void wipeInstructorInfo() {
		addInstructorName.setText("");
		addInstructorDepartmentChoiceBox.getItems().removeAll(addInstructorDepartmentChoiceBox.getItems());
	}
	
	public void instructorSearchButtonListener() throws IOException{
		try {
			searchInstructorRequestedID = Integer.parseInt(searchInstructorTextField.getText());
		}
		catch(NumberFormatException e) {
			ErrorMessage("Invalid Search, IDs are numerical");
		}
		if(searchInstructorRequestedID < 1 || searchInstructorRequestedID > InstructorList.size()) 
			ErrorMessage("Invalid Search");
		else {
			instructorJustSearched = true;
			System.out.println(searchInstructorRequestedID + " searc");
			displaySearchedInstructor(searchInstructorRequestedID);
		}
	}
	
	public void postAddInstructor() throws IOException{
		LOG("Post add Instructor");
		
		makeAllInvisible();
		postAddInstructorVBOX.setVisible(true);
		
		Instructor instruct = InstructorList.get(InstructorList.size() - 1);
		postAddInstructorID.setText(String.valueOf(instruct.getId()));
		postAddInstructorDepName.setText(instruct.getDepName());
		postAddInstructorName.setText(instruct.getName());
	}
	
	public void postAddInstructorEditButtonListener() throws IOException{
		makeAllInvisible();
		editInstructorVBOX.setVisible(true);
		instructorRecordNumberEditing = InstructorList.size();
		Instructor instruct = InstructorList.get(instructorRecordNumberEditing - 1);
		editInstructorName.setText(instruct.getName());
		editInstructorDepartmentChoiceBox.getItems().removeAll(editInstructorDepartmentChoiceBox.getItems());
		DepartmentList.forEach(dep ->
		{
			String option = dep.getId() + "\t" + dep.getName();
		 	editInstructorDepartmentChoiceBox.getItems().add(option);
		});
		editInstructorDepartmentChoiceBox.setValue(instruct.getDep().getId() + "\t" + instruct.getDepName());
	}
	
	public void editInstructorSaveChangesButtonListener() throws IOException{
		String name = editInstructorName.getText();
		Department dep = DepartmentList.get(extractID(editInstructorDepartmentChoiceBox.getValue()) - 1);
		Instructor instruct = new Instructor(instructorRecordNumberEditing, name, dep);
		InstructorList.get(instructorRecordNumberEditing - 1).copy(instruct);
		WriteToInstructorFile();
		displaySearchedInstructor(instructorRecordNumberEditing);
	}
	
	public void displaySearchedInstructor(int recordNumber) throws IOException{
		LOG(recordNumber);
		Instructor instruct = InstructorList.get(recordNumber - 1);
		
		makeAllInvisible();
		
		displayInstructorVBOX.setVisible(true);
		
		displayInstructorID.setText(String.valueOf(instruct.getId()));
		displayInstructorDepName.setText(instruct.getDepName());
		displayInstructorName.setText(instruct.getName());
	}
	
	public void displayInstructorEditButtonListener() throws IOException{
		makeAllInvisible();
		editInstructorVBOX.setVisible(true);
		
		instructorRecordNumberEditing = (searchInstructorRequestedID == 0 || !instructorJustSearched)
											? InstructorList.size()
											: searchInstructorRequestedID;
		
		Instructor instruct = InstructorList.get(instructorRecordNumberEditing - 1);
		editInstructorName.setText(instruct.getName());
		editInstructorDepartmentChoiceBox.getItems().removeAll(editInstructorDepartmentChoiceBox.getItems());
		DepartmentList.forEach(dep ->
		{
			String option = dep.getId() + "\t" + dep.getName();
		 	editInstructorDepartmentChoiceBox.getItems().add(option);
		});
		editInstructorDepartmentChoiceBox.setValue(instruct.getDep().getId() + "\t" + instruct.getDepName());
	}
	
	//////////////////////
	// sort students
	public void sortStudentsButtonListener() {
		LOG("Sort students");
		makeAllInvisible();
		if(StudentLinkedList.isEmpty()) ErrorMessage("No Students have been made");
		else {
			sortStudentsVBOX.setVisible(true);
			updateListOfStudents();
		}
		
	}
	
	private void updateListOfStudents() {
		listOfStudents.getItems().removeAll(listOfStudents.getItems());
		for(int i = 0; i < StudentLinkedList.size(); i++) {
			String str = "STID: " + StudentLinkedList.get(i).getId() + " ; " 
						+ StudentLinkedList.get(i).getLastName() + ", " 
						+ StudentLinkedList.get(i).getFirstName();
			listOfStudents.getItems().add(str);
		}
	}
	
	public void studentsSortButtonListener() throws IOException{
		StudentLinkedList.sort();
		WriteToStudentFile();
		updateListOfStudents();
	}
	
	////////////////
	// sort courses
	public void sortCoursesButtonListener() {
		LOG("Sort courses");
		makeAllInvisible();
		if(CourseLinkedList.isEmpty()) ErrorMessage("No Courses have been made");
		else {
			sortCoursesVBOX.setVisible(true);
			updateListOfCourses();
		}
	}
	
	private void updateListOfCourses() {
		listOfCourses.getItems().removeAll(listOfCourses.getItems());
		for(int i = 0; i < CourseLinkedList.size(); i++) {
			String str = "CID: " + CourseLinkedList.get(i).getId() + " ; " 
						+ CourseLinkedList.get(i).getName() + " ; Department: " 
						+ CourseLinkedList.get(i).getDepartment();
			listOfCourses.getItems().add(str);
		}
	}
	
	public void coursesSortButtonListener() throws IOException{
		CourseLinkedList.sort();
		WriteToCourseFile();
		updateListOfCourses();
	}
	
	public static void main(String[] args) throws IOException{}
}
	
class ObjLinkedList<T extends Comparable<T>> implements Serializable{
	private class Node implements Serializable{
		private T obj;
		Node next;
		
		public Node(T obj, Node next) {
			this.obj = obj;
			this.next = next;
		}
		
		public Node(T obj) {
			this.obj = obj;
			this.next = null;
		}
	}
	
	private Node first;
	private int IndexCounter;
	public static boolean doEqualsInstead;
	
	public ObjLinkedList() {
		this.first = null;
	}
	
	public boolean isEmpty() {
		return this.first == null;
	}
	
	public int size() {
		return size(first);
	}
	
	private int size(Node list) {
		if(list == null)
			return 0;
		else
			return size(list.next) + 1; 
	}
	
	public void add(T obj) {
		IndexCounter = 0;
		this.first = add(obj, this.first);
	}
	
	public Node add(T obj, Node list) {
		if(list == null) 
			return new Node(obj);
		else {
			list.next = add(obj, list.next);
			return list;
		}
	}
	
	// add based on index??
	public boolean add(T obj, int index) {
		// if index is greater than total number of tings, less than 0, or if list is empty, return false
		if(isEmpty() || index > size() || index < 0)
			return false;
		
		if(index == 0)  
			first.obj = obj;
		else {
			Node p = first;
			
			// traverse list until predecessor is reached
			for(int k = 1; k < index; k++)
				p = p.next;
			
			p.next.obj = obj;
		}
		return true;
	}
	
	// using recursion, index will be target obj (0 index = 1st obj)
	public T get(int index) {
		IndexCounter = 0;
		return get(index, first);
	}
	
	private T get(int index, Node list) {
		// if list is empty
		if(list == null) {
			String message = String.valueOf(index);
			throw new IndexOutOfBoundsException(message);
		}
		
		// if the indexcounter == index desired
		if(IndexCounter == index)
			return list.obj;
		else { // indexcounter will be incremented
			IndexCounter++;
			return get(index, list.next); // recursion
		} 
	}
	
	public T get(T obj, boolean doEquals) {
		return get(getIndex(obj, doEquals));
	}
	
	public int getIndex(T obj, boolean doEquals) {
		Node p = first;
		int indexCounter = 0;
		
		doEqualsInstead = doEquals;
		
		if(first.obj.compareTo(obj) == 1) return indexCounter;
		
		while(p.next != null && p.obj.compareTo(obj) == 0) {
			p = p.next;
			indexCounter++;
		}
		
		doEqualsInstead = false;
		
		return indexCounter;
	}
	
	
	// will remove based on obj selected, will most likely delete and just use index
	public boolean remove(T remObj) {
		// if linked list is empty
		if(this.isEmpty()) 
			return false; // failure to remove
		
		// will use overriden compareTo to decide if the remObj is the first Obj
		if(first.obj.compareTo(remObj) == 1) {
			first = first.next;
			return true; // successful removal
		}
		else {
			Node p = first;
			// traverse through linked list until the end
			while(p.next != null) {
				// if the next obj is the desired remobj
				if(p.next.obj.compareTo(remObj) == 1) {
					// next will become 2nd next, removing the og next
					p.next = p.next.next;
					return true; // successful removal
				}
				p = p.next; // traverse
			}
			return false; // failure to remove
		}
	}
	
	// will remove based on index
	public boolean remove(int index) {
		// if index is greater than total number of tings, less than 0, or if list is empty, return false
		if(isEmpty() || index > size() || index < 0)
			return false;
		
		if(index == 0)  
			first = first.next;
		else {
			Node p = first;
			
			// traverse list until predecessor is reached
			for(int k = 1; k < index; k++)
				p = p.next;
			
			p.next = p.next.next;
		}
		return true;
	}
	
	public T getLast() {
		Node p = first;
		if(p == null) return null;
		
		while(p.next != null) p = p.next;
		
		return p.obj;
	}
	
	public void sort() {
		Node current = first, index = null;
		T temp;
		
		if(first == null) return;
		else {
			while(current != null) {
				index = current;
				
				while(index != null) {
					if(current.obj.compareTo(index.obj) > 0) {
						temp = current.obj;
						current.obj = index.obj;
						index.obj = temp;
					}
					index = index.next;
				}
				current = current.next;
			}
		}
	}
	
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		Node p = this.first;
		while(p != null) {
			strBuilder.append(p.obj + "\n");
			p = p.next;
		}
		return strBuilder.toString();
	}
	
}

class Student implements Comparable<Student>, Serializable{
	private int id = 0; // 4 bytes
	private String firstName = "", lastName = "", address = "", city = "", state = "";
	// 20 chars each -> 40 bytes each, 4 strings = 40 * 5 = 200
	// 204 bytes total 
	
	public Student(int id) {this.id = id;}
	
	public Student(int id, String firstName, String lastName, String address, String city, String state) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
	}
	
	public void setId(int id) {this.id = id;}
	public void setFirstName(String firstName) {this.firstName = firstName;}
	public void setLastName(String lastName) {this.lastName = lastName;}
	public void setAddress(String address) {this.address = address;}
	public void setCity(String city) {this.city = city;}
	public void setState(String state) {this.state = state;}
	
	public int getId() {return this.id;}
	public String getFirstName() {return this.firstName;}
	public String getLastName() {return this.lastName;}
	public String getAddress() {return this.address;}
	public String getCity() {return this.city;}
	public String getState() {return this.state;}
	
	public String toString() {
		String str = "SID: " + this.id + "\nFirst Name: " + this.firstName + "\nLast Name: " + this.lastName +"\nAddress: " + this.address
				+ "\nCity: " + this.city + "\nState: " + this.state;
		
		return str;
	}
	
	public void copy(Student s) {
		this.firstName = s.firstName;
		this.lastName = s.lastName;
		this.address = s.address;
		this.city = s.city;
		this.state = s.state;
	}
	
	// will be the sorting method, by lexical order
	@Override
	public int compareTo(Student o) {
		System.out.println("Comparing (" + ObjLinkedList.doEqualsInstead + ")");
		// will compare last name of this to last name of inputted ting
		// 0 = equal strings
		// positive return = this is after arg alphabetically
		// negative return = this is before arg alphabetically
		if(ObjLinkedList.doEqualsInstead) {
			if(this.id == o.getId()) return 1;
			else return 0;
		}
		else {
			int result = this.lastName.compareTo(o.getLastName());
			if(result > 0) {
				//int temp = o.id;
				//o.setId(this.id);
				//this.setId(temp);
			}
			return result;
		}
	}
}

/*
 * int = 4 bytes
 * char = 2 bytes
 * long = 8 bytes
 * float = 4 bytes
 * double = 8 bytes
 * short  = 2 bytes
 */

class Course implements Comparable<Course>, Serializable{
	private int id = 0; // 4 bytes
	private String num = "", name = "";
	int instruct = 0;
	int department = 0;
	// 160 bytes, 20 chars each
	// 164 bytes
	public Course(int id) {this.id = id;}
	public Course(int id, String num, String name, int instruct, int department) {
		this.num = num;
		this.id = id;
		this.name = name;
		this.instruct = instruct;
		this.department = department;
	}
	
	public void setNum(String num) {this.num = num;}
	public void setId(int id) {this.id = id;}
	public void setName(String name) {this.name = name;}
	public void setInstruct(int instruct) {this.instruct = instruct;}
	public void setDepartment(int department) {this.department = department;}
	
	public String getNum() {return this.num;}
	public int getId() {return this.id;}
	public String getName() {return this.name;}
	public int getInstruct() {return this.instruct;}
	public int getDepartment() {return this.department;}
	
	public String toString() {
		String str = "CID: " + id + "\nCName: " + name + "\nInstruct: " + instruct + "\nDepartment: " + department +
				"\nCNUM: " + num;
		
		return str;
	}
	
	public void copy(Course c) {
		this.num = c.num;
		this.name = c.name;
		this.instruct = c.instruct;
		this.department = c.department;
	}
	
	// will be sorting method, compare by name
	// 0 = same department
	// positive = this comes after arg
	// negative = this comes before arg
	@Override
	public int compareTo(Course o) {
		System.out.println("Comparing (" + ObjLinkedList.doEqualsInstead + ") ");
		if(ObjLinkedList.doEqualsInstead) {
			if(this.id == o.getId()) return 1;
			else return 0;
		}
		else{
			int result = this.department.compareTo(o.getDepartment());
			if(result > 0) {
				//int temp = o.id;
				//o.setId(this.id);
				//this.setId(temp);
			}
			return result;
		}
	}
}

/*
 * int = 4 bytes
 * char = 2 bytes
 * long = 8 bytes
 * float = 4 bytes
 * double = 8 bytes
 * short  = 2 bytes
 */

class Enrollment implements Comparable<Enrollment>, Serializable{
	private int enrollmentId = 0; // 4
	private int studentId = 0; // 4
	private int courseId = 0; // 4
	private int year = 0; // 40
	private String semester = ""; // 20 * 2 = 40
	private String grade = ""; // 20 * 2 = 40
	// 132
	public Enrollment(int enrollmentId) {this.enrollmentId = enrollmentId;}
	
	public Enrollment(int enrollmentId, int courseId, int studentId, int year, String semester, String grade) {
		this.enrollmentId = enrollmentId;
		this.studentId = studentId;
		this.courseId = courseId;
		this.year = year;
		this.semester = semester;
		this.grade = grade;
	}
	
	public Enrollment(int enrollmentId, int courseId, int studentId, int year, String semester) {
		this.enrollmentId = enrollmentId;
		this.studentId = studentId;
		this.courseId = courseId;
		this.year = year;
		this.semester = semester;
		this.grade = "NA";
	}
	
	public void setEnrollmentId(int enrollmentId) {this.enrollmentId = enrollmentId;}
	public void setStudentId(int studentId) {this.studentId = studentId;}
	public void setCourseId(int courseId) {this.courseId = courseId;}
	public void setYear(int year) {this.year = year;}
	public void setSemester(String semester) {this.semester = semester;}
	public void setGrade(String grade) {this.grade = grade;}
	
	public int getEnrollmentId() {return this.enrollmentId;}
	public int getStudentId() {return this.studentId;}
	public int getCourseId() {return this.courseId;}
	public int getYear() {return this.year;}
	public String getSemester() {return this.semester;}
	public String getGrade() {return this.grade;}
	
	public void copy(Enrollment e) {
		this.studentId = e.studentId;
		this.courseId = e.courseId;
		this.year = e.year;
		this.semester = e.semester;
		this.grade = e.grade;
	}
	
	public String toString() {
		String str = "EID: " + enrollmentId + "\nSID: " + studentId + " \nCID: " + courseId + "\nYear: " + year + "\nSemester: " + semester + "\nGrade: " + grade;
		
		return str;
	}

	// will be sorting method, compare by student id
	// 0 = same id
	// -x = less than
	// x = greater than
	@Override
	public int compareTo(Enrollment o) {
		System.out.println("Comparing (" + ObjLinkedList.doEqualsInstead + ")");
		if(ObjLinkedList.doEqualsInstead) {
			if(this.enrollmentId == o.getEnrollmentId()) return 1;
			else return 0;
		}
		else {
			if(this.studentId == o.getStudentId()) return 1;
			else return 0;
		}
	}
}


class Department implements Serializable{
	private String name;
	private int id;
	
	public Department() {}
	
	public Department(int id, String name) {
		this.name = name;
		this.id = id;
	}
	
	public void setName(String name) {this.name = name;}
	public String getName() {return this.name;}
	public int getId() {return this.id;}
	
	public boolean equals(Department dep) {return this.id == dep.getId();}
	public boolean equals(String name) {return this.name.equalsIgnoreCase(name);}
	
	public void copy(Department dep) {
		this.name = dep.name;
		this.id = dep.id;
	}
	
	public String toString() {
		return ("Department: " + name);
	}
}

class Instructor implements Serializable{
	private String name;
	private Department dep;
	private int id;
	
	public Instructor() {};
	public Instructor(int id, String name, Department dep) {
		this.name = name;
		this.dep = dep;
		this.id = id;
	}
	
	public void setName(String name) {this.name = name;}
	public void setDepartmentName(String depName) {this.dep.setName(depName);}
	
	public int getId() {return this.id;}
	public String getName() {return this.name;};
	public String getDepName() {return this.dep.getName();}
	public Department getDep() {return this.dep;}
	
	public boolean isSameDepartment(Instructor instruct) {
		return (this.dep.equals(instruct.dep));
	}
	
	public void copy(Instructor inst) {
		this.id = inst.getId();
		this.dep = inst.getDep();
		this.name = inst.getName();
	}
	
	public String toString() {
		String str = "Name: " + this.name + "\nDepartment: " + dep;
		return str;
	}
}

class Highest implements Serializable{
	public int SID, CID, EID;
	
	public int getSID() {return SID;}
	public int getCID() {return CID;}
	public int getEID() {return EID;}
	
	public void setSID(int SID) {this.SID = SID;}
	public void setCID(int CID) {this.CID = CID;}
	public void setEID(int EID) {this.EID = EID;}
	
	public void copy(Highest o) {
		this.SID = o.SID;
		this.CID = o.CID;
		this.EID = o.EID;
	}
}


/*
 * int = 4 bytes
 * char = 2 bytes
 * long = 8 bytes
 * float = 4 bytes
 * double = 8 bytes
 * short  = 2 bytes
 */