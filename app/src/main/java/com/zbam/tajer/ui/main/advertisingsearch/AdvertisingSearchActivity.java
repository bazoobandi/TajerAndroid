package com.zbam.tajer.ui.main.advertisingsearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.zbam.tajer.BR;
import com.zbam.tajer.R;
import com.zbam.tajer.data.model.api.notification.NotificationData;
import com.zbam.tajer.databinding.ActivityAdvertisingSearchBinding;
import com.zbam.tajer.ui.base.BaseActivity;
import com.zbam.tajer.utils.AppConstants;
import com.zbam.tajer.utils.NumberFormatter;

import java.util.HashMap;

import javax.inject.Inject;

/**
 * Created by z.bazoubandi on 9/27/2018.
 */

public class AdvertisingSearchActivity extends BaseActivity<ActivityAdvertisingSearchBinding,AdvertisingSearchViewModel> implements
        SearchAdvertisingInterface{

    @Inject
    AdvertisingSearchViewModel mAdvertisingSearchViewModel;

    ActivityAdvertisingSearchBinding mActivityAdvertisingSearchBinding;
    public static SearchAdvertisingInterface searchAdvertisingInterface;
    boolean _ignore = false ;
    private long inputNumber = 0 ;
    HashMap<String,String> searchParams = new HashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityAdvertisingSearchBinding = getViewDataBinding();
        mAdvertisingSearchViewModel.setActivity(this);
        searchAdvertisingInterface = this;
        if(getIntent().hasExtra(AppConstants.REQ_KEY_PARAMETR_ITEMS_SEARCH)){
            searchParams = (HashMap<String, String>) getIntent().getSerializableExtra(AppConstants.REQ_KEY_PARAMETR_ITEMS_SEARCH);
            fillXmlData();
        }
        init();
    }

    public void fillXmlData()
    {
        try {
            mActivityAdvertisingSearchBinding.etxAdvertisingTitleValue.setText(searchParams.get(AppConstants.REQ_KEY_TITLE_SEARCH));
            mActivityAdvertisingSearchBinding.etxAdvertisingPriceMinValue.setText(searchParams.get(AppConstants.REQ_KEY_SEARCH_MIN_PRICE));
            mActivityAdvertisingSearchBinding.etxAdvertisingPriceMaxValue.setText(searchParams.get(AppConstants.REQ_KEY_SEARCH_MAX_PRICE));
            if (searchParams.get(AppConstants.REQ_KEY_SEARCH_HAVE_IMAGE) != null && searchParams.get(AppConstants.REQ_KEY_SEARCH_HAVE_IMAGE).equals("yes"))
                mActivityAdvertisingSearchBinding.swAdvertisingSearchWithImage.setChecked(true);
            if (searchParams.get(AppConstants.REQ_KEY_SEARCH_TYPE) != null && searchParams.get(AppConstants.REQ_KEY_SEARCH_TYPE).equals("premium")) {
                mActivityAdvertisingSearchBinding.swAdvertisingSearchImmediate.setChecked(true);
            }
            if (searchParams.get(AppConstants.REQ_KEY_SEARCH_TYPE) != null && searchParams.get(AppConstants.REQ_KEY_SEARCH_TYPE).equals("top")) {
                mActivityAdvertisingSearchBinding.swAdvertisingSearchAuction.setChecked(true);
            }

            if (searchParams.get(AppConstants.REQ_KEY_SEARCH_USE_TYPE) != null && searchParams.get(AppConstants.REQ_KEY_SEARCH_USE_TYPE).equals("all")) {
                mActivityAdvertisingSearchBinding.rgAdvertisingSearchAll.setChecked(true);
            } else if (searchParams.get(AppConstants.REQ_KEY_SEARCH_USE_TYPE) != null && searchParams.get(AppConstants.REQ_KEY_SEARCH_USE_TYPE).equals("old")) {
                mActivityAdvertisingSearchBinding.rgAdvertisingSearchOld.setChecked(true);
            } else if (searchParams.get(AppConstants.REQ_KEY_SEARCH_USE_TYPE) != null && searchParams.get(AppConstants.REQ_KEY_SEARCH_USE_TYPE).equals("new")) {
                mActivityAdvertisingSearchBinding.rgAdvertisingSearchNew.setChecked(true);
            }

            mActivityAdvertisingSearchBinding.txtAdvertisingSearchValue.setText(searchParams.get(AppConstants.REQ_KEY_CATEGORY_NAME_SEARCH));

            if (searchParams.get(AppConstants.REQ_KEY_SEARCH_PRICE_TYPE) != null)
                mActivityAdvertisingSearchBinding.spAdvertisingPriceType.setSelection(3);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void init()
    {
        try {
            String[] items = new String[]{"همه", "مقطوع(تومان)", "توافقی", "جهت معاوضه", "مجانی"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
            mActivityAdvertisingSearchBinding.spAdvertisingPriceType.setAdapter(adapter);

            mActivityAdvertisingSearchBinding.spAdvertisingPriceType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (id == 0 || id==1) {
                        mAdvertisingSearchViewModel.actPriceShow.set(true);
                    } else
                        mAdvertisingSearchViewModel.actPriceShow.set(false);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            //seprate min value
            mActivityAdvertisingSearchBinding.etxAdvertisingPriceMinValue.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (_ignore) {
                        return;
                    }
                    if (mActivityAdvertisingSearchBinding.etxAdvertisingPriceMinValue.getText().toString().isEmpty()) {
                        return;
                    }
                    _ignore = true;
                    inputNumber *= 10;
                    inputNumber += Integer.parseInt(String.valueOf(s.charAt(count - 1)));
                    _ignore = false;
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (_ignore) {
                        return;
                    }
                    _ignore = true;
                    mActivityAdvertisingSearchBinding.etxAdvertisingPriceMinValue.setText(NumberFormatter.format(inputNumber));
                    _ignore = false;
                }
            });

            mActivityAdvertisingSearchBinding.etxAdvertisingPriceMinValue.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        if (keyCode == KeyEvent.KEYCODE_DEL) {
                            _ignore = true;
                            inputNumber /= 10;
                            mActivityAdvertisingSearchBinding.etxAdvertisingPriceMinValue.setText(NumberFormatter.format(inputNumber));
                            _ignore = false;
                        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
                            onBackPressed();
                        }
                    }
                    return true;
                }
            });

            //separetor price max
            mActivityAdvertisingSearchBinding.etxAdvertisingPriceMaxValue.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (_ignore) {
                        return;
                    }
                    if (mActivityAdvertisingSearchBinding.etxAdvertisingPriceMaxValue.getText().toString().isEmpty()) {
                        return;
                    }
                    _ignore = true;
                    inputNumber *= 10;
                    inputNumber += Integer.parseInt(String.valueOf(s.charAt(count - 1)));
                    _ignore = false;
                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (_ignore) {
                        return;
                    }
                    _ignore = true;
                    mActivityAdvertisingSearchBinding.etxAdvertisingPriceMaxValue.setText(NumberFormatter.format(inputNumber));
                    _ignore = false;
                }
            });

            mActivityAdvertisingSearchBinding.etxAdvertisingPriceMaxValue.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        if (keyCode == KeyEvent.KEYCODE_DEL) {
                            _ignore = true;
                            inputNumber /= 10;
                            mActivityAdvertisingSearchBinding.etxAdvertisingPriceMaxValue.setText(NumberFormatter.format(inputNumber));
                            _ignore = false;
                        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
                            onBackPressed();
                        }
                    }
                    return true;
                }
            });
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void searchAdvertising()
    {
        HashMap<String,String> params= new HashMap<>();
        String searchTxt = mActivityAdvertisingSearchBinding.etxAdvertisingTitleValue.getText().toString();
        String useType = "";
        if(mActivityAdvertisingSearchBinding.rgAdvertisingSearchNew.isChecked())
        {
            useType = "new";
        }else if(mActivityAdvertisingSearchBinding.rgAdvertisingSearchOld.isChecked())
        {
            useType = "old";
        }else if(mActivityAdvertisingSearchBinding.rgAdvertisingSearchAll.isChecked())
        {
            useType = "all";
        }

        String type = "";
        if(mActivityAdvertisingSearchBinding.swAdvertisingSearchImmediate.isChecked())
        {
            type = "premium";
        }else if(mActivityAdvertisingSearchBinding.swAdvertisingSearchAuction.isChecked())
        {
            type = "top";
        }

        String haveImage = "";
        if(mActivityAdvertisingSearchBinding.swAdvertisingSearchWithImage.isChecked())
        {
            haveImage = "yes";
        }

        int position = Integer.valueOf(mActivityAdvertisingSearchBinding.spAdvertisingPriceType.getSelectedItemId()+"");

        params.put(AppConstants.REQ_KEY_SEARCH_HAVE_IMAGE, haveImage);
        params.put(AppConstants.REQ_KEY_SEARCH_TYPE, type);
        params.put(AppConstants.REQ_KEY_SEARCH_USE_TYPE, useType);
        params.put(AppConstants.REQ_KEY_CATEGORY_SEARCH,mAdvertisingSearchViewModel.categoryId.get().toString());
        params.put(AppConstants.REQ_KEY_CATEGORY_NAME_SEARCH,mAdvertisingSearchViewModel.categoryName.get().toString());
        params.put(AppConstants.REQ_KEY_TITLE_SEARCH,searchTxt);
        params.put(AppConstants.REQ_KEY_SEARCH_PRICE_TYPE, mActivityAdvertisingSearchBinding.spAdvertisingPriceType.getSelectedItemId()+"");
        params.put(AppConstants.REQ_KEY_SEARCH_MIN_PRICE, mActivityAdvertisingSearchBinding.etxAdvertisingPriceMinValue.getText().toString());
        params.put(AppConstants.REQ_KEY_SEARCH_MAX_PRICE, mActivityAdvertisingSearchBinding.etxAdvertisingPriceMaxValue.getText().toString());
        openAdvertisingActivity(params);
    }

    @Override
    public void onNotificationReceived(NotificationData notificationData) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_advertising_search;
    }

    @Override
    public AdvertisingSearchViewModel getViewModel() {
        return mAdvertisingSearchViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public void onItemClickListener(String category,String categoryId,String categorySubject,String categoryType) {
        mAdvertisingSearchViewModel.categoryName.set(category);
        mAdvertisingSearchViewModel.categoryId.set(categoryId);

        if(categoryType.equals("kala"))
        {
            mAdvertisingSearchViewModel.fieldShowBycategory.set(true);
        }else if(categoryType.equals("intro"))
            mAdvertisingSearchViewModel.fieldShowBycategory.set(false);
    }
}