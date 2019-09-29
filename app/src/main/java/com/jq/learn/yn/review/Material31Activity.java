package com.jq.learn.yn.review;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class Material31Activity extends AppCompatActivity {

    private BottomSheetBehavior<LinearLayout> mBottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material31);

        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mDialog != null && mDialog.isShowing()) {
                    hideDialog();
                } else {
                    showDialog();
                }

            }
        });
//        //1.通过id获得底部菜单布局的实例
//        LinearLayout mBottomLayout = findViewById(R.id.bottom_sheet);
//        //2.把这个底部菜单和一个BottomSheetBehavior关联起来
//        mBottomSheetBehavior = BottomSheetBehavior.from(mBottomLayout);
    }

    private BottomSheetDialog mDialog;
    public void showDialog() {
        mDialog = new BottomSheetDialog(this);
        mDialog.setContentView(R.layout.layout_bottom_sheet_linear);
        mDialog.show();
    }

    public void hideDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.hide();
        }
    }



}
