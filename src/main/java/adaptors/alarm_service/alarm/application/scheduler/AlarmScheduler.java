package adaptors.alarm_service.alarm.application.scheduler;

import adaptors.alarm_service.alarm.application.port.out.AlarmRepositoryPort;
import adaptors.alarm_service.alarm.domain.model.AlarmDomain;
import adaptors.alarm_service.alarm.domain.service.AlarmDomainService;
import adaptors.alarm_service.global.utils.SseEmitterManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlarmScheduler {

    private final AlarmRepositoryPort alarmRepositoryPort;
    private final AlarmDomainService alarmDomainService;
    private final SseEmitterManager sseEmitterManager;

    @Scheduled(cron = "0 0 * * * *", zone = "Asia/Seoul") // 매 시간 정각에 실행
    public void findAlarmsEqualTriggerDate() {
        LocalDateTime now = LocalDateTime.now();

        log.info("findAlarmsEqualTriggerDate now : {}", now);

        List<AlarmDomain> alarmDomainList = alarmRepositoryPort.findAlarmsByTriggerDate(now).stream()
                .map(alarmDomainService::toDomain)
                .toList();

        for (AlarmDomain alarmDomain : alarmDomainList) {
            sseEmitterManager.sendAlarm(alarmDomain);
        }

    }

}