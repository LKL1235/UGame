package com.myApp.gamestore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName game
 */
@TableName(value ="game")
@Data
public class Game implements Serializable {
    /**
     *
     */
    @TableId(type=IdType.AUTO)
    private Integer gameId;

    /**
     *
     */
    private String gameName;

    /**
     *
     */
    private String repository;

    /**
     *
     */
    private String images;

    /**
     *
     */
    private String files;

    /**
     *
     */
    private String tags;

    /**
     *
     */
    private Integer sales;

    /**
     *
     */
    private String state;

    /**
     *
     */
    private Double price;

    /**
     *
     */
    private String introduce;

    /**
     *
     */
    private String about;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

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
        Game other = (Game) that;
        return (this.getGameId() == null ? other.getGameId() == null : this.getGameId().equals(other.getGameId()))
            && (this.getGameName() == null ? other.getGameName() == null : this.getGameName().equals(other.getGameName()))
            && (this.getRepository() == null ? other.getRepository() == null : this.getRepository().equals(other.getRepository()))
            && (this.getImages() == null ? other.getImages() == null : this.getImages().equals(other.getImages()))
            && (this.getFiles() == null ? other.getFiles() == null : this.getFiles().equals(other.getFiles()))
            && (this.getTags() == null ? other.getTags() == null : this.getTags().equals(other.getTags()))
            && (this.getSales() == null ? other.getSales() == null : this.getSales().equals(other.getSales()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getIntroduce() == null ? other.getIntroduce() == null : this.getIntroduce().equals(other.getIntroduce()))
            && (this.getAbout() == null ? other.getAbout() == null : this.getAbout().equals(other.getAbout()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGameId() == null) ? 0 : getGameId().hashCode());
        result = prime * result + ((getGameName() == null) ? 0 : getGameName().hashCode());
        result = prime * result + ((getRepository() == null) ? 0 : getRepository().hashCode());
        result = prime * result + ((getImages() == null) ? 0 : getImages().hashCode());
        result = prime * result + ((getFiles() == null) ? 0 : getFiles().hashCode());
        result = prime * result + ((getTags() == null) ? 0 : getTags().hashCode());
        result = prime * result + ((getSales() == null) ? 0 : getSales().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getIntroduce() == null) ? 0 : getIntroduce().hashCode());
        result = prime * result + ((getAbout() == null) ? 0 : getAbout().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", gameId=").append(gameId);
        sb.append(", gameName=").append(gameName);
        sb.append(", repository=").append(repository);
        sb.append(", images=").append(images);
        sb.append(", files=").append(files);
        sb.append(", tags=").append(tags);
        sb.append(", sales=").append(sales);
        sb.append(", state=").append(state);
        sb.append(", price=").append(price);
        sb.append(", introduce=").append(introduce);
        sb.append(", about=").append(about);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
