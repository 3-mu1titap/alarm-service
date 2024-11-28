package adaptors.alarm_service.alarm.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AlarmDomain {

    private String userUuid;
    private String sessionUuid;
    private AlarmType alarmType;
    private String message;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Boolean isDeleted;

    @Builder
    public AlarmDomain(
            String userUuid,
            String sessionUuid,
            AlarmType alarmType,
            String message,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Boolean isDeleted) {
        this.userUuid = userUuid;
        this.sessionUuid = sessionUuid;
        this.alarmType = alarmType;
        this.message = message;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
    }

}