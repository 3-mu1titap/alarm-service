package adaptors.alarm_service.alarm.adaptor.in.consumer.config;

import adaptors.alarm_service.alarm.adaptor.in.consumer.vo.AlarmConsumerVo;
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

    @Bean
    public ConsumerFactory<String, AlarmConsumerVo> userSessionJoinConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaClusterUri);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka-alarm-service");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                new JsonDeserializer<>(AlarmConsumerVo.class, false)
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AlarmConsumerVo> userSessionJoinDtoListener() {
        ConcurrentKafkaListenerContainerFactory<String, AlarmConsumerVo> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userSessionJoinConsumerFactory());
        return factory;
    }

}