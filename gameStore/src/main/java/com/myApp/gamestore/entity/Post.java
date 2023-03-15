package com.myApp.gamestore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName post
 */
@TableName(value ="post")
public class Post implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer postId;

    /**
     * 
     */
    private String postTitle;

    /**
     * 
     */
    private String contents;

    /**
     * 
     */
    private String userName;

    /**
     * 
     */
    private Integer boardId;

    /**
     * 
     */
    private Date createdTime;

    /**
     * 
     */
    private Date lastReplyTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getPostId() {
        return postId;
    }

    /**
     * 
     */
    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    /**
     * 
     */
    public String getPostTitle() {
        return postTitle;
    }

    /**
     * 
     */
    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    /**
     * 
     */
    public String getContents() {
        return contents;
    }

    /**
     * 
     */
    public void setContents(String contents) {
        this.contents = contents;
    }

    /**
     * 
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 
     */
    public Integer getBoardId() {
        return boardId;
    }

    /**
     * 
     */
    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    /**
     * 
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 
     */
    public Date getLastReplyTime() {
        return lastReplyTime;
    }

    /**
     * 
     */
    public void setLastReplyTime(Date lastReplyTime) {
        this.lastReplyTime = lastReplyTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Post other = (Post) that;
        return (this.getPostId() == null ? other.getPostId() == null : this.getPostId().equals(other.getPostId()))
            && (this.getPostTitle() == null ? other.getPostTitle() == null : this.getPostTitle().equals(other.getPostTitle()))
            && (this.getContents() == null ? other.getContents() == null : this.getContents().equals(other.getContents()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getBoardId() == null ? other.getBoardId() == null : this.getBoardId().equals(other.getBoardId()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
            && (this.getLastReplyTime() == null ? other.getLastReplyTime() == null : this.getLastReplyTime().equals(other.getLastReplyTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPostId() == null) ? 0 : getPostId().hashCode());
        result = prime * result + ((getPostTitle() == null) ? 0 : getPostTitle().hashCode());
        result = prime * result + ((getContents() == null) ? 0 : getContents().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getBoardId() == null) ? 0 : getBoardId().hashCode());
        result = prime * result + ((getCreatedTime() == null) ? 0 : getCreatedTime().hashCode());
        result = prime * result + ((getLastReplyTime() == null) ? 0 : getLastReplyTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", postId=").append(postId);
        sb.append(", postTitle=").append(postTitle);
        sb.append(", contents=").append(contents);
        sb.append(", userName=").append(userName);
        sb.append(", boardId=").append(boardId);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", lastReplyTime=").append(lastReplyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}