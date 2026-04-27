package com.vergissmeinnicht.java.ai.langchain4j.config;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EembeddingStoreConfig {
    @Autowired
    private EmbeddingModel embeddingModel;

    @Bean
    public EmbeddingStore<TextSegment> embeddingStore(){
        PgVectorEmbeddingStore pgVectorEmbeddingStore = PgVectorEmbeddingStore.builder()
                .host("localhost")
                .port(5432)
                .database("java-ai-langchain4j")
                .user("postgres")
                .password("zhangqi9029")
                .table("vectors") // 指定存储向量的表名
                .dimension(1024)         // 向量维度，需要与你使用的 Embedding 模型维度一致
                .useIndex(false)         // 关闭 IVFFlat 索引加速检索，IVFFlat只适用于数据很多的时候，少量测试需要关闭，不然后面查找可能会查找不到！！
                .createTable(true)      // 如果表不存在，自动创建
                .build();

        return pgVectorEmbeddingStore;
    }
}
