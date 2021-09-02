package com.icuxika.scaffold.module.property.mapper;

import com.icuxika.scaffold.module.property.entity.BusinessProperty;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusinessPropertyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BusinessProperty record);

    int insertSelective(BusinessProperty record);

    BusinessProperty selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessProperty record);

    int updateByPrimaryKey(BusinessProperty record);
}