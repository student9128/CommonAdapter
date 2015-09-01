package kale.adapter.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;


import java.util.HashMap;
import java.util.List;

import kale.adapter.handler.ItemHandler;


public   abstract  class SingleAutoAdapter extends  AutoAdapter implements ItemHandler {


    protected SingleAutoAdapter(Activity activity, List<?> data) {
        super(activity, data);
    }

    protected SingleAutoAdapter(Fragment fragment, List<?> data) {
        super(fragment, data);
    }

    @Override
    protected void initHandlers(HashMap<Integer, ItemHandler> itemHandlerHashMap) {
        addHandler(0,this);
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }


}
