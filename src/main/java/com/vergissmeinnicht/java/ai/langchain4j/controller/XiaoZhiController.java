package com.vergissmeinnicht.java.ai.langchain4j.controller;

import com.vergissmeinnicht.java.ai.langchain4j.assistant.XiaoZhiAgent;
import com.vergissmeinnicht.java.ai.langchain4j.bean.ChatForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "小智助手", description = "小智助手相关接口")
@RestController
@RequestMapping("/xiaozhi")
public class XiaoZhiController {

    @Autowired
    private XiaoZhiAgent xiaoZhiAgent;

    @Operation(summary = "与小智助手进行对话", description = "发送消息给小智助手，并获取回复")
    @PostMapping("/chat")
    public String chat(@RequestBody ChatForm chatForm){
        return xiaoZhiAgent.chat(chatForm.getMemoryId(),chatForm.getMessage());
    }
}
