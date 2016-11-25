package com.wr_education.dialog;

import android.content.Context;
import android.view.View;

import com.wr_education.app.AbstractDialog;

/**
 * Created by Administrator on 2016/4/18.
 */
public class CommitPicDialog extends AbstractDialog{

    public CommitPicDialog(Context context,View view) {
        super(context);
        setContentView(view);
    }

}
