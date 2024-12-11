package adaptors.alarm_service.alarm.application.port.in;

import adaptors.alarm_service.alarm.adaptor.out.mongo.dto.AlarmReadResponseDto;
import adaptors.alarm_service.alarm.application.port.in.dto.consumer.AlarmPortInDto;
import adaptors.alarm_service.alarm.application.port.in.dto.rest.RestReadAlarmDto;
import adaptors.alarm_service.alarm.application.port.out.dto.AlarmReadQueryDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface AlarmUseCase {

    void createAlarm(AlarmPortInDto alarmPortInDto);

    AlarmReadResponseDto getAlarm(RestReadAlarmDto readDto);

    Slice<AlarmReadResponseDto> getAlarms(Pageable pageable, String userUuid);

    void deleteAlarm(RestReadAlarmDto readAlarmDto);

}