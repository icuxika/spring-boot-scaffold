package com.icuxika.scaffold.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class StringUtil {

    /**
     * 身份证号码格式验证
     */
    public static boolean verifyIDCardFormat(String idCard) {
        if (idCard.length() != 18) {
            return false;
        }
        String regex = "^\\d{6}(18|19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12]\\d|3[01])\\d{3}(\\d|X|x)$";
        if (Pattern.compile(regex).matcher(idCard).matches()) {
            // 日期验证
            String dateStr = idCard.substring(6, 14);
            if (verifyDate(dateStr)) {
                return verifyCheckCode(idCard);
            }
        }
        return false;
    }

    /**
     * 校验日期是否符合正确，不全面
     */
    @Deprecated
    private static boolean verifyDateOld(String dateStr) {
        int year = Integer.parseInt(dateStr.substring(0, 4));
        int month = Integer.parseInt(dateStr.substring(4, 6));
        int day = Integer.parseInt(dateStr.substring(6, 8));
        if (month >= 1 && month <= 12) {
            if (day < 1 || day > 31) {
                return false;
            } else {
                if ((month == 4) || (month == 6) || (month == 9) || (month == 11) && (day == 31)) {
                    return false;
                }
                boolean leap;
                if (month == 2) {
                    leap = ((year % 4 == 0) && ((year % 100 != 0) || (year % 400) == 0));
                    if (leap) {
                        return day == 29;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private static boolean verifyDate(String dateStr) {
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.BASIC_ISO_DATE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 身份证号码最后一位校验
     */
    private static boolean verifyCheckCode(String idCard) {
        // 校验码
        char[] verificationCodeArray = new char[]{'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
        // 权重值
        int[] verificationCodeWeightArray = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum += verificationCodeWeightArray[i] * Integer.parseInt(String.valueOf(idCard.charAt(i)));
        }
        return idCard.toUpperCase().charAt(idCard.length() - 1) == verificationCodeArray[sum % 11];
    }
}
