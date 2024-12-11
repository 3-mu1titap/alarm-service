package adaptors.alarm_service.global.utils;

import lombok.NoArgsConstructor;

import java.util.UUID;

import static lombok.AccessLevel.*;

@NoArgsConstructor(access = PRIVATE)
public class UuidGenerator {

    public static String generateUuid() {
        return UUID.randomUUID().toString();
    }

}