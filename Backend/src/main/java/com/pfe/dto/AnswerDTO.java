package com.pfe.dto;

import lombok.Data;

@Data
public class AnswerDTO {
    private Long questionId;
    private Integer ratingValue;
    private String textValue;
}