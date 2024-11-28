package in.mshitlearner.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import in.mshitlearner.model.CounterSequence;

@Service
public class SequenceGeneratorService {
	
	@Autowired
	private MongoOperations mongoOperations;
	
	public Long generateSequence(String sequenceName) {
		Query query = new Query(Criteria.where("_id").is(sequenceName));
	    Update update = new Update().inc("autoIncrementId", 1);
	    FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(true);
	    CounterSequence counter = mongoOperations.findAndModify(query, update, options, CounterSequence.class); 
	    return !Objects.isNull(counter) ? counter.getAutoIncrementId() : 1;
	}
}
