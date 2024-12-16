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

    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    /**
     * SseEmitter 생성 및 등록
     */
    public SseEmitter createEmitter(String userUuid) {

        SseEmitter emitter = new SseEmitter(30 ^ 60 * 1000L); // 30분
        emitters.put(userUuid, emitter);

        log.info("SSE Emitter created for user: {}", userUuid);

        emitter.onCompletion(() -> {
            emitters.remove(userUuid);
            log.info("SSE connection completed for user: {}", userUuid);
        });
        emitter.onTimeout(() -> {
            emitters.remove(userUuid);
            log.info("SSE connection timed out for user: {}", userUuid);
        });
        emitter.onError((e) -> {
            emitters.remove(userUuid);
            log.error("SSE connection error for user: {}", userUuid, e);
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

        log.info("sendAlarm senderUuid : {} -> {}", senderUuid, senderMessage);

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
                log.info("전송 성공 : {}", message);
//                emitter.send(message);
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

//    public void sendSseNotification(String userInfo, Notification notification) {
//        SseEmitter emitter = clients.get(userInfo);
//        log.info("——————————————");
//        log.info(clients.toString());
//        log.info("——————————————");
//        if (emitter != null) {
//            try {
//                emitter.send(notification.toString());
//                log.info("sse success send event, emitter: {}", emitter);
//            } catch (Exception e) {
//                clients.remove(userInfo);
//                log.error("Error while sending {} notification: {}", notification.getType(), e.getMessage());
//            }
//        }
//        else log.info("emitter is null");
//    }

}