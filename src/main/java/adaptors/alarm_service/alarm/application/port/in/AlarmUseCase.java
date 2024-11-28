package adaptors.alarm_service.alarm.application.port.in;

import adaptors.alarm_service.alarm.application.port.in.dto.AlarmConsumerDto;

public interface AlarmUseCase {

    void createAlarm(AlarmConsumerDto alarmConsumerDto);

}