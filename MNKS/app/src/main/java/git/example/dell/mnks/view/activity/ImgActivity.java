package git.example.dell.mnks.view.activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;

import git.example.dell.mnks.R;
import git.example.dell.mnks.util.SharedPreferencesUtils;
import git.example.dell.mnks.util.ZoomableDraweeView;

public class ImgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);

        ZoomableDraweeView big_img=findViewById(R.id.big_img);

        String img = (String) SharedPreferencesUtils.getParam(this, "img", "");

        big_img.setImageURI(Uri.parse(img));
    }
}
