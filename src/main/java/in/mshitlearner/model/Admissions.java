package in.mshitlearner.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admissions {
	
	@Transient
    public static final String SEQUENCE_NAME = "admission_sequence";
	@Id
	private Long admissionId;
	private String adminNo;
	private String branchName;
}
