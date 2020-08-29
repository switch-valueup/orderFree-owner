package com.example.userapplication.Util;

public class CategoryConverter {
    public String toStringConvert(int category){
        String result = "분류없음";
        if (category == 0) result = "조식뷔페";
        if (category == 1) result = "A코너";
        if (category == 2) result = "B코너";
        if (category == 3) result = "푸드코트";
        return result;
    }
    public int toIntConvert(String category){
        int result = -1;
        if (category.equals("조식뷔페")) result = 0;
        if (category.equals("A코너")) result = 1;
        if (category.equals("B코너")) result = 2;
        if (category.equals("푸드코트")) result = 3;
        return result;
    }
}
