package com.vergissmeinnicht.java.ai.langchain4j;

import com.vergissmeinnicht.java.ai.langchain4j.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ToolsTest {

    @Autowired
    private SeparateChatAssistant separateChatAssistant;

    @Test
    public void testCalculatorTools(){
        String chat = separateChatAssistant.chat(3, "请计算一下 99 + 456 的结果,475695037565的平方根是多少");
        System.out.println(chat);
    }
}
