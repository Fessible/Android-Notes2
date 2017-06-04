package com.example.greendaotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.greendaodtext.greendao.GreenDaoManager;
import com.example.greendaodtext.greendao.UserDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edit_text;
    private Button btn_add;
    private Button btn_delete;
    private Button btn_update;
    private Button btn_query;
    private ListView listvew;
    private List<User> userList = new ArrayList<>();
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }


    private void initView() {
        edit_text = (EditText) findViewById(R.id.edit_text);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_query = (Button) findViewById(R.id.btn_query);
        listvew = (ListView) findViewById(R.id.listview);
        btn_add.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_query.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                insert(null, edit_text.getText().toString());
                break;
            case R.id.btn_delete:
                delete(edit_text.getText().toString());
                break;
            case R.id.btn_update:
                update(edit_text.getText().toString(),"newName");
                break;
            case R.id.btn_query:
                query();
                break;
            default:
                break;
        }

    }

    private void initData() {
        //初始化数据,将数据库的查询结果显示在listview中
        userList = GreenDaoManager.getInstance().getSession().getUserDao().queryBuilder().build().list();
        adapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, userList);
        listvew.setAdapter(adapter);
    }

    //插入
    public void insert(Long id, String name) {
        User user = new User(id, name);
        UserDao userDao = GreenDaoManager.getInstance().getSession().getUserDao();
        userDao.insert(user);

    }

    //查询
    public void query() {
        UserDao userDao = GreenDaoManager.getInstance().getSession().getUserDao();
        userList.clear();
        userList.addAll(userDao.loadAll());
        adapter.notifyDataSetChanged();
    }

    public void delete(String name) {
        UserDao userDao = GreenDaoManager.getInstance().getSession().getUserDao();
        //当有多个相同名字的，一同删除
        List<User> findUser = userDao.queryBuilder().where(UserDao.Properties.Name.eq(name)).build().list();
        if (findUser != null) {
            for (int i = 0; i < findUser.size(); i++) {
                //同主键删除
                userDao.deleteByKey(findUser.get(i).getId());
            }

        }

    }

    public void update(String preName, String newName) {
        UserDao userDao = GreenDaoManager.getInstance().getSession().getUserDao();
        //当有多个相同名字的，一同修改
        List<User> findUser = userDao.queryBuilder().where(UserDao.Properties.Name.eq(preName)).build().list();
        if (findUser != null) {
            for (int i = 0; i < findUser.size(); i++) {
                findUser.get(i).setName(newName);
                userDao.update(findUser.get(i));//更新名字
                Toast.makeText(MainActivity.this, "update successful!", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(MainActivity.this, "no user!", Toast.LENGTH_SHORT).show();

        }


    }
}
