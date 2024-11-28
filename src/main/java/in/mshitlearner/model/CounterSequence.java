package in.mshitlearner.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "counter")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CounterSequence {
	
	@Id
	private String id;
    private Long autoIncrementId;
}
