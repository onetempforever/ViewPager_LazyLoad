package git.example.dell.mnks.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import git.example.dell.mnks.R;
import git.example.dell.mnks.adapter.ShowAdapter;

/**
 * Created by DELL on 2018/5/5.
 */

public class F1 extends BaseFragment {

    private RecyclerView recycle_view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.f1, null);

        recycle_view = view.findViewById(R.id.recycle_view);

        return view;
    }

    @Override
    protected void loadData() {

        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {

            list.add("高新高新高新。。。"+i);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recycle_view.setLayoutManager(linearLayoutManager);

        ShowAdapter showAdapter = new ShowAdapter(getActivity(),list);
        recycle_view.setAdapter(showAdapter);


    }
}
