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
* **其他相关**: (可以在这里补充你使用的向量数据库，例如 Chroma、Milvus，或者使用的构建工具 Maven/Gradle 等)

## 📁 核心模块

* `Agent`: 包含智能代理的实现代码，测试大模型如何自主调用函数。
* `RAG`: 包含文档解析、向量化嵌入（Embedding）以及结合检索的问答实现。
* *(根据你的实际包结构在此处进行修改)*

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
