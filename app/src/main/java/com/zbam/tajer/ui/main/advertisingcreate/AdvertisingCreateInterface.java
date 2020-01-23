package com.zbam.tajer.ui.main.advertisingcreate;

import com.zbam.tajer.data.model.api.response.CategoryResponse;

/**
 * Created by z.bazoubandi on 9/3/2018.
 */

public interface AdvertisingCreateInterface {
    void onItemClickListener(String category,String categoryId,String categorySubject,String categoryType);
    void onItemCityClickListener(String city,String cityId);
    void onItemCityByJobClickListener(String city,String cityId);
    void onItemCityByTripClickListener(String city,String cityId);
    void onItemCarTypeClickListener(String car,String craId);
    void onItemMobileTypeClickListener(String mobileBrand,String mobileId);
}
