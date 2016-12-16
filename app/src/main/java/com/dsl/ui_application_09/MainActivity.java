package com.dsl.ui_application_09;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能: Android Dialog对话框的实现（效果1）
 * 并且实现了返回按钮的监听
 *
 * 作者：单胜凌
 * QQ：794275763
 */
public class MainActivity extends AppCompatActivity {

    //定义图5选项菜单
    private String[] allMenuText = {"电话","相机","管理","相册","记事本","工具栏","保存","删除"};
    private int[] allMenuorders = {5,6,4,3,1,2,8,7};
    private int[] allMenuIds = {Menu.FIRST+1,Menu.FIRST+2,Menu.FIRST+3,Menu.FIRST+4,
            Menu.FIRST+5,Menu.FIRST+6,Menu.FIRST+7,Menu.FIRST+8};
    private int[] allMenuIcons = {
            android.R.drawable.ic_menu_call,
            android.R.drawable.ic_menu_camera,
            android.R.drawable.ic_menu_manage,
            android.R.drawable.ic_menu_sort_alphabetically,
            android.R.drawable.ic_menu_week,
            android.R.drawable.ic_menu_today,
            android.R.drawable.ic_menu_edit,
            android.R.drawable.ic_menu_delete
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    /**
     * 功能：返回按键的监听
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==0)
        {
            showDialog1();
        }
        return false;
    }


    /*******************************第一种Dialog方式**********************************/
    /**
     * 自定义Button引出dialog弹框
     * @param v
     */
    public void ClickButtondialog1(View v)
    {
        showDialog1();
    }

    /**
     * 功能：Dialog1内部实现
     */
    public void showDialog1(){
        AlertDialog.Builder mbuilder = new AlertDialog.Builder(this).setMessage("亲、确定要离我而去吗？")
                .setTitle("小凌温馨提示")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {//设置确定退出按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//关闭当前对话框
                        finish();//结束当前Activity
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {//设置取消按钮、取消当前弹框
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//只是关闭当前对话框、不退出当前Activity
                    }
                });
        mbuilder.create().show();//创建并且显示该对话框
        }


    /*******************************第二种Dialog方式**********************************/
    public void ClickButtondialog2(View v)
    {
        showDialog2();
    }
    public void showDialog2()
    {
        final Context mContext =this;
        Dialog mDialog = new AlertDialog.Builder(mContext)
                .setIcon(android.R.drawable.btn_star)//设置对话框图标
        .setTitle("问卷调查")//设置对话框标题
        .setMessage("你喜欢Android吗？")//设置对话框内容
        .setPositiveButton("非常喜欢", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(mContext,"我非常喜欢用Android",Toast.LENGTH_SHORT).show();
            }
        })//设置肯定按钮
        .setNeutralButton("还好", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(mContext,"Android和苹果都喜欢",Toast.LENGTH_SHORT).show();
            }
        })//中性对话框
        .setNegativeButton("不喜欢", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(mContext,"我更喜欢用IPhone一些",Toast.LENGTH_SHORT).show();
            }
        }).create();//创建对话框
        mDialog.show();//显示对话框
    }
    /*******************************第三种Dialog方式**********************************/
    public void ClickButtondialog3(View v)
    {
        showDialog3();
    }
    public void showDialog3()
    {
        final Context mContext=this;
        //定义文本输入框
        final EditText mET = new EditText(this);
        //创建新的对话框
        new AlertDialog.Builder(mContext)
                .setTitle("请输入您的名字")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setView(mET)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext,"用户名为："+mET.getText().toString(),Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消",null)//设置否定按钮
                .show();
    }
    /*******************************第四种Dialog方式**********************************/
    public void ClickButtondialog4(View v)
    {
        showDialog4();
    }
    public void showDialog4()
    {
        final Context mContext=this;
        //定义单选框选项
        String[] singleChoiceItems ={"第一行","第二行"};
        int defaultSelectedIndex = 1;
        //创建新的对话框
        new AlertDialog.Builder(mContext)
                .setTitle("单项选择")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setSingleChoiceItems(singleChoiceItems, defaultSelectedIndex, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext,"当前为："+which,Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("确定",null)
                .setNegativeButton("取消",null)
                .show();
    }

    /*******************************第五种Dialog方式**********************************/
    public void ClickButtondialog5(View v)
    {
        showDialog5();
    }
    public void showDialog5()
    {
        final Context mContext = this;
        //获取自定义布局
        LayoutInflater layoutInflater = getLayoutInflater();
        View menuView = layoutInflater.inflate(R.layout.tu5_gridview,null);

        //获取GridView组件并配置适配器
        GridView mgridView = (GridView)menuView.findViewById(R.id.gridview);
        SimpleAdapter menuSimpleAdapter = createSimpleAdapter(allMenuText,allMenuIcons);
        mgridView.setAdapter(menuSimpleAdapter);
        mgridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext,"菜单【"+allMenuText[position]+"】被点击了",Toast.LENGTH_SHORT).show();
            }
        });
        //创建对话框并显示
        new AlertDialog.Builder(mContext).setView(menuView).show();

    }

    public SimpleAdapter createSimpleAdapter(String[] menuNamees,int[] menuImages){
        List<Map<String,?>> data = new ArrayList<Map<String,?>>();
        String[] fromAdapter = {"item_text","item_image"};
        int[] tosAdapter = {R.id.item_text,R.id.item_image};
        for(int i=0;i<menuNamees.length;i++)
        {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put(fromAdapter[0],menuNamees[i]);
            map.put(fromAdapter[1],menuImages[i]);
            data.add(map);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,data,R.layout.tu5_item,fromAdapter,tosAdapter);
        return simpleAdapter;
    }
}
