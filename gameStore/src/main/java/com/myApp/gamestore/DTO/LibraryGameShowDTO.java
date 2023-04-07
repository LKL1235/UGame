package com.myApp.gamestore.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description:
 * @author:25445
 * @date:2023/4/4 11:44
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryGameShowDTO {


        private Integer gameId;
        private String gameName;
        private List<String> img;
        private List<String> tags;
        private double price;
        private String files;

}
