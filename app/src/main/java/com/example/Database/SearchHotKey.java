package com.example.Database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by carter on 4/23/17.
 */
@Table(name= "search_hot_key" )
public class SearchHotKey extends Model{

    //商品显示的名字
    @Column(name = "key")
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
