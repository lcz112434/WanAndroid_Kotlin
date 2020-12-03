package com.lcz.lczed_mvpbase.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @author: Lczed
 * @date on 2020/11/19 10:33 星期四
 * E-mail: lcz3466601343@163.com
 * Description :
 */
public abstract class IBaseAdapter<T> extends BaseAdapter {

    private List<T> list;
    private Context context;

    public IBaseAdapter(List<T> list, Context context) {
        this.list = list;
        this.context = context;
    }


    public int getCount() {
        return list == null ? 0 : list.size();
    }


    public Object getItem(int position) {
        return list.get(position);
    }


    public long getItemId(int position) {
        return position;
    }

    @SuppressWarnings("unchecked")

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, getLayoutResId(), null);
            viewHolder = new ViewHolder();
            //将控件与 ViewHolder 绑定
            int[] viewIdArray = bindView();
            for (int aViewIdArray : viewIdArray) {
                viewHolder.bindViewById(convertView, aViewIdArray);
            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        initData(viewHolder, list.get(position), position);
        return convertView;
    }

    /**
     * 获取 layout 文件
     *
     * @return layout 文件 ID
     */
    protected abstract int getLayoutResId();

    /**
     * 将控件与 ViewHolder 绑定
     *
     * @return 需要绑定的控件 ID 数组
     */
    protected abstract int[] bindView();

    /**
     * 绑定显示数据,增加回调监听等操作
     *
     * @param holder   ViewHolder
     * @param position 对应的位置
     * @param t        list.get(position)数据
     */
    protected abstract void initData(ViewHolder holder, T t, int position);

    public class ViewHolder {
        private SparseArray<View> viewSparseArray;

        ViewHolder() {
            this.viewSparseArray = new SparseArray<>();
        }

        /**
         * 通过 id获取 View
         *
         * @param id  View ID
         * @param <E> View 类型
         * @return 对应的 View
         */
        @SuppressWarnings("unchecked")
        public <E extends View> E getViewById(@IdRes int id) {
            return (E) viewSparseArray.get(id);
        }

        /**
         * 通过 id 获取 View并绑定到 ViewHolder
         *
         * @param view 布局文件 View
         * @param id   View ID
         */
        public void bindViewById(View view, @IdRes int id) {
            viewSparseArray.put(id, view.findViewById(id));
        }

    }
}