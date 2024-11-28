package in.mshitlearner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.mshitlearner.dto.StudentDetials;
import in.mshitlearner.model.Student;
import in.mshitlearner.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping(value="/add")
	@Operation(summary = "Saving the Student Details", description = "Returns a msg as Document Added Successfully")
	@ApiResponses(
			value = { @ApiResponse(responseCode = "200", description = "Document Added Successfully")
	})
	public String addStudent(@RequestBody StudentDetials studentDetials) {
		return studentService.addStudent(studentDetials);
	}
	
	@PostMapping(value="/addList")
	@Operation(summary = "Saving the Multiple Student Details at a time", description = "Returns a msg as List of Document Added Successfully")
	public String addStudentList(@Parameter(description = "Excepting the multiple Students")  @RequestBody List<Student> lstStudent) {
		return studentService.addStudentList(lstStudent);
	}
	
	@PutMapping(value="/update")
	@Operation(summary = "Updating the Student Details", description = "Returns a msg as Document Updated Successfully")
	public String updateStudent(@Parameter(description = "Excepting the updated student Object") @RequestBody Student student) {
		return studentService.updateStudent(student);
	}
	
	@DeleteMapping(value="/delete/{studentId}")
	@Operation(summary = "Updating the Student Details", description = "Returns a msg as Document Deleted Successfully")
	public String deleteStudent(@Parameter(description = "Excepting the Student ID for Deleting the student Object") @PathVariable Long studentId) {
		return studentService.deleteStudent(studentId);
	}
	
	@GetMapping(value="/get/{studentId}")
	@Operation(summary="Geting the student object", description="Return the student object for corresponding for selected ID")
	public Student fetchStudentById(@Parameter(description = "Exceptingt the Stundent ID for gettting the student Object") @PathVariable Long studentId) {
		return studentService.fetchStudentById(studentId);
	}
	
	@GetMapping(value="/getAll")
	@Operation(summary="Getting the all student objects", description="Returning the all student objects")
	public List<Student> fetchAllStudent() {
		return studentService.fetchAllStudent();
	}
	
	@GetMapping(value="/minAge")
	@Operation(summary="Getting the Min Age among all Students", description="Returning the Min. Age")
	public String getMinAgeStudent() {
		return studentService.getMinAgeStudent();
	}
	
	@GetMapping(value="/maxAge")
	@Operation(summary="Getting the Max Age among all Students", description="Returning the Max. Age")
	public String getMaxAgeStudent() {
		return studentService.getMaxAgeStudent();
	}
	
	@GetMapping(value="/findAllSortedByAgeAsc")
	@Operation(summary="Getting the all Students by ascending sorting", description="Getting all the stundents by ascending order based on Age")
	public List<Student> findAllSortedByAgeAsc() {
		return studentService.findAllSortedByAgeAsc();
	}
	
	@GetMapping(value="/getByAgeGreaterThan/{age}")
	@Operation(summary="Getting the all Students which are greater then selected age ", description="Getting the all Students which are greater then selected age and displaying descending order based on age")
	public List<Student> getByAgeGreaterThan(@Parameter(description = "Excepting the age of the student for filtering") @PathVariable int age) {
		return studentService.getByAgeGreaterThan(age);
	}
	
	@GetMapping(value="/getTopByNameSorted/{Limit}")
	@Operation(summary="Fetching the students based on the Limit ", description="Fetching the students based on the Limit")
	public List<Student> getTopByNameSorted(@Parameter(description = "Excepting the limit Integer for fetching students based on filtering") @PathVariable int Limit) {
		return studentService.getTopByNameSorted(Limit);
	}
	
	@GetMapping(value="/getCountOfStudents")
	@Operation(summary="Fetching the students count", description="Fetching the students count")
	public String getCountOfStudents() {
		return studentService.getCountOfStudents();
	}
	
	@GetMapping(value="/fetchStudentBasedOnAge/{age}")
	@Operation(summary="Fetching the List Of student Details based on Age", description="Fetching the students Details based on Age Using Agg")
	public Student getStudentBasedOnAge(@Parameter(description = "Excepting the age of the student for filtering") @PathVariable int age) {
		return studentService.getStudentBasedOnAge(age);
	}
	
	@GetMapping(value="/getStudentAdmissionDetails/{rollNo}")
	@Operation(summary="Fetching the student Admission Details based on RollNo", description="Fetching the student Admission Details based on RollNo")
	public void getStudentAdmissionDetails(@Parameter(description = "Excepting the age of the student for filtering") @PathVariable long rollNo) {
		studentService.getStudentAdmissionDetails(rollNo);
	}
	
}
