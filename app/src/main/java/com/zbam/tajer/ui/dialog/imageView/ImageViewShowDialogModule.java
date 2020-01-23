package com.zbam.tajer.ui.dialog.imageView;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.zbam.tajer.costum.adapter.AdvertisingAdapter;
import com.zbam.tajer.costum.adapter.AdvertisingImgeViewShowAdapter;
import com.zbam.tajer.data.model.api.response.AdvertisingImageResponse;
import com.zbam.tajer.data.model.api.response.AdvertisingResponse;
import com.zbam.tajer.data.pref.DataManager;
import com.zbam.tajer.ui.dialog.contactInfo.ContactInfoViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by z.bazoubandi on 10/17/2018.
 */

@Module
public class ImageViewShowDialogModule {

    @Provides
    ImageViewShowViewModel porvideImageViewShowViewModel(DataManager dataManager){
        return new ImageViewShowViewModel(dataManager);
    }

    @Provides
    AdvertisingImgeViewShowAdapter providesAdvertisingImgeViewShowAdapter(Context mContext, List<AdvertisingImageResponse> items)
    {
        return new AdvertisingImgeViewShowAdapter(mContext,items);
    }

//    @Provides
//    List<AdvertisingImageResponse> provideAdvertisingImageShowEmptyList(){
//        List<AdvertisingImageResponse> items = new ArrayList<>();
//        return items ;
//    }

    @Provides
    LinearLayoutManager providesLinearLayoutManager(Context context)
    {
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        return llm;
    }
}
