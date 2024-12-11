package adaptors.alarm_service.alarm.application.mapper;

import adaptors.alarm_service.alarm.adaptor.out.mongo.dto.AlarmReadResponseDto;
import adaptors.alarm_service.alarm.application.port.out.dto.AlarmCreateQueryDto;
import adaptors.alarm_service.alarm.application.port.out.dto.AlarmReadQueryDto;
import adaptors.alarm_service.alarm.domain.model.AlarmDomain;
import org.springframework.stereotype.Component;

@Component
public class AlarmQueryMapper {

    public AlarmCreateQueryDto toCreateQueryDto(AlarmDomain alarmDomain) {

        return AlarmCreateQueryDto.builder()
                .uuid(alarmDomain.getUuid())
                .userUuid(alarmDomain.getSenderUuid())
                .sessionUuid(alarmDomain.getReceiverUuid())
                .alarmType(alarmDomain.getAlarmType())
                .senderMessage(alarmDomain.getSenderMessage())
                .receiverMessage(alarmDomain.getReceiverMessage())
                .createdAt(alarmDomain.getCreatedAt())
                .updatedAt(alarmDomain.getUpdatedAt())
                .isDeleted(alarmDomain.getIsDeleted())
                .build();
    }

    public AlarmReadResponseDto toReadResponseDto(AlarmReadQueryDto alarmReadQueryDto, String userUuid) {

        if (userUuid.equals(alarmReadQueryDto.getSenderUuid())) {
            return AlarmReadResponseDto.builder()
                    .uuid(alarmReadQueryDto.getUuid())
                    .userUuid(alarmReadQueryDto.getSenderUuid())
                    .message(alarmReadQueryDto.getSenderMessage())
                    .alarmType(alarmReadQueryDto.getAlarmType())
                    .updatedAt(alarmReadQueryDto.getUpdatedAt())
                    .build();
        }

        return AlarmReadResponseDto.builder()
                .uuid(alarmReadQueryDto.getUuid())
                .userUuid(alarmReadQueryDto.getReceiverUuid())
                .message(alarmReadQueryDto.getReceiverMessage())
                .alarmType(alarmReadQueryDto.getAlarmType())
                .updatedAt(alarmReadQueryDto.getUpdatedAt())
                .build();
    }

}