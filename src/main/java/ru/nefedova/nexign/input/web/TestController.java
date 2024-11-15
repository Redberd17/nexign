package ru.nefedova.nexign.input.web;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.nefedova.nexign.input.kafka.model.TaskModel;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final KafkaTemplate<String, TaskModel> kafkaTemplate;

    @PostMapping("/send")
    public void sendMessageToKafka(@RequestBody TaskModel message) {
        kafkaTemplate.send("tasks", message);
    }

}
