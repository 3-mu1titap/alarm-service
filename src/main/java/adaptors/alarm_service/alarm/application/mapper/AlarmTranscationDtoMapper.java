package adaptors.alarm_service.alarm.application.mapper;

import adaptors.alarm_service.alarm.application.port.out.dto.AlarmConsumerTransactionDto;
import adaptors.alarm_service.alarm.domain.model.AlarmDomain;
import org.springframework.stereotype.Component;

@Component
public class AlarmTranscationDtoMapper {

    public AlarmConsumerTransactionDto toTransactionDto(AlarmDomain alarmDomain) {
        return AlarmConsumerTransactionDto.builder()
                .userUuid(alarmDomain.getUserUuid())
                .sessionUuid(alarmDomain.getSessionUuid())
                .alarmType(alarmDomain.getAlarmType())
                .isDeleted(alarmDomain.getIsDeleted())
                .build();
    }

}