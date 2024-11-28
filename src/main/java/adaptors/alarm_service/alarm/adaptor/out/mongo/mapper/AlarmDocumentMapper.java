package adaptors.alarm_service.alarm.adaptor.out.mongo.mapper;

import adaptors.alarm_service.alarm.adaptor.out.mongo.document.AlarmDocument;
import adaptors.alarm_service.alarm.application.port.out.dto.AlarmConsumerTransactionDto;
import org.springframework.stereotype.Component;

@Component
public class AlarmDocumentMapper {

    public AlarmDocument createDocument(AlarmConsumerTransactionDto alarmConsumerTransactionDto) {
        return AlarmDocument.builder()
                .userUuid(alarmConsumerTransactionDto.getUserUuid())
                .sessionUuid(alarmConsumerTransactionDto.getSessionUuid())
                .alarmType(alarmConsumerTransactionDto.getAlarmType())
                .isDeleted(alarmConsumerTransactionDto.getIsDeleted())
                .build();
    }

}