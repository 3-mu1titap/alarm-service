package adaptors.alarm_service.alarm.application.port.in.dto.consumer;

import adaptors.alarm_service.alarm.domain.model.AlarmType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AlarmPortInDto {

    private String senderUuid;
    private String receiverUuid;
    private AlarmType alarmType;
    private String[] senderMessage;
    private String[] receiverMessage;
    private LocalDateTime triggerDate;

    @Builder
    public AlarmPortInDto(
            String senderUuid,
            String receiverUuid,
            AlarmType alarmType,
            String[] senderMessage,
            String[] receiverMessage,
            LocalDateTime triggerDate
    ) {
        this.senderUuid = senderUuid;
        this.receiverUuid = receiverUuid;
        this.alarmType = alarmType;
        this.senderMessage = senderMessage;
        this.receiverMessage = receiverMessage;
        this.triggerDate = triggerDate;
    }

}