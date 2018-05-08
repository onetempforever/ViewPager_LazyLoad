package git.example.dell.mnks.view;

import git.example.dell.mnks.model.bean.WelfareBean;

/**
 * Created by DELL on 2018/5/5.
 */

public interface View {
    void LoadSuccessViewData(WelfareBean welfareBean);
    void LoadErrorViewData(Throwable t);

}
