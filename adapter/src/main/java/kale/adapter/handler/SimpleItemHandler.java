package kale.adapter.handler;

import android.content.Context;
import android.view.View;

import kale.adapter.util.ViewHolder;


/**
 * @author zwm
 * @version 2.0
 * @ClassName SimpleItemHandler
 * @Description TODO(这里用一句话描述这个类的作用)
 * @date 2015/7/29.
 */
public abstract class SimpleItemHandler<T extends  Object> implements  ItemHandler<T> ,View.OnClickListener{



    protected Context mContext;


    @Override
    public void onBindView(ViewHolder vh, T data, int position) {
        if(mContext == null){
            mContext=vh.getContext();
        }
        onBindView33(vh,data,position);
    }
    public abstract void onBindView33(ViewHolder vh, T data, int position);

    public void setOnClickListener(ViewHolder vh,int vid){

        vh.setonClickListener(vid,this);

    }
    public void setOnClickListener(ViewHolder vh){

        vh.getConvertView().setOnClickListener(this);

    }




    @Override
    public void onClick(View v) {

      Object o=  v.getTag();
        if(o != null && o instanceof ViewHolder){
            onClick(v,(T) ((ViewHolder)v.getTag()).data,((ViewHolder)v.getTag()).position);
        }


    }

    public  abstract void  onClick(View v,T t,int position);
}
