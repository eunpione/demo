package com.spring.board.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
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

    @Column(nullable = false, length = 100)
    private String author;

    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "changed_date")
    private LocalDateTime changedDate;

    @PrePersist
    protected void onCreate(){
        this.createdDate = LocalDateTime.now();
        this.changedDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        this.changedDate = LocalDateTime.now();
    }






}
