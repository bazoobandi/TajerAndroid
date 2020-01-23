package com.zbam.tajer.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by z.bazoubandi on 7/24/2018.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder{
    public BaseViewHolder(View itemView) {
        super(itemView);
    }
    public abstract void onBind(int position);
}
