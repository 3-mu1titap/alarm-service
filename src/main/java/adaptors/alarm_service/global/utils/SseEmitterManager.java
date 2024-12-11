package adaptors.alarm_service.global.utils;

import adaptors.alarm_service.alarm.domain.model.AlarmDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class SseEmitterManager {

    // userUuid(receiver)를 key로 SseEmitter를 저장
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    /**
     * SseEmitter 생성 및 등록
     */
    public SseEmitter createEmitter(String receiverUud) {

        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE); // 무제한 연결
        emitters.put(receiverUud, emitter);

        log.info("SSE Emitter created for user: {}", receiverUud);

        emitter.onCompletion(() -> {
            emitters.remove(receiverUud);
            log.info("SSE connection completed for user: {}", receiverUud);
        });
        emitter.onTimeout(() -> {
            emitters.remove(receiverUud);
            log.info("SSE connection timed out for user: {}", receiverUud);
        });
        emitter.onError((e) -> {
            emitters.remove(receiverUud);
            log.error("SSE connection error for user: {}", receiverUud, e);
        });

        return emitter;
    }

    /**
     * 알림 전송
     */
    public void sendAlarm(AlarmDomain alarmDomain) {

        String senderUuid = alarmDomain.getSenderUuid();
        String senderMessage = alarmDomain.getSenderMessage();

        String receiverUuid = alarmDomain.getReceiverUuid();
        String receiverMessage = alarmDomain.getReceiverMessage();

        if (senderUuid != null) {
            sendSSE(alarmDomain, senderUuid, senderMessage);

        }
        if (receiverUuid != null) {
            sendSSE(alarmDomain, receiverUuid, receiverMessage);
        }
    }

    private void sendSSE(AlarmDomain alarmDomain, String userUuid, String message) {

        SseEmitter emitter = emitters.get(userUuid);

        if (emitter != null) {
            try {
                emitter.send(
                        SseEmitter.event()
                                .id(userUuid)
                                .name(alarmDomain.getAlarmType().toString())
                                .data(message)
                );
            } catch (IOException | IllegalStateException e) {
                log.error("Error sending SSE for user {} : {}", userUuid, e.getMessage());
                emitters.remove(userUuid);
            }
        } else {
            log.warn("No SseEmitter found for user: {} ", userUuid);
        }
    }

}