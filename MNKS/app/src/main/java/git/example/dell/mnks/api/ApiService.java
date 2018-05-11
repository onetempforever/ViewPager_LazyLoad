package git.example.dell.mnks.api;

import git.example.dell.mnks.model.bean.WelfareBean;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by DELL on 2018/5/5.
 */

public interface ApiService {


    @GET("data/福利/10/{page}")
    Flowable<WelfareBean> getWelfareData(@Path("page") String page);
}
