package com.myApp.gamestore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 
 * @TableName board
 */
@TableName(value ="board")
public class Board implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer boardId;

    /**
     * 
     */
    private Integer gameId;

    /**
     * 
     */
    private String boardName;

    /**
     * 
     */
    private String description;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

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
    public Integer getGameId() {
        return gameId;
    }

    /**
     * 
     */
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    /**
     * 
     */
    public String getBoardName() {
        return boardName;
    }

    /**
     * 
     */
    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    /**
     * 
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     */
    public void setDescription(String description) {
        this.description = description;
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
        Board other = (Board) that;
        return (this.getBoardId() == null ? other.getBoardId() == null : this.getBoardId().equals(other.getBoardId()))
            && (this.getGameId() == null ? other.getGameId() == null : this.getGameId().equals(other.getGameId()))
            && (this.getBoardName() == null ? other.getBoardName() == null : this.getBoardName().equals(other.getBoardName()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBoardId() == null) ? 0 : getBoardId().hashCode());
        result = prime * result + ((getGameId() == null) ? 0 : getGameId().hashCode());
        result = prime * result + ((getBoardName() == null) ? 0 : getBoardName().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", boardId=").append(boardId);
        sb.append(", gameId=").append(gameId);
        sb.append(", boardName=").append(boardName);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}