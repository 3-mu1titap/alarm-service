package adaptors.alarm_service.alarm.application.port.out.dto;

import adaptors.alarm_service.alarm.domain.model.AlarmType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AlarmReadQueryDto {

    private String id;
    private String uuid;
    private String senderUuid;
    private String receiverUuid;
    private AlarmType alarmType;
    private String senderMessage;
    private String receiverMessage;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Boolean isDeleted;

    @Builder
    public AlarmReadQueryDto(
        String id,
        String uuid,
        String senderUuid,
        String receiverUuid,
        AlarmType alarmType,
        String senderMessage,
        String receiverMessage,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Boolean isDeleted
    ) {
        this.id = id;
        this.uuid = uuid;
        this.senderUuid = senderUuid;
        this.receiverUuid = receiverUuid;
        this.alarmType = alarmType;
        this.senderMessage = senderMessage;
        this.receiverMessage = receiverMessage;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
    }

}