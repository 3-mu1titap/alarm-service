package adaptors.alarm_service.alarm.application.port.in.dto.rest;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RestReadAlarmDto {

    private String uuid;
    private String userUuid;

    @Builder
    public RestReadAlarmDto(String uuid, String userUuid) {
        this.uuid = uuid;
        this.userUuid = userUuid;
    }

}