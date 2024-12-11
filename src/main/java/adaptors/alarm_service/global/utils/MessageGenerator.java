package adaptors.alarm_service.global.utils;

import adaptors.alarm_service.alarm.application.port.in.dto.consumer.AlarmPortInDto;
import adaptors.alarm_service.alarm.domain.model.AlarmType;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static adaptors.alarm_service.alarm.domain.model.AlarmType.*;

@Component
public class MessageGenerator {

    public AlarmType getAlarmType(AlarmPortInDto dto) {
        return dto.getAlarmType();
    }

    public String generateSenderMessage(AlarmPortInDto dto) {

        String[] senderMessages = dto.getSenderMessage();
        AlarmType alarmType = dto.getAlarmType();

        return Optional.ofNullable(senderMessages)
                .map(messages -> {
                    String template = MessageTemplates.SENDER_TEMPLATES.get(alarmType);
                    return template != null
                            ? String.format(template, (Object[]) messages)
                            : "메시지 생성에 실패하였습니다.";
                })
                .orElse(null);
    }

    public String generateReceiverMessage(AlarmPortInDto dto) {

        String[] receiverMessages = dto.getReceiverMessage();
        AlarmType alarmType = dto.getAlarmType();

        return Optional.ofNullable(receiverMessages)
                .map(messages -> {
                    String template = MessageTemplates.RECEIVER_TEMPLATES.get(alarmType);
                    return template != null
                            ? String.format(template, (Object[]) messages)
                            : "메시지 생성에 실패하였습니다.";
                })
                .orElse(null);
    }
}