package ru.nefedova.nexign.input.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.nefedova.nexign.input.kafka.model.TaskModel;
import ru.nefedova.nexign.util.LocalizationService;

import static ru.nefedova.nexign.common.constant.Constant.KAFKA_TOPIC_TASKS;
import static ru.nefedova.nexign.common.constant.Endpoint.Test.SEND;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final KafkaTemplate<String, TaskModel> kafkaTemplate;

    private final LocalizationService localizationService;

    @PostMapping(SEND)
    public ResponseEntity<String> sendMessageToKafka(@RequestBody TaskModel message) {
        kafkaTemplate.send(KAFKA_TOPIC_TASKS, message);
        return ResponseEntity.ok(localizationService.getMessage("kafka.producer.send"));
    }

}
