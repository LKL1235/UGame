package com.myApp.gamestore.DTO;

import com.myApp.gamestore.entity.Game;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author:25445
 * @date:2023/2/24 22:33
 **/
@Data
public class GameInfoDTO extends Game {
    private ArrayList<String> imgs;

}
