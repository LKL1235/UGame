package com.myApp.gamestore.service;

import com.myApp.gamestore.entity.Library;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author:25445
 * @date:2022/11/30 18:21
 **/

@Service
public interface LibraryService {
    List<Library> getLibrary(String name);
}
