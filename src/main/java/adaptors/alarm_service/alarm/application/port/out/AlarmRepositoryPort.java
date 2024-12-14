package adaptors.alarm_service.alarm.application.port.out;

import adaptors.alarm_service.alarm.application.port.in.dto.rest.RestReadAlarmDto;
import adaptors.alarm_service.alarm.application.port.out.dto.AlarmCreateQueryDto;
import adaptors.alarm_service.alarm.application.port.out.dto.AlarmReadQueryDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;
import java.util.List;

public interface AlarmRepositoryPort {

    void saveAlarm(AlarmCreateQueryDto alarmCreateQueryDto);

    AlarmReadQueryDto findAlarm(RestReadAlarmDto readAlarmDto);

    Slice<AlarmReadQueryDto> findAlarms(Pageable pageable, String userUuid);

    void deleteAlarm(AlarmReadQueryDto alarmReadQueryDto);

    AlarmReadQueryDto findLastAlarm(String userUuid);

    List<AlarmReadQueryDto> findAlarmsByTriggerDate(LocalDateTime now);
}