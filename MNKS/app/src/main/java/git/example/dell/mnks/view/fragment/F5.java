package git.example.dell.mnks.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.wzq.wzlibrary.view.XListView;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import git.example.dell.mnks.R;
import git.example.dell.mnks.model.bean.EventMessage;
import git.example.dell.mnks.view.adapter.MyAdapter;
import git.example.dell.mnks.model.bean.WelfareBean;
import git.example.dell.mnks.presenter.ShowPresenter;

/**
 * Created by DELL on 2018/5/5.
 */

public class F5 extends BaseFragment {

    private XListView xlv;
    private int num=1;
    Handler myHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                //关闭底部视图
                xlv.stopLoadMore();
            }else{
                Date date = new Date(System.currentTimeMillis());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                String format = simpleDateFormat.format(date);
                //设置时间
                xlv.setRefreshTime(format);
                //关闭头部视图
                xlv.stopRefresh();
            }
        }
    };
    ArrayList<WelfareBean.ResultsBean> list = new ArrayList<>();
    private ShowPresenter showPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View inflate = inflater.inflate(R.layout.f5, null);
        xlv = inflate.findViewById(R.id.xlv);

        return inflate;
    }

    @Override
    protected void loadData() {

        initData();
        setRefresh();

    }

    private void initData() {
        showPresenter = new ShowPresenter();
        showPresenter.getPresenterData(num+"", new git.example.dell.mnks.view.View() {
                    @Override
                    public void LoadSuccessViewData(WelfareBean welfareBean) {
                        final List<WelfareBean.ResultsBean> results = welfareBean.getResults();

                        xlv.setAdapter(new MyAdapter(getActivity(),results));
                        //发送
                        EventBus.getDefault().post(new EventMessage(results.get(1).getUrl()));
                    }
                    @Override
                    public void LoadErrorViewData(Throwable t) {

                    }
                });
    }

    private void setRefresh() {
        ShowPresenter showPresenter = new ShowPresenter();
        showPresenter.getPresenterData(num+"", new git.example.dell.mnks.view.View() {
            @Override
            public void LoadSuccessViewData(WelfareBean welfareBean) {
                final List<WelfareBean.ResultsBean> results = welfareBean.getResults();
                //2、设置刷新和加载更多的方法并设置监听
                //支持下拉刷新
                xlv.setPullLoadEnable(true);
                //支持加载更多
                xlv.setPullLoadEnable(true);
                //设置监听事件
                xlv.setXListViewListener(new XListView.IXListViewListener() {
                    @Override
                    public void onRefresh() {
                        num++;
                        //添加到大集合
                        list.addAll(results);
                        xlv.setAdapter(new MyAdapter(getActivity(),list));
                        //延迟关闭头部视图
                        myHandler.sendEmptyMessageDelayed(2,500);
                        }

                    @Override
                    public void onLoadMore() {
                        num=1;
                        list.clear();
                        //延迟关闭头部视图
                        myHandler.sendEmptyMessageDelayed(1,500);

                    }
                });



            }

            @Override
            public void LoadErrorViewData(Throwable t) {

            }
        });
    }

    /**
     *
     * 解绑
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.showPresenter.DetachView();
    }
}
