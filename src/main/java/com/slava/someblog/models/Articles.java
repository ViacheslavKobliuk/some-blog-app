package com.slava.someblog.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Articles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String announce;
    private String fullText;
    private int likes;

    public Articles(String title, String announce, String fullText) {
        this.title = title;
        this.announce = announce;
        this.fullText = fullText;
    }
}

