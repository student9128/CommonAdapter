# CommonAdapter
通过封装BaseAdapter和RecyclerView.Adapter得到的通用的，简易的Adapter  


这里内部集成了[AndroidAutoList:自动更新列表的AutoList ](https://github.com/zzz40500/AndroidAutoList) 这个库
###gradle
/build.gradle
~~~
repositories {
    maven {
        url "https://jitpack.io"
    }
}
~~~
/app/build.gradle
~~~
   compile 'com.github.zzz40500:CommonAdapter:1.0'
~~~
使用:
继承AutoAdapter 类,复写initHandlers()和getViewType();  
`getViewType()` 根据position 返回类型(因为ListView 的限制,从0开始,即返回的type 0,1,2... )  
`initHandlers()`  添加对没个类型的处理类ItemHandler
~~~

/**
 * Created by zzz40500 on 15/8/30.
 */
public class ManAdapter extends AutoAdapter {
    public ManAdapter(Activity activity, List<?> data) {
        super(activity, data);
    }

    protected ManAdapter(Fragment fragment, List<?> data) {
        super(fragment, data);
    }

    @Override
    protected void initHandlers(HashMap<Integer, ItemHandler> itemHandlerHashMap) {

        addHandler(0,new ManHandler());
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }
}
~~~
ItemHandler 类的编写:  
你可以实现接口ItemHandler  也可以继承SimpleItemHandler (推荐这个,这个多了变量Content,和点击事件,你可以更快的实现你要实现的功能 )  
实现getLayoutResId()和 onBindView33();  
`getLayoutResId()` 得到这个Item的布局id  
`onBindView33(ViewHolder ,T,int)` 绑定数据.(这里等同ItemHandler 中的onBindView() 方法,主要是在onBindView()做了预处理)  
`onClick(ViewHolder ,T,int)`点击事件;  
这边有一个泛型.就是数据源的类型,这么写了泛型的话,下面就不要强转了.  
~~~
/**
 * Created by zzz40500 on 15/8/30.
 */
public class ManHandler extends SimpleItemHandler<ManEntity> {


    @Override
    public int getLayoutResId() {
        return  R.layout.item_man;
    }


    @Override
    public void onBindView33(ViewHolder vh, ManEntity data, int position) {
        vh.setTextView(R.id.nameTV, data.name);
        vh.setTextView(R.id.ageTV, "年龄:".concat(data.age+""));
        vh.setTextView(R.id.heightTV, "身高:".concat(data.height));

        setOnClickListener(vh);
    }

    @Override
    public void onClick(View v, ManEntity manEntity, int position) {

        ManDetailActivity.startActivity(mContext, manEntity);
    }


}
~~~
ViewHolder 提供一些简单的方法:     
`setTextView(tvId, String)` 设置文字在在id为tvId上面.  
####note:  
这里点击事件请调用:`setOnClickListener(ViewHolder vh,int vid)`或者 `setOnClickListener(ViewHolder vh)`  
响应事件在SimpleItemHandler 中的` onClick(View v, ManEntity manEntity, int position)`中  
这里吧数据和位置信息传递过来了;  

上面是ListView 的适配器RecycleView的适配就是把继承的AutoAdapter 换成AutoRVAdapter类,其他全部相同,  
所以你切换ListView 和RecycleView很快.  

够简单 够快吗?

尾巴
所以用的代码均来自AndroidAutoList 的App Demo中.
这是一个很好的解决了Item复用的问题.


