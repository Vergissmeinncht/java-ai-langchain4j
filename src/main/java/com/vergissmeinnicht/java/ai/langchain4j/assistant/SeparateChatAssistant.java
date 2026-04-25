package com.vergissmeinnicht.java.ai.langchain4j.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;


/**
 * 根据记忆id隔离聊天记忆的智能体
 */
@AiService(
        wiringMode = EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemoryProvider = "chatMemoryProvider",
        tools = "calculatorTools" //工具配置
)
public interface SeparateChatAssistant {
    @SystemMessage(fromResource = "my-prompt-template.txt")
    //@SystemMessage("你是我的好朋友，请用东北话回答问题。今天是{{current_date}}")//系统消息提示词
    //@SystemMessage("你是我的好朋友，请用粤语回答问题。")//系统消息提示词
    String chat(@MemoryId int memoryId,@UserMessage String userMessage);

    @UserMessage("你是我的好朋友，请用上海话回答问题。{{userMessage}}")
    String chat2(@MemoryId int memoryId,@V("userMessage") String userMessage);

    //使用SystemMessage和@V

    @SystemMessage(fromResource = "my-prompt-template3.txt")
    String chat3(@MemoryId int memoryId,
                 @UserMessage String userMessage,
                 @V("username") String username,
                 @V("age") int age);
}
