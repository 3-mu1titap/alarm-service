package adaptors.alarm_service.alarm.domain.service;

import adaptors.alarm_service.alarm.application.port.in.dto.consumer.AlarmPortInDto;
import adaptors.alarm_service.alarm.application.port.out.dto.AlarmReadQueryDto;
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
                .triggerDate(alarmPortInDto.getTriggerDate())
                .isDeleted(false)
                .build();
    }

    public AlarmDomain toDomain(AlarmReadQueryDto readQueryDto) {
        return AlarmDomain.builder()
                .uuid(readQueryDto.getUuid())
                .alarmType(readQueryDto.getAlarmType())
                .senderUuid(readQueryDto.getSenderUuid())
                .senderMessage(readQueryDto.getSenderMessage())
                .receiverUuid(readQueryDto.getReceiverUuid())
                .receiverMessage(readQueryDto.getReceiverMessage())
                .triggerDate(readQueryDto.getTriggerDate())
                .createdAt(readQueryDto.getCreatedAt())
                .updatedAt(readQueryDto.getUpdatedAt())
                .isDeleted(readQueryDto.getIsDeleted())
                .build();
    }
}