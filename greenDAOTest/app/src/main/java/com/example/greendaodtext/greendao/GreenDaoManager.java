package com.example.greendaodtext.greendao;

import android.database.sqlite.SQLiteDatabase;

import com.example.greendaotest.MyApplication;

import org.greenrobot.greendao.database.Database;

/**
 * Created by rhm on 2017/6/4.
 */

//创建数据库，需要使用单例模式，保证唯一的一个数据库。
public class GreenDaoManager {

    private DaoMaster master;
    private DaoSession session;

    private static GreenDaoManager instance;


    //创建一个数据库
    private GreenDaoManager() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(MyApplication.getContext(), "user-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        master = new DaoMaster(db);
        session = master.newSession();
    }


    //获得唯一的实例
    public static GreenDaoManager getInstance() {
        if (instance == null) {
            instance = new GreenDaoManager();
        }
        return  instance;
    }


    public DaoMaster getMaster() {
        return master;
    }

    public DaoSession getSession() {
        return session;
    }
}
