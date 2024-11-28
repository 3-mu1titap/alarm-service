package adaptors.alarm_service.alarm.application.port.in.dto;

import adaptors.alarm_service.alarm.domain.model.AlarmType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AlarmConsumerDto {

    private String userUuid;
    private String sessionUuid;
    private AlarmType alarmType;

    @Builder
    public AlarmConsumerDto(
            String userUuid,
            String sessionUuid,
            AlarmType alarmType) {
        this.userUuid = userUuid;
        this.sessionUuid = sessionUuid;
        this.alarmType = alarmType;
    }

}