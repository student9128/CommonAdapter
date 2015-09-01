package kale.adapter.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.mingle.autolist.AutoList;

import java.util.HashMap;
import java.util.List;

import kale.adapter.handler.ItemHandler;
import kale.adapter.util.ViewHolder;


public abstract class AutoAdapter extends BaseAdapter {

    protected List<?> mData;
    protected Context mContext;
    private AdapterAnimationHelper mAdapterAnimationHelper=new AdapterAnimationHelper();

    private HashMap<Integer,ItemHandler> mItemHandlerHashMap =new HashMap<>();
    public AutoAdapter(Activity activity,List<?> data) {

        mData = data;
        mContext = activity;
        initHandlers(mItemHandlerHashMap);
        if(data instanceof AutoList && activity instanceof FragmentActivity){
            ((AutoList) data).setup((FragmentActivity) activity);
            ((AutoList) data).setAdapter(this);
        }

    }

    public AutoAdapter(Fragment fragment,List<?> data) {
        mData = data;
        mContext = fragment.getActivity();
        initHandlers(mItemHandlerHashMap);
        if(data instanceof AutoList){
            ((AutoList) data).setup(fragment);
            ((AutoList) data).setAdapter(this);
        }
    }

    protected abstract void initHandlers(HashMap<Integer,ItemHandler> itemHandlerHashMap);


    protected void addHandler(int index,ItemHandler itemHandler){
        mItemHandlerHashMap.put(index,itemHandler);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return getViewType(position);
    }

    protected abstract int getViewType(int position);

    @Override
    public int getViewTypeCount() {
        return mItemHandlerHashMap.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ItemHandler item =getItemHandler(getViewType(position));
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(item.getLayoutResId(), parent,false);
        }
        item.onBindView(ViewHolder.newInstant(convertView), mData.get(position), position);
        ViewHolder.newInstant(convertView).data=mData.get(position);
        ViewHolder.newInstant(convertView).position=position;
        //list 动画
//        mAdapterAnimationHelper.add(position,ViewHolder.newInstant(convertView));
        return convertView;
    }

    protected ItemHandler getItemHandler(int index) {
        return mItemHandlerHashMap.get(index);
    }



}


