package com.vergissmeinnicht.java.ai.langchain4j;

import com.vergissmeinnicht.java.ai.langchain4j.assistant.Assistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AIServiceTest {

    @Autowired
    private QwenChatModel qwenChatModel;

    @Test
    public void testChat(){
        Assistant assistant = AiServices.create(Assistant.class, qwenChatModel);
        String chat = assistant.chat("你是谁？");
        System.out.println(chat);
    }

    @Autowired
    private Assistant assistant;
    @Test
    public void testAssistant(){
        String chat = assistant.chat("可以帮我写一个100的小故事吗?");
        System.out.println(chat);
    }
}
