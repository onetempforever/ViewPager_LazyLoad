package git.example.dell.mnks.model;

import git.example.dell.mnks.model.bean.WelfareBean;

/**
 * Created by DELL on 2018/5/5.
 */

public interface IntModel {

    void LoadSuccessModelData(WelfareBean welfareBean);
    void LoadErrorModelData(Throwable t);
}
