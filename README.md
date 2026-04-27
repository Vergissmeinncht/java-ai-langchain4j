# Java AI Langchain4j 🚀

[![Language](https://img.shields.io/badge/Language-Java-orange.svg)](https://java.com/)

欢迎来到 **Java AI Langchain4j** 项目！这是一个基于 Java 的探索性项目，主要用于学习和实践大语言模型（LLM）相关的应用开发。本项目利用了强大的 [LangChain4j](https://github.com/langchain4j/langchain4j) 框架。

## 🎯 项目目标

本项目的核心目标是学习并实现以下先进的 AI 技术：
* **LangChain4j 基础**: 掌握在 Java 环境下如何与各种大语言模型（如 OpenAI、千问等）进行交互。
* **RAG (检索增强生成)**: 学习如何结合外部知识库（向量数据库）来增强大语言模型的回答准确性和时效性。
* **AI Agent (智能代理)**: 构建能够自主思考、调用工具（Tools）并完成复杂任务的智能体。

## 🛠️ 技术栈

* **编程语言**: Java (100%)
* **核心框架**: LangChain4j
* **其他相关**: MySQL，MongoDB，PostgreSQL

## 📁 核心模块

* `assistant` (智能代理层): 
  * 包含 `XiaoZhiAgent`、`SeparateChatAssistant` 等基于 `@AiService` 定义的 AI 接口。
  * 实现了将聊天模型、历史记忆（Chat Memory）、系统提示词（SystemMessage）以及外部工具与检索器组装，打造能够自主对话的智能体。
* `tools` (函数调用/工具层): 
  * 包含大模型可自主调用的工具函数，如 `AppointmentTools`。
  * 演示了 LLM 如何根据用户意图解析参数，并执行具体的业务逻辑（如查询医生排班、进行医疗预约挂号、取消预约等）。
* `store` (持久化存储层): 
  * 包含如 `MongoChatMemoryStore`，展示了如何自定义 `ChatMemoryStore`，将用户的多轮对话记录持久化存储到 MongoDB 中，实现跨会话的长期记忆隔离。
* `config` (核心配置层): 
  * **RAG 检索配置** (`XiaoZhiAgentConfig`): 演示了使用 `FileSystemDocumentLoader` 加载本地文本知识库，切分向量化，并构建 `ContentRetriever`。
  * **向量数据库配置** (`EembeddingStoreConfig`): 演示了如何集成 `PgVectorEmbeddingStore` (PostgreSQL pgvector) 作为外部向量库存储并管理高维向量数据。
* `entity / mapper / service` (业务与数据访问层): 
  * 结合 MyBatis-Plus 提供常规业务实体的增删改查支持（例如预约挂号系统 `Appointment`），为 Agent 工具调用提供底层数据支撑。


## 🚀 快速开始

### 前置要求

* JDK 17 或更高版本 (推荐)
* Maven 或 Gradle 构建工具
* 相关的 API Key (如 OpenAI API Key)，需配置在环境变量或配置文件中。

### 运行步骤

1. 克隆本项目到本地：
   ```bash
   git clone https://github.com/Vergissmeinncht/java-ai-langchain4j.git
   ```
2. 使用 IDE (IntelliJ IDEA 或 Eclipse) 打开项目。
3. 配置你的 LLM API Key (可以在 application.properties 或直接在代码中设置环境变量)。
4. 找到你想测试的特定功能的 `main` 方法或测试类，直接运行即可。

## 📚 学习资源

* [LangChain4j 官方文档](https://docs.langchain4j.dev/)
* [LangChain4j GitHub 仓库](https://github.com/langchain4j/langchain4j)

## 🤝 贡献与交流

这是一个个人学习项目，但随时欢迎提出 Issue 或 Pull Request 进行交流和探讨！如果你也在学习 Java 和 AI 相关的开发，欢迎 Star ⭐ 支持一下！
