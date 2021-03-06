package com.titian.dianzishu.mapper;


import com.titian.dianzishu.bean.BookResource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface BookResourceMapper {

    /**
     * 插入书目资源链接
     * @param bookResource
     * @return
     */
    @Transactional
    public int insertBookResource(BookResource bookResource);

}
