package com.icuxika.scaffold.util;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.regex.Pattern;

class StringUtilTest {

    @Test
    void verifyIDCardFormat() {
        boolean result = StringUtil.verifyIDCardFormat("420104198801310857");
        Assert.isTrue(result, "身份证号码格式不正确");

        boolean result1 = Pattern.compile("^\\d{6}(18|19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12]\\d|3[01])\\d{3}(\\d|X|x)$").matcher("420104198801310857").matches();
        Assert.isTrue(result1, "验证不通过");

        boolean result2 = Pattern.compile("""
                ^\\d{6}(18|19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12]\\d|3[01])\\d{3}(\\d|X|x)$
                """.trim()).matcher("420104198801310857").matches();
        Assert.isTrue(result2, "验证不通过");
    }
}