package in.mshitlearner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDetials {
	
	private String studName;
	private Integer studage;
	private String studAddress;
	private String adminssionBranch;
	private String admissionNo;
	
}
