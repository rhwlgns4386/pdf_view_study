package io.eddle.thyleeftopdf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Post {
    private String title;
    private String content;
}
