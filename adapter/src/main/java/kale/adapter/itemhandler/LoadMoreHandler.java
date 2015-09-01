package kale.adapter.itemhandler;


import kale.adapter.handler.ItemHandler;
import kale.adapter.util.ViewHolder;

/**
 * @author zwm
 * @version 2.0
 * @ClassName LoadMoreHandler
 * @Description TODO(这里用一句话描述这个类的作用)
 * @date 2015/7/17.
 */
public class LoadMoreHandler implements ItemHandler {
    @Override
    public int getLayoutResId() {
//        return R.layout.layout_load_foot_view;
        return 0;
    }

    @Override
    public void onBindView(ViewHolder vh, Object data, int position) {

    }


}
