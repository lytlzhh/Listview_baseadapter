package com.example.llw.listview_baseadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by llw on 2015/11/5.
 */
public class Myadapter extends BaseAdapter {

    private  List<my_get_set> mylist;
    LayoutInflater minflater;
    long sum;
    public Myadapter(List<my_get_set> list,Context context) {
        this.mylist = list;
        minflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mylist.size();
    }

    @Override
    public Object getItem(int position) {
        return mylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //逗比式 //没次都会创建一个view实例 缺点：耗资源
  /*      View view = minflater.inflate(R.layout.my_wight,null);
        ImageView imageView = (ImageView) view.findViewById(image);
        TextView textView1 = (TextView) view.findViewById(R.id.text1);
        TextView textView2 = (TextView) view.findViewById(R.id.text2);
        imageView.setImageResource(mylist.get(position).item_image);
        textView2.setText(mylist.get(position).item_content);
        textView1.setText(mylist.get(position).item_title);*/

        //普通式……………………………………………………………………………………………………………………………………………………………………………………
        //普通式  //采用了listview的缓存机制 提高效率
        //虽然是利用了listview的缓存机制 但是在findViewByid()的时候依然会耗时间 //这里只是进行了convertview优化
/*
        long start = System.nanoTime();
        if (convertView==null)
        {
            convertView = minflater.inflate(R.layout.my_wight,null);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
        TextView textView1 = (TextView) convertView.findViewById(R.id.text1);
        TextView textView2 = (TextView) convertView.findViewById(R.id.text2);
        imageView.setImageResource(mylist.get(position).item_image);
        textView2.setText(mylist.get(position).item_content);
        textView1.setText(mylist.get(position).item_title);
*/

//文艺式&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
        //文艺式 //和其他两个相比较；这里的大大的提高了效率 convertView 和 findViewByid()两处都进行了优化
        long start = System.nanoTime();
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = minflater.inflate(R.layout.my_wight,null);
            viewHolder.my_image_id = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.my_textview1_id = (TextView) convertView.findViewById(R.id.text1);
            viewHolder.my_textview2_id = (TextView) convertView.findViewById(R.id.text2);
            convertView.setTag(viewHolder);//convertView 和viewHolder关联起来 用于后面的conertview.getTag()

        }else {
            viewHolder = (ViewHolder) convertView.getTag();//此处是避免重复的findviewbyid()的精髓
        }
        viewHolder.my_image_id.setImageResource(mylist.get(position).item_image);
        viewHolder.my_textview1_id.setText(mylist.get(position).item_title);
        viewHolder.my_textview2_id.setText(mylist.get(position).item_content);
//文艺式&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&


        //测试运行时间$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
        long end = System.nanoTime();
        long value = end-start;
        sum +=value;
        Log.d("消耗时间是：", String.valueOf(value));
        //测试运行时间$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
        return convertView;
    }

    //文艺专属%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    public  class ViewHolder{
        ImageView my_image_id;
        TextView my_textview1_id;
        TextView my_textview2_id;
    }
    //文艺专属%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    //通过运行时间结果的比较知道：文艺式比其他快很多
}
