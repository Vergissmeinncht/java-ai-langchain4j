package com.vergissmeinnicht.java.ai.langchain4j;

import com.vergissmeinnicht.java.ai.langchain4j.assistant.MemoryAssistant;
import com.vergissmeinnicht.java.ai.langchain4j.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PromptTest {
    @Autowired
    private SeparateChatAssistant separateChatAssistant;

    @Test
    public void testSystemMessage(){
        String answer = separateChatAssistant.chat(5, "今天是几号呢");
        System.out.println(answer);
    }

    @Autowired
    private MemoryAssistant memoryAssistant;
    @Test
    public void testUserMessage() {
        String answer1 = memoryAssistant.chat("我是环环");
        System.out.println(answer1);

        String answer2 = memoryAssistant.chat("今年18了");
        System.out.println(answer2);

        String answer3 = memoryAssistant.chat("你知道我是谁吗");
        System.out.println(answer3);
    }

    @Test
    public void testV(){
        String answer1 = separateChatAssistant.chat2(6,"我是环环");
        System.out.println(answer1);

        String answer2 = separateChatAssistant.chat2(6,"我是谁");
        System.out.println(answer2);
    }

    @Test
    public void testUserInfo(){

        //从数据库中获取用户信息
        String username = "翠花";
        int age = 18;

        String answer = separateChatAssistant.chat3(7, "我是谁,我的年龄多大了", username, age);
        System.out.println(answer);

    }
}
