package adaptors.alarm_service.alarm.adaptor.out.mongo.dto;

import adaptors.alarm_service.alarm.domain.model.AlarmType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class AlarmReadResponseDto {

    private String uuid;
    private String userUuid;
    private String message;
    private AlarmType alarmType;
    private LocalDateTime updatedAt;

    @Builder
    public AlarmReadResponseDto(
            String uuid,
            String userUuid,
            String message,
            AlarmType alarmType,
            LocalDateTime updatedAt) {
        this.uuid = uuid;
        this.userUuid = userUuid;
        this.message = message;
        this.alarmType = alarmType;
        this.updatedAt = updatedAt;
    }

}