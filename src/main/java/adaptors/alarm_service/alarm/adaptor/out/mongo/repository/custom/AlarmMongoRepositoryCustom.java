package adaptors.alarm_service.alarm.adaptor.out.mongo.repository.custom;

import adaptors.alarm_service.alarm.application.port.in.dto.rest.RestReadAlarmDto;
import adaptors.alarm_service.alarm.application.port.out.dto.AlarmReadQueryDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;
import java.util.List;

public interface AlarmMongoRepositoryCustom {

    AlarmReadQueryDto findAlarm(RestReadAlarmDto readAlarmDto);

    Slice<AlarmReadQueryDto> findAlarmsByUserUuid(Pageable pageable, String userUuid);

    AlarmReadQueryDto findLastAlarm(String userUuid);

    List<AlarmReadQueryDto> findAlarmsByTriggerDate(LocalDateTime now);
}