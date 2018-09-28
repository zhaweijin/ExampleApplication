package com.example.Database;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;
import com.example.Util.CarterLogger;
import com.example.carter.R;

import java.util.List;

public class DatabaseActivityTest extends AppCompatActivity {

    /**
     *
     * 所有操作
     Delete
     From
     Join
     Select
     Set
     Update

     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_activity_test);


        CarterLogger.v("active dabase test");

        for(int i=10;i<30;i++){
            savePerson(i);
        }

        /*List<Person> list = getAll();
        for(int i=0;i<list.size();i++){
            CarterLogger.v(list.get(i).getName()+", "+list.get(i).getAge());
        }*/

        //updatePersion("aa22",1000);

        //deletePerson(10);

/*        if(getPerson("aa22").get(0).getSex()==null){
            updatePersionSex("aa22","test");
//            Log.v("test","null");
        }*/


        //new Delete().from(Person.class).execute();

    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, DatabaseActivityTest.class);
        activity.startActivity(intent);
    }


    /**
     * 小数据插入，大数据插入使用ActiveAndroid.setTransactionSuccessful()
     * @param i
     */
    private void savePerson(int i){
        Person person = new Person();
        person.setName("aa"+i);
        person.setAge(i);
        person.save();
    }



    private void deletePerson(int age){
        new Delete()
                .from(Person.class)
                .where("age2 = ?",age)
                .execute();
    }


    private List<Person> getPerson(String name){
        return new Select()
                .from(Person.class)
                .where("name1 = ?",name)
                .orderBy("age2 ASC")
                .execute();
    }

    private List<Person> getAll(){
        return new Select()
                .from(Person.class)
                .orderBy("age2 ASC")
                .execute();
    }

    private void updatePersion(String name,int age){
        new Update(Person.class)
                .set("age2 = ?",age)
                .where("name1 = ?",name)
                .execute();
    }


    private void updatePersionSex(String name,String bb){
        new Update(Person.class)
                .set("sex = ?",bb)
                .where("name1 = ?",name)
                .execute();
    }


}
