package adaptors.alarm_service.alarm.domain.service;

import adaptors.alarm_service.alarm.application.port.in.dto.AlarmConsumerDto;
import adaptors.alarm_service.alarm.domain.model.AlarmDomain;
import org.springframework.stereotype.Service;

@Service
public class AlarmDomainService {

    public AlarmDomain createAlarm(AlarmConsumerDto alarmConsumerDto) {
        return AlarmDomain.builder()
                .userUuid(alarmConsumerDto.getUserUuid())
                .sessionUuid(alarmConsumerDto.getSessionUuid())
                .alarmType(alarmConsumerDto.getAlarmType())
                .isDeleted(false)
                .build();
    }

}