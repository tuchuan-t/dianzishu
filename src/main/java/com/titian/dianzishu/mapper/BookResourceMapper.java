package com.titian.dianzishu.mapper;


import com.titian.dianzishu.bean.BookResource;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookResourceMapper {


    public int insertBookResource(BookResource bookResource);


}
