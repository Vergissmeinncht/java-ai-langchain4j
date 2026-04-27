package com.vergissmeinnicht.java.ai.langchain4j;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmbeddingTest {
    @Autowired
    private EmbeddingModel embeddingModel;

    @Test
    public void testEmbedding(){
        Response<Embedding> embed = embeddingModel.embed("你好");
        System.out.println("向量维度:"+embed.content().vector().length);
        System.out.println("向量输出:"+embed.toString());
    }

    @Autowired
    private EmbeddingStore<TextSegment> embeddingStore;

    @Test
    public void testPineconeEmbeded() {
        //将文本转换成向量
        TextSegment segment1 = TextSegment.from("我喜欢羽毛球");
        Embedding embedding1 = embeddingModel.embed(segment1).content();
        //存入向量数据库
        embeddingStore.add(embedding1, segment1);
        TextSegment segment2 = TextSegment.from("今天天气很好");
        Embedding embedding2 = embeddingModel.embed(segment2).content();
        embeddingStore.add(embedding2, segment2);
    }

    @Test
    public void embeddingSearch(){
        Embedding queryEmbedding = embeddingModel.embed("我喜欢打球").content();
        EmbeddingSearchRequest request = EmbeddingSearchRequest.builder()
                .queryEmbedding(queryEmbedding)
                .maxResults(1)
                .build();
        EmbeddingSearchResult<TextSegment> search = embeddingStore.search(request);
        search.matches().forEach(match -> {
            System.out.println("相似度:"+match.score());
            System.out.println("文本内容:"+match.embedded().text());
        });
    }

}
