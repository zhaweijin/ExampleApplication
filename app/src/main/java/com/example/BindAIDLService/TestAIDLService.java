package com.example.BindAIDLService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.aidl.IBook;


/**
 * Created by zwj on 3/12/18.
 */

public class TestAIDLService extends Service{

    private IBinder iBinder = new BookBinder();


    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }



    private class BookBinder extends IBook.Stub{
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String queryBook(int bookNo) throws RemoteException {
            return "book"+bookNo;
        }
    }
}
