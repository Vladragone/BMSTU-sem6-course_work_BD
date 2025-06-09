package com.example.game.persistence.mongo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "faq")
public class FaqDocument {
    @Id
    private Long id;
    private String question;
    private String answer;
    private Long userId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
