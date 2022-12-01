package com.myApp.gamestore.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myApp.gamestore.entity.Library;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @author:25445
 * @date:2022/11/30 18:19
 **/
@Mapper
public interface LibraryMapper{

    List<Library> getLibrary(String name);
}
