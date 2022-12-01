package com.myApp.gamestore.service.impl;

import com.myApp.gamestore.entity.Library;
import com.myApp.gamestore.mapper.LibraryMapper;
import com.myApp.gamestore.service.LibraryService;
import com.myApp.gamestore.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author:25445
 * @date:2022/11/30 18:21
 **/
@Service
public class LibraryServiceImpl implements LibraryService {
    private LibraryMapper libraryMapper;
    @Autowired
    public void setLibraryMapper(LibraryMapper libraryMapper) {
        this.libraryMapper = libraryMapper;
    }

    @Override
    public List<Library> getLibrary(String name) {
        return libraryMapper.getLibrary(name);
    }
}
