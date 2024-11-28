package in.mshitlearner.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	@Transient
    public static final String SEQUENCE_NAME = "student_sequence";
	@Id
	private Long rno;
	private String name;
	private String address;
	private Integer age;
	@DBRef
	private Admissions admissionDetails;
}
