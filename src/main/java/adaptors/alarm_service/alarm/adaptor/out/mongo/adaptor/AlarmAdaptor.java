package adaptors.alarm_service.alarm.adaptor.out.mongo.adaptor;

import adaptors.alarm_service.alarm.adaptor.out.mongo.document.AlarmDocument;
import adaptors.alarm_service.alarm.adaptor.out.mongo.mapper.AlarmDocumentMapper;
import adaptors.alarm_service.alarm.adaptor.out.mongo.repository.AlarmMongoRepository;
import adaptors.alarm_service.alarm.adaptor.out.mongo.repository.custom.AlarmMongoRepositoryCustom;
import adaptors.alarm_service.alarm.application.port.in.dto.rest.RestReadAlarmDto;
import adaptors.alarm_service.alarm.application.port.out.AlarmRepositoryPort;
import adaptors.alarm_service.alarm.application.port.out.dto.AlarmCreateQueryDto;
import adaptors.alarm_service.alarm.application.port.out.dto.AlarmReadQueryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AlarmAdaptor implements AlarmRepositoryPort {

    private final AlarmMongoRepository alarmMongoRepository;
    private final AlarmMongoRepositoryCustom alarmMongoRepositoryCustom;
    private final AlarmDocumentMapper alarmDocumentMapper;

    @Override
    public void saveAlarm(AlarmCreateQueryDto alarmCreateQueryDto) {
        AlarmDocument alarmDocument = alarmDocumentMapper.createDocument(alarmCreateQueryDto);
        log.info("alarmDocument : {}", alarmDocument);
        alarmMongoRepository.save(alarmDocument);
    }

    @Override
    public AlarmReadQueryDto findAlarm(RestReadAlarmDto readAlarmDto) {
        return alarmMongoRepositoryCustom.findAlarm(readAlarmDto);
    }

    @Override
    public Slice<AlarmReadQueryDto> findAlarms(Pageable pageable, String userUuid) {
        return alarmMongoRepositoryCustom.findAlarmsByUserUuid(pageable, userUuid);
    }

    @Override
    public void deleteAlarm(AlarmReadQueryDto alarmReadQueryDto) {
        alarmMongoRepository.save(alarmDocumentMapper.toDocument(alarmReadQueryDto));
    }
}