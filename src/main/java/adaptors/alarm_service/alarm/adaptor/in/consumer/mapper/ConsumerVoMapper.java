package adaptors.alarm_service.alarm.adaptor.in.consumer.mapper;

import adaptors.alarm_service.alarm.adaptor.in.consumer.vo.*;
import adaptors.alarm_service.alarm.application.port.in.dto.consumer.AlarmPortInDto;
import adaptors.alarm_service.alarm.domain.model.AlarmType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class ConsumerVoMapper {

    public AlarmPortInDto toPortInDto(
            ConsumerSessionRegisterVo sessionRegisterVo,
            AlarmType alarmType) {
        return AlarmPortInDto.builder()
                .alarmType(alarmType)
                .senderUuid(sessionRegisterVo.getMenteeUuid())
                .senderMessage(new String[]{
                        sessionRegisterVo.getMentoringName()})
                .receiverUuid(sessionRegisterVo.getMentorUuid())
                .receiverMessage(new String[]{
                        sessionRegisterVo.getMenteeNickName(),
                        sessionRegisterVo.getMentoringName()})
                .build();
    }

    public AlarmPortInDto toPortInDto(
            ConsumerCreateMentoringVo createMentoringVo,
            AlarmType alarmType) {
        return AlarmPortInDto.builder()
                .alarmType(alarmType)
                .senderUuid(createMentoringVo.getMentorUuid())
                .senderMessage(new String[] {
                        createMentoringVo.getMentoringName()})
                .build();
    }

    public AlarmPortInDto toPortInDto(
            ConsumerCreateReviewVo createReviewVo,
            AlarmType alarmType) {
        return AlarmPortInDto.builder()
                .alarmType(alarmType)
                .senderUuid(createReviewVo.getMenteeUuid())
                .senderMessage(new String[]{
                        createReviewVo.getMentoringName()})
                .receiverUuid(createReviewVo.getMentorUuid())
                .receiverMessage(new String[]{
                        createReviewVo.getMenteeNickName(),
                        createReviewVo.getMentoringName()})
                .build();
    }

    public AlarmPortInDto toPortInDto(
            ConsumerSessionPayVo sessionPayVo,
            AlarmType alarmType) {
        return AlarmPortInDto.builder()
                .alarmType(alarmType)
                .senderUuid(sessionPayVo.getMenteeUuid())
                .senderMessage(new String[]{
                        sessionPayVo.getMentoringName(),
                        sessionPayVo.getVolt().toString()})
                .receiverUuid(sessionPayVo.getMentorUuid())
                .receiverMessage(new String[] {
                        sessionPayVo.getMenteeNickName(),
                        sessionPayVo.getMentoringName(),
                        sessionPayVo.getVolt().toString()})
                .build();
    }

    public AlarmPortInDto toPortInDto(
            ConsumerUpdateSessionUserVo updateSessionUserVo,
            String mentoringName,
            LocalTime startTime,
            String mentorUuid,
            AlarmType alarmType) {
        return AlarmPortInDto.builder()
                .alarmType(alarmType)
                .senderUuid(updateSessionUserVo.getUserUuid())
                .senderMessage(new String[]{
                        updateSessionUserVo.getStartDate().getMonth().toString(),
                        String.valueOf(updateSessionUserVo.getStartDate().getDayOfMonth()),
                        startTime.toString(),
                        mentoringName
                })
                .receiverUuid(mentorUuid)
                .receiverMessage(new String[]{
                        updateSessionUserVo.getStartDate().getMonth().toString(),
                        String.valueOf(updateSessionUserVo.getStartDate().getDayOfMonth()),
                        startTime.toString(),
                        mentoringName
                })
                .triggerDate(LocalDateTime.of(updateSessionUserVo.getStartDate(), startTime))
                .build();
    }

}