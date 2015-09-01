package kale.adapter.util;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;

public class ViewHolder {

    private SparseArray<View> viewHolder;
    private View view;
    public Object data;
    public int position;


    public static  ViewHolder newInstant(View view){
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolder(view);

            view.setTag(viewHolder);
        }

        return viewHolder;
    }




    private ViewHolder(View view) {
        this.view = view;
        viewHolder = new SparseArray<>();
        view.setTag(viewHolder);
    }

    public <T extends View> T get(int id) {

        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }

    public View getConvertView() {
        return view;
    }

    public TextView getTextView(int id) {

        return get(id);
    }
    public TextSwitcher getTextSwitcher(int id) {

        return get(id);
    }
    public Button getButton(int id) {
        return get(id);
    }

    public ImageView getImageView(int id) {
        return get(id);
    }

    public void setTextView(int  id,CharSequence charSequence){

        getTextView(id).setText(charSequence);
    }

    public Context getContext(){
      return  getConvertView().getContext();
    }


    public <T extends  Object> void setonClickListener(int vid, View.OnClickListener onClick) {
        this.get(vid).setOnClickListener(onClick);
        this.get(vid).setTag(this);
    }
}
