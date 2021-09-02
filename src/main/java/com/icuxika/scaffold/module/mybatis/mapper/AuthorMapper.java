package com.icuxika.scaffold.module.mybatis.mapper;

import com.icuxika.scaffold.module.mybatis.entity.Author;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Author record);

    Author selectByPrimaryKey(Long id);

    List<Author> selectAll();

    int updateByPrimaryKey(Author record);
}