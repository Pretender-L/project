package com.demo.pattern.template;

/**
 * 模板设计模式
 * @author wjl
 */
public class TemplateTest {
    public static void main(String[] args) {
        CookingTemplate cookingTemplate = new EggsWithTomato();
        cookingTemplate.cooking();
    }
}
