package com.example.firstproject.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter

public class Article {
    @Id           //엔티티의 대푯값 = pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 숫자 추가
    private Long id;
    @Column
    private String title;
    @Column
    private String content;




}