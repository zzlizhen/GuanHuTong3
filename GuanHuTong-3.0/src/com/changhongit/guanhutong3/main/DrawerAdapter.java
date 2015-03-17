package com.changhongit.guanhutong3.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.changhongit.guanhutong3.R;

/**
 * 数据写死的adapter
 * 
 */
public class DrawerAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private static final String[] titlelist = { "个人信息", "修改密码", "终端管理", "离线地图", "关于" };
    private static final int[] iconlist = { R.drawable.personal_info_img, R.drawable.modify_psd_img,
             R.drawable.update_img, R.drawable.offline_img,
            R.drawable.about_img
            };

    public DrawerAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    private final class DrawerItem {
        public ImageView img;
        public TextView text;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 5;
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        // TODO Auto-generated method stub.
        DrawerItem item = null;
        if (arg1 == null) {
            item = new DrawerItem();
            arg1 = inflater.inflate(R.layout.drawer_item, null);
            item.img = (ImageView) arg1.findViewById(R.id.item_icon);
            item.text = (TextView) arg1.findViewById(R.id.item_text);
            arg1.setTag(item);
        } else {
            item = (DrawerItem) arg1.getTag();
        }
        item.img.setBackgroundResource(iconlist[arg0]);
        item.text.setText(titlelist[arg0]);
        return arg1;
    }

}
