package com.example.JsonTest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.RecycleView.RecycleViewTest;
import com.example.carter.BaseActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carter on 5/11/17.
 */

public class JsonStringTest extends BaseActivity{


    private String tag = "jsonTest";

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, JsonStringTest.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        test();

        test2();


        test3();

        //最新序列化json Gson方法
        test4();
    }

    private void test4(){
        Log.v(tag,"test4.....");
        List<Person> persons = new ArrayList<>();
        for(int i=0;i<3;i++){
            Person person = new Person();
            person.setName("cc"+i);
            person.setValue(3);
            persons.add(person);
        }
        Gson gson = new Gson();
        Log.v(tag,gson.toJson(persons));
    }

    class Person{
        String name;
        int value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }



    private void test(){
        try{
            org.json.JSONObject json = new org.json.JSONObject();
            json.put("name1","carter");
            json.put("name2","alina");
            Log.v(tag,json.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void test2(){
        try{
            ArrayList<Pp> list = new ArrayList<>();
            for(int i=0;i<3;i++){
                Pp pp = new Pp();
                pp.setAge("age"+i);
                pp.setName("name"+i);
                list.add(pp);
            }

            org.json.JSONObject json = new org.json.JSONObject();
            json.put("key","cccccc");
            JSONArray jsonArray = new JSONArray();
            for(int i=0;i<list.size();i++){
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("age",list.get(i).getAge());
                jsonObject1.put("name",list.get(i).getName());
                jsonArray.put(jsonObject1);
            }
            json.put("pp",jsonArray);
            Log.v(tag,json.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    class Pp{
        String name;
        String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }


    private void test3(){
        try{
            //init goods
            ArrayList<Commodity> list = new ArrayList<>();

            Commodity commodity = new Commodity();
            commodity.setBody("女士小黑裙");
            commodity.setProduceID("abc1");
            commodity.setPrice("$32");
            commodity.setQuailty(3);
            list.add(commodity);

            commodity = new Commodity();
            commodity.setBody("女士手提包");
            commodity.setProduceID("abc2");
            commodity.setPrice("$222");
            commodity.setQuailty(100);
            list.add(commodity);


            JSONObject json = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            for(int x=0;x<2;x++){
                JSONObject jsonObject1 = new JSONObject();
                JSONArray jsonArray1 = new JSONArray();
                jsonObject1.put("domypay_id","商户编号"+x);
                for(int i=0;i<list.size();i++){
                    JSONObject jsonObjectcommodity = new JSONObject();
                    jsonObjectcommodity.put("body",list.get(i).getBody());
                    jsonObjectcommodity.put("price",list.get(i).getPrice());
                    jsonObjectcommodity.put("quailty",list.get(i).getQuailty());
                    jsonObjectcommodity.put("product_id",list.get(i).getProduceID());
                    jsonArray1.put(jsonObjectcommodity);
                }
                jsonObject1.put("good_items",jsonArray1);
                jsonArray.put(jsonObject1);
            }

            json.put("good_groups",jsonArray);
            Log.v(tag,json.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    class Commodity{
        String body;
        String price;
        int quailty;
        String produceID;

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getQuailty() {
            return quailty;
        }

        public void setQuailty(int quailty) {
            this.quailty = quailty;
        }

        public String getProduceID() {
            return produceID;
        }

        public void setProduceID(String produceID) {
            this.produceID = produceID;
        }
    }

}
