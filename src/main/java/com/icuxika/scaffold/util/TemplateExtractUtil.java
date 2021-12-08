package com.icuxika.scaffold.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class TemplateExtractUtil {

    /**
     * 对char进行重复生成字符串
     *
     * @param identifier  字符
     * @param layerNumber 重复个数
     * @return layerNumber个字符组成的字符串
     */
    private static String repeatChar2Str(char identifier, int layerNumber) {
        return new String(new char[layerNumber]).replace('\u0000', identifier);
    }

    /**
     * 对模板字符串中占位符进行替换，如：这是一篇[[desc]]的文章
     *
     * @param template        模板字符串
     * @param startIdentifier 占位符开始记号，如[[desc]]中的[
     * @param endIdentifier   占位符结束记号，如[[desc]]中的]
     * @param layerNumber     占位符开始（结束）记号的个数，如[[desc]]中个数为2
     * @param function        对占位符进行的替换内容，key为不包含开始与结束记号的占位符，Map(desc -> 替换内容) => [[desc]] -> 替换内容
     * @return 替换后的字符串
     */
    public static String extract(String template, char startIdentifier, char endIdentifier, int layerNumber, Function<List<String>, Map<String, String>> function) {
        long startIdentifierNumber = template.chars().mapToObj(i -> (char) i).filter(character -> character == startIdentifier).count();
        long endIdentifierNumber = template.chars().mapToObj(i -> (char) i).filter(character -> character == endIdentifier).count();
        if (startIdentifierNumber != endIdentifierNumber) {
            // 模板中的 '[' 与 ']' 数量不一致
            throw new RuntimeException("模板中的 " + startIdentifier + " 与 " + endIdentifier + " 数量不一致");
        }
        String startIdentifierStr = repeatChar2Str(startIdentifier, layerNumber);
        String endIdentifierStr = repeatChar2Str(endIdentifier, layerNumber);
        // 获取占位符个数
        int number = (int) (startIdentifierNumber / layerNumber);
        List<String> placeholders = new ArrayList<>(number);
        int startIdentifierStrIndex = 0;
        int endIdentifierStrIndex = 0;
        for (int i = 0; i < number; i++) {
            startIdentifierStrIndex = template.indexOf(startIdentifierStr, startIdentifierStrIndex);
            endIdentifierStrIndex = template.indexOf(endIdentifierStr, endIdentifierStrIndex);
            placeholders.add(template.substring(startIdentifierStrIndex + layerNumber, endIdentifierStrIndex));
            startIdentifierStrIndex += layerNumber;
            endIdentifierStrIndex += layerNumber;
        }
        if (placeholders.isEmpty()) return template;
        // 获取占位符替换内容Map
        Map<String, String> map = function.apply(placeholders);
        StringBuilder stringBuilder = new StringBuilder();
        int startPos = 0;
        for (int i = 0; i < placeholders.size(); i++) {
            String currentPlaceHolder = startIdentifierStr + placeholders.get(i) + endIdentifierStr;
            stringBuilder.append(template, startPos, template.indexOf(currentPlaceHolder));
            stringBuilder.append(map.get(placeholders.get(i)));
            startPos += template.indexOf(currentPlaceHolder) + currentPlaceHolder.length() - startPos;
            if (i == placeholders.size() - 1) {
                if (startPos < template.length()) {
                    stringBuilder.append(template.substring(startPos));
                }
            }
        }
        return stringBuilder.toString();
    }
}
