package com.myApp.gamestore.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.myApp.gamestore.DTO.LibraryDTO;
import com.myApp.gamestore.entity.Library;
import com.myApp.gamestore.service.LibraryService;
import com.myApp.gamestore.utils.ResultCode;
import com.myApp.gamestore.utils.myResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author:25445
 * @date:2022/11/30 19:00
 **/
@RestController
public class LibraryController {

    private LibraryService libraryService;
    @Autowired
    public void setLibraryService(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @RequestMapping("/getLibrary/{name}")
    public myResult getLibrary(@PathVariable String name){
        QueryWrapper<Library> libraryQueryWrapper = new QueryWrapper<>();
        libraryQueryWrapper.eq("username",name);
        List<Library> list=libraryService.list(libraryQueryWrapper);
        List<LibraryDTO> libraryDTOList = new ArrayList<>();
        list.forEach(item->{libraryDTOList.add(new LibraryDTO(item.getGameId(),item.getGameName()));});
        return new myResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(),libraryDTOList);
    }
}
