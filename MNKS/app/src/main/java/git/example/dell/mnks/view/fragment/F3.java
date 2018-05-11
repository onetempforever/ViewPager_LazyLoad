package git.example.dell.mnks.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import git.example.dell.mnks.R;
import git.example.dell.mnks.model.bean.EventMessage;

/**
 * Created by DELL on 2018/5/5.
 */

public class F3 extends BaseFragment {

    private ImageView f3_img;
    private TextView f3_tv;
    private String msg;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //注册Event  单例设计模式
        Log.e("绑定","1111111");
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.f3, null);
        f3_img = view.findViewById(R.id.f3_img);
        f3_tv = view.findViewById(R.id.f3_tv);

        if(null!=msg){


            f3_tv.setText(msg);
            Glide.with(getActivity()).load(msg).into(f3_img);

        }

        return view;
    }

    @Override
    protected void loadData() {

    }

    //接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventMessage message) {
        msg = message.getMsg();
        Log.e("数据","1111111"+ msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        Log.e("解绑","1111111");

    }

}
