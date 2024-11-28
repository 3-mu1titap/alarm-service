package adaptors.alarm_service.alarm.application.port.out;

import adaptors.alarm_service.alarm.application.port.out.dto.AlarmConsumerTransactionDto;

public interface AlarmRepositoryPort {

    void saveAlarm(AlarmConsumerTransactionDto alarmConsumerTransactionDto);

}
