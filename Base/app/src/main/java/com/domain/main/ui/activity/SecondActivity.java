package com.domain.main.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.domain.main.R;
import com.domain.main.ui.adapter.GalleryAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EActivity(R.layout.activity_second)
public class SecondActivity extends AppCompatActivity {

    @ViewById
    RecyclerView recyclerView;
    private List<String> mDatas;
    private GalleryAdapter mAdapter;

    @AfterViews
    void initView() {
        initDatas();
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器
        mAdapter = new GalleryAdapter(this, mDatas);
        mAdapter.setOnItemClickLitener(new GalleryAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(SecondActivity.this, position + "", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    private void initDatas() {
        mDatas = new ArrayList<>(Arrays.asList("http://b394.photo.store.qq.com/psb?/V10IHpOG3mfnbo/uWrDO42KCowcfYDYXgtDThwwCaxDgJQT2FnQKV*ANrc!/b/dIoBAAAAAAAA&bo=IAPCASADwgEFByQ!&rf=viewer_4",
                "http://b374.photo.store.qq.com/psb?/V10IHpOG3mfnbo/eS9Lh1N24MTNotXtg8uqwk9JcGDmBquX2OFP51L*.ms!/b/dHYBAAAAAAAA&bo=MASAAj8GuwMFABQ!&rf=viewer_4",
                "http://b110.photo.store.qq.com/psb?/V10IHpOG3mfnbo/cNVZua05svLdY4uy0LmZOkDiAXxoC8trhtqHCYPd3T0!/b/dG4AAAAAAAAA&bo=5wOAAkAGAQQFAAY!&rf=viewer_4",
                "http://b374.photo.store.qq.com/psb?/V10IHpOG3mfnbo/gxadtR6wZhk4TGJgzXnkj5IQj3ksCn.61hcvk*pDnHM!/b/dHYBAAAAAAAA&bo=cgSAAkAGhAMFABY!&rf=viewer_4",
                "http://b394.photo.store.qq.com/psb?/V10IHpOG3mfnbo/60eI.X*QUNZ4yvKMBqoDyzK5tFriP7H*TDWgmsCn3GE!/b/dIoBAAAAAAAA&bo=FQIgAxUCIAMFACM!&rf=viewer_4",
                "http://b32.photo.store.qq.com/psb?/V10IHpOG3mfnbo/KXjSdx8qrOThUF3uhTlg8UGoKM24eH59AWUOLuD.JzU!/b/dCAAAAAAAAAA&bo=cgSAAkAGhAMFABY!&rf=viewer_4",
                "http://b32.photo.store.qq.com/psb?/V10IHpOG3mfnbo/FzxwSUL5CfYtskn9MgtHA0PxTMHXnm*MW2jgR7djets!/b/dCAAAAAAAAAA&bo=cgSAAkAGhAMFABY!&rf=viewer_4",
                "http://b22.photo.store.qq.com/psb?/V10IHpOG3mfnbo/Zs9dJN*.w7sKCirGjc3kK*q5uSqoXmXTFEP0QLTlmSg!/b/dBYAAAAAAAAA&bo=cgSAAkAGhAMFABY!&rf=viewer_4",
                "http://b99.photo.store.qq.com/psb?/V10IHpOG3mfnbo/h4UOvSSo2RCfNIdfL2Jcv1QGwrFRaXJSxplBeJAFP7Y!/b/dGMAAAAAAAAA&bo=cgSAAgAAAAAFANc!&rf=viewer_4",
                "http://b351.photo.store.qq.com/psb?/V10IHpOG3mfnbo/cQDduxkhVxDYIjQo03LnDOx.koFJqMw8ncNdqSBeaoU!/b/dGSKOdEgFAAA&bo=TwOAAgAAAAADAOs!&rf=viewer_4"));
    }

}
