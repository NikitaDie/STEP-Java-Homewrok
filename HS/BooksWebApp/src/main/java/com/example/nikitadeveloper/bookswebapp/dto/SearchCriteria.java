package com.example.nikitadeveloper.bookswebapp.dto;

import lombok.Data;

@Data
public class SearchCriteria {
    private String title;
    private String author;
    private Integer publicationYear;
    private String genre;
    private Integer pageCount;
    private String description;
}