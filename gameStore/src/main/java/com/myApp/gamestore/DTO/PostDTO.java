package com.myApp.gamestore.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description:
 * @author:25445
 * @date:2023/3/22 0:21
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private Integer postId;
    private String postTitle;
    private String contents;
    private String useName;
    private Integer boardId;
    private String boardName;
    private Date createdTime;
    private Date LastReplyTime;
}
