package adaptors.alarm_service.alarm.adaptor.out.mongo.mapper;

import adaptors.alarm_service.alarm.adaptor.out.mongo.document.AlarmDocument;
import adaptors.alarm_service.alarm.application.port.out.dto.AlarmCreateQueryDto;
import adaptors.alarm_service.alarm.application.port.out.dto.AlarmReadQueryDto;
import org.springframework.stereotype.Component;

@Component
public class AlarmDocumentMapper {

    public AlarmDocument createDocument(AlarmCreateQueryDto alarmCreateQueryDto) {
        return AlarmDocument.builder()
                .uuid(alarmCreateQueryDto.getUuid())
                .senderUuid(alarmCreateQueryDto.getUserUuid())
                .receiverUuid(alarmCreateQueryDto.getSessionUuid())
                .alarmType(alarmCreateQueryDto.getAlarmType())
                .senderMessage(alarmCreateQueryDto.getSenderMessage())
                .receiverMessage(alarmCreateQueryDto.getReceiverMessage())
                .triggerDate(alarmCreateQueryDto.getTriggerDate())
                .createdAt(alarmCreateQueryDto.getCreatedAt())
                .updatedAt(alarmCreateQueryDto.getUpdatedAt())
                .isDeleted(alarmCreateQueryDto.getIsDeleted())
                .build();
    }

    public AlarmDocument toDocument(AlarmReadQueryDto alarmReadQueryDto) {
        return AlarmDocument.builder()
                .id(alarmReadQueryDto.getId())
                .uuid(alarmReadQueryDto.getUuid())
                .senderUuid(alarmReadQueryDto.getSenderUuid())
                .receiverUuid(alarmReadQueryDto.getReceiverUuid())
                .alarmType(alarmReadQueryDto.getAlarmType())
                .senderMessage(alarmReadQueryDto.getSenderMessage())
                .receiverMessage(alarmReadQueryDto.getReceiverMessage())
                .triggerDate(alarmReadQueryDto.getTriggerDate())
                .createdAt(alarmReadQueryDto.getCreatedAt())
                .updatedAt(alarmReadQueryDto.getUpdatedAt())
                .isDeleted(true)
                .build();
    }

    public AlarmReadQueryDto toReadQueryDto(AlarmDocument alarmDocument) {
        return AlarmReadQueryDto.builder()
                .id(alarmDocument.getId())
                .uuid(alarmDocument.getUuid())
                .senderUuid(alarmDocument.getSenderUuid())
                .receiverUuid(alarmDocument.getReceiverUuid())
                .alarmType(alarmDocument.getAlarmType())
                .senderMessage(alarmDocument.getSenderMessage())
                .receiverMessage(alarmDocument.getReceiverMessage())
                .triggerDate(alarmDocument.getTriggerDate())
                .createdAt(alarmDocument.getCreatedAt())
                .updatedAt(alarmDocument.getUpdatedAt())
                .isDeleted(alarmDocument.getIsDeleted())
                .build();
    }

}