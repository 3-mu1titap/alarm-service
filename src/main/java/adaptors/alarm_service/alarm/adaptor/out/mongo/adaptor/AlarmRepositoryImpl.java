package adaptors.alarm_service.alarm.adaptor.out.mongo.adaptor;

import adaptors.alarm_service.alarm.adaptor.out.mongo.mapper.AlarmDocumentMapper;
import adaptors.alarm_service.alarm.adaptor.out.mongo.repository.AlarmMongoRepository;
import adaptors.alarm_service.alarm.application.port.out.AlarmRepositoryPort;
import adaptors.alarm_service.alarm.application.port.out.dto.AlarmConsumerTransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AlarmRepositoryImpl implements AlarmRepositoryPort {

    private final AlarmMongoRepository alarmMongoRepository;
    private final AlarmDocumentMapper alarmDocumentMapper;

    @Override
    public void saveAlarm(AlarmConsumerTransactionDto alarmConsumerTransactionDto) {
        alarmMongoRepository.save(alarmDocumentMapper.createDocument(alarmConsumerTransactionDto));
    }
}