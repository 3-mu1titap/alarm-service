package adaptors.alarm_service.alarm.application.service;

import adaptors.alarm_service.alarm.application.mapper.AlarmTranscationDtoMapper;
import adaptors.alarm_service.alarm.application.port.in.AlarmUseCase;
import adaptors.alarm_service.alarm.application.port.in.dto.AlarmConsumerDto;
import adaptors.alarm_service.alarm.application.port.out.AlarmRepositoryPort;
import adaptors.alarm_service.alarm.application.port.out.dto.AlarmConsumerTransactionDto;
import adaptors.alarm_service.alarm.domain.model.AlarmDomain;
import adaptors.alarm_service.alarm.domain.service.AlarmDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlarmService implements AlarmUseCase {

    private final AlarmDomainService alarmDomainService;
    private final AlarmRepositoryPort alarmRepositoryPort;
    private final AlarmTranscationDtoMapper alarmTranscationDtoMapper;

    // todo : SSE
    @Override
    public void createAlarm(AlarmConsumerDto alarmConsumerDto) {

        AlarmDomain alarmDomain = alarmDomainService.createAlarm(alarmConsumerDto);

        AlarmConsumerTransactionDto transactionDto = alarmTranscationDtoMapper.toTransactionDto(alarmDomain);

        alarmRepositoryPort.saveAlarm(transactionDto);

    }

}