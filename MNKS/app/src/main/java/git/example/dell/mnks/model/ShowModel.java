package git.example.dell.mnks.model;

import git.example.dell.mnks.api.API;
import git.example.dell.mnks.api.ApiService;
import git.example.dell.mnks.model.bean.WelfareBean;
import git.example.dell.mnks.util.RetrofitUtils;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by DELL on 2018/5/5.
 */

public class ShowModel implements Model{

    @Override
    public void getModelData(String num, final IntModel intModel) {
        ApiService retrofit = RetrofitUtils.getInData().getRetrofit(API.WELFARE_URL, ApiService.class);

        Flowable<WelfareBean> flowable = retrofit.getWelfareData(num);

        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<WelfareBean>() {
                    @Override
                    public void onNext(WelfareBean welfareBean) {
                        intModel.LoadSuccessModelData(welfareBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        intModel.LoadErrorModelData(t);

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
