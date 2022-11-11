package com.financial;


import java.util.Objects;

import org.hibernate.resource.transaction.spi.TransactionCoordinatorBuilder.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class SequenceGeneratorService {
	
	@Autowired
	private MongoOperations mongoOperations;
	
	 public long getNextSequence(String seqName) {
	        // get the sequence number
	        final Query q = new Query(Criteria.where("id").is(seqName));
	        // increment the sequence number by 1
	        // "seq" should match the attribute value specified in DatabaseSequence.java class.
	        final Update u = new Update().inc("seq", 1);
	        // modify in document
	        final DatabaseSequence counter = mongoOperations.findAndModify(q, u,
	                FindAndModifyOptions.options().returnNew(true).upsert(true), DatabaseSequence.class);
	 
	        return !Objects.isNull(counter) ? counter.getSeq() : 1;
	    }
	
	
//	@Autowired
//    public SequenceGeneratorService(MongoOperations mongoOperations) {
//        this.mongoOperations = mongoOperations;
//    }
	
	
//public long generateSequence(String seqName)
//{
//	DatabaseSequence counter = mongoOperations.findAndModify(Criteria.where("_id").is(seqName), 
//			new Update().inc("seq",1),options().returnNew(true).upsert(true),
//			DatabaseSequence.class);
//	return !Objects.isNull(counter)? counter.getSeq() :1;
//}

}
