package git.example.dell.mnks.view.activity;

import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;

import git.example.dell.mnks.R;
import git.example.dell.mnks.view.fragment.F1;
import git.example.dell.mnks.view.fragment.F2;
import git.example.dell.mnks.view.fragment.F3;
import git.example.dell.mnks.view.fragment.F4;
import git.example.dell.mnks.view.fragment.F5;
import git.example.dell.mnks.view.fragment.F6;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab;
    private ViewPager vp;
    private String[] TITLE={"关注","推荐","视频","直播","图片","段子"};
    private ArrayList<Fragment> fragmentList;


    @Override
    protected void onStart() {
        super.onStart();
        tab.post(new Runnable() {
            @Override
            public void run() {

                setIndicator(tab,30,30);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview(

        );
        setData();
        setAdapter();
        //实现ViewPager与TabLayout联动
        tab.setupWithViewPager(vp);


    }
    //ViewPagerAdapter
    private void setAdapter() {
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public CharSequence getPageTitle(int position) {
                return TITLE[position];
            }

            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return TITLE.length;
            }
        });
    }

    private void setData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new F1());
        fragmentList.add(new F2());
        fragmentList.add(new F3());
        fragmentList.add(new F4());
        fragmentList.add(new F5());
        fragmentList.add(new F6());
    }

    //获取控件
    private void initview() {
        tab = findViewById(R.id.tab);
        vp = findViewById(R.id.vp);
    }

    //设置左右间距
    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }


    }


}
