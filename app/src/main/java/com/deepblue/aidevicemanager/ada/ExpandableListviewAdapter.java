package com.deepblue.aidevicemanager.ada;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.deepblue.aidevicemanager.F;
import com.deepblue.aidevicemanager.R;
import com.deepblue.aidevicemanager.item.EpOne;
import com.deepblue.aidevicemanager.item.EpTwo;

/**
 * Created by fyf on 2019/3/1.
 * 描述：是二级显示列表的adapter
 */
public class ExpandableListviewAdapter extends BaseExpandableListAdapter {
    //Model：定义的数据
    private String[] groups;
    //注意，字符数组不要写成{{"A1,A2,A3,A4"}, {"B1,B2,B3,B4，B5"}, {"C1,C2,C3,C4"}}
    private String[][] childs;
    private Context context;

    public ExpandableListviewAdapter(Context context, String[] groups, String[][] childs) {
        this.context = context;
        this.groups = groups;
        this.childs = childs;
    }

    @Override
    public int getGroupCount() {
        return groups.length;
    }

    @Override
    public int getChildrenCount(int i) {

        return childs.length > i ? childs[i].length : 0;
    }

    @Override
    public Object getGroup(int i) {
        return groups[i];
    }

    @Override
    public Object getChild(int i, int i1) {
        return childs[i][i1];
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    //分组和子选项是否持有稳定的ID, 就是说底层数据的改变会不会影响到它们
    public boolean hasStableIds() {
        return true;
    }


    @Override
/**
 *
 * 获取显示指定组的视图对象
 *
 * @param groupPosition 组位置
 * @param isExpanded 该组是展开状态还是伸缩状态，true=展开
 * @param convertView 重用已有的视图对象
 * @param parent 返回的视图对象始终依附于的视图组
 */
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new EpOne(context);
        }
        ((EpOne) convertView).set(groups[groupPosition], isExpanded,groupPosition);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (groupPosition) {
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        F.INSTANCE.logOut(context);
                        break;
                }
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new EpTwo(context);
        }
        ((EpTwo) convertView).set(childs[groupPosition][childPosition]);

        return convertView;
    }

    //指定位置上的子元素是否可选中
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
