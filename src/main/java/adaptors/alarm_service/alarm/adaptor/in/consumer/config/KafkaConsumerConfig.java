package adaptors.alarm_service.alarm.adaptor.in.consumer.config;

import adaptors.alarm_service.alarm.adaptor.in.consumer.vo.*;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${kafka.cluster.uri}")
    private String kafkaClusterUri;

    /*
    세션 참가 알림
     */
    @Bean
    public ConsumerFactory<String, ConsumerSessionRegisterVo> userSessionJoinConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaClusterUri);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka-alarm-service");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                new JsonDeserializer<>(ConsumerSessionRegisterVo.class, false)
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ConsumerSessionRegisterVo> userSessionJoinDtoListener() {
        ConcurrentKafkaListenerContainerFactory<String, ConsumerSessionRegisterVo> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userSessionJoinConsumerFactory());
        return factory;
    }

    /*
    멘토링 등록 알림
     */
    @Bean
    public ConsumerFactory<String, ConsumerCreateMentoringVo> createMentoringConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaClusterUri);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka-alarm-service");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                new JsonDeserializer<>(ConsumerCreateMentoringVo.class, false)
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ConsumerCreateMentoringVo> createMentoringDtoListener() {
        ConcurrentKafkaListenerContainerFactory<String, ConsumerCreateMentoringVo> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(createMentoringConsumerFactory());
        return factory;
    }

    /*
    리뷰 등록 알림
     */
    @Bean
    public ConsumerFactory<String, ConsumerCreateReviewVo> createReviewConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaClusterUri);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka-alarm-service");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                new JsonDeserializer<>(ConsumerCreateReviewVo.class, false)
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ConsumerCreateReviewVo> createReviewDtoListener() {
        ConcurrentKafkaListenerContainerFactory<String, ConsumerCreateReviewVo> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(createReviewConsumerFactory());
        return factory;
    }

    /*
    세션 결제 알림
     */
    @Bean
    public ConsumerFactory<String, ConsumerSessionPayVo> sessionPayConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaClusterUri);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka-alarm-service");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                new JsonDeserializer<>(ConsumerSessionPayVo.class, false)
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ConsumerSessionPayVo> sessionPayDtoListener() {
        ConcurrentKafkaListenerContainerFactory<String, ConsumerSessionPayVo> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(sessionPayConsumerFactory());
        return factory;
    }

    /*
    세션 확정 알림
     */
    @Bean
    public ConsumerFactory<String, ConsumerUpdateSessionUserVo> updateSessionConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaClusterUri);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka-alarm-service");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                new JsonDeserializer<>(ConsumerUpdateSessionUserVo.class, false)
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ConsumerUpdateSessionUserVo> updateSessionUserDtoListener() {
        ConcurrentKafkaListenerContainerFactory<String, ConsumerUpdateSessionUserVo> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(updateSessionConsumerFactory());
        return factory;
    }

}