package com.vergissmeinnicht.java.ai.langchain4j.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;


/**
 * 根据记忆id隔离聊天记忆的智能体
 */
@AiService(
        wiringMode = EXPLICIT,
        chatModel = "qwenChatModel",
        chatMemoryProvider = "chatMemoryProvider"
)
public interface SeparateChatAssistant {
    String chat(@MemoryId int memoryId,@UserMessage String userMessage);
}
