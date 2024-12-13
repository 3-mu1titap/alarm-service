package adaptors.alarm_service.alarm.adaptor.in.rest;

import adaptors.alarm_service.alarm.adaptor.in.rest.mapper.RestVoMapper;
import adaptors.alarm_service.alarm.adaptor.out.mongo.dto.AlarmReadResponseDto;
import adaptors.alarm_service.alarm.application.port.in.AlarmUseCase;
import adaptors.alarm_service.global.response.BaseResponse;
import adaptors.alarm_service.global.utils.SseEmitterManager;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/alarm-service")
public class AlarmController {

    private final AlarmUseCase alarmUseCase;
    private final RestVoMapper restVoMapper;
    private final SseEmitterManager emitterManager;

    @Operation(summary = "알림 연결", description = "userUuid로 SSE 커넥션 생성", tags = {"알림"})
    @GetMapping(value = "/alarms/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(
            @RequestParam("userUuid") String userUuid) {
        return emitterManager.createEmitter(userUuid);
    }

    @Operation(summary = "알림 단 건 조회", description = "userUuid & alarmUuid 기준 수신알림 조회", tags = {"알림"})
    @GetMapping("/alarms/{uuid}")
    public BaseResponse<AlarmReadResponseDto> getAlarm(
            @RequestHeader("userUuid") String userUuid,
            @PathVariable("uuid") String uuid) {
        return new BaseResponse<>(
                alarmUseCase.getAlarm(restVoMapper.toReadDto(uuid, userUuid))
        );
    }

    @Operation(summary = "가장 최근 알림 조회", description = "userUuid & alarmUuid 필요", tags = {"알림"})
    @GetMapping("/alarms/last")
    public BaseResponse<AlarmReadResponseDto> getLastAlarm(
            @RequestParam("userUuid") String userUuid) {
        return new BaseResponse<>(
                alarmUseCase.getLastAlarm(userUuid)
        );
    }

    @Operation(summary = "수신알림 조회", description = "userUuid 기준 수신알림 조회", tags = {"알림"})
    @GetMapping("/alarms")
    public BaseResponse<Slice<AlarmReadResponseDto>> getAlarms(
            @RequestHeader("userUuid") String userUuid,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageRequest pageable = PageRequest.of(page, size);

        return new BaseResponse<>(
                alarmUseCase.getAlarms(pageable, userUuid)
        );
    }

    @Operation(summary = "수신알림 삭제", description = "userUuid 기준 수신알림 삭제", tags = {"알림"})
    @DeleteMapping("/alarms/{uuid}")
    public BaseResponse<Void> deleteAlarm(
            @RequestHeader("userUuid") String userUuid,
            @PathVariable("uuid") String uuid
    ) {
        alarmUseCase.deleteAlarm(restVoMapper.toReadDto(uuid, userUuid));
        return new BaseResponse<>();
    }

}