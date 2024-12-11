package adaptors.alarm_service.alarm.domain.service;

import adaptors.alarm_service.alarm.adaptor.out.mongo.document.AlarmDocument;
import adaptors.alarm_service.alarm.application.port.in.dto.consumer.AlarmPortInDto;
import adaptors.alarm_service.alarm.domain.model.AlarmDomain;
import org.springframework.stereotype.Service;

import static adaptors.alarm_service.global.utils.UuidGenerator.*;

@Service
public class AlarmDomainService {

    public AlarmDomain createAlarm(AlarmPortInDto alarmPortInDto, String senderMessage, String receiverMessage) {
        return AlarmDomain.builder()
                .uuid(generateUuid())
                .senderUuid(alarmPortInDto.getSenderUuid())
                .receiverUuid(alarmPortInDto.getReceiverUuid())
                .alarmType(alarmPortInDto.getAlarmType())
                .senderMessage(senderMessage)
                .receiverMessage(receiverMessage)
                .isDeleted(false)
                .build();
    }

    public AlarmDomain deleteAlarm(AlarmDocument alarmDocument) {
        return AlarmDomain.builder()
                .uuid(alarmDocument.getUuid())
                .senderUuid(alarmDocument.getSenderUuid())
                .receiverUuid(alarmDocument.getReceiverUuid())
                .alarmType(alarmDocument.getAlarmType())
//                .message(alarmDocument.getMessage())
                .isDeleted(true)
                .createdAt(alarmDocument.getCreatedAt())
                .updatedAt(alarmDocument.getUpdatedAt())
                .build();
    }
}