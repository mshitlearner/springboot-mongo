package in.mshitlearner.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.mshitlearner.dto.StudentDetials;
import in.mshitlearner.model.Admissions;
import in.mshitlearner.model.Student;
import in.mshitlearner.repository.AdmissionsRepository;
import in.mshitlearner.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	@Autowired
	private AdmissionsRepository admissionsRepository;


	public String addStudentList(List<Student> lstStudent) {
		List<Student> lstStudentResult = studentRepository.saveAll(lstStudent);
		if (lstStudentResult.size() == lstStudent.size())
			return "List of Document Added Successfully";
		else
			return "List of Document failed to save";
	}
	
	@Transactional
	public String addStudent(StudentDetials studentDetails) {
		
		Admissions admissions = new Admissions();
		admissions.setAdmissionId(sequenceGeneratorService.generateSequence(Admissions.SEQUENCE_NAME));
		admissions.setAdminNo(studentDetails.getAdmissionNo());
		admissions.setBranchName(studentDetails.getAdminssionBranch());
		Admissions adminResult = admissionsRepository.save(admissions);
		
		Student stud = new Student();
		stud.setRno(sequenceGeneratorService.generateSequence(Student.SEQUENCE_NAME));
		stud.setAddress(studentDetails.getStudAddress());
		stud.setAge(studentDetails.getStudage());
		stud.setName(studentDetails.getStudName());
		stud.setAdmissionDetails(adminResult);
		//stud.setAdmissionId(adminResult.getAdmissionId());
		Student studentResult = studentRepository.save(stud);
		
		if (studentResult.getRno() > 0 && adminResult.getAdmissionId() > 0)
			return "Document Added Successfully";
		else
			return "Document failed to save";
	}

	public String updateStudent(Student student) {
		Optional<Student> optStudent = studentRepository.findById(student.getRno());
		if (optStudent.isPresent()) {
			Student finalStudent = new Student();
			finalStudent = optStudent.get();
			finalStudent.setName(student.getName());
			finalStudent.setAddress(student.getAddress());
			finalStudent.setAge(student.getAge());
			Student studentResult = studentRepository.save(student);
			if (studentResult.getRno() > 0)
				return "Document Updated Successfully";
			else
				return "Document failed to update";
		} else
			return "Document failed to update";

	}

	public String deleteStudent(Long studentId) {
		Optional<Student> optStudent = studentRepository.findById(studentId);
		if (optStudent.isPresent()) {
			// studentRepository.delete(optStudent.get());
			studentRepository.deleteById(studentId);
			return "Document Deleted Successfully";
		}
		return "Document failed to delete";
	}

	public Student fetchStudentById(Long studentId) {
		Optional<Student> optStudent = studentRepository.findById(studentId);
		if (optStudent.isPresent())
			return optStudent.get();
		else
			return null;
	}

	public List<Student> fetchAllStudent() {
		List<Student> lstStudent = studentRepository.findAll();
		return lstStudent;
	}

	public String getMinAgeStudent() {
		int minAge = studentRepository.minAge();
		return "Min. Age in all students are -- " + minAge;
	}

	public String getMaxAgeStudent() {
		int maxAge = studentRepository.maxAge();
		return "Max. Age in all students are -- " + maxAge;
	}

	public List<Student> findAllSortedByAgeAsc() {
		return studentRepository.findAll(Sort.by(Sort.Direction.ASC, "age"));
	}

	public List<Student> getByAgeGreaterThan(int age) {
		return studentRepository.findByAgeGreaterThan(age, Sort.by(Sort.Direction.DESC, "age"));
	}

	public List<Student> getTopByNameSorted(int limit) {
		return studentRepository.findTopByNameSorted(limit);
	}

	public String getCountOfStudents() {
		return "Total No of Students --" + studentRepository.countOfStudents();
	}

	public Student getStudentBasedOnAge(int age) {
		return studentRepository.fetchStudentBasedOnAge(age);
	}
	 
	
	public void getStudentAdmissionDetails(long rollNo) {
		List<Document> studentList =  (List<Document>) studentRepository.getStudentAdmissionDetails(rollNo);
		for(Document student : studentList) {
			System.out.println("Student List --"+student.value());
		}
		
	}
}
