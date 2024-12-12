package adaptors.alarm_service.global.utils;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AlarmScheduler {

    @Scheduled(fixedRate = 60000)
    public void checkAndSendAlarms() {

        LocalDateTime now = LocalDateTime.now();


    }

}