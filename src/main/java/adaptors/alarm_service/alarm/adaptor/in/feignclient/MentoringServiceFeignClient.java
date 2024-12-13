package adaptors.alarm_service.alarm.adaptor.in.feignclient;

import adaptors.alarm_service.alarm.adaptor.in.feignclient.dto.SessionRoomResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://api.adaptors.site/mentoring-query-service", name = "mentoring-query-service")
public interface MentoringServiceFeignClient {

    // mentoring-query-service 의 세션 조회 api 호출
    @GetMapping("/api/v1/mentoring-query-service/session-room/{sessionUuid}")
    SessionRoomResponseDto findSessionRoomBySessionUuid(@PathVariable(name = "sessionUuid") String sessionUuid);

    // mentoring-query-service 의 멘토 uuid api 호출
    @GetMapping("/api/v1/mentoring-query-service/mentor-info/{sessionUuid}")
    String getMentorUuidBySessionUuid(@PathVariable(name = "sessionUuid") String sessionUuid);

}