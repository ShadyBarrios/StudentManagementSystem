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


///////////////////////////
// FINAL DUE BY MONDAY
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
	private boolean departmentJustSearched = false, instructorJustSearched = false;
	private Enrollment searchedEnrollment;
	private List<Integer> listOfReportEnrollmentIds;
	
	private static File HighestFile;
	
	private static FileOutputStream HighestOutput;
	private static ObjectOutputStream ObjectHighestOutput;
	private static FileInputStream HighestInput;
	private static ObjectInputStream ObjectHighestInput;
	
	public static String url;
	public static String user;
	public static String pass;
	
	public static Connection conn;
	public static Statement stmnt;
	
	// Use OnMouseClicked for instructor box
	
	public void initialize() throws IOException, ClassNotFoundException, SQLException{
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
		
		HighestFile = new File("HighestFile.dat");
		
		TruncateDB(true);
	
//		addCourseInstructorChoiceBox.getItems().removeAll(addCourseInstructorChoiceBox.getItems());
//		addCourseInstructorChoiceBox.getItems().addAll("Kim", "Jones", "Java", "Pete", "Scott");
//		addCourseDepartmentChoiceBox.getItems().removeAll(addCourseDepartmentChoiceBox.getItems());
//		addCourseDepartmentChoiceBox.getItems().addAll("English", "Science", "Biology", "Math", "Chemistry");
		
		makeAllInvisible();
		MenuLabel.setVisible(true);
	}
	
	private void TruncateDB(boolean doTruncate) throws ClassNotFoundException, IOException, SQLException {
		SetUpHighestList(doTruncate);
		try {
			// will create conn and stmnt for future use
			conn = DriverManager.getConnection(url, user, pass);
			stmnt = conn.createStatement();
				if(doTruncate){
					int rows = 0;
					String[] wipes = {"DELETE FROM students", "DELETE FROM courses", 
							"DELETE FROM enrollments", "DELETE FROM instructors",
							"DELETE FROM departments"};
					for(String wipe : wipes)
						rows += stmnt.executeUpdate(wipe);
					
					System.out.println(rows + " total rows deleted\n");
				
					return;
				}
		} catch (SQLException e) {
			System.out.println("Exception: " + e);
		}
		System.out.println("Did not truncate");
	}
	
	private boolean IdIsInDB(int id, String table) {
		try {
			String type = table.equals("enrollment") ? "EID" : "ID";
			String query = "SELECT * FROM " + table + " WHERE " + type + " = " + id; 
			ResultSet set = stmnt.executeQuery(query);
			boolean returnVal = set.next();
			return returnVal;
		}
		catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return false;
		}
	}
	
	private int GetHighestID(String objType) {
		try {
			String ID = objType.equals("enrollments") ? "EID" : "ID";
			String query = "SELECT MAX(" + ID + ") FROM " + objType;
			ResultSet set = stmnt.executeQuery(query);
			set.next();
			int id = set.getInt(1);
			return id;
		}
		catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return 0;
		}
	}
	
	private boolean IsEmpty(String type) {
		try {
			ResultSet set = stmnt.executeQuery("SELECT * FROM " + type);
			return !set.next();
		}
		catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return true;
		}
	}
	
	private Object GetHighestFrom(String table) throws SQLException{
		try {
			String type = table.equals("enrollments") ? "EID" : "ID";
			ResultSet set = stmnt.executeQuery("SELECT MAX(" + type + ") FROM " + table);
			set.next();
			int highest = set.getInt(1);
			set = stmnt.executeQuery("SELECT * FROM " + table + " WHERE " + type + " = " + highest);
			Object obj = GenerateObject(table, set);
			return obj;
		}
		catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
	private String GetDepartmentName(int depId) throws SQLException{
		ResultSet set = stmnt.executeQuery("SELECT * FROM departments WHERE ID = " + depId);
		if(set.next())
			return set.getString("Name");
		else
			return "";
	}
	
	private String GetInstructorName(int instructID) throws SQLException{
		ResultSet set = stmnt.executeQuery("SELECT * FROM instructors WHERE ID = " + instructID);
		if(set.next())
			return set.getString("Name");
		else
			return "";
	}
	
	private Object GenerateObject(String table, ResultSet set) throws SQLException {
		set.next();
		if(table.equals("students"))
			return new Student(set.getInt("ID"), set.getString("FirstName"), set.getString("LastName"), set.getString("Address"), set.getString("City"), set.getString("State"));
		else if(table.equals("courses"))
			return new Course(set.getInt("ID"), set.getString("Num"), set.getString("Name"), set.getInt("InstructorID"), set.getInt("DepartmentID"));
		// public Enrollment(int enrollmentId, int courseId, int studentId, int year, String semester, String grade) {
		else if(table.equals("enrollments"))
			return new Enrollment(set.getInt("EID"), set.getInt("CID"), set.getInt("SID"), set.getInt("Year"), set.getString("Semester"), set.getString("Grade"));
		else if(table.equals("instructors"))
			return new Instructor(set.getInt("ID"), set.getString("Name"), set.getInt("DepartmentID"));
		else if(table.equals("departments"))
			return new Department(set.getInt("ID"), set.getString("Name"));
		else 
			return null;
	}
	
	private Object GetFrom(String table, int id) throws SQLException{
		try {
			String type = table.equals("enrollments") ? "EID" : "ID";
			ResultSet set = stmnt.executeQuery(("SELECT * FROM " + table + " WHERE " + type + " = " + id));
			return GenerateObject(table, set);
		}
		catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return null;
		}
	}
	
	private boolean AddToDatabase(Object obj) throws SQLException {
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
					+ ", '" + course.getNum() + "', '" + course.getName() + "', '" + course.getInstructorID() + "', '" 
					+ course.getDepartmentID() + "')";
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
			instructor.getName() + "', " + instructor.getDepID() + ")";
			stmnt.executeUpdate(update);
			returner = true;
		}
		else
			returner = false;
		return returner;
	}
	
	/*
	 * NEEEDDDD TOOO FINISH HEREREERER
	 */
	private boolean EditDatabase(Object obj) throws SQLException{
		boolean returner;
		if(obj instanceof Student) {
			Student student = (Student)obj;
			String[] updates = {
					"UPDATE students SET FirstName = '" + student.getFirstName() + "' WHERE ID = " + student.getId(),
					"UPDATE students SET LastName = '" + student.getLastName() + "' WHERE ID = " + student.getId(),
					"UPDATE students SET Address = '" + student.getAddress() + "' WHERE ID = " + student.getId(),
					"UPDATE students SET City = '" + student.getCity() + "' WHERE ID = " + student.getId(),
					"UPDATE students SET State = '" + student.getState() + "' WHERE ID = " + student.getId()
			};
			for(String update : updates)
				stmnt.executeUpdate(update);
			returner = true;
		}
		else if(obj instanceof Course) {
			Course course = (Course)obj;
			String[] updates = {
				"UPDATE courses SET Num = '" + course.getNum() + "' WHERE ID = " + course.getId(),
				"UPDATE courses SET Name = '" + course.getName() + "' WHERE ID = " + course.getId(),
				"UPDATE courses SET InstructorID = " + course.getInstructorID() + " WHERE ID = " + course.getId(),
				"UPDATE courses SET DepartmentID = " + course.getDepartmentID() + " WHERE ID = " + course.getId()
			};
			for(String update : updates)
				stmnt.executeUpdate(update);
			returner = true;
		}
		else if(obj instanceof Enrollment) {
			Enrollment enrollment = (Enrollment)obj;
			String[] updates = {
					"UPDATE enrollments SET SID = " + enrollment.getStudentId() + " WHERE EID = " + enrollment.getEnrollmentId(),
					"UPDATE enrollments SET CID = " + enrollment.getCourseId() + " WHERE EID = " + enrollment.getEnrollmentId(),
					"UPDATE enrollments SET Year = " + enrollment.getYear() + " WHERE EID = " + enrollment.getEnrollmentId(),
					"UPDATE enrollments SET Semester = '" + enrollment.getSemester() + "' WHERE EID = " + enrollment.getEnrollmentId(),
					"UPDATE enrollments SET Grade = '" + enrollment.getGrade() + "' WHERE EID = " + enrollment.getEnrollmentId()
			};
			for(String update : updates)
				stmnt.executeUpdate(update);
			returner = true;
		}
		else if(obj instanceof Department) {
			Department department = (Department)obj;
			String update = "UPDATE departments SET Name = '" + department.getName() + "' WHERE ID = " + department.getId();
			stmnt.executeUpdate(update);
			returner = true;
		}
		else if(obj instanceof Instructor) {
			Instructor instructor = (Instructor)obj;
			String[] updates = {
					"UPDATE instructors SET Name = '" + instructor.getName() + "' WHERE ID = " + instructor.getId(),
					"UPDATE instructors set DepID = " + instructor.getDepID() + " WHERE ID = " + instructor.getId()
			};
			for(String update : updates)
				stmnt.executeUpdate(update);
			returner = true;
		}
		else
			returner = false;
		return returner;
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
	
	private static void DeleteStudentFromDatabase(int stuID) throws IOException {
		try{
			String deleteQuery = "DELETE * FROM students WHERE SID = " + stuID;
			stmnt.executeUpdate(deleteQuery);
			deleteQuery = "DELETE * FROM enrollments WHERE SID = " + stuID;
			stmnt.executeUpdate(deleteQuery);
			
		}
		catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
	// same as method above
	private static void DeleteCourseFromDatabase(int corID) throws IOException {
		try{
			String deleteQuery = "DELETE * FROM courses WHERE ID = " + corID;
			stmnt.executeUpdate(deleteQuery);
			deleteQuery = "DELETE * FROM enrollments WHERE CID = " + corID;
			stmnt.executeUpdate(deleteQuery);
		}
		catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	private static void LOG(String str) {System.out.println(str);}
	private static void LOG(int number) {System.out.println(number);}
	
	// sets all searched flags to false
	private void allJustSearchedFalse() {
		studentJustSearched = false;
		courseJustSearched = false;
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
			ResultSet departments = stmnt.executeQuery("SELECT * FROM departments");
			while(departments.next())
				addCourseDepartmentChoiceBox.getItems().add(departments.getInt("ID") + "    " +departments.getString("name"));
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
			ResultSet departments = stmnt.executeQuery("SELECT * FROM departments");
			while(departments.next()) {
				String option = departments.getInt("ID") + "    " +departments.getString("Name");
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
			ResultSet courses = stmnt.executeQuery("SELECT * FROM courses");
			int id;
			String num;
			String option;
			while(courses.next()) {
				id = courses.getInt("ID");
				num = courses.getString("Num");
				option = id + "    " +num;
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
		
		Student editing = (Student)GetHighestFrom("students");
		postAddStudentID.setText(String.valueOf(editing.getId()));
		postAddStudentFirstName.setText(editing.getFirstName());
		postAddStudentLastName.setText(editing.getLastName());
		postAddStudentAddress.setText(editing.getAddress());
		postAddStudentCity.setText(editing.getCity());
		postAddStudentState.setText(editing.getState());
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
		Student editing = (Student)GetHighestFrom("students");
		editStudentFirstName.setText(editing.getFirstName());
		editStudentLastName.setText(editing.getLastName());
		editStudentAddress.setText(editing.getAddress());
		editStudentCity.setText(editing.getCity());
		editStudentState.setText(editing.getState());
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
		System.out.println(recordNumber);
		Student searched = (Student)GetFrom("students", recordNumber);
		
		makeAllInvisible();
		displayStudentVBOX.setVisible(true);
		
		displayStudentID.setText(String.valueOf(searched.getId()));
		displayStudentFirstName.setText(searched.getFirstName());
		displayStudentLastName.setText(searched.getLastName());
		displayStudentAddress.setText(searched.getAddress());
		displayStudentCity.setText(searched.getCity());
		displayStudentState.setText(searched.getState());
	}
	
	public void displayStudentEditButtonListener() throws SQLException{
		makeAllInvisible();
		editStudentVBOX.setVisible(true);
		
		studentRecordNumberEditing = (searchStudentRequestedID == 0 || !studentJustSearched) 
									? GetHighestID("students")
									: searchStudentRequestedID;
		
		Student editing = (Student)GetFrom("students", studentRecordNumberEditing);
		
		editStudentFirstName.setText(editing.getFirstName());
		editStudentLastName.setText(editing.getLastName());
		editStudentAddress.setText(editing.getAddress());
		editStudentCity.setText(editing.getCity());
		editStudentState.setText(editing.getState());
	}
	
	public void deleteStudentButtonListener() throws IOException {
		int id = studentRecordNumberEditing;
		DeleteStudentFromDatabase(id);
		ErrorMessage("Student Deleted");
	}
	
	/////////////
	// course
	public void createCourseButtonListener() throws IOException, SQLException{
		LOG("Creating course");
		String courseNumber = addCourseNumber.getText().trim();
		String courseName = addCourseName.getText().trim();
		int courseInstructor = ExtractID(addCourseInstructorChoiceBox.getValue().trim());
		int courseDepartment = ExtractID(addCourseDepartmentChoiceBox.getValue().trim());
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// int id = (CourseLinkedList.isEmpty()) ? 1 : getHighestCourseID() + 1;
		int id = (IsEmpty("courses") || HighestList.getCID() != 0) ? HighestList.getCID() + 1 : 1;
		Course course = new Course(id, courseNumber, courseName, courseInstructor, courseDepartment);
		HighestList.setCID(course.getId());
		UpdateHighestFile();
		
		AddToDatabase(course);
		wipeCourseFormInfo();
		postAddCourse();
	}
	
	private void postAddCourse() throws IOException, SQLException{
		makeAllInvisible(); 
		postAddCourseVBOX.setVisible(true);
		
		Course course = (Course)GetHighestFrom("courses");
		postAddCourseID.setText(String.valueOf(course.getId()));
		postAddCourseNumber.setText(course.getNum());
		postAddCourseName.setText(course.getName());
		postAddCourseInstructor.setText(course.getInstructorID() + "    " +GetInstructorName(course.getInstructorID()));
		postAddCourseDepartment.setText(course.getDepartmentID() +  " " + GetDepartmentName(course.getDepartmentID()));
	}
	
	private void wipeCourseFormInfo() {
		addCourseNumber.setText("");
		addCourseName.setText("");
		addCourseInstructorChoiceBox.setValue("");
		addCourseDepartmentChoiceBox.setValue("");
	}
	
	public void postAddCourseEditButtonListener() throws IOException, SQLException{
		makeAllInvisible();
		editCourseVBOX.setVisible(true);
		courseRecordNumberEditing = GetHighestID("courses");
		Course course = (Course)GetHighestFrom("courses");
		
		editCourseNumber.setText(course.getNum());
		editCourseName.setText(course.getName());
		editCourseDepartmentChoiceBox.getItems().removeAll(editCourseDepartmentChoiceBox.getItems());
		editCourseDepartmentChoiceBox.setValue("");
		ResultSet set = stmnt.executeQuery("SELECT * FROM departments");
		while(set.next())
			editCourseDepartmentChoiceBox.getItems().add(set.getInt("ID") + "    " +set.getString("Name"));
		editCourseInstructorChoiceBox.getItems().removeAll(editCourseInstructorChoiceBox.getItems());
		editCourseInstructorChoiceBox.setValue("");
	}
	
	public void updateInstructorListEditCourseChoiceBox() throws SQLException {
		String depName = editCourseDepartmentChoiceBox.getValue();
		editCourseInstructorChoiceBox.getItems().removeAll(editCourseInstructorChoiceBox.getItems());
		if(depName.equals(""))
			editCourseInstructorChoiceBox.getItems().add("No Department Selected");
		else {
			ResultSet set = stmnt.executeQuery("SELECT * FROM instructors");
			while(set.next()){
				if(GetDepartmentName(set.getInt("DepartmentID")).equalsIgnoreCase(depName))
					editCourseInstructorChoiceBox.getItems().add(set.getInt("ID") +  " " + set.getString("Name"));
			}
			editCourseInstructorChoiceBox.setValue("");
		}
	}
	
	public void updateInstructorListAddCourseChoiceBox() throws SQLException {
		String depName = addCourseDepartmentChoiceBox.getValue();
		addCourseInstructorChoiceBox.getItems().removeAll(addCourseInstructorChoiceBox.getItems());
		if(depName.equals(""))
			addCourseInstructorChoiceBox.getItems().add("No Department Selected");
		else {
			ResultSet set = stmnt.executeQuery("SELECT * FROM instructors");
			while(set.next()){
				if(GetDepartmentName(set.getInt("DepartmentID")).equalsIgnoreCase(depName))
					addCourseInstructorChoiceBox.getItems().add(set.getInt("ID") + "    " + set.getString("Name"));
			}
	
			addCourseInstructorChoiceBox.setValue("");
		}
	
	}
	
	public void editCourseSaveChangesButton() throws IOException, SQLException{
		String number = editCourseNumber.getText().trim();
		String name = editCourseName.getText().trim();
		int instructor = ExtractID(editCourseInstructorChoiceBox.getValue().trim());
		int department = ExtractID(editCourseDepartmentChoiceBox.getValue().trim());
		
		Course course = new Course(courseRecordNumberEditing, number, name, instructor, department);
		EditDatabase(course);
		displaySearchedCourse(courseRecordNumberEditing);
	}
	
	public void courseSearchButtonListener() throws IOException, SQLException{
		try{
			searchCourseRequestedID = Integer.valueOf(courseSearchID.getText());
		}
		catch(NumberFormatException e) {
			ErrorMessage("Invalid search, IDs are numerical");
		}
		if(IdIsInDB(searchCourseRequestedID, "courses")) {
			courseJustSearched = true;
			displaySearchedCourse(searchCourseRequestedID);
		}
		else 
			ErrorMessage("Invalid Search");
		
	}
	
	private void displaySearchedCourse(int recordNumber) throws IOException, SQLException{
		Course course = (Course)GetFrom("courses", recordNumber);
		
		makeAllInvisible();
		displayCourseVBOX.setVisible(true);
		
		displayCourseID.setText(String.valueOf(course.getId()));
		displayCourseNumber.setText(course.getNum());
		displayCourseName.setText(course.getName());
		displayCourseInstructor.setText(course.getInstructorID() + "    " +GetInstructorName(course.getInstructorID()));
		displayCourseDepartment.setText(course.getDepartmentID() + "    " +GetDepartmentName(course.getDepartmentID()));
	}
	
	public void displayCourseEditButtonListener() throws IOException, SQLException{
		makeAllInvisible();
		editCourseVBOX.setVisible(true);
		
		courseRecordNumberEditing = (searchCourseRequestedID == 0 || !courseJustSearched) 
										? GetHighestID("courses")
										: searchCourseRequestedID;
		
		Course course = (Course) GetFrom("courses", courseRecordNumberEditing);
		
		editCourseNumber.setText(course.getNum());
		editCourseName.setText(course.getName());
		editCourseDepartmentChoiceBox.getItems().removeAll(editCourseDepartmentChoiceBox.getItems());
		editCourseDepartmentChoiceBox.setValue("");
		ResultSet set = stmnt.executeQuery("SELECT * FROM instructors");
		while(set.next()){
			editCourseDepartmentChoiceBox.getItems().add(set.getInt("ID") + "    " +set.getString("Name"));
		}
		editCourseInstructorChoiceBox.getItems().removeAll(editCourseInstructorChoiceBox.getItems());
		editCourseInstructorChoiceBox.setValue("");
	}
	
	public void deleteCourseButtonListener() throws IOException {
		int id = courseRecordNumberEditing;
		DeleteCourseFromDatabase(id);
		ErrorMessage("Course Deleted");
	}
	
	// 10/24 LEFT OFF HERE
	//////
	// enrollment
	public void createEnrollmentStudentSearchButtonListener() throws IOException, SQLException{
		try {
			createEnrollmentRequestedStudentID = Integer.valueOf(enrollmentStudentSearchID.getText());
		}
		catch(NumberFormatException e) {
			ErrorMessage("Invalid search, IDs are numerical");
		}
		
		if(IsEmpty("students")) 
			ErrorMessage("No students have been created");
		else if(!IdIsInDB(createEnrollmentRequestedStudentID, "students"))
			ErrorMessage("Invalid Student Search");
		else if(IsEmpty("courses"))
			ErrorMessage("No courses have been created");
		else
			enrollmentCanBeCreated();
	}
	
	private void enrollmentCanBeCreated() throws IOException, SQLException{
		makeAllInvisible();
		createEnrollmentHBOX.setVisible(true);
		
		Student set = (Student)GetFrom("students", createEnrollmentRequestedStudentID);

		createEnrollmentDisplayStudentID.setText(String.valueOf(set.getId()));
		createEnrollmentDisplayStudentFirstName.setText(set.getFirstName());
		createEnrollmentDisplayStudentLastName.setText(set.getLastName());
		createEnrollmentDisplayStudentAddress.setText(set.getAddress());
		createEnrollmentDisplayStudentCity.setText(set.getCity());
		createEnrollmentDisplayStudentState.setText(set.getState());
		
		createEnrollmentYearChoiceBox.getItems().removeAll(createEnrollmentYearChoiceBox.getItems());
		createEnrollmentYearChoiceBox.getItems().addAll("2019", "2020", "2021", "2022");
		createEnrollmentSemesterChoiceBox.getItems().removeAll(createEnrollmentSemesterChoiceBox.getItems());
		createEnrollmentSemesterChoiceBox.getItems().addAll("Spring", "Summer", "Fall", "Winter");
		addAllCourseEnrollmentOptions();
		
	}
	
	private void addAllCourseEnrollmentOptions() throws IOException, SQLException {
		createEnrollmentCourseChoiceBox.getItems().removeAll(createEnrollmentCourseChoiceBox.getItems());
		ResultSet set = stmnt.executeQuery("SELECT * FROM courses");
		while(set.next()) {
			int id = set.getInt("ID");
			String num = set.getString("Num");
			String option = String.valueOf(id) + "   " + num;
			
			createEnrollmentCourseChoiceBox.getItems().add(option);
		}
	}
	
	public void createEnrollmentButtonListener() throws IOException, SQLException{
		LOG("Create enrollment");
		// enrollment id, course id, student id, year, semester, grade
		int courseID = ExtractID(createEnrollmentCourseChoiceBox.getValue().trim());
		int studentID = Integer.valueOf(createEnrollmentDisplayStudentID.getText().trim());
		String semester = createEnrollmentSemesterChoiceBox.getValue().trim();
		int year = Integer.parseInt(createEnrollmentYearChoiceBox.getValue().trim());
		
		int id = (IsEmpty("enrollments") || HighestList.getEID() != 0) ? HighestList.getEID() + 1 : 1;
		Enrollment enrollment = new Enrollment(id, courseID, studentID, year, semester);
		HighestList.setEID(enrollment.getEnrollmentId());
		UpdateHighestFile();
		
		AddToDatabase(enrollment);
		postAddEnrollment();
	}
	
	private void postAddEnrollment() throws IOException, SQLException{
		makeAllInvisible();
		postAddEnrollmentVBOX.setVisible(true);
		
		Enrollment enrollment = (Enrollment)GetHighestFrom("enrollments");
		
		postAddEnrollmentEID.setText(String.valueOf(enrollment.getEnrollmentId()));
		postAddEnrollmentCID.setText(String.valueOf(enrollment.getCourseId()));
		postAddEnrollmentSID.setText(String.valueOf(enrollment.getStudentId()));
		postAddEnrollmentYear.setText(String.valueOf(enrollment.getYear()));
		postAddEnrollmentSemester.setText(enrollment.getSemester());
		postAddEnrollmentGrade.setText(enrollment.getGrade());
	}
	
	public void postAddEnrollmentEditButtonListener() throws IOException, SQLException{
		makeAllInvisible();
		editEnrollmentHBOX.setVisible(true);
		
		enrollmentRecordNumberEditing = GetHighestID("enrollments");
		
		Enrollment enrollment = (Enrollment)GetHighestFrom("enrollments");
		Student student = (Student)GetFrom("students", enrollment.getStudentId());
		
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
		
		if(EnrollmentExists(courseID, studentID, year, semester)) {
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
	
	private boolean EnrollmentExists(int CID, int SID, int year, String semester) throws SQLException{
		String query = "SELECT * FROM enrollments WHERE CID = " + CID + " AND SID = " + SID + " AND Year = " + year + " AND Semester LIKE '%" + semester + "%'";
		ResultSet set = stmnt.executeQuery(query);
		boolean bool = set.next();	
		if(bool) 
			searchedEnrollment = new Enrollment(set.getInt("EID"), set.getInt("CID"), set.getInt("SID"), set.getInt("Year"), set.getString("Semester"));
		return bool;
	}
	
	private boolean EnrollmentExists(int course, int year) throws SQLException{
		String query = "SELECT * FROM enrollments WHERE CID = " + course + " AND Year = " + year ;
		ResultSet set = stmnt.executeQuery(query);
		boolean bool = set.next();	
		if(bool) 
			searchedEnrollment = new Enrollment(set.getInt("EID"), set.getInt("CID"), set.getInt("SID"), set.getInt("Year"), set.getString("Semester"));
		return bool;
	}
	
	private List<Integer> FindEnrollmentIDs(int course, int year) throws SQLException{
		List<Integer> ids = new ArrayList<Integer>();
		String query = "SELECT * FROM enrollments WHERE CID = " + course + " AND Year = " + year ;
		ResultSet set = stmnt.executeQuery(query);
		
		while(set.next())
			ids.add(set.getInt("EID"));
		
		return ids;
	}
	
	public int getHighestEnrollmentID() throws SQLException {
		String query = "SELECT MAX(ID) FROM enrollments";
		ResultSet set = stmnt.executeQuery(query);
	
		if(set.next())
			return set.getInt(1);
		return 0;
	}
	
	public void gradeManagementSaveChangesButtonListener() throws IOException, SQLException{
		int enrollmentID = Integer.valueOf(gradeManagementDisplayEID.getText().trim());
	
		String query = "UPDATE enrollments SET Grade = '" + gradeManagementGradeChoiceBox.getValue().trim() + "' WHERE EID = " + enrollmentID;
		stmnt.executeUpdate(query);
		ResultSet updatedSet = stmnt.executeQuery("SELECT * FROM enrollments where EID = " + enrollmentID);
	
		updatedSet.next();
		gradeManagementSaveIndicator.setText("Grade Saved: " + updatedSet.getString("Grade"));
	}
	
	public void editEnrollmentSaveChangesButtonListener() throws IOException, SQLException{
		int courseID = ExtractID(editEnrollmentCourseChoiceBox.getValue().trim());
		int studentID = Integer.valueOf(editEnrollmentDisplayStudentID.getText().trim());
		int enrollmentID = enrollmentRecordNumberEditing;
		String semester = editEnrollmentSemesterChoiceBox.getValue().trim();
		int year = Integer.parseInt(editEnrollmentYearChoiceBox.getValue().trim());
		
		// not sure if this is smart but doing multiple queries
		String query = "UPDATE enrollments SET CID = " + courseID + " WHERE EID = " + enrollmentID;
		stmnt.executeUpdate(query);
		query = "UPDATE enrollments SET SID = " + studentID + " WHERE EID = " + enrollmentID;
		stmnt.executeUpdate(query);
		query = "UPDATE enrollments SET Semester = '" + semester + "' WHERE EID = " + enrollmentID;
		stmnt.executeUpdate(query);
		query = "UPDATE enrollments SET Year = " + year + " WHERE EID = " + enrollmentID;
		stmnt.executeUpdate(query);
	
		displaySearchedEnrollment(enrollmentRecordNumberEditing);
	}
	
	public void displayEnrollmentEditButtonListener() throws IOException, SQLException{
		makeAllInvisible();
		editEnrollmentHBOX.setVisible(true);
		
		enrollmentRecordNumberEditing = (searchEnrollmentRequestedID == 0 || !enrollmentJustSearched) 
											? GetHighestID("enrollments")
											: searchEnrollmentRequestedID;
		
		Enrollment enrollment = (Enrollment)GetFrom("enrollments", enrollmentRecordNumberEditing);
		
		Student student = new Student(enrollment.getStudentId());
		
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
	
	private void displaySearchedEnrollment(int recordNumber) throws IOException, SQLException{
		Enrollment enrollment = (Enrollment)GetFrom("enrollments", recordNumber);
		makeAllInvisible();
		displayEnrollmentVBOX.setVisible(true);
		
		displayEnrollmentEID.setText(String.valueOf(enrollment.getEnrollmentId()));
		displayEnrollmentCID.setText(String.valueOf(enrollment.getCourseId()));
		displayEnrollmentSID.setText(String.valueOf(enrollment.getStudentId()));
		displayEnrollmentYear.setText(String.valueOf(enrollment.getYear()));
		displayEnrollmentSemester.setText(enrollment.getSemester());
		displayEnrollmentGrade.setText(enrollment.getGrade());		
	}
	
	public void enrollmentSearchButtonListener() throws IOException, SQLException{
		searchEnrollmentRequestedID = Integer.valueOf(enrollmentSearchID.getText());
		
		if(IsEmpty("enrollments"))
			ErrorMessage("No enrollments have been created");
		
		if (IdIsInDB(searchEnrollmentRequestedID, "enrollments")){
			enrollmentJustSearched = true;
			displaySearchedEnrollment(searchEnrollmentRequestedID);
		}
		else
			ErrorMessage("Invalid search");
	}
	
	private void setUpEditForEnrollmentCourse(int courseID) throws IOException, SQLException{
		editEnrollmentCourseChoiceBox.getItems().removeAll(editEnrollmentCourseChoiceBox.getItems());
		ResultSet set = stmnt.executeQuery("SELECT * FROM courses");
		while(set.next()) {
			int id = set.getInt("ID");
			String num = set.getString("Num");
			String option = String.valueOf(id) + "   " + num;
			
			editEnrollmentCourseChoiceBox.getItems().add(option);
		}
		
		Course course = (Course)GetFrom("courses", courseID);
		String num = course.getNum();
		String option = String.valueOf(courseID) + "   " + num;
		editEnrollmentCourseChoiceBox.setValue(option);
	}
	////////
	// report
	public void reportSearchButtonListener() throws IOException, SQLException{
		makeAllInvisible();
		displayReportVBOX.setVisible(true);
		listOfStudentInfo.getItems().removeAll(listOfStudentInfo.getItems());
		int CID = ExtractID(searchReportCourseChoiceBox.getValue().trim());
		int year = Integer.parseInt(searchReportYearChoiceBox.getValue().trim());
		if(EnrollmentExists(CID, year)) {
			listOfReportEnrollmentIds = FindEnrollmentIDs(CID, year);
			if(listOfReportEnrollmentIds.isEmpty()) {
				ErrorMessage("Invalid Search");
				return;
			}
			
			Enrollment enrollment = (Enrollment)GetFrom("enrollments", listOfReportEnrollmentIds.get(0));
			
			String header;
			
			Course course = (Course)GetFrom("courses", enrollment.getCourseId());
			header = "Course: " + course.getName() + " ; " + enrollment.getYear() + " ; " + enrollment.getSemester();
			ReportClassName.setText(header);
			
			String str;
			Student student;
			for(int EID : listOfReportEnrollmentIds) {
				enrollment = (Enrollment)GetFrom("enrollments", EID);
				student = (Student)GetFrom("students", enrollment.getStudentId());
				
				str = "Student ID: " + student.getId() + "; Student Name: " + student.getLastName() + ", " + student.getFirstName()
						+ "; Grade: " + enrollment.getGrade();
				listOfStudentInfo.getItems().add(str);
			}
			
		}
		else {
			ErrorMessage("There are no enrollments with those details.");
		}
	}
	
	private int ExtractID(String string) {
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
		return Integer.parseInt(str);
	}
	

	
	// static Department department;
	// static DepartmentFile departmentFile;
	
	// static Instructor instructor;
	// static InstructorFile instructorFile;
	
	///////////////
	// department
	public void createDepartmentButtonListener() throws IOException, SQLException {
		String name = addDepartmentName.getText();
		Department dep = new Department(GetHighestID("departments") + 1, name);
		LOG("Get highest: " + GetHighestID("departments"));
		AddToDatabase(dep);
		wipeDepartmentFormInfo();
		postAddDepartment();
		
	}
	
	public void departmentSearchButtonListener() throws IOException, SQLException{
		try {
			searchDepartmentRequestedID = Integer.parseInt(searchDepartmentTextField.getText());
		}
		catch(NumberFormatException e) {
			ErrorMessage("Invalid Search, IDs are numerical");
		}
		
		if(searchDepartmentRequestedID < 1 || searchDepartmentRequestedID > GetHighestID("departments"))
			ErrorMessage("Invalid Search");
		else {
			departmentJustSearched = true;
			displaySearchedDepartment(searchDepartmentRequestedID);
		}
	}
	
	public void postAddDepartment() throws IOException, SQLException{
		LOG("Post add Department");
		
		makeAllInvisible();
		postAddDepartmentVBOX.setVisible(true);
		
		Department dep = (Department)GetHighestFrom("departments");
		postAddDepartmentID.setText(String.valueOf(dep.getId()));
		postAddDepartmentName.setText(dep.getName());
	}
	
	private void wipeDepartmentFormInfo() {
		addDepartmentName.setText("");
	}
	
	public void postAddDepartmentEditButtonListener() throws IOException, SQLException{
		makeAllInvisible();
		editDepartmentVBOX.setVisible(true);
		departmentRecordNumberEditing = GetHighestID("departments");
		LOG("Get highest: " + GetHighestID("departments"));
		Department dep = (Department)GetFrom("departments", departmentRecordNumberEditing);
		editDepartmentName.setText(dep.getName());
	}
	
	public void editDepartmentSaveChangesButtonListener() throws IOException, SQLException{
		String name = editDepartmentName.getText();
		Department dep = new Department(departmentRecordNumberEditing, name);
		
		EditDatabase(dep);
		displaySearchedDepartment(departmentRecordNumberEditing);
	}
	
	private void displaySearchedDepartment(int recordNumber) throws IOException, SQLException{
		LOG(recordNumber);
		Department dep = (Department)GetFrom("departments", recordNumber);
		
		makeAllInvisible();
		displayDepartmentVBOX.setVisible(true);
		
		displayDepartmentID.setText(String.valueOf(dep.getId()));
		displayDepartmentName.setText(dep.getName());
	}
	
	public void displayDepartmentEditButtonListener() throws IOException, SQLException{
		makeAllInvisible();
		editDepartmentVBOX.setVisible(true);
		
		departmentRecordNumberEditing = (searchDepartmentRequestedID == 0 || !departmentJustSearched)
											? GetHighestID("departments")
											: searchDepartmentRequestedID;
		
		Department dep = (Department)GetFrom("departments", departmentRecordNumberEditing);
		
		editDepartmentName.setText(dep.getName());
	}
	
	///////////////////
	// instructor
	public void createInstructorButtonListener() throws IOException, SQLException{
		String name = addInstructorName.getText();
		int depID = ExtractID(addInstructorDepartmentChoiceBox.getValue()); // get id from line
		Instructor instruct = new Instructor(GetHighestID("instructors") + 1, name, depID);
		AddToDatabase(instruct);
		wipeInstructorInfo();
		postAddInstructor();
	}
	
	private void wipeInstructorInfo() {
		addInstructorName.setText("");
		addInstructorDepartmentChoiceBox.getItems().removeAll(addInstructorDepartmentChoiceBox.getItems());
	}
	
	public void instructorSearchButtonListener() throws IOException, SQLException{
		try {
			searchInstructorRequestedID = Integer.parseInt(searchInstructorTextField.getText());
		}
		catch(NumberFormatException e) {
			ErrorMessage("Invalid Search, IDs are numerical");
		}
		if(searchInstructorRequestedID < 1 || searchInstructorRequestedID > GetHighestID("instructors")) 
			ErrorMessage("Invalid Search");
		else {
			instructorJustSearched = true;
			displaySearchedInstructor(searchInstructorRequestedID);
		}
	}
	
	public void postAddInstructor() throws IOException, SQLException{
		LOG("Post add Instructor");
		
		makeAllInvisible();
		postAddInstructorVBOX.setVisible(true);
		
		Instructor instruct = (Instructor)GetHighestFrom("instructors");
		postAddInstructorID.setText(String.valueOf(instruct.getId()));
		postAddInstructorDepName.setText(GetDepartmentName(instruct.getDepID()));
		postAddInstructorName.setText(instruct.getName());
	}
	
	public void postAddInstructorEditButtonListener() throws IOException, SQLException{
		makeAllInvisible();
		editInstructorVBOX.setVisible(true);
		instructorRecordNumberEditing = GetHighestID("instructors");
		Instructor instruct = (Instructor)GetHighestFrom("instructors");
		editInstructorName.setText(instruct.getName());
		editInstructorDepartmentChoiceBox.getItems().removeAll(editInstructorDepartmentChoiceBox.getItems());
		ResultSet set = stmnt.executeQuery("SELECT * FROM departments");
		while(set.next()){
			String option = set.getInt("ID") + "    " + set.getString("Name");
		 	editInstructorDepartmentChoiceBox.getItems().add(option);
		}
		editInstructorDepartmentChoiceBox.setValue(instruct.getDepID() + "\t" + GetDepartmentName(instruct.getDepID()));
	}
	
	public void editInstructorSaveChangesButtonListener() throws IOException, SQLException{
		String name = editInstructorName.getText();
		Department dep = (Department)GetFrom("departments", ExtractID(editInstructorDepartmentChoiceBox.getValue()));
		Instructor instruct = new Instructor(instructorRecordNumberEditing, name, dep.getId());
		EditDatabase(instruct);
		displaySearchedInstructor(instructorRecordNumberEditing);
	}
	
	public void displaySearchedInstructor(int recordNumber) throws IOException, SQLException{
		LOG(recordNumber);
		Instructor instruct = (Instructor)GetFrom("instructors", recordNumber);
		
		makeAllInvisible();
		
		displayInstructorVBOX.setVisible(true);
		
		displayInstructorID.setText(String.valueOf(instruct.getId()));
		displayInstructorDepName.setText(GetDepartmentName(instruct.getDepID()));
		displayInstructorName.setText(instruct.getName());
	}
	
	public void displayInstructorEditButtonListener() throws IOException, SQLException{
		makeAllInvisible();
		editInstructorVBOX.setVisible(true);
		
		instructorRecordNumberEditing = (searchInstructorRequestedID == 0 || !instructorJustSearched)
											? GetHighestID("instructors")
											: searchInstructorRequestedID;
		
		Instructor instruct = (Instructor)GetHighestFrom("instructors");
		editInstructorName.setText(instruct.getName());
		editInstructorDepartmentChoiceBox.getItems().removeAll(editInstructorDepartmentChoiceBox.getItems());
		ResultSet set = stmnt.executeQuery("SELECT * FROM departments");
		while(set.next())
		{
			String option = set.getInt("ID") + "   " + set.getString("Name");
		 	editInstructorDepartmentChoiceBox.getItems().add(option);
		}
		editInstructorDepartmentChoiceBox.setValue(instruct.getDepID() + "    " + GetDepartmentName(instruct.getDepID()));
	}
	
	//////////////////////
	// sort students
	public void sortStudentsButtonListener() {
		LOG("Sort students");
		makeAllInvisible();
		if(IsEmpty("students")) ErrorMessage("No Students have been made");
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

class Student{
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
}

/*
 * int = 4 bytes
 * char = 2 bytes
 * long = 8 bytes
 * float = 4 bytes
 * double = 8 bytes
 * short  = 2 bytes
 */

class Course{
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
	public void setInstructorID(int instruct) {this.instruct = instruct;}
	public void setDepartmentID(int department) {this.department = department;}
	
	public String getNum() {return this.num;}
	public int getId() {return this.id;}
	public String getName() {return this.name;}
	public int getInstructorID() {return this.instruct;}
	public int getDepartmentID() {return this.department;}
	
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
}

/*
 * int = 4 bytes
 * char = 2 bytes
 * long = 8 bytes
 * float = 4 bytes
 * double = 8 bytes
 * short  = 2 bytes
 */

class Enrollment{
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
}


class Department{
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

class Instructor{
	private String name;
	private int depID;
	private int id;
	
	public Instructor() {};
	public Instructor(int id, String name, int dep) {
		this.name = name;
		this.depID = dep;
		this.id = id;
	}
	
	public void setName(String name) {this.name = name;}
	public void setDepartmentID(int depID) {this.depID = depID;}
	
	public int getId() {return this.id;}
	public String getName() {return this.name;};
	public int getDepID() {return this.depID;}
	
	public boolean isSameDepartment(Instructor instruct) {
		return (this.depID == instruct.depID);
	}
	
	public void copy(Instructor inst) {
		this.id = inst.getId();
		this.depID = inst.getDepID();
		this.name = inst.getName();
	}
	
	public String toString() {
		String str = "Name: " + this.name + "\nDepartment: " + depID;
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