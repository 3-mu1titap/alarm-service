package adaptors.alarm_service.alarm.adaptor.in.rest.mapper;

import adaptors.alarm_service.alarm.application.port.in.dto.rest.RestReadAlarmDto;
import org.springframework.stereotype.Component;

@Component
public class RestVoMapper {

    public RestReadAlarmDto toReadDto(String uuid, String userUuid) {
        return RestReadAlarmDto.builder()
                .uuid(uuid)
                .userUuid(userUuid)
                .build();
    }

}