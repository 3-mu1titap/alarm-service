package adaptors.alarm_service.alarm.adaptor.in.consumer;

import adaptors.alarm_service.alarm.adaptor.in.consumer.vo.AlarmConsumerVo;
import adaptors.alarm_service.alarm.adaptor.in.consumer.mapper.ConsumerVoMapper;
import adaptors.alarm_service.alarm.application.port.in.AlarmUseCase;
import adaptors.alarm_service.alarm.domain.model.AlarmType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final AlarmUseCase alarmUseCase;
    private final ConsumerVoMapper consumerVoMapper;
    private static final String GROUP_ID = "kafka-alarm-service";

    @KafkaListener(topics = "register-session-user", groupId = GROUP_ID, containerFactory = "userSessionJoinDtoListener")
    public void processCreateAlarm(AlarmConsumerVo alarmConsumerVo) {
        log.info("alarmConsumerVo: {}", alarmConsumerVo);
        alarmUseCase.createAlarm(consumerVoMapper.toDto(alarmConsumerVo, AlarmType.SESSION));
    }

}