package adaptors.alarm_service.alarm.adaptor.in.consumer.mapper;

import adaptors.alarm_service.alarm.adaptor.in.consumer.vo.AlarmConsumerVo;
import adaptors.alarm_service.alarm.application.port.in.dto.AlarmConsumerDto;
import adaptors.alarm_service.alarm.domain.model.AlarmType;
import org.springframework.stereotype.Component;

@Component
public class ConsumerVoMapper {

    public AlarmConsumerDto toDto(AlarmConsumerVo alarmConsumerVo, AlarmType alarmType) {
        return AlarmConsumerDto.builder()
                .userUuid(alarmConsumerVo.getUserUuid())
                .sessionUuid(alarmConsumerVo.getSessionUuid())
                .alarmType(alarmType)
                .build();
    }

}