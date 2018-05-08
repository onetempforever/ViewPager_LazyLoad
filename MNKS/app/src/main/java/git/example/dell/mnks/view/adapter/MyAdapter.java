package git.example.dell.mnks.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;

import git.example.dell.mnks.view.activity.ImgActivity;
import git.example.dell.mnks.R;
import git.example.dell.mnks.model.bean.WelfareBean;
import git.example.dell.mnks.util.SharedPreferencesUtils;

/**
 * Created by DELL on 2018/5/5.
 */

public class MyAdapter extends BaseAdapter{


    Context context;
    List<WelfareBean.ResultsBean> results;

    public MyAdapter(Context context, List<WelfareBean.ResultsBean> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public Object getItem(int i) {
        return results.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.f5_item, null);
            holder.imageView=(ImageView)view.findViewById(R.id.iv);
            holder.textView=(TextView) view.findViewById(R.id.tv);
            view.setTag(holder);
        }else {

            holder= (ViewHolder) view.getTag();
        }

        Glide.with(context).load(results.get(i).getUrl()).into(holder.imageView);
        holder.textView.setText(results.get(i).get_id());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferencesUtils.setParam(context,"img",results.get(i).getUrl());

                Intent intent = new Intent(context, ImgActivity.class);
                context.startActivity(intent);

            }
        });



        return view;
    }

    class ViewHolder{

        ImageView imageView;
        TextView textView;
    }
}
