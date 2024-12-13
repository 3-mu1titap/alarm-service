package adaptors.alarm_service.global.utils;

import adaptors.alarm_service.alarm.domain.model.AlarmType;
import org.springframework.stereotype.Component;

import java.util.Map;

import static adaptors.alarm_service.alarm.domain.model.AlarmType.*;

@Component
public class MessageTemplates {

    public static final Map<AlarmType, String> SENDER_TEMPLATES = Map.of(
            SESSION_REGISTER, "%s 세션에 등록하였습니다.",
            MENTORING_REGISTER, "%s 멘토링을 생성하였습니다.",
            REVIEW_REGISTER, "%s 세션에 리뷰를 작성하였습니다.",
            PAY_SESSION, "%s 세션에 %s볼트 결제하였습니다.",
            SESSION_CONFIRM, "%s월 %s일, %s 세션이 확정되었습니다."
    );

    public static final Map<AlarmType, String> RECEIVER_TEMPLATES = Map.of(
            SESSION_REGISTER, "%s님이 %s 세션에 등록하였습니다.",
            REVIEW_REGISTER, "%s님이 %s 세션에 리뷰를 작성하였습니다.",
            PAY_SESSION, "%s님이 %s 세션에 %s볼트 결제하였습니다.",
            SESSION_CONFIRM, "%s월 %s일, %s 세션이 확정되었습니다."
    );

}