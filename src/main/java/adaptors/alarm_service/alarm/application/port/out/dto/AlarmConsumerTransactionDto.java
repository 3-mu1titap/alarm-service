package adaptors.alarm_service.alarm.application.port.out.dto;

import adaptors.alarm_service.alarm.domain.model.AlarmType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AlarmConsumerTransactionDto {

    private String userUuid;
    private String sessionUuid;
    private AlarmType alarmType;
    private String message;
    private Boolean isDeleted;

    @Builder
    public AlarmConsumerTransactionDto(
            String userUuid,
            String sessionUuid,
            AlarmType alarmType,
            String message,
            Boolean isDeleted) {
        this.userUuid = userUuid;
        this.sessionUuid = sessionUuid;
        this.alarmType = alarmType;
        this.message = message;
        this.isDeleted = isDeleted;
    }

}