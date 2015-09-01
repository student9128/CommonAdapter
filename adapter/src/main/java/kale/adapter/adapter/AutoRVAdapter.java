package kale.adapter.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.mingle.autolist.AutoList;

import java.util.HashMap;
import java.util.List;

import kale.adapter.handler.ItemHandler;
import kale.adapter.util.ViewHolder;


public abstract class AutoRVAdapter extends RecyclerView.Adapter {

    protected List<?> mData;

    protected  Activity mActivity;
    protected HashMap<Integer,ItemHandler> mItemHandlerHashMap =new HashMap<>();
    private AdapterAnimationHelper mAdapterAnimationHelper=new AdapterAnimationHelper();


    protected abstract void initHandlers(HashMap<Integer,ItemHandler> itemHandlerHashMap);

    public AutoRVAdapter(Activity activity,List<?> data) {
        mData = data;
        mActivity = activity;
        initHandlers(mItemHandlerHashMap);
        if(data instanceof AutoList && activity instanceof FragmentActivity){
            ((AutoList) data).setup((FragmentActivity) activity);
            ((AutoList) data).setRVAdapter(this);
        }
    }

    public AutoRVAdapter(Fragment fragment,List<?> data) {

        mData = data;
        mActivity = fragment.getActivity();
        initHandlers(mItemHandlerHashMap);
        if(data instanceof AutoList){
            ((AutoList) data).setup(fragment);
            ((AutoList) data).setRVAdapter(this);
        }

    }

    protected void addHandler(int index,ItemHandler itemHandler){
        mItemHandlerHashMap.put(index,itemHandler);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getViewType(position);
    }

    protected abstract int getViewType(int position);


    protected ItemHandler getItemHandler(int index) {
        return mItemHandlerHashMap.get(index);
    }
    @Override
    public RcvAdapterItem onCreateViewHolder(ViewGroup parent, int viewType) {

        return new  RcvAdapterItem(parent.getContext(), getItemHandler(viewType));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            ItemHandler itemHandler = getItemHandler(getViewType(position));

        if(itemHandler== null){
            throw  new RuntimeException(mData.get(position).getClass()+"  缺少ItemHandler 类,导致不能绑定数据");
        }else {

            itemHandler.onBindView((ViewHolder) holder.itemView.getTag(), mData.get(position), position);
            ((ViewHolder) holder.itemView.getTag()).data=mData.get(position);
            ((ViewHolder) holder.itemView.getTag()).position=position;
//            mAdapterAnimationHelper.add(position, ((ViewHolder) holder.itemView.getTag()));

        }

    }



    public  class RcvAdapterItem extends RecyclerView.ViewHolder {

        private ViewHolder vh;

        public RcvAdapterItem(Context context,ItemHandler t) {
            super((LayoutInflater.from(context).inflate(t.getLayoutResId(), null)));
            vh = ViewHolder.newInstant(itemView);
            itemView.setTag(vh);
        }





    }

}
