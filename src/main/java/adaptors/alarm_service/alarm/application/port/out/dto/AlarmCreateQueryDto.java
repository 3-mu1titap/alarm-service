package adaptors.alarm_service.alarm.application.port.out.dto;

import adaptors.alarm_service.alarm.domain.model.AlarmType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class AlarmCreateQueryDto {

    private String uuid;
    private String userUuid;
    private String sessionUuid;
    private AlarmType alarmType;
    private String senderMessage;
    private String receiverMessage;

    private Boolean isDeleted;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public AlarmCreateQueryDto(
            String uuid,
            String userUuid,
            String sessionUuid,
            AlarmType alarmType,
            String senderMessage,
            String receiverMessage,
            Boolean isDeleted,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.uuid = uuid;
        this.userUuid = userUuid;
        this.sessionUuid = sessionUuid;
        this.alarmType = alarmType;
        this.senderMessage = senderMessage;
        this.receiverMessage = receiverMessage;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}