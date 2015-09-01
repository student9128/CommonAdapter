package kale.adapter.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;


import java.util.HashMap;
import java.util.List;

import kale.adapter.handler.ItemHandler;


public  abstract  class SingleRVAutoAdapter extends  AutoRVAdapter implements ItemHandler{


    protected SingleRVAutoAdapter(Activity activity, List<?> data) {
        super(activity, data);
    }

    protected SingleRVAutoAdapter(Fragment fragment, List<?> data) {
        super(fragment, data);
    }

    @Override
    protected void initHandlers(HashMap<Integer, ItemHandler> itemHandlerHashMap) {
        addHandler(0, this);
    }



    @Override
    protected int getViewType(int position) {
        return 0;
    }

}
