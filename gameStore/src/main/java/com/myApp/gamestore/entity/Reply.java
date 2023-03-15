package com.myApp.gamestore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName reply
 */
@TableName(value ="reply")
public class Reply implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer replyId;

    /**
     * 
     */
    private String contents;

    /**
     * 
     */
    private Integer userName;

    /**
     * 
     */
    private Integer postId;

    /**
     * 
     */
    private Date createdTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getReplyId() {
        return replyId;
    }

    /**
     * 
     */
    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
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
    public Integer getUserName() {
        return userName;
    }

    /**
     * 
     */
    public void setUserName(Integer userName) {
        this.userName = userName;
    }

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
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
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
        Reply other = (Reply) that;
        return (this.getReplyId() == null ? other.getReplyId() == null : this.getReplyId().equals(other.getReplyId()))
            && (this.getContents() == null ? other.getContents() == null : this.getContents().equals(other.getContents()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getPostId() == null ? other.getPostId() == null : this.getPostId().equals(other.getPostId()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getReplyId() == null) ? 0 : getReplyId().hashCode());
        result = prime * result + ((getContents() == null) ? 0 : getContents().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getPostId() == null) ? 0 : getPostId().hashCode());
        result = prime * result + ((getCreatedTime() == null) ? 0 : getCreatedTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", replyId=").append(replyId);
        sb.append(", contents=").append(contents);
        sb.append(", userName=").append(userName);
        sb.append(", postId=").append(postId);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}