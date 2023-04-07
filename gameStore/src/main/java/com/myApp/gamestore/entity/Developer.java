package com.myApp.gamestore.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @TableName developer
 */
@TableName(value ="developer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Developer implements Serializable {
    /**
     *
     */
    @TableId
    private String name;

    /**
     *
     */
    private Double totalReward;

    /**
     *
     */
    private Double reward;

    /**
     *
     */
    private String repository;

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
        Developer other = (Developer) that;
        return (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getTotalReward() == null ? other.getTotalReward() == null : this.getTotalReward().equals(other.getTotalReward()))
            && (this.getReward() == null ? other.getReward() == null : this.getReward().equals(other.getReward()))
            && (this.getRepository() == null ? other.getRepository() == null : this.getRepository().equals(other.getRepository()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getTotalReward() == null) ? 0 : getTotalReward().hashCode());
        result = prime * result + ((getReward() == null) ? 0 : getReward().hashCode());
        result = prime * result + ((getRepository() == null) ? 0 : getRepository().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", name=").append(name);
        sb.append(", totalReward=").append(totalReward);
        sb.append(", reward=").append(reward);
        sb.append(", repository=").append(repository);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
