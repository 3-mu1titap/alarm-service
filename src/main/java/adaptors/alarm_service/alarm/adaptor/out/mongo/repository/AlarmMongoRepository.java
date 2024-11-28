package adaptors.alarm_service.alarm.adaptor.out.mongo.repository;

import adaptors.alarm_service.alarm.adaptor.out.mongo.document.AlarmDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlarmMongoRepository extends MongoRepository<AlarmDocument, String> {

}