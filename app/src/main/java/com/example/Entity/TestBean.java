package com.example.Entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by carter on 3/6/17.
 */
public class TestBean implements Parcelable {


    /**
     * 快速创建parcelable,File -> Settings -> Pugins -> Browse Repositories 如下，输入android parcelable code generator
     * 重启后，code->Generate->parcel
     */
    private String aa;
    private String bb;
    private int x;

    @Override
    public String toString() {
        return "TestBean{" +
                "aa='" + aa + '\'' +
                ", bb='" + bb + '\'' +
                ", x=" + x +
                '}';
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getAa() {

        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa;
    }

    public String getBb() {
        return bb;
    }

    public void setBb(String bb) {
        this.bb = bb;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.aa);
        dest.writeString(this.bb);
        dest.writeInt(this.x);
    }

    public TestBean() {
    }

    protected TestBean(Parcel in) {
        this.aa = in.readString();
        this.bb = in.readString();
        this.x = in.readInt();
    }

    public static final Parcelable.Creator<TestBean> CREATOR = new Parcelable.Creator<TestBean>() {
        @Override
        public TestBean createFromParcel(Parcel source) {
            return new TestBean(source);
        }

        @Override
        public TestBean[] newArray(int size) {
            return new TestBean[size];
        }
    };
}
