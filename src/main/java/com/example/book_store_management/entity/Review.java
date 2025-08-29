package com.example.book_store_management.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Min(1)
    @Max(10)
    private int rating;

    private String userName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    public Book book;
}
