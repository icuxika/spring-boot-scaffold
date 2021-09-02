package com.icuxika.scaffold.module.property.mapper;

import com.icuxika.scaffold.module.property.entity.BusinessProperty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@SpringBootTest
class BusinessPropertyMapperTest {

    @Autowired
    private BusinessPropertyMapper businessPropertyMapper;

    @Test
    void insertSelective() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date;
        for (int i = 0; i < 10; i++) {
            if (i != 0) {
                localDateTime = localDateTime.plusDays(1);
            }
            date = Date.from(localDateTime.toInstant(ZoneOffset.of("+8")));

            BusinessProperty businessProperty = new BusinessProperty();
            businessProperty.setDate(date);
            businessProperty.setPrice(BigDecimal.valueOf(10.87));
            businessProperty.setDetailDate(date);
            businessProperty.setIsAvailable(true);
            businessProperty.setIsUnsigned(1);
            businessProperty.setType(2);
            businessPropertyMapper.insertSelective(businessProperty);

            System.out.println(businessProperty.getId());
        }
    }

    @Test
    void selectOne() {
        BusinessProperty businessProperty = businessPropertyMapper.selectByPrimaryKey(170L);
        System.out.println(businessProperty);
    }
}