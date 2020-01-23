 package com.zbam.tajer.ui.option.support;


 import android.content.Context;
 import android.support.v7.widget.LinearLayoutManager;

 import com.zbam.tajer.costum.adapter.SupportAdapter;
 import com.zbam.tajer.data.model.api.response.SupportTopicResponse;
 import com.zbam.tajer.data.pref.DataManager;

 import java.util.ArrayList;
 import java.util.List;

 import dagger.Module;
 import dagger.Provides;

 /**
 * Created by zb on 2/14/2018.
 */
@Module
public class SupportActivityModule {

    @Provides
    SupportViewModel provideSupportViewModel(DataManager dataManager){
        return new SupportViewModel(dataManager);
    }

    @Provides
    List<SupportTopicResponse> provideSupportTopicEmptyList(){
        List<SupportTopicResponse> supportTopicResponses = new ArrayList<>();
        return supportTopicResponses ;
    }

    @Provides
    SupportAdapter provideSupportAdapter(Context context , List<SupportTopicResponse> supportTopicResponses){
        return new SupportAdapter(context , supportTopicResponses);
    }

    @Provides
    LinearLayoutManager provideLayoutManager(SupportActivity activity){
        LinearLayoutManager llm = new LinearLayoutManager(activity);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        return llm ;
    }

}
