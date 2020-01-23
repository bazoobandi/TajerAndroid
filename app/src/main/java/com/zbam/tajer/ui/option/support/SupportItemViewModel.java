package com.zbam.tajer.ui.option.support;

import android.databinding.ObservableField;
import com.zbam.tajer.data.model.api.response.SupportTopicResponse;


/**
 * Created by zb on 2/14/2018.
 */

public class SupportItemViewModel {

    public ObservableField<String> title = new ObservableField<>("");

    public SupportItemViewModel(SupportTopicResponse topic){
        this.title.set(topic.getText());
    }
}
