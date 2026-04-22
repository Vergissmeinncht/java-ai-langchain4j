package com.vergissmeinnicht.java.ai.langchain4j;

import com.vergissmeinnicht.java.ai.langchain4j.assistant.Assistant;
import com.vergissmeinnicht.java.ai.langchain4j.assistant.MemoryAssistant;
import com.vergissmeinnicht.java.ai.langchain4j.assistant.SeparateChatAssistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class ChatMemoryTest {
    @Autowired
    private Assistant assistant;
    @Test
    public void testChatMemory1() {
        String answer1 = assistant.chat("我是环环");
        System.out.println(answer1);
        String answer2 = assistant.chat("我是谁");
        System.out.println(answer2);
    }

    @Autowired
    private QwenChatModel qwenChatModel;

    @Test
    public void testChatMemory2(){
        //测试Agent的记忆机制，还是需要将之前的对话放入prompt中
        //类似于全部对话都记录，会导致token花费很高，而且由于冗余内容过多，会导致模型会丧失对原任务的注意力。
        UserMessage userMessage1 = UserMessage.userMessage("我是环环");
        ChatResponse chatResponse1 = qwenChatModel.chat(userMessage1);
        AiMessage aiMessage1 = chatResponse1.aiMessage();
        System.out.println(aiMessage1.text());

        UserMessage userMessage2 = UserMessage.userMessage("我是谁");
        ChatResponse chatResponse2 = qwenChatModel.chat(Arrays.asList(userMessage1,aiMessage1,userMessage2));
        AiMessage aiMessage2 = chatResponse2.aiMessage();
        System.out.println(aiMessage2.text());
    }

    @Test
    public void testChatMemory3(){
        /*
        滑动窗口式的聊天记忆机制，记录最近k条信息,包括提问与响应信息
        容易导致模型丧失对原任务的记忆力。
        langchain4j的优化：对于SystemMessage：如果在上下文中存在 SystemMessage（系统提示词，比如“你是一个翻译专家”）
        SystemMessage 永远会被保留在最前面，不会被滑动窗口淘汰掉。
         */

        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

        Assistant assistant = AiServices.builder(Assistant.class)
                .chatLanguageModel(qwenChatModel)
                .chatMemory(chatMemory)
                .build();
        String chat1 = assistant.chat("我是环环");
        System.out.println(chat1);
        String chat2 = assistant.chat("我是谁");
        System.out.println(chat2);
    }

    @Autowired
    private MemoryAssistant memoryAssistant;
    @Test
    public void testChatMemory4(){
        //AiServices的简易实现记忆对话的Agent
        String chat1 = memoryAssistant.chat("我是环环");
        System.out.println(chat1);
        String chat2 = memoryAssistant.chat("我是谁？");
        System.out.println(chat2);
    }


    @Autowired
    private SeparateChatAssistant separateChatAssistant;
    @Test
    public void testChatMemory5(){
        String chat1 = separateChatAssistant.chat(1,"我是环环");
        System.out.println(chat1);
        String chat2 = separateChatAssistant.chat(1,"我是谁？");
        System.out.println(chat2);

        String chat4 = separateChatAssistant.chat(2,"我是谁？");
        System.out.println(chat4);
    }
}
