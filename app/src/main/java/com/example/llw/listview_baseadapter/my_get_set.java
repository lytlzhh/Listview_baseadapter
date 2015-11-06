package com.example.llw.listview_baseadapter;

import java.io.Serializable;

/**
 * Created by llw on 2015/11/5.
 */
public class my_get_set implements Serializable {
    int item_image;
    String item_title;
    String item_content;

    public my_get_set(int item_image, String item_title, String item_content) {
        this.item_image = item_image;
        this.item_title = item_title;
        this.item_content = item_content;
    }

}
