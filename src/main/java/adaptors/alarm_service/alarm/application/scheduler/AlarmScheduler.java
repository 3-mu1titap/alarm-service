package adaptors.alarm_service.alarm.application.scheduler;

import adaptors.alarm_service.alarm.application.port.in.AlarmUseCase;
import adaptors.alarm_service.alarm.application.port.out.AlarmRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AlarmScheduler {

    private final AlarmUseCase alarmUseCase;
    private final AlarmRepositoryPort alarmRepositoryPort;

    @Scheduled(cron = "0 * * * * *", zone = "Asia/Seoul") // 매 분 0초에 실행
    public void findAlarmsEqualTriggerDate() {
        LocalDateTime now = LocalDateTime.now();

        alarmRepositoryPort.findAlarmsByTriggerDate(now);
    }

}