package com.spring.board.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) //외래 키 지정(user_id로 DB에서 연결)
    private User user;

    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "changed_date")
    private LocalDateTime changedDate;

    @Column(name = "delete_yn", nullable = false)
    private boolean deleteYn;

    @PrePersist
    protected void onCreate(){
        this.createdDate = LocalDateTime.now();
        this.changedDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        this.changedDate = LocalDateTime.now();
    }

    // 객체가 스스로 책임을 갖도록
    public void update(String newTitle, String newContent){
        
        if (newTitle == null || newTitle.trim().isEmpty()){
            throw new IllegalArgumentException("게시글 제목 필수 작성");
        }
        
        if (newTitle.length() > 100){
            throw new IllegalArgumentException("게시글 제목은 100자를 초과할 수 없음");
        }
        this.title = newTitle;
        this.content = newContent;
    }






}
