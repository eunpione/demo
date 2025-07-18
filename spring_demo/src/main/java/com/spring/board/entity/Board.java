package com.spring.board.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "board")
@Where(clause = "delete_yn = false")
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

//    [공유4] board setter나 update 메서드 처럼 엔터티 직접 수정 시에만 동작
//    @PreUpdate
//    protected void onUpdate(){
//        this.changedDate = LocalDateTime.now();
//    }

    // [공유2] 객체가 스스로 책임을 갖도록 하려고 했는데
    // 엥 Entity는 DB 접근시에만 쓰려고 한건데 왜 서비스 단에서 쓰고 있지?
    // 이렇게 쓰는 코드는 살릴 수 없는가?

//    public void update(String newTitle, String newContent){
//
//        if (newTitle == null || newTitle.trim().isEmpty()){
//            throw new IllegalArgumentException("게시글 제목 필수 작성");
//        }
//
//        if (newTitle.length() > 100){
//            throw new IllegalArgumentException("게시글 제목은 100자를 초과할 수 없음");
//        }
//        this.title = newTitle;
//        this.content = newContent;
//    }






}
