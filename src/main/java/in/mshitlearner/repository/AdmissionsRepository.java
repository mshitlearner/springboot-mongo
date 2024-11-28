package in.mshitlearner.repository;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import in.mshitlearner.model.Admissions;

public interface AdmissionsRepository  extends MongoRepository<Admissions, Long> {

	
}
