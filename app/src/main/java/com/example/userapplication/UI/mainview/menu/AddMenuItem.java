package com.example.userapplication.UI.mainview.menu;

public class AddMenuItem {
    private String category;
    private String menu;

    AddMenuItem(String category, String menu){
        this.category = category;
        this.menu = menu;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getCategory() {
        return category;
    }

    public String getMenu() {
        return menu;
    }
}
