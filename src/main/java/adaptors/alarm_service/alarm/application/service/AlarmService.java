package adaptors.alarm_service.alarm.application.service;

import adaptors.alarm_service.alarm.adaptor.out.mongo.dto.AlarmReadResponseDto;
import adaptors.alarm_service.alarm.application.mapper.AlarmQueryMapper;
import adaptors.alarm_service.alarm.application.port.in.AlarmUseCase;
import adaptors.alarm_service.alarm.application.port.in.dto.consumer.AlarmPortInDto;
import adaptors.alarm_service.alarm.application.port.in.dto.rest.RestReadAlarmDto;
import adaptors.alarm_service.alarm.application.port.out.AlarmRepositoryPort;
import adaptors.alarm_service.alarm.application.port.out.dto.AlarmCreateQueryDto;
import adaptors.alarm_service.alarm.application.port.out.dto.AlarmReadQueryDto;
import adaptors.alarm_service.alarm.domain.model.AlarmDomain;
import adaptors.alarm_service.alarm.domain.service.AlarmDomainService;
import adaptors.alarm_service.global.utils.MessageGenerator;
import adaptors.alarm_service.global.utils.SseEmitterManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AlarmService implements AlarmUseCase {

    private final AlarmDomainService alarmDomainService;
    private final AlarmRepositoryPort alarmRepositoryPort;
    private final AlarmQueryMapper alarmQueryMapper;

    private final MessageGenerator messageGenerator;
    private final SseEmitterManager sseEmitterManager;

    @Transactional
    @Override
    public void createAlarm(AlarmPortInDto alarmPortInDto) {

        String senderMessage = messageGenerator.generateSenderMessage(alarmPortInDto);

        String receiverMessage = messageGenerator.generateReceiverMessage(alarmPortInDto);

        AlarmDomain alarmDomain = alarmDomainService.createAlarm(alarmPortInDto, senderMessage, receiverMessage);

        log.info("alarmDomain : {}", alarmDomain);

        AlarmCreateQueryDto transactionDto = alarmQueryMapper.toCreateQueryDto(alarmDomain);

        log.info("transactionDto : {}", transactionDto);

        alarmRepositoryPort.saveAlarm(transactionDto);

        sseEmitterManager.sendAlarm(alarmDomain);
    }

    @Override
    public AlarmReadResponseDto getAlarm(RestReadAlarmDto readDto) {
        return alarmQueryMapper.toReadResponseDto(alarmRepositoryPort.findAlarm(readDto), readDto.getUserUuid());
    }

    @Override
    public AlarmReadResponseDto getLastAlarm(String userUuid) {
        return alarmQueryMapper.toReadResponseDto(alarmRepositoryPort.findLastAlarm(userUuid), userUuid);
    }

    @Override
    public Slice<AlarmReadResponseDto> getAlarms(Pageable pageable, String userUuid) {

        Slice<AlarmReadQueryDto> alarms = alarmRepositoryPort.findAlarms(pageable, userUuid);

        List<AlarmReadResponseDto> responseList = alarms.getContent().stream()
                .map(queryDto -> alarmQueryMapper.toReadResponseDto(queryDto, userUuid))
                .toList();

        return new SliceImpl<>(
                responseList,
                pageable,
                alarms.hasNext()
        );
    }

    @Transactional
    @Override
    public void deleteAlarm(RestReadAlarmDto readAlarmDto) {
        alarmRepositoryPort.deleteAlarm(alarmRepositoryPort.findAlarm(readAlarmDto));
    }

}