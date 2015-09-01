package kale.adapter.handler;


import kale.adapter.util.ViewHolder;

public interface ItemHandler<T extends Object> {

      int getLayoutResId();

      void onBindView(ViewHolder vh, T data, int position);


}
