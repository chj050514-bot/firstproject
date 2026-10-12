package com.example.firstproject.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    @GeneratedValue // 자동으로 숫자 추가
    private Long id;
    @Column
    private String title;
    @Column
    private String content;




}