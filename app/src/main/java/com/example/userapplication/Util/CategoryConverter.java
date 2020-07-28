package com.example.userapplication.Util;

public class CategoryConverter {
    private int category;
    public CategoryConverter(int cate){
        this.category = cate;
    }
    public String convert(){
        String result = "분류없음";
        if (category == 0) result = "단품메뉴";
        if (category == 1) result = "세트메뉴";
        if (category == 2) result = "음료메뉴";
        if (category == 3) result = "사이드메뉴";
        return result;
    }
}
