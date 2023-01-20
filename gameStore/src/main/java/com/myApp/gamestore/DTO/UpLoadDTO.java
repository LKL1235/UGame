package com.myApp.gamestore.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author:25445
 * @date:2023/1/12 20:45
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpLoadDTO {
    private String name;
    private String gameName;
    private Double price;
    private String tags;
    private String introduce;
    private String about;
}
