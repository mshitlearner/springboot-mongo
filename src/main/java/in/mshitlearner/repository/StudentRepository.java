package in.mshitlearner.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import in.mshitlearner.model.Student;

public interface StudentRepository extends MongoRepository<Student, Long> {
	
	@Aggregation(pipeline = {"{$group : {_id:'', maxAge:{$max: $age}}}"})
	public Integer maxAge();
	
	@Aggregation(pipeline = {"{$group : {_id:'', minAge:{$min: $age}}}"})
	public Integer minAge();
	
	@Query("{'age':{$gt:?0}}")
	public List<Student> findByAgeGreaterThan(int age, Sort sort);
	
	@Aggregation(pipeline = {"{$sort : {name:1}}","{$limit :?0}"})
	public List<Student> findTopByNameSorted(int limit);
	
	@Aggregation(pipeline = {"{$count : 'totalCount'}"})
	public Integer countOfStudents();
	
	@Aggregation(pipeline = {"{$match: { 'age': { $eq: ?0 } }}"})
	public Student fetchStudentBasedOnAge(int age);
	
	@Aggregation(pipeline={
			"{$match: { '_id': { $eq: ?0 } }}",	
			"{$lookup:{from:'Admissions',localField:'Admissions',foreignField:'_id', as:'admissionDetails'}}"
		})
		public List<Document> getStudentAdmissionDetails(long studentRollNo);
	
	/*
	@Component
	public class CustomProductsRepositoryImpl implements CustomProductsRepository {

	   private static final Logger LOG = LoggerFactory
	           .getLogger(CustomProductsRepository.class);

	   @Autowired
	   MongoTemplate mongoTemplate;

	   public int bulkInsertProducts(int count) {

	       LOG.info("Dropping collection...");
	       mongoTemplate.dropCollection(Products.class);
	       LOG.info("Dropped!");

	       Instant start = Instant.now();
	       mongoTemplate.setWriteConcern(WriteConcern.W1.withJournal(true));

	       Products [] productList = Products.RandomProducts(count);
	       BulkOperations bulkInsertion = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, Products.class);

	       for (int i=0; i<productList.length; ++i)
	           bulkInsertion.insert(productList[i]);

	       BulkWriteResult bulkWriteResult = bulkInsertion.execute();

	       LOG.info("Bulk insert of "+bulkWriteResult.getInsertedCount()+" documents completed in "+ Duration.between(start, Instant.now()).toMillis() + " milliseconds");
	       return bulkWriteResult.getInsertedCount();
	   }
	}
	*/
}
