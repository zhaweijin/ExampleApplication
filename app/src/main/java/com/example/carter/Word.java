package com.example.carter;

import com.example.ListViewLoadMore.ListViewTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carter on 9/11/17.
 */

public class Word {
    private Long id;
    private String name;

    private List<Long> list = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<Long> getList() {
        return list;
    }

    public void setList(List<Long> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
