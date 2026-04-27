# Java AI Langchain4j 🚀

[![Language](https://img.shields.io/badge/Language-Java-orange.svg)](https://java.com/)
[![Framework](https://img.shields.io/badge/Framework-LangChain4j-blue.svg)](https://github.com/langchain4j/langchain4j)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)

欢迎来到 **Java AI Langchain4j** 项目！这是一个基于 Java 的探索性项目，主要用于学习和实践大语言模型（LLM）相关的应用开发。本项目利用了强大的 [LangChain4j](https://github.com/langchain4j/langchain4j) 框架，结合 Spring Boot 落地了智能体（Agent）、长期记忆、RAG 检索以及函数调用等多种前沿 AI 场景。

## 🎯 项目目标

本项目的核心目标是学习并实现以下先进的 AI 技术：
* **LangChain4j 基础**: 掌握在 Java 环境下如何与各种大语言模型（如 OpenAI、DeepSeek、通义千问等）进行交互。
* **RAG (检索增强生成)**: 学习如何结合外部知识库（向量数据库）来增强大语言模型的回答准确性和时效性。
* **AI Agent (智能代理)**: 构建能够自主思考、调用工具（Tools）并完成复杂任务（如预约挂号）的智能体。

## 🌟 核心技术亮点与实现细节

本项目不仅仅是简单的 API 调用，而是深度结合了 Spring Boot 体系，落地了多个主流的 LLM 应用范式：

* **声明式 AI 客户端 (`@AiService`)**: 利用 LangChain4j 的 `AiService` 特性，像编写 Spring Data JPA 一样，通过定义 Java 接口和注解，隐式地将底层的大模型调用、记忆存储、工具函数和 RAG 检索器自动组装在一起，大大降低了业务代码的耦合度。
* **Agent 工具调用 (Function Calling)**: 在 `AppointmentTools` 中，通过 `@Tool` 注解将普通的 Java 方法（如预约挂号、取消预约、查询号源）暴露给大模型。大模型能够自主理解用户意图，提取自然语言中的实体参数，并主动触发 Java 方法执行数据库操作。
* **RAG 检索增强生成流水线**: 完整实现了一套本地知识库问答系统。使用 `FileSystemDocumentLoader` 读取本地 txt 知识库，调用阿里云 DashScope 的 `text-embedding-v3` 模型进行向量化，并结合 PostgreSQL 的 `pgvector` 插件进行相似度检索。
* **基于 MongoDB 的长期持久化记忆**: 打破了默认的内存级对话历史限制。通过自定义实现 `ChatMemoryStore` 接口 (`MongoChatMemoryStore`)，将用户的多轮对话序列化持久化到 MongoDB 中，实现不同用户的长期记忆隔离。
* **Prompt 模板化与变量注入**: 将 Prompt 从代码中剥离至资源文件，并通过 `@V` 注解（如 `{{current_date}}` 或 `{{username}}`）动态注入业务上下文变量，实现了 Prompt 提示词的工程化管理。

## 🛠️ 技术栈

* **编程语言**: Java (100%)
* **核心框架**: Spring Boot, LangChain4j
* **数据库/中间件**: 
  * **MySQL** (基于 MyBatis-Plus 的业务数据存储)
  * **MongoDB** (多轮对话记忆持久化)
  * **PostgreSQL + pgvector** (高维向量存储与相似度检索)

## 📁 核心模块

* `assistant` (智能代理层): 
  * 包含 `XiaoZhiAgent`、`SeparateChatAssistant` 等基于 `@AiService` 定义的 AI 接口，打造能够自主对话的智能体。
* `tools` (函数调用/工具层): 
  * 包含大模型可自主调用的工具函数，如 `AppointmentTools`。演示了 LLM 如何根据用户意图解析参数并执行具体的医疗挂号业务逻辑。
* `store` (持久化存储层): 
  * 包含 `MongoChatMemoryStore`，将用户的多轮对话记录持久化存储到 MongoDB 中，实现跨会话的长期记忆隔离。
* `config` (核心配置层): 
  * **RAG 检索配置** (`XiaoZhiAgentConfig`): 演示了加载本地文本知识库，切分向量化，并构建 `ContentRetriever`。
  * **向量数据库配置** (`EembeddingStoreConfig`): 演示了集成 `PgVectorEmbeddingStore` 管理高维向量数据。
* `entity / mapper / service` (业务与数据访问层): 
  * 结合 MyBatis-Plus 提供常规业务实体的增删改查支持（例如预约挂号系统 `Appointment`）。

## 🧩 已支持的大模型

本项目配置并测试了多种大模型调用方式：
- **Qwen (通义千问)**: 使用 DashScope 原生接入 (`qwen3.6-max-preview`)。
- **DeepSeek (云端)**: 演示了通过 OpenAI 兼容接口接入云端 `deepseek-v3` 模型。
- **DeepSeek (本地/Ollama)**: 支持连接本地的 `http://localhost:11434` 服务，调用 `deepseek-r1:8b` 等本地模型。

## 🚀 快速开始

### 1. 前置要求

在运行本项目之前，请确保你的本地环境已安装并启动以下服务：
* **JDK 17** 或更高版本
* **Maven** (依赖管理)
* **MySQL** (默认运行在 3306 端口)
* **MongoDB** (默认运行在 27017 端口)
* **PostgreSQL** (默认运行在 5432 端口，且已安装 `pgvector` 扩展)
* **Ollama** (可选，仅在测试本地模型时需要)

### 2. 获取 API Key

本项目默认接入了阿里云百炼平台（DashScope）的模型。你需要前往[阿里云百炼控制台](https://bailian.console.aliyun.com/)申请 API Key，并将其配置在环境变量中：
```bash
# 设置环境变量 (Windows 请使用系统环境变量设置)
export DASH_SCOPE_API_KEY="你的阿里云API_KEY"
```

### 3. 初始化数据库

* **MySQL**: 创建名为 `guiguxiaozhi` 的数据库，并配置相应的表结构。
* **MongoDB**: 确保能够正常连接本地 `chat_memory_db` 数据库。
* **PostgreSQL**: 创建名为 `java-ai-langchain4j` 的数据库，以便项目自动创建 `vectors` 向量表。

### 4. 修改配置文件

检查并修改 `src/main/resources/application.properties` 和 `EembeddingStoreConfig.java`，确保你的数据库账密配置与本地环境一致：
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/guiguxiaozhi?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useSSL=false
spring.datasource.username=root
spring.datasource.password=你的密码
```

### 5. 启动项目

1. 克隆本项目到本地：
   ```bash
   git clone https://github.com/Vergissmeinncht/java-ai-langchain4j.git
   ```
2. 使用 IDE (IntelliJ IDEA) 打开项目，等待 Maven 下载依赖。
3. 找到项目启动类 `src/main/java/com/vergissmeinnicht/java/ai/langchain4j/XiaoZhiApp.java`。
4. 运行 `main` 方法，启动 Spring Boot 服务并进行相关功能测试。

## 📚 学习资源

* [LangChain4j 官方文档](https://docs.langchain4j.dev/)
* [LangChain4j GitHub 仓库](https://github.com/langchain4j/langchain4j)
* [Spring AI / LangChain4j 在 Spring Boot 中的集成](https://docs.langchain4j.dev/tutorials/spring-boot-integration/)

## 🤝 贡献与交流

这是一个个人学习探索项目，但随时欢迎提出 Issue 或 Pull Request 进行交流和探讨！如果你也在学习 Java、Spring Boot 和 AI 相关的开发，欢迎给本项目点个 Star ⭐ 支持一下！
