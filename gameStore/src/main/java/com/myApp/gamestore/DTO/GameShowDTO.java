package com.myApp.gamestore.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description:
 * @author:25445
 * @date:2022/12/9 18:27
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameShowDTO {
    private Integer gameId;
    private String gameName;
    private List<String> img;
    private List<String> tags;
    private double price;

}
