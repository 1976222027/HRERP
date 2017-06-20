package com.huirong.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.huirong.R;
import com.huirong.application.MyApplication;
import com.huirong.base.BaseListAdapter;
import com.huirong.common.ImageLoadingConfig;
import com.huirong.model.MyApplicationModel;
import com.huirong.widget.CircleTextView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.Random;


/**
 * 工作计划 适配
 *
 * @author
 */

public class WorkplanListAdapter extends BaseListAdapter {
    private ImageLoader imgLoader;
    private DisplayImageOptions imgOptions;

    public class WidgetHolder {
        public TextView tvType;
        public CircleTextView tvName;
        public TextView tvTime;
        public TextView tvdetail;
    }

    public WorkplanListAdapter(Context context) {
        super(context);
        imgLoader = ImageLoader.getInstance();
        imgLoader.init(ImageLoaderConfiguration.createDefault(context));
        imgOptions = ImageLoadingConfig.generateDisplayImageOptions(R.mipmap.ic_launcher);
    }

    @Override
    protected View inflateConvertView() {
        //一条记录的布局
        View view = inflater.inflate(R.layout.item_workplan, null);
        //该布局上的控件
        WidgetHolder holder = new WidgetHolder();
        holder.tvName = (CircleTextView) view.findViewById(R.id.tv_name);
        holder.tvType = (TextView) view.findViewById(R.id.tv_type);
        holder.tvdetail = (TextView) view.findViewById(R.id.tv_detail);
        holder.tvTime = (TextView) view.findViewById(R.id.tv_time);
        view.setTag(holder);
        return view;
    }

    @Override
    protected void initViewData(final int position, View convertView) {
        WidgetHolder holder = (WidgetHolder) convertView.getTag();//获取控件管理实例
        //获取一条信息
        //?java.lang.ClassCastException: java.util.ArrayList cannot be cast to com.yvision.model.VisitorBModel
        MyApplicationModel model = (MyApplicationModel) entityList.get(position);
        holder.tvName.setText(model.getEmployeeName());
        holder.tvName.setBackgroundColor(ContextCompat.getColor(MyApplication.getInstance(), randomColor()));

        holder.tvTime.setText(model.getCreateTime());
        holder.tvType.setText(model.getApplicationType());
        holder.tvdetail.setText(model.getDetail());
    }

    //设置一条记录的随机颜色
    private int randomColor() {
        int[] colorArray = new int[]{R.color.pink, R.color.lightgreen, R.color.gray, R.color.yellow, R.color.common_color, R.color.aquamarine, R.color.brown};
        return colorArray[new Random().nextInt(6)];
    }

    public void destroy() {
        imgLoader.clearMemoryCache();
        imgLoader.destroy();
    }

}
