package adaptors.alarm_service.global.exception;

import adaptors.alarm_service.global.response.BaseResponse;
import adaptors.alarm_service.global.response.BaseResponseStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import javax.naming.AuthenticationException;

@Slf4j
@Component
@RequiredArgsConstructor
public class BaseExceptionHandlerFilter implements WebExceptionHandler {

    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        if (ex instanceof BaseException baseException) {
            return handleBaseException(exchange, baseException);
        } else if (ex instanceof AuthenticationException) {
            return handleAuthenticationException(exchange);
        }
        return Mono.error(ex); // 다른 예외는 그대로 전달
    }

    private Mono<Void> handleBaseException(ServerWebExchange exchange, BaseException ex) {
        log.error("BaseException -> {}({})", ex.getStatus(), ex.getStatus().getMessage(), ex);
        return setErrorResponse(exchange, ex.getStatus(), HttpStatus.UNAUTHORIZED);
    }

    private Mono<Void> handleAuthenticationException(ServerWebExchange exchange) {
        log.error("AuthenticationException -> No sign-in authority");
        return setErrorResponse(exchange, BaseResponseStatus.NO_SIGN_IN, HttpStatus.UNAUTHORIZED);
    }

    private Mono<Void> setErrorResponse(ServerWebExchange exchange, BaseResponseStatus status, HttpStatus httpStatus) {
        try {
            BaseResponse<BaseResponseStatus> baseResponse = new BaseResponse<>(status);
            byte[] responseBytes = objectMapper.writeValueAsBytes(baseResponse);

            exchange.getResponse().setStatusCode(httpStatus);
            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(responseBytes);
            return exchange.getResponse().writeWith(Mono.just(buffer));
        } catch (Exception e) {
            return Mono.error(e);
        }
    }
}
