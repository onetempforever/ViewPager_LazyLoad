package git.example.dell.mnks.presenter;

import git.example.dell.mnks.model.bean.WelfareBean;
import git.example.dell.mnks.model.IntModel;
import git.example.dell.mnks.model.ShowModel;
import git.example.dell.mnks.view.View;

/**
 * Created by DELL on 2018/5/5.
 */

public class ShowPresenter implements IntPresenter{


    private View view;

    @Override
    public void getPresenterData(String num, final View view) {
        ShowModel showModel = new ShowModel();
        showModel.getModelData(num, new IntModel() {
            @Override
            public void LoadSuccessModelData(WelfareBean welfareBean) {
                //不等于空就回调
                if (view!=null){
                    view.LoadSuccessViewData(welfareBean);

                }
            }

            @Override
            public void LoadErrorModelData(Throwable t) {
                view.LoadErrorViewData(t);

            }
        });
    }

    /*public void AttachView(View view){

        this.view = view;

    }*/

    public void DetachView(){

        this.view=null;

    }


}
