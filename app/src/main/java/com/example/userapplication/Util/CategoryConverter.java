package com.example.userapplication.Util;

public class CategoryConverter {
    public String toStringConvert(int category){
        String result = "분류없음";
        if (category == 0) result = "단품메뉴";
        if (category == 1) result = "세트메뉴";
        if (category == 2) result = "음료메뉴";
        if (category == 3) result = "사이드메뉴";
        return result;
    }
    public int toIntConvert(String category){
        int result = -1;
        if (category == "단품메뉴") result = 0;
        if (category == "세트메뉴") result = 1;
        if (category == "음료메뉴") result = 2;
        if (category == "사이드메뉴") result = 3;
        return result;
    }
}
