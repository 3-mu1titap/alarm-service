package adaptors.alarm_service.alarm.adaptor.in.consumer;

import adaptors.alarm_service.alarm.adaptor.in.consumer.vo.*;
import adaptors.alarm_service.alarm.adaptor.in.consumer.mapper.ConsumerVoMapper;
import adaptors.alarm_service.alarm.adaptor.in.feignclient.MentoringServiceFeignClient;
import adaptors.alarm_service.alarm.adaptor.in.feignclient.dto.SessionRoomResponseDto;
import adaptors.alarm_service.alarm.application.port.in.AlarmUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

import static adaptors.alarm_service.alarm.domain.model.AlarmType.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final AlarmUseCase alarmUseCase;
    private final ConsumerVoMapper consumerVoMapper;
    private static final String GROUP_ID = "kafka-alarm-service";

    private final MentoringServiceFeignClient mentoringServiceFeignClient;

    @KafkaListener(topics = "register-session-user", groupId = GROUP_ID, containerFactory = "userSessionJoinDtoListener")
    public void processSessionJoinAlarm(ConsumerSessionRegisterVo consumerSessionRegisterVo) {
        log.info("alarmConsumerVo: {}", consumerSessionRegisterVo);
        alarmUseCase.createAlarm(consumerVoMapper.toPortInDto(consumerSessionRegisterVo, SESSION_REGISTER));
    }

    @KafkaListener(topics = "create-mentoring", groupId = GROUP_ID, containerFactory = "createMentoringDtoListener")
    public void processCreateMentoringAlarm(ConsumerCreateMentoringVo consumerCreateMentoringVo) {
        log.info("consumerCreateMentoringVo: {}", consumerCreateMentoringVo);
        alarmUseCase.createAlarm(consumerVoMapper.toPortInDto(consumerCreateMentoringVo, MENTORING_REGISTER));
    }

    @KafkaListener(topics = "create-review-topic", groupId = GROUP_ID, containerFactory = "createReviewDtoListener")
    public void processCreateReviewAlarm(ConsumerCreateReviewVo consumerCreateReviewVo) {
        log.info("consumerCreateReviewVo: {}", consumerCreateReviewVo);
//        alarmUseCase.createAlarm(consumerVoMapper.toPortInDto(consumerCreateReviewVo, REVIEW_REGISTER));
    }

    @KafkaListener(topics = "pay-session", groupId = GROUP_ID, containerFactory = "sessionPayDtoListener")
    public void processCreateReviewAlarm(ConsumerSessionPayVo consumerSessionPayVo) {
        log.info("consumerSessionPayVo: {}", consumerSessionPayVo);
        alarmUseCase.createAlarm(consumerVoMapper.toPortInDto(consumerSessionPayVo, PAY_SESSION));
    }

    @KafkaListener(topics = "update-session-user", groupId = GROUP_ID, containerFactory = "updateSessionUserDtoListener")
    public void processUpdateSessionUserAlarm(ConsumerUpdateSessionUserVo consumerUpdateSessionUserVo) {
        log.info("consumerUpdateSessionUserVo: {}", consumerUpdateSessionUserVo);

        if (consumerUpdateSessionUserVo.getStatus().equals("CONFIRMED")) {
            log.info("feignClient 호출 전1");
            SessionRoomResponseDto sessionRoomResponseDto = mentoringServiceFeignClient.findSessionRoomBySessionUuid(consumerUpdateSessionUserVo.getSessionUuid());
            String mentoringName = sessionRoomResponseDto.getMentoringName();
            LocalTime startTime = sessionRoomResponseDto.getStartTime();
            log.info("mentoringName: {}", mentoringName);
            log.info("startTime: {}", startTime);
            log.info("feignClient 호출 후1");

            log.info("feignClient 호출 전2");
            String mentorUuid = mentoringServiceFeignClient.getMentorUuidBySessionUuid(consumerUpdateSessionUserVo.getSessionUuid());
            log.info("mentorUuid: {}", mentorUuid);
            log.info("feignClient 호출 후2");

            alarmUseCase.createAlarm(consumerVoMapper.toPortInDto(consumerUpdateSessionUserVo, mentoringName, startTime, mentorUuid, SESSION_CONFIRM));
        }
    }

}