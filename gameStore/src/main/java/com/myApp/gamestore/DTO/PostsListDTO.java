package com.myApp.gamestore.DTO;

import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author:25445
 * @date:2023/3/14 23:50
 **/

@Data
public class PostsListDTO {
    private Integer postId;
    private String postTitle;
    private String userName;
    private Integer boardId;
    private Date createdTime;
    private Date lastReplyTime;
    private String boardName;
    private String description;
}
