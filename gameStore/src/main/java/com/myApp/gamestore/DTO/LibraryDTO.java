package com.myApp.gamestore.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author:25445
 * @date:2022/12/4 11:05
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryDTO {
    private Integer gameId;
    private String gameName;
}
