package com.zbam.tajer.ui.main.advertisingcreate;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.zbam.tajer.BR;
import com.zbam.tajer.R;
import com.zbam.tajer.data.model.api.AdvertisingRequest;
import com.zbam.tajer.data.model.api.notification.NotificationData;
import com.zbam.tajer.data.model.api.response.ParametersListResponse;
import com.zbam.tajer.databinding.ActivityAdvertisingCreateBinding;
import com.zbam.tajer.ui.base.BaseActivity;
import com.zbam.tajer.ui.pemission.PermissionsActivity;
import com.zbam.tajer.ui.pemission.PermissionsChecker;
import com.zbam.tajer.utils.AppConstants;
import com.zbam.tajer.utils.CommonUtils;
import com.zbam.tajer.utils.NumberFormatter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by z.bazoubandi on 9/3/2018.
 */

public class AdvertisingCreateActivity extends BaseActivity<ActivityAdvertisingCreateBinding,AdvertisingCreateViewModel>
        implements AdvertisingCreateInterface
{

//    @Inject
//    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;


    @Inject
    AdvertisingCreateViewModel mAdvertisingCreateViewModel;

    @Inject
    AdvertisingRequest advertisingRequest;



    ActivityAdvertisingCreateBinding mActivityAdvertisingCreateBinding;

    public static AdvertisingCreateInterface createInterface;
    public static final int PICK_IMAGE = 1;
    Bitmap bitmap;
    boolean _ignore = false ;
    private long inputNumber = 0 ;
    PermissionsChecker checker;
    ProgressDialog progressDialog;

    /**
     * Permission List
     */
    private static final String[] PERMISSIONS_READ_STORAGE = new String[]
            {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityAdvertisingCreateBinding = getViewDataBinding();
        mAdvertisingCreateViewModel.setActivity(this);
        createInterface =this;
        init();
        initImage();

       // getAdvertisingCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void initImage()
    {
        /**
         * Permission Checker Initialized
         */
        checker = new PermissionsChecker(this);
    }

    public void initCar()
    {
        try{
            callCarFetch();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void initHCar()
    {
        try{
            callHCarFetch();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void initbicycle()
    {
        try {
            callBicycle();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void initMotor()
    {
        try{
            callMotor();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void initFurniture()
        {
            try{
                callFurniture();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

     public void initAppliance(String categoryId)
            {
                try{
                    callAppliance(categoryId);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
    public void initTripp(String categoryId)
                {
                    try{
                        callTripp(categoryId);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }

    public void initAccessories(String categoryId)
    {
        try {
            callAccessories(categoryId);
        }catch (Exception e)
    {
        e.printStackTrace();
    }
    }

    public void initProductType(String categoryId)
    {
        try {
            callProductType(categoryId);
        }catch (Exception e)
    {
        e.printStackTrace();
    }
    }

    public void initMobile()
    {
        try {
            callMobile();
        }catch (Exception e)
    {
        e.printStackTrace();
    }
    }

    public void initEstate(String categoryId)
    {
        try {
            callEstate(categoryId);
        }catch (Exception e)
    {
        e.printStackTrace();
    }
    }

    public void fillColorCar(List<ParametersListResponse> colorListResponses)
    {
        try{
            if(colorListResponses!=null) {
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, colorListResponses);
                mActivityAdvertisingCreateBinding.icAdvertisingCar.spAdvertisingCarColor.setAdapter(adapter);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void fillFualTypeCar(List<ParametersListResponse> fualTypeListResponses)
    {
        try{
            if(fualTypeListResponses!=null) {
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, fualTypeListResponses);
                mActivityAdvertisingCreateBinding.icAdvertisingCar.spAdvertisingCarFualType.setAdapter(adapter);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void fillBodyStatusCar(List<ParametersListResponse> bodyStatusListResponses)
    {
        try{
            if(bodyStatusListResponses!=null) {
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, bodyStatusListResponses);
                mActivityAdvertisingCreateBinding.icAdvertisingCar.spAdvertisingCarBodyStatus.setAdapter(adapter);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void fillTransmissionCar(List<ParametersListResponse> transmissionListResponses)
    {
        try{
            if(transmissionListResponses!=null) {
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, transmissionListResponses);
            mActivityAdvertisingCreateBinding.icAdvertisingCar.spAdvertisingCarTransmission.setAdapter(adapter);}
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void fillPlaqueList(List<ParametersListResponse> plaqueListResponses)
    {
        try{
            if(plaqueListResponses!=null) {
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, plaqueListResponses);
                mActivityAdvertisingCreateBinding.icAdvertisingCar.spAdvertisingCarPlaque.setAdapter(adapter);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void fillProducYear(List<ParametersListResponse> producYearListResponses)
    {
        try{
            if(producYearListResponses!=null) {
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, producYearListResponses);
                mActivityAdvertisingCreateBinding.icAdvertisingCar.spAdvertisingCarProducYear.setAdapter(adapter);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void fillInsideColor(List<ParametersListResponse> insideColorListResponses)
    {
        try{
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, insideColorListResponses);
            mActivityAdvertisingCreateBinding.icAdvertisingCar.spAdvertisingCarInsideColor.setAdapter(adapter);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void fillhCarproducYear(List<ParametersListResponse> hCarproducYearList)
    {
        try {
            if (hCarproducYearList != null) {
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, hCarproducYearList);
                mActivityAdvertisingCreateBinding.icAdvertisingHcar.spAdvertisingHcarProducYear.setAdapter(adapter);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void fillhBrandList(List<ParametersListResponse> hhBrandList)
    {
        try{
            if(hhBrandList!=null)
            {
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, hhBrandList);
                mActivityAdvertisingCreateBinding.icAdvertisingHcar.spAdvertisingHcarBrand.setAdapter(adapter);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public void fillhCartypeList(List<ParametersListResponse> hCartypeList)
    {
        try{
            if(hCartypeList!=null) {
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, hCartypeList);
                mActivityAdvertisingCreateBinding.icAdvertisingHcar.spAdvertisingHcarTypeName.setAdapter(adapter);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void fillBicycleProducYear(List<ParametersListResponse> bicycleProducYearList)
    {
        try{
            if(bicycleProducYearList!=null) {
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, bicycleProducYearList);
                mActivityAdvertisingCreateBinding.icAdvertisingBicycle.spAdvertisingBicycleProducYear.setAdapter(adapter);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void fillBicycleBrandList(List<ParametersListResponse> bicycleBrandList)
    {
        try{
            if(bicycleBrandList!=null) {
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, bicycleBrandList);
                mActivityAdvertisingCreateBinding.icAdvertisingBicycle.spAdvertisingBicycleBrandName.setAdapter(adapter);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void fillBicycleTypeList(List<ParametersListResponse> bicycleTypeList)
    {
        try {
            if (bicycleTypeList != null) {
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, bicycleTypeList);
                mActivityAdvertisingCreateBinding.icAdvertisingBicycle.spAdvertisingBicycleType.setAdapter(adapter);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public void fillBicycleColorList(List<ParametersListResponse> bicycleColorList)
    {
        if(bicycleColorList!=null) {
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, bicycleColorList);
            mActivityAdvertisingCreateBinding.icAdvertisingBicycle.spAdvertisingBicycleColor.setAdapter(adapter);
        }
    }
    public void fillBicycleBySexList(List<ParametersListResponse> bicycleBySexList)
    {
        try{
            if(bicycleBySexList!=null) {
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, bicycleBySexList);
                mActivityAdvertisingCreateBinding.icAdvertisingBicycle.spAdvertisingBicycleBySexName.setAdapter(adapter);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void fillBicycleMaterialList(List<ParametersListResponse> bicycleMaterialList)
    {
        try{
            if(bicycleMaterialList!=null) {
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, bicycleMaterialList);
                mActivityAdvertisingCreateBinding.icAdvertisingBicycle.spAdvertisingBicycleMaterialName.setAdapter(adapter);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void fillBicycleStopTypeList(List<ParametersListResponse> bicycleStopTypeList)
    {
        try{
            if(bicycleStopTypeList!=null)
            {
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, bicycleStopTypeList);
                mActivityAdvertisingCreateBinding.icAdvertisingBicycle.spAdvertisingBicycleStopTypeName.setAdapter(adapter);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public void fillBicycleBySizeList(List<ParametersListResponse> bicycleBySizeList)
    {
        try{
            if(bicycleBySizeList!=null) {
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, bicycleBySizeList);
                mActivityAdvertisingCreateBinding.icAdvertisingBicycle.spAdvertisingBicycleBySizeName.setAdapter(adapter);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void fillMotorProductYearList(List<ParametersListResponse> motorProductYearList)
    {
        try{
            if(motorProductYearList!=null) {
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, motorProductYearList);
                mActivityAdvertisingCreateBinding.icAdvertisingMotor.spAdvertisingMotorProductYear.setAdapter(adapter);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void fillMotorBrandList(List<ParametersListResponse> motorBrandList)
    {
        try{
            if(motorBrandList!=null) {
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, motorBrandList);
                mActivityAdvertisingCreateBinding.icAdvertisingMotor.spAdvertisingMotorBrandName.setAdapter(adapter);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void fillAccessoriesTypeList(List<ParametersListResponse> accessoriesTypeList)
    {
        try{
            if(accessoriesTypeList!=null)
            {
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, accessoriesTypeList);
                mActivityAdvertisingCreateBinding.spAdvertisingCreateAccessories.setAdapter(adapter);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void fillProductTypeList(List<ParametersListResponse> productTypeList)
    {
        try{
            if(productTypeList!=null)
            {
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, productTypeList);
                mActivityAdvertisingCreateBinding.spAdvertisingCreateProductType.setAdapter(adapter);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    //mobile
    public void fillOsNameList(List<ParametersListResponse> osNameList)
    {
        try{
            if(osNameList!=null)
            {
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, osNameList);
                mActivityAdvertisingCreateBinding.icAdvertisingMobile.spAdvertisingMobileOsName.setAdapter(adapter);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public void fillSimsNumber()
    {
        try{
            String[] SimsNumbers = new String[]{"تک سیم کارت", "دو سیم کارت"};
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, SimsNumbers);
            mActivityAdvertisingCreateBinding.icAdvertisingMobile.spAdvertisingMobileSimsNumber.setAdapter(adapter);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    //tablet
    public void fillTabletBarndList(List<ParametersListResponse> tabletBarndList)
    {
        try{
            if(tabletBarndList!=null)
            {
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tabletBarndList);
                mActivityAdvertisingCreateBinding.icAdvertisingMobile.spAdvertisingMobileTabletBrand.setAdapter(adapter);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    //furniture
    public void fillFurnitureConditionList(List<ParametersListResponse> furnitureConditionList)
    {
        try{
            if(furnitureConditionList!=null)
            {
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, furnitureConditionList);
                mActivityAdvertisingCreateBinding.icAdvertisingFurniture.spAdvertisingFurnitureConditionName.setAdapter(adapter);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void fillFurnitureAgeList(List<ParametersListResponse> furnitureAgeList)
    {
        try{
            if(furnitureAgeList!=null)
            {
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, furnitureAgeList);
                mActivityAdvertisingCreateBinding.icAdvertisingFurniture.spAdvertisingFurnitureAgeName.setAdapter(adapter);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    //appliance
    public void fillApplianceBrandList(List<ParametersListResponse> applianceBrandList)
    {
        try{
            if(applianceBrandList!=null)
            {
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, applianceBrandList);
                mActivityAdvertisingCreateBinding.icAdvertisingAppliance.spAdvertisingApplianceBrandName.setAdapter(adapter);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void fillApplianceTypeList(List<ParametersListResponse> applianceTypeList)
    {
        try{
            if(applianceTypeList!=null)
            {
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, applianceTypeList);
                mActivityAdvertisingCreateBinding.icAdvertisingAppliance.spAdvertisingApplianceTypeName.setAdapter(adapter);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void fillApplianceColorList(List<ParametersListResponse> applianceColorList)
    {
        try{
            if(applianceColorList!=null)
            {
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, applianceColorList);
                mActivityAdvertisingCreateBinding.icAdvertisingAppliance.spAdvertisingApplianceColor.setAdapter(adapter);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //trip
    public void fillTripTypeList(List<ParametersListResponse> tripTypeList)
        {
            try{
                if(tripTypeList!=null)
                {
                    ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tripTypeList);
                    mActivityAdvertisingCreateBinding.icAdvertisingTrip.spAdvertisingTripTypeName.setAdapter(adapter);
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        public void fillTripTransportTypeList(List<ParametersListResponse> tripTransportTypeList)
        {
            try{
                if(tripTransportTypeList!=null)
                {
                    ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tripTransportTypeList);
                    mActivityAdvertisingCreateBinding.icAdvertisingTrip.spAdvertisingTripTransportType.setAdapter(adapter);
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        public void fillTripSubjectTypeList(List<ParametersListResponse> tripSubjectTypeList)
        {
            try{
                if(tripSubjectTypeList!=null)
                {
                    ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, tripSubjectTypeList);
                    mActivityAdvertisingCreateBinding.icAdvertisingTrip.spAdvertisingTripSubjectType.setAdapter(adapter);
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        public void fillEstateRoomNumberList(List<ParametersListResponse> roomNumberList)
        {
            try{
                if(roomNumberList!=null)
                {
                    ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roomNumberList);
                    mActivityAdvertisingCreateBinding.icAdvertisingEstate.spAdvertisingEstateRoomNumber.setAdapter(adapter);
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        public void fillEstatefacingList(List<ParametersListResponse> facingList)
        {
            try{
                if(facingList!=null)
                {
                    ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, facingList);
                    mActivityAdvertisingCreateBinding.icAdvertisingEstate.spAdvertisingEstateFacingName.setAdapter(adapter);
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        public void fillEstateFloorCoveringList(List<ParametersListResponse> floorCoveringList)
        {
            try{
                if(floorCoveringList!=null)
                {
                    ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, floorCoveringList);
                    mActivityAdvertisingCreateBinding.icAdvertisingEstate.spAdvertisingEstateFloorCoveringName.setAdapter(adapter);
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        public void fillEstateCabinetList(List<ParametersListResponse> cabinetList)
        {
            try{
                if(cabinetList!=null)
                {
                    ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, cabinetList);
                    mActivityAdvertisingCreateBinding.icAdvertisingEstate.spAdvertisingEstateCabinetName.setAdapter(adapter);
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        public void fillEstateWcList(List<ParametersListResponse> wcList)
        {
            try{
                if(wcList!=null)
                {
                    ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, wcList);
                    mActivityAdvertisingCreateBinding.icAdvertisingEstate.spAdvertisingEstateWcName.setAdapter(adapter);
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        public void fillEstatHeatingList(List<ParametersListResponse> heatingList)
                {
                    try{
                        if(heatingList!=null)
                        {
                            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, heatingList);
                            mActivityAdvertisingCreateBinding.icAdvertisingEstate.spAdvertisingEstateHeatingName.setAdapter(adapter);
                        }
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
             public void fillEstatCoolingList(List<ParametersListResponse> coolingList)
                {
                    try{
                        if(coolingList!=null)
                        {
                            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, coolingList);
                            mActivityAdvertisingCreateBinding.icAdvertisingEstate.spAdvertisingEstateCoolingName.setAdapter(adapter);
                        }
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                public void fillEstatViewList(List<ParametersListResponse> viewList)
                {
                    try{
                        if(viewList!=null)
                        {
                            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, viewList);
                            mActivityAdvertisingCreateBinding.icAdvertisingEstate.spAdvertisingEstateViewName.setAdapter(adapter);
                        }
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                public void fillEstatAgeList(List<ParametersListResponse> ageList)
                {
                    try{
                        if(ageList!=null)
                        {
                            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ageList);
                            mActivityAdvertisingCreateBinding.icAdvertisingEstate.spAdvertisingEstateAgeName.setAdapter(adapter);
                        }
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }

    public void init()
    {
        try {
            String[] items = new String[]{"مقطوع(تومان)", "توافقی","جهت معاوضه","مجانی"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
            mActivityAdvertisingCreateBinding.spAdvertisingPriceType.setAdapter(adapter);

            mActivityAdvertisingCreateBinding.spAdvertisingPriceType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(id==0)
                    {
                        mAdvertisingCreateViewModel.actPriceShow.set(true);
                    }else
                        mAdvertisingCreateViewModel.actPriceShow.set(false);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            String[] jobItems = new String[]{"تمام وقت", "ساعتی","پاره وقت","پروژه ای","پورسانتی","کار در منزل"};
            ArrayAdapter<String> jobAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, jobItems);
            mActivityAdvertisingCreateBinding.icAdvertisingJob.spAdvertisingJobTypeName.setAdapter(jobAdapter);

            mActivityAdvertisingCreateBinding.icAdvertisingJob.spAdvertisingJobTypeName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(id==0)
                    {
                        advertisingRequest.setJobTypeId("89");
                    }else if(id==1)
                    {
                        advertisingRequest.setJobTypeId("90");
                    }else if(id==2)
                    {
                        advertisingRequest.setJobTypeId("91");
                    }else if(id==3)
                    {
                        advertisingRequest.setJobTypeId("92");
                    }else if(id==4)
                    {
                        advertisingRequest.setJobTypeId("-9");
                    }else if(id==5)
                    {
                        advertisingRequest.setJobTypeId("93");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            //color
            mActivityAdvertisingCreateBinding.icAdvertisingCar.spAdvertisingCarColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingCar.spAdvertisingCarColor.getSelectedItem();
                    String colorId = st.getParameterId();
                    advertisingRequest.setColorId(colorId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //fualType
            mActivityAdvertisingCreateBinding.icAdvertisingCar.spAdvertisingCarFualType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingCar.spAdvertisingCarFualType.getSelectedItem();
                    String fualTypeId = st.getParameterId();
                    advertisingRequest.setFualTypeId(fualTypeId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //BodyStatus
            mActivityAdvertisingCreateBinding.icAdvertisingCar.spAdvertisingCarBodyStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingCar.spAdvertisingCarBodyStatus.getSelectedItem();
                    String bodyStatusId = st.getParameterId();
                    advertisingRequest.setBodyStatusId(bodyStatusId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //transmission
            mActivityAdvertisingCreateBinding.icAdvertisingCar.spAdvertisingCarTransmission.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingCar.spAdvertisingCarTransmission.getSelectedItem();
                    String transmissionId = st.getParameterId();
                    advertisingRequest.setTransmissionId(transmissionId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //plaqueId
            mActivityAdvertisingCreateBinding.icAdvertisingCar.spAdvertisingCarPlaque.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingCar.spAdvertisingCarPlaque.getSelectedItem();
                    String plaqueId = st.getParameterId();
                    advertisingRequest.setPlaqueId(plaqueId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //producYear
            mActivityAdvertisingCreateBinding.icAdvertisingCar.spAdvertisingCarProducYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingCar.spAdvertisingCarProducYear.getSelectedItem();
                    String producYearId = st.getParameterId();
                    advertisingRequest.setProducYearId(producYearId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            //InsideColor
            mActivityAdvertisingCreateBinding.icAdvertisingCar.spAdvertisingCarInsideColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingCar.spAdvertisingCarInsideColor.getSelectedItem();
                    String insideColorId = st.getParameterId();
                    advertisingRequest.setInsideColorId(insideColorId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //hcarProducYearId
            mActivityAdvertisingCreateBinding.icAdvertisingHcar.spAdvertisingHcarProducYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingHcar.spAdvertisingHcarProducYear.getSelectedItem();
                    String hcarProducYearId = st.getParameterId();
                    advertisingRequest.setHcarProducYearId(hcarProducYearId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //hHcarTypeId
            mActivityAdvertisingCreateBinding.icAdvertisingHcar.spAdvertisingHcarTypeName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingHcar.spAdvertisingHcarTypeName.getSelectedItem();
                    String hHcarTypeId = st.getParameterId();
                    advertisingRequest.setHcarTypeId(hHcarTypeId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //hHcarTypeId
            mActivityAdvertisingCreateBinding.icAdvertisingHcar.spAdvertisingHcarBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingHcar.spAdvertisingHcarBrand.getSelectedItem();
                    String hcarBrandId = st.getParameterId();
                    advertisingRequest.setHcarBrandId(hcarBrandId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //bicycleProducYearId
            mActivityAdvertisingCreateBinding.icAdvertisingBicycle.spAdvertisingBicycleProducYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingBicycle.spAdvertisingBicycleProducYear.getSelectedItem();
                    String bicycleProducYearId = st.getParameterId();
                    advertisingRequest.setBicycleProducYearId(bicycleProducYearId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //bicycleProducYearId
            mActivityAdvertisingCreateBinding.icAdvertisingBicycle.spAdvertisingBicycleBrandName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingBicycle.spAdvertisingBicycleBrandName.getSelectedItem();
                    String bicycleBrandId = st.getParameterId();
                    advertisingRequest.setBicycleBrandId(bicycleBrandId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //bicycleTypeId
            mActivityAdvertisingCreateBinding.icAdvertisingBicycle.spAdvertisingBicycleType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingBicycle.spAdvertisingBicycleType.getSelectedItem();
                    String bicycleTypeId = st.getParameterId();
                    advertisingRequest.setBicycleTypeId(bicycleTypeId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //bicycleColorId
            mActivityAdvertisingCreateBinding.icAdvertisingBicycle.spAdvertisingBicycleColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingBicycle.spAdvertisingBicycleColor.getSelectedItem();
                    String bicycleColorId = st.getParameterId();
                    advertisingRequest.setBicycleColorId(bicycleColorId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //bicycleBySexNameId
            mActivityAdvertisingCreateBinding.icAdvertisingBicycle.spAdvertisingBicycleBySexName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingBicycle.spAdvertisingBicycleBySexName.getSelectedItem();
                    String bicycleBySexNameId = st.getParameterId();
                    advertisingRequest.setBicycleBySexNameId(bicycleBySexNameId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //bicycleMaterialId
            mActivityAdvertisingCreateBinding.icAdvertisingBicycle.spAdvertisingBicycleMaterialName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingBicycle.spAdvertisingBicycleMaterialName.getSelectedItem();
                    String bicycleMaterialId = st.getParameterId();
                    advertisingRequest.setBicycleMaterialId(bicycleMaterialId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //bicycleStopId
            mActivityAdvertisingCreateBinding.icAdvertisingBicycle.spAdvertisingBicycleStopTypeName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingBicycle.spAdvertisingBicycleStopTypeName.getSelectedItem();
                    String bicycleStopId = st.getParameterId();
                    advertisingRequest.setBicycleStopTypeId(bicycleStopId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //bicycleSizeId
            mActivityAdvertisingCreateBinding.icAdvertisingBicycle.spAdvertisingBicycleBySizeName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingBicycle.spAdvertisingBicycleBySizeName.getSelectedItem();
                    String bicycleSizeId = st.getParameterId();
                    advertisingRequest.setBicycleSizeId(bicycleSizeId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //motorProductYearId
            mActivityAdvertisingCreateBinding.icAdvertisingMotor.spAdvertisingMotorProductYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingMotor.spAdvertisingMotorProductYear.getSelectedItem();
                    String motorProductYearId = st.getParameterId();
                    advertisingRequest.setMotorProductYearId(motorProductYearId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //motorBrandId
            mActivityAdvertisingCreateBinding.icAdvertisingMotor.spAdvertisingMotorBrandName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingMotor.spAdvertisingMotorBrandName.getSelectedItem();
                    String motorBrandId = st.getParameterId();
                    advertisingRequest.setMotorBrandId(motorBrandId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            //accessoriesId
            mActivityAdvertisingCreateBinding.spAdvertisingCreateAccessories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.spAdvertisingCreateAccessories.getSelectedItem();
                    String accessoriesId = st.getParameterId();
                    advertisingRequest.setAccessoriesId(accessoriesId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //accessoriesId
            mActivityAdvertisingCreateBinding.spAdvertisingCreateProductType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.spAdvertisingCreateProductType.getSelectedItem();
                    String productTypeId = st.getParameterId();
                    advertisingRequest.setProductTypeId(productTypeId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //mobile
            mActivityAdvertisingCreateBinding.icAdvertisingMobile.spAdvertisingMobileOsName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingMobile.spAdvertisingMobileOsName.getSelectedItem();
                    String mobileOsId = st.getParameterId();
                    advertisingRequest.setMobileOsId(mobileOsId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            mActivityAdvertisingCreateBinding.icAdvertisingMobile.spAdvertisingMobileSimsNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    String mobileSimsNumberId="";
                    if(id==0)
                    {
                        mobileSimsNumberId= "1";
                        advertisingRequest.setMobileSimsNumberId(mobileSimsNumberId);
                    }else if(id==1)
                    {
                        mobileSimsNumberId= "2";
                        advertisingRequest.setMobileSimsNumberId(mobileSimsNumberId);
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //tablet
            mActivityAdvertisingCreateBinding.icAdvertisingMobile.spAdvertisingMobileTabletBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingMobile.spAdvertisingMobileTabletBrand.getSelectedItem();
                    String tabletBrandId = st.getParameterId();
                    advertisingRequest.setTabletBrandId(tabletBrandId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //furniture
            mActivityAdvertisingCreateBinding.icAdvertisingFurniture.spAdvertisingFurnitureConditionName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingFurniture.spAdvertisingFurnitureConditionName.getSelectedItem();
                    String furnitureConditionId = st.getParameterId();
                    advertisingRequest.setFurnitureConditionId(furnitureConditionId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            mActivityAdvertisingCreateBinding.icAdvertisingFurniture.spAdvertisingFurnitureAgeName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingFurniture.spAdvertisingFurnitureAgeName.getSelectedItem();
                    String furnitureAgeId = st.getParameterId();
                    advertisingRequest.setFurnitureAgeId(furnitureAgeId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //Appliance
            mActivityAdvertisingCreateBinding.icAdvertisingAppliance.spAdvertisingApplianceColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingAppliance.spAdvertisingApplianceColor.getSelectedItem();
                    String applianceColorId = st.getParameterId();
                    advertisingRequest.setApplianceColorId(applianceColorId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            mActivityAdvertisingCreateBinding.icAdvertisingAppliance.spAdvertisingApplianceTypeName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingAppliance.spAdvertisingApplianceTypeName.getSelectedItem();
                    String applianceTypeId = st.getParameterId();
                    advertisingRequest.setApplianceTypeId(applianceTypeId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            mActivityAdvertisingCreateBinding.icAdvertisingAppliance.spAdvertisingApplianceBrandName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingAppliance.spAdvertisingApplianceBrandName.getSelectedItem();
                    String applianceBrandId = st.getParameterId();
                    advertisingRequest.setApplianceBrandId(applianceBrandId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //trip
            mActivityAdvertisingCreateBinding.icAdvertisingTrip.spAdvertisingTripSubjectType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingTrip.spAdvertisingTripSubjectType.getSelectedItem();
                    String tripSubjectTypeId = st.getParameterId();
                    advertisingRequest.setTripSubjectTypeId(tripSubjectTypeId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            mActivityAdvertisingCreateBinding.icAdvertisingTrip.spAdvertisingTripTransportType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingTrip.spAdvertisingTripTransportType.getSelectedItem();
                    String tripTransporTypeId = st.getParameterId();
                    advertisingRequest.setTripTransportTypId(tripTransporTypeId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            mActivityAdvertisingCreateBinding.icAdvertisingTrip.spAdvertisingTripTypeName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingTrip.spAdvertisingTripTypeName.getSelectedItem();
                    String tripTypeId = st.getParameterId();
                    advertisingRequest.setTripTypeId(tripTypeId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //estate
            mActivityAdvertisingCreateBinding.icAdvertisingEstate.spAdvertisingEstateFacingName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingEstate.spAdvertisingEstateFacingName.getSelectedItem();
                    String estateFacingId = st.getParameterId();
                    advertisingRequest.setEstateFacingId(estateFacingId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            mActivityAdvertisingCreateBinding.icAdvertisingEstate.spAdvertisingEstateRoomNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingEstate.spAdvertisingEstateRoomNumber.getSelectedItem();
                    String estateRoomNumberId = st.getParameterId();
                    advertisingRequest.setEstateRoomNumberId(estateRoomNumberId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            mActivityAdvertisingCreateBinding.icAdvertisingEstate.spAdvertisingEstateFloorCoveringName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingEstate.spAdvertisingEstateFloorCoveringName.getSelectedItem();
                    String estateFloorCoveringId = st.getParameterId();
                    advertisingRequest.setEstateFloorCoveringId(estateFloorCoveringId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            mActivityAdvertisingCreateBinding.icAdvertisingEstate.spAdvertisingEstateCabinetName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingEstate.spAdvertisingEstateCabinetName.getSelectedItem();
                    String estateCabinetId = st.getParameterId();
                    advertisingRequest.setEstateCabinetId(estateCabinetId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            mActivityAdvertisingCreateBinding.icAdvertisingEstate.spAdvertisingEstateWcName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingEstate.spAdvertisingEstateWcName.getSelectedItem();
                    String estateWcId = st.getParameterId();
                    advertisingRequest.setEstateWcId(estateWcId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            mActivityAdvertisingCreateBinding.icAdvertisingEstate.spAdvertisingEstateHeatingName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingEstate.spAdvertisingEstateHeatingName.getSelectedItem();
                    String estateHeatingId = st.getParameterId();
                    advertisingRequest.setEstateHeatingId(estateHeatingId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            mActivityAdvertisingCreateBinding.icAdvertisingEstate.spAdvertisingEstateCoolingName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingEstate.spAdvertisingEstateCoolingName.getSelectedItem();
                    String estateCoolingId = st.getParameterId();
                    advertisingRequest.setEstateCoolingId(estateCoolingId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            mActivityAdvertisingCreateBinding.icAdvertisingEstate.spAdvertisingEstateViewName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingEstate.spAdvertisingEstateViewName.getSelectedItem();
                    String estateViewId = st.getParameterId();
                    advertisingRequest.setEstateViewId(estateViewId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            mActivityAdvertisingCreateBinding.icAdvertisingEstate.spAdvertisingEstateAgeName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ParametersListResponse st = (ParametersListResponse)mActivityAdvertisingCreateBinding.icAdvertisingEstate.spAdvertisingEstateAgeName.getSelectedItem();
                    String estateAgeId = st.getParameterId();
                    advertisingRequest.setEstateAgeId(estateAgeId);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            mActivityAdvertisingCreateBinding.etxAdvertisingPriceValue.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (_ignore) {
                        return;
                    }
                    if (mActivityAdvertisingCreateBinding.etxAdvertisingPriceValue.getText().toString().isEmpty()) {
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
                    mActivityAdvertisingCreateBinding.etxAdvertisingPriceValue.setText(NumberFormatter.format(inputNumber));
                    _ignore = false;
                }
            });

            mActivityAdvertisingCreateBinding.etxAdvertisingPriceValue.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        if (keyCode == KeyEvent.KEYCODE_DEL) {
                            _ignore = true;
                            inputNumber /= 10;
                            mActivityAdvertisingCreateBinding.etxAdvertisingPriceValue.setText(NumberFormatter.format(inputNumber));
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



    ///callWebService
    public void callCarFetch()
    {
        try{
            HashMap<String,String> map = new HashMap<>();
            mAdvertisingCreateViewModel.callCarFetch(this,map,iApiCall);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void callHCarFetch()
    {
        try{
            HashMap<String,String> map = new HashMap<>();
            mAdvertisingCreateViewModel.callHCarFetch(this,map,iApiCall);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void callBicycle()
    {
        try{
            HashMap<String,String> map = new HashMap<>();
            mAdvertisingCreateViewModel.callBicycle(this,map,iApiCall);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void callMotor()
    {
        try{
            HashMap<String,String> map = new HashMap<>();
            mAdvertisingCreateViewModel.callMotor(this,map,iApiCall);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void callFurniture()
    {
        try{
            HashMap<String,String> map = new HashMap<>();
            mAdvertisingCreateViewModel.callFurniture(this,map,iApiCall);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void callMobile()
    {
        try{
            HashMap<String,String> map = new HashMap<>();
            mAdvertisingCreateViewModel.callMobile(this,map,iApiCall);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void callAppliance(String categoryId)
    {
        try{
            HashMap<String,String> map = new HashMap<>();
            map.put(AppConstants.REQ_KEY_CATEGORY_SEARCH,categoryId);
            mAdvertisingCreateViewModel.callAppliance(this,map,iApiCall);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void callTripp(String categoryId)
    {
        try{
            HashMap<String,String> map = new HashMap<>();
            map.put(AppConstants.REQ_KEY_CATEGORY_SEARCH,categoryId);
            mAdvertisingCreateViewModel.callTripp(this,map,iApiCall);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void callAccessories(String categoryId)
    {
        try{
            HashMap<String,String> map = new HashMap<>();
            map.put(AppConstants.REQ_KEY_CATEGORY_SEARCH,categoryId);
            mAdvertisingCreateViewModel.callAccessories(this,map,iApiCall);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void callEstate(String categoryId)
    {
        try{
            HashMap<String,String> map = new HashMap<>();
            map.put(AppConstants.REQ_KEY_CATEGORY_SEARCH,categoryId);
            mAdvertisingCreateViewModel.callEstate(this,map,iApiCall);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public void callProductType(String categoryId)
    {
        try{
            HashMap<String,String> map = new HashMap<>();
            map.put(AppConstants.REQ_KEY_CATEGORY_SEARCH,categoryId);
            mAdvertisingCreateViewModel.callProductType(this,map,iApiCall);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void callAdvertisingCreate()
    {
        try {
        HashMap<String,String> map = new HashMap<>();
        map.put(AppConstants.REQ_KEY_CATEGORY_SEARCH, advertisingRequest.getCategoryId());
        map.put(AppConstants.REQ_KEY_TITLE_SEARCH, mActivityAdvertisingCreateBinding.etxAdvertisingTitleValue.getText().toString());

        String actType = "";
        if(mActivityAdvertisingCreateBinding.rgAdvertisingCreateCustomer.isChecked())
        {
            actType = "buyer";
        }else if(mActivityAdvertisingCreateBinding.rgAdvertisingCreateSeller.isChecked())
        {
            actType = "seller";
        }
        map.put(AppConstants.REQ_KEY_ACT_TYPE, actType);

        String useType = "";
        if(mActivityAdvertisingCreateBinding.rgAdvertisingCreateNew.isChecked())
        {
            useType = "new";
        }else if(mActivityAdvertisingCreateBinding.rgAdvertisingCreateOld.isChecked())
        {
            useType = "old";
        }
        map.put(AppConstants.REQ_KEY_USE_TYPE, useType);

        String userType = "";
        if(mActivityAdvertisingCreateBinding.rgAdvertisingCreateMainOwner.isChecked())
        {
            userType = "m";
        }else if(mActivityAdvertisingCreateBinding.rgAdvertisingCreateExpert.isChecked())
        {
            userType = "k";
        }
        map.put(AppConstants.REQ_KEY_USER_TYPE, userType);

        String type = "";
        if(mActivityAdvertisingCreateBinding.rgAdvertisingCreateImmediate.isChecked())
        {
            type = "premium";
        }else if(mActivityAdvertisingCreateBinding.rgAdvertisingCreateAuction.isChecked())
        {
            type = "top";
        }else if(mActivityAdvertisingCreateBinding.rgAdvertisingCreateFree.isChecked())
        {
            type = "free";
        }
        map.put(AppConstants.REQ_KEY_TYPE, type);

        String alert = "n";
        if(mActivityAdvertisingCreateBinding.swAdvertisingCreateSend.isChecked())
        {
            alert = "y";
        }

        map.put(AppConstants.REQ_KEY_CONTENT, mActivityAdvertisingCreateBinding.etxAdvertisingDescriptionValue.getText().toString());
        map.put(AppConstants.REQ_KEY_PRICE_TYPE, mActivityAdvertisingCreateBinding.spAdvertisingPriceType.getSelectedItemId()+"");
        map.put(AppConstants.REQ_KEY_PRICE, mActivityAdvertisingCreateBinding.etxAdvertisingPriceValue.getText().toString());
        map.put(AppConstants.REQ_KEY_NAME, mActivityAdvertisingCreateBinding.etxAdvertisingContactName.getText().toString());
        map.put(AppConstants.REQ_KEY_MOBILE, mActivityAdvertisingCreateBinding.etxAdvertisingContactMobile.getText().toString());
        map.put(AppConstants.REQ_KEY_TEL, mActivityAdvertisingCreateBinding.etxAdvertisingContactTel.getText().toString());
        map.put(AppConstants.REQ_KEY_EMAIL, mActivityAdvertisingCreateBinding.etxAdvertisingContactEmail.getText().toString());
        map.put(AppConstants.REQ_KEY_SEND_ALERT,alert );

        map.put(AppConstants.REQ_KEY_IMAGE1, mAdvertisingCreateViewModel.imgeUrlStr1.get().toString());
        map.put(AppConstants.REQ_KEY_IMAGE2, mAdvertisingCreateViewModel.imgeUrlStr2.get().toString());
        map.put(AppConstants.REQ_KEY_IMAGE3, mAdvertisingCreateViewModel.imgeUrlStr3.get().toString());
        map.put(AppConstants.REQ_KEY_IMAGE4, mAdvertisingCreateViewModel.imgeUrlStr4.get().toString());

        map.put(AppConstants.REQ_KEY_LOCATION, advertisingRequest.getCityId());


        //car
        if(advertisingRequest!=null && advertisingRequest.getCategorySubject()!=null && advertisingRequest.getCategorySubject().equals("carFetch"))
        {
            map.put(AppConstants.REQ_KEY_CAR_MODEL, advertisingRequest.getCarModelId());
            map.put(AppConstants.REQ_KEY_CAR_KMS_DRIVEN, mActivityAdvertisingCreateBinding.icAdvertisingCar.etAdvertisingCarKmsDriven.getText().toString());
            map.put(AppConstants.REQ_KEY_CAR_PRODUCT_YEAR,mActivityAdvertisingCreateBinding.icAdvertisingCar.spAdvertisingCarProducYear.getSelectedItem().toString());
            map.put(AppConstants.REQ_KEY_CAR_COLOR,advertisingRequest.getColorId());
            map.put(AppConstants.REQ_KEY_CAR_FUAL_TYPE,advertisingRequest.getFualTypeId());
            map.put(AppConstants.REQ_KEY_CAR_BODY_STATUS,advertisingRequest.getBodyStatusId());
            map.put(AppConstants.REQ_KEY_CAR_INSIDE_COLOR,advertisingRequest.getInsideColorId());
            map.put(AppConstants.REQ_KEY_CAR_TRANSMISSION,advertisingRequest.getTransmissionId());
            map.put(AppConstants.REQ_KEY_CAR_PLAQUE,advertisingRequest.getPlaqueId());
            map.put(AppConstants.REQ_KEY_CAR_INSURANCE_VALID_TELL,mActivityAdvertisingCreateBinding.icAdvertisingCar.etAdvertisingCarInsurance.getText().toString());
            map.put(AppConstants.REQ_KEY_CAR_INSURANCE_DISCOUNT,mActivityAdvertisingCreateBinding.icAdvertisingCar.etAdvertisingCarInsuranceDiscount.getText().toString());
        }

        //hcar
        if(advertisingRequest!=null && advertisingRequest.getCategorySubject()!=null && advertisingRequest.getCategorySubject().equals("hcarFetch"))
        {
            map.put(AppConstants.REQ_KEY_HCAR_PRODUCT_YEAR,mActivityAdvertisingCreateBinding.icAdvertisingHcar.spAdvertisingHcarProducYear.getSelectedItem().toString());
            map.put(AppConstants.REQ_KEY_HCAR_BRAND_NAME, advertisingRequest.getHcarBrandId());
            map.put(AppConstants.REQ_KEY_HCAR_TYPE_NAME, advertisingRequest.getHcarTypeId());
            map.put(AppConstants.REQ_KEY_HCAR_KMS_DRIVEN, mActivityAdvertisingCreateBinding.icAdvertisingHcar.etAdvertisingHcarKmsDriven.getText().toString());
        }

        //bicycle
        if(advertisingRequest!=null && advertisingRequest.getCategorySubject()!=null && advertisingRequest.getCategorySubject().equals("bicycleFetch"))
        {
            map.put(AppConstants.REQ_KEY_BICYCLE_SEX_NAME, advertisingRequest.getBicycleBySexNameId());
            //map.put(AppConstants.REQ_KEY_BICYCLE_PRODUCT_YEAR,mActivityAdvertisingCreateBinding.icAdvertisingBicycle.spAdvertisingBicycleProducYear.getSelectedItem().toString());
            map.put(AppConstants.REQ_KEY_BICYCLE_BRAND_NAME, advertisingRequest.getBicycleBrandId());
            map.put(AppConstants.REQ_KEY_BICYCLE_TYPE_NAME, advertisingRequest.getBicycleTypeId());
            map.put(AppConstants.REQ_KEY_BICYCLE_COLOR, advertisingRequest.getBicycleColorId());
            map.put(AppConstants.REQ_KEY_BICYCLE_SIZE_NAME, advertisingRequest.getBicycleSizeId());
            map.put(AppConstants.REQ_KEY_BICYCLE_MATERIAL_NAME, advertisingRequest.getBicycleMaterialId());
            map.put(AppConstants.REQ_KEY_BICYCLE_STOP_TYPE_NAME, advertisingRequest.getBicycleStopTypeId());
        }
        //motor
        if(advertisingRequest!=null && advertisingRequest.getCategorySubject()!=null && advertisingRequest.getCategorySubject().equals("motorFetch"))
        {
            map.put(AppConstants.REQ_KEY_MOTOR_PRODUCT_YEAR, mActivityAdvertisingCreateBinding.icAdvertisingMotor.spAdvertisingMotorProductYear.getSelectedItem().toString());
            map.put(AppConstants.REQ_KEY_MOTOR_BRAND_NAME, advertisingRequest.getMotorBrandId());
            map.put(AppConstants.REQ_KEY_MOTOR_KMS_DRIVEN, mActivityAdvertisingCreateBinding.icAdvertisingMotor.etAdvertisingMotorKmsDriven.getText().toString());
            map.put(AppConstants.REQ_KEY_MOTOR_INSURANCE_VALID_TILL, mActivityAdvertisingCreateBinding.icAdvertisingMotor.etAdvertisingMotorInsuranceValidTill.getText().toString());
            map.put(AppConstants.REQ_KEY_MOTOR_INSURANCE_DISCOUNT, mActivityAdvertisingCreateBinding.icAdvertisingMotor.etAdvertisingMotorInsuranceDiscount.getText().toString());
        }

        //accessories
        if(advertisingRequest!=null && advertisingRequest.getCategorySubject()!=null && advertisingRequest.getCategorySubject().equals("accessoriesFetch"))
        {
            map.put(AppConstants.REQ_KEY_ACCESSORIES_TYPE_NAME, advertisingRequest.getAccessoriesId());
        }

        //accessories
        if(advertisingRequest!=null && advertisingRequest.getCategorySubject()!=null && advertisingRequest.getCategorySubject().equals("productFetch"))
        {
            map.put(AppConstants.REQ_KEY_PRODUCT_TYPE_NAME, advertisingRequest.getProductTypeId());
        }

        //job
        if(advertisingRequest!=null && advertisingRequest.getCategorySubject()!=null && advertisingRequest.getCategorySubject().equals("jobFetch"))
        {
            map.put(AppConstants.REQ_KEY_JOB_TYPE_NAME, advertisingRequest.getJobTypeId());
            map.put(AppConstants.REQ_KEY_JOB_CITY_NAME, advertisingRequest.getJobCityId());
            map.put(AppConstants.REQ_KEY_JOB_MIN_SALARY, mActivityAdvertisingCreateBinding.icAdvertisingJob.etAdvertisingJobMinSalary.getText().toString());
            map.put(AppConstants.REQ_KEY_JOB_MAX_SALARY, mActivityAdvertisingCreateBinding.icAdvertisingJob.etAdvertisingJobMaxSalary.getText().toString());

        }

        //mobile
        if(advertisingRequest!=null && advertisingRequest.getCategorySubject()!=null && advertisingRequest.getCategorySubject().equals("mobileFetch"))
        {
            map.put(AppConstants.REQ_KEY_MOBILE_MODEL_NAME, advertisingRequest.getMobileBrandId());
            map.put(AppConstants.REQ_KEY_MOBILE_OS_NAME, advertisingRequest.getMobileOsId());
            map.put(AppConstants.REQ_KEY_MOBILE_SIMS_NUM_NAME, advertisingRequest.getMobileSimsNumberId());
            map.put(AppConstants.REQ_KEY_MOBILE_ACCESSORIES, mActivityAdvertisingCreateBinding.icAdvertisingMobile.etAdvertisingMobileAccessories.getText().toString());

        }

        //tablet
        if(advertisingRequest!=null && advertisingRequest.getCategorySubject()!=null && advertisingRequest.getCategorySubject().equals("tabletFetch"))
        {
            map.put(AppConstants.REQ_KEY_TABLET_BRAND_NAME, advertisingRequest.getTabletBrandId());
            map.put(AppConstants.REQ_KEY_MOBILE_OS_NAME, advertisingRequest.getMobileOsId());
            map.put(AppConstants.REQ_KEY_MOBILE_SIMS_NUM_NAME, advertisingRequest.getMobileSimsNumberId());
            map.put(AppConstants.REQ_KEY_MOBILE_ACCESSORIES, mActivityAdvertisingCreateBinding.icAdvertisingMobile.etAdvertisingMobileAccessories.getText().toString());

        }

        //Furniture
        if(advertisingRequest!=null && advertisingRequest.getCategorySubject()!=null && advertisingRequest.getCategorySubject().equals("furnitureFetch"))
        {
            map.put(AppConstants.REQ_KEY_FURNITURE_CONDITION_ID, advertisingRequest.getFurnitureConditionId());
            map.put(AppConstants.REQ_KEY_FURNITURE_AGE_ID, advertisingRequest.getFurnitureAgeId());
        }

        //Appliance
        if(advertisingRequest!=null && advertisingRequest.getCategorySubject()!=null && advertisingRequest.getCategorySubject().equals("applianceFetch"))
        {
            map.put(AppConstants.REQ_KEY_APPLIANCE_BRAND_ID, advertisingRequest.getApplianceBrandId());
            map.put(AppConstants.REQ_KEY_APPLIANCE_COLOR_ID, advertisingRequest.getApplianceColorId());
            map.put(AppConstants.REQ_KEY_APPLIANCE_TYPE_ID, advertisingRequest.getApplianceTypeId());
        }

        //Appliance
        if(advertisingRequest!=null && advertisingRequest.getCategorySubject()!=null && advertisingRequest.getCategorySubject().equals("tripFetch"))
        {
            map.put(AppConstants.REQ_KEY_TRIP_TYPE, advertisingRequest.getTripTypeId());
            map.put(AppConstants.REQ_KEY_TRIP_TRANSPORT_TYPE, advertisingRequest.getTripTransportTypId());
            map.put(AppConstants.REQ_KEY_TRIP_SUBJECT_TYPE, advertisingRequest.getTripSubjectTypeId());
            map.put(AppConstants.REQ_KEY_TRIP_CITY, advertisingRequest.getTripCityId());
            map.put(AppConstants.REQ_KEY_TRIP_REQUIRED_DOCUMENTS, mActivityAdvertisingCreateBinding.icAdvertisingTrip.etAdvertisingTripDocument.getText().toString());
            map.put(AppConstants.REQ_KEY_TRIP_STAY_TIME, mActivityAdvertisingCreateBinding.icAdvertisingTrip.etAdvertisingTripStayTime.getText().toString());
            map.put(AppConstants.REQ_KEY_TRIP_DESTINATION, mActivityAdvertisingCreateBinding.icAdvertisingTrip.etAdvertisingTripDestination.getText().toString());
            map.put(AppConstants.REQ_KEY_TRIP_END_DATE, mActivityAdvertisingCreateBinding.icAdvertisingTrip.etAdvertisingTripEndDate.getText().toString());
            map.put(AppConstants.REQ_KEY_TRIP_START_DATE, mActivityAdvertisingCreateBinding.icAdvertisingTrip.etAdvertisingTripStartDate.getText().toString());
        }

        //Estate
        if(advertisingRequest!=null && advertisingRequest.getCategorySubject()!=null && advertisingRequest.getCategorySubject().equals("estateFetch"))
        {
            map.put(AppConstants.REQ_KEY_ESTATE_AGE, advertisingRequest.getEstateAgeId());
            map.put(AppConstants.REQ_KEY_ESTATE_CABINET, advertisingRequest.getEstateCabinetId());
            map.put(AppConstants.REQ_KEY_ESTATE_COOLING, advertisingRequest.getEstateCoolingId());
            map.put(AppConstants.REQ_KEY_ESTATE_FACING, advertisingRequest.getEstateFacingId());

            //1kafpoosh
            map.put(AppConstants.REQ_KEY_ESTATE_FLOOR_COVERING, advertisingRequest.getEstateFloorCoveringId());
            map.put(AppConstants.REQ_KEY_ESTATE_HEATING, advertisingRequest.getEstateHeatingId());
            map.put(AppConstants.REQ_KEY_ESTATE_ROOM_NUMBER, advertisingRequest.getEstateRoomNumberId());
            map.put(AppConstants.REQ_KEY_ESTATE_VIEW, advertisingRequest.getEstateViewId());
            map.put(AppConstants.REQ_KEY_ESTATE_WC, advertisingRequest.getEstateWcId());

            map.put(AppConstants.REQ_KEY_ESTATE_ALLEY_WIDTH, mActivityAdvertisingCreateBinding.icAdvertisingEstate.etAdvertisingEstateAlleyWidth.getText().toString());
            map.put(AppConstants.REQ_KEY_ESTATE_AREA, mActivityAdvertisingCreateBinding.icAdvertisingEstate.etAdvertisingEstateArea.getText().toString());
            map.put(AppConstants.REQ_KEY_ESTATE_BUILDING_FLOOR, mActivityAdvertisingCreateBinding.icAdvertisingEstate.etAdvertisingEstateBuildingFloor.getText().toString());
            map.put(AppConstants.REQ_KEY_ESTATE_FLOOR, mActivityAdvertisingCreateBinding.icAdvertisingEstate.etAdvertisingEstateFloor.getText().toString());

            map.put(AppConstants.REQ_KEY_ESTATE_LAND_AREA, mActivityAdvertisingCreateBinding.icAdvertisingEstate.etAdvertisingEstateLandArea.getText().toString());
            map.put(AppConstants.REQ_KEY_ESTATE_LAND_LENGTH, mActivityAdvertisingCreateBinding.icAdvertisingEstate.etAdvertisingEstateLandLength.getText().toString());
            map.put(AppConstants.REQ_KEY_ESTATE_SHOP_HEIGHT, mActivityAdvertisingCreateBinding.icAdvertisingEstate.etAdvertisingEstateShopHeight.getText().toString());
            map.put(AppConstants.REQ_KEY_ESTATE_UNIT_FLOOR, mActivityAdvertisingCreateBinding.icAdvertisingEstate.etAdvertisingEstateUnitFloor.getText().toString());


            if(mActivityAdvertisingCreateBinding.icAdvertisingEstate.swAdvertisingEstateBalcony.isChecked())
            {
                map.put(AppConstants.REQ_KEY_ESTATE_BALCONY,"y");
            }else
                map.put(AppConstants.REQ_KEY_ESTATE_BALCONY,"n");

            if(mActivityAdvertisingCreateBinding.icAdvertisingEstate.swAdvertisingEstateCentralAntenna.isChecked())
            {
                map.put(AppConstants.REQ_KEY_ESTATE_CENTRAL_ANTENNA,"y");
            }else
                map.put(AppConstants.REQ_KEY_ESTATE_CENTRAL_ANTENNA,"n");

            if(mActivityAdvertisingCreateBinding.icAdvertisingEstate.swAdvertisingEstateDepot.isChecked())
            {
                map.put(AppConstants.REQ_KEY_ESTATE_DEPOT,"y");
            }else
                map.put(AppConstants.REQ_KEY_ESTATE_DEPOT,"n");

            if(mActivityAdvertisingCreateBinding.icAdvertisingEstate.swAdvertisingEstateDoorRemote.isChecked())
            {
                map.put(AppConstants.REQ_KEY_ESTATE_DOOR_REMOTE,"y");
            }else
                map.put(AppConstants.REQ_KEY_ESTATE_DOOR_REMOTE,"n");

            if(mActivityAdvertisingCreateBinding.icAdvertisingEstate.swAdvertisingEstateIPhone.isChecked())
            {
                map.put(AppConstants.REQ_KEY_ESTATE_IPHONE,"y");
            }else
                map.put(AppConstants.REQ_KEY_ESTATE_IPHONE,"n");

            if(mActivityAdvertisingCreateBinding.icAdvertisingEstate.swAdvertisingEstateLift.isChecked())
            {
                map.put(AppConstants.REQ_KEY_ESTATE_LIFT,"y");
            }else
                map.put(AppConstants.REQ_KEY_ESTATE_LIFT,"n");

            if(mActivityAdvertisingCreateBinding.icAdvertisingEstate.swAdvertisingEstateParking.isChecked())
            {
                map.put(AppConstants.REQ_KEY_ESTATE_PARKING,"y");
            }else
                map.put(AppConstants.REQ_KEY_ESTATE_PARKING,"n");

            if(mActivityAdvertisingCreateBinding.icAdvertisingEstate.swAdvertisingEstateTel.isChecked())
            {
                map.put(AppConstants.REQ_KEY_ESTATE_TEL,"y");
            }else
                map.put(AppConstants.REQ_KEY_ESTATE_TEL,"n");
        }



        mAdvertisingCreateViewModel.callAdvertisingCreate(this,map,iApiCall);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean isValidInputs()
    {
        boolean flag = true;
        try {

            if (mAdvertisingCreateViewModel.categoryId.get().equals("")) {
                mActivityAdvertisingCreateBinding.txtAdvertisingCategoryValue.setBackground(getResources().getDrawable(R.drawable.edt_error_bg));
                mActivityAdvertisingCreateBinding.txtAdvertisingCategoryValue.setHint(getString(R.string.important_input));
                mActivityAdvertisingCreateBinding.txtAdvertisingCategoryValue.setHintTextColor(getResources().getColor(R.color.red_error));
                if(flag)
                    mActivityAdvertisingCreateBinding.txtAdvertisingCategoryValue.requestFocus();
                flag = false;
            }else
            {
                mActivityAdvertisingCreateBinding.txtAdvertisingCategoryValue.setBackground(getResources().getDrawable(R.drawable.edt_bg));
                mActivityAdvertisingCreateBinding.txtAdvertisingCategoryValue.setTextColor(getResources().getColor(R.color.gray_800));
            }

            if (TextUtils.isEmpty(mActivityAdvertisingCreateBinding.etxAdvertisingTitleValue.getText().toString().trim())) {
                mActivityAdvertisingCreateBinding.etxAdvertisingTitleValue.setBackground(getResources().getDrawable(R.drawable.edt_error_bg));
                mActivityAdvertisingCreateBinding.etxAdvertisingTitleValue.setHint(getString(R.string.important_input));
                mActivityAdvertisingCreateBinding.etxAdvertisingTitleValue.setHintTextColor(getResources().getColor(R.color.red_error));
                //mActivityAdvertisingCreateBinding.etxAdvertisingTitleValue.setTh
                if(flag)
                    mActivityAdvertisingCreateBinding.etxAdvertisingTitleValue.requestFocus();
                flag = false;
            }else
            {
                mActivityAdvertisingCreateBinding.etxAdvertisingTitleValue.setBackground(getResources().getDrawable(R.drawable.edt_bg));
                mActivityAdvertisingCreateBinding.etxAdvertisingTitleValue.setTextColor(getResources().getColor(R.color.gray_800));
            }

            String mobile = mActivityAdvertisingCreateBinding.etxAdvertisingContactMobile.getText().toString();
            if (TextUtils.isEmpty(mobile.trim())) {
                mActivityAdvertisingCreateBinding.etxAdvertisingContactMobile.setBackground(getResources().getDrawable(R.drawable.edt_error_bg));
                mActivityAdvertisingCreateBinding.etxAdvertisingContactMobile.setHint(getString(R.string.important_input_mobile));
                mActivityAdvertisingCreateBinding.etxAdvertisingContactMobile.setHintTextColor(getResources().getColor(R.color.red_error));
                if(flag)
                    mActivityAdvertisingCreateBinding.etxAdvertisingContactMobile.requestFocus();
                flag = false;
            } else
            {
                mActivityAdvertisingCreateBinding.etxAdvertisingContactMobile.setBackground(getResources().getDrawable(R.drawable.edt_bg));
                mActivityAdvertisingCreateBinding.etxAdvertisingContactMobile.setTextColor(getResources().getColor(R.color.gray_800));
            }
            if (!mobile.isEmpty() && !CommonUtils.isValidPhoneNumber(mobile)) {
                CommonUtils.showToast(AdvertisingCreateActivity.this, getString(R.string.msg_invalid_mobile));
                flag = false;
            }

            String email = mActivityAdvertisingCreateBinding.etxAdvertisingContactEmail.getText().toString();
            if (TextUtils.isEmpty(email.trim())) {
                mActivityAdvertisingCreateBinding.etxAdvertisingContactEmail.setBackground(getResources().getDrawable(R.drawable.edt_error_bg));
                mActivityAdvertisingCreateBinding.etxAdvertisingContactEmail.setHint(getString(R.string.important_input_email));
                mActivityAdvertisingCreateBinding.etxAdvertisingContactEmail.setHintTextColor(getResources().getColor(R.color.red_error));
                if(flag)
                    mActivityAdvertisingCreateBinding.etxAdvertisingContactEmail.requestFocus();
                flag = false;
            } else
            {
                mActivityAdvertisingCreateBinding.etxAdvertisingContactEmail.setBackground(getResources().getDrawable(R.drawable.edt_bg));
                mActivityAdvertisingCreateBinding.etxAdvertisingContactEmail.setTextColor(getResources().getColor(R.color.gray_800));
            }
            if (!email.isEmpty() && !CommonUtils.isEmailValid(email)) {
                CommonUtils.showToast(AdvertisingCreateActivity.this, getString(R.string.msg_invalid_email));
                flag = false;
            }

            if (TextUtils.isEmpty(mActivityAdvertisingCreateBinding.etxAdvertisingDescriptionValue.getText().toString().trim())) {
                mActivityAdvertisingCreateBinding.etxAdvertisingDescriptionValue.setBackground(getResources().getDrawable(R.drawable.edt_error_bg));
                mActivityAdvertisingCreateBinding.etxAdvertisingDescriptionValue.setHint(getString(R.string.important_input));
                mActivityAdvertisingCreateBinding.etxAdvertisingDescriptionValue.setHintTextColor(getResources().getColor(R.color.red_error));
                if(flag)
                    mActivityAdvertisingCreateBinding.etxAdvertisingDescriptionValue.requestFocus();
                flag = false;
            } else
            {
                mActivityAdvertisingCreateBinding.etxAdvertisingDescriptionValue.setBackground(getResources().getDrawable(R.drawable.edt_bg));
                mActivityAdvertisingCreateBinding.etxAdvertisingDescriptionValue.setTextColor(getResources().getColor(R.color.gray_800));
            }

            if(mAdvertisingCreateViewModel.actPriceShow.get())
                if (TextUtils.isEmpty(mActivityAdvertisingCreateBinding.etxAdvertisingPriceValue.getText().toString().trim())) {
                    mActivityAdvertisingCreateBinding.etxAdvertisingPriceValue.setBackground(getResources().getDrawable(R.drawable.edt_error_bg));
                    mActivityAdvertisingCreateBinding.etxAdvertisingPriceValue.setHint(getString(R.string.important_input));
                    mActivityAdvertisingCreateBinding.etxAdvertisingPriceValue.setHintTextColor(getResources().getColor(R.color.red_error));
                    if(flag)
                        mActivityAdvertisingCreateBinding.etxAdvertisingPriceValue.requestFocus();
                    flag = false;
                } else
                {
                    mActivityAdvertisingCreateBinding.etxAdvertisingPriceValue.setBackground(getResources().getDrawable(R.drawable.edt_bg));
                    mActivityAdvertisingCreateBinding.etxAdvertisingPriceValue.setTextColor(getResources().getColor(R.color.gray_800));
                }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public void onNotificationReceived(NotificationData notificationData) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_advertising_create;
    }

    @Override
    public AdvertisingCreateViewModel getViewModel() {
        return mAdvertisingCreateViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public void onItemClickListener(String category,String categoryId,String categorySubject,String categoryType) {
        try{
        mAdvertisingCreateViewModel.categoryName.set(category);
        mAdvertisingCreateViewModel.categoryId.set(categoryId);
        if(categorySubject.equals("carFetch"))
        {
            initCar();
        }else
            mAdvertisingCreateViewModel.carDeatailsShow.set(false);

        if(categorySubject.equals("hcarFetch"))
        {
            initHCar();
        }else
            mAdvertisingCreateViewModel.hCarShow.set(false);

        if(categorySubject.equals("bicycleFetch"))
        {
            initbicycle();
        }else
            mAdvertisingCreateViewModel.bicycleShow.set(false);

        if(categorySubject.equals("motorFetch"))
        {
            initMotor();
        }else
            mAdvertisingCreateViewModel.motorShow.set(false);

        if(categorySubject.equals("accessoriesFetch"))
        {
            initAccessories(categoryId);
        }else
            mAdvertisingCreateViewModel.accessoriesShow.set(false);

        if(categorySubject.equals("productFetch"))
        {
            initProductType(categoryId);
        }else
            mAdvertisingCreateViewModel.productTypeShow.set(false);

        if(categorySubject.equals("furnitureFetch"))
        {
            initFurniture();
        }else
            mAdvertisingCreateViewModel.furnitureShow.set(false);

        if(categorySubject.equals("applianceFetch"))
        {
            initAppliance(categoryId);
        }else
            mAdvertisingCreateViewModel.applianceShow.set(false);

        if(categorySubject.equals("tripFetch"))
        {
            initTripp(categoryId);
        }else
            mAdvertisingCreateViewModel.tripShow.set(false);

        if(categorySubject.equals("jobFetch"))
        {
            mAdvertisingCreateViewModel.jobShow.set(true);
        }else
            mAdvertisingCreateViewModel.jobShow.set(false);

        if(categorySubject.equals("mobileFetch"))
        {
            initMobile();
            mAdvertisingCreateViewModel.tabletBrandShow.set(false);
            mAdvertisingCreateViewModel.mobileBrandShow.set(true);
        }else
            mAdvertisingCreateViewModel.mobileShow.set(false);

        if(categorySubject.equals("estateFetch"))
        {
            initEstate(categoryId);
            mAdvertisingCreateViewModel.estateShow.set(true);
        }else
            mAdvertisingCreateViewModel.estateShow.set(false);

        if(categorySubject.equals("tabletFetch"))
        {
            initMobile();
            mAdvertisingCreateViewModel.tabletBrandShow.set(true);
            mAdvertisingCreateViewModel.mobileBrandShow.set(false);
        }else
            mAdvertisingCreateViewModel.mobileShow.set(false);

        if(categoryType.equals("kala"))
        {
            mAdvertisingCreateViewModel.fieldShowBycategory.set(true);
        }else if(categoryType.equals("intro"))
            mAdvertisingCreateViewModel.fieldShowBycategory.set(false);


        advertisingRequest.setCategoryId(categoryId);
        advertisingRequest.setCategorySubject(categorySubject);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public void onItemCityClickListener(String city, String cityId) {
        try{
        mAdvertisingCreateViewModel.cityName.set(city);
        mAdvertisingCreateViewModel.cityId.set(cityId);
        advertisingRequest.setCityId(cityId);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemCityByJobClickListener(String city, String cityId) {
        try{
            mAdvertisingCreateViewModel.cityNameByJob.set(city);
            mAdvertisingCreateViewModel.cityIdByJob.set(cityId);
            advertisingRequest.setJobCityId(cityId);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemCityByTripClickListener(String city, String cityId) {
        try{
            mAdvertisingCreateViewModel.cityNameByTrip.set(city);
            mAdvertisingCreateViewModel.cityIdByTrip.set(cityId);
            advertisingRequest.setTripCityId(cityId);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemCarTypeClickListener(String car, String carId) {
        try{
        mAdvertisingCreateViewModel.carModelName.set(car);
        mAdvertisingCreateViewModel.carModelId.set(carId);
        advertisingRequest.setCarModelId(carId);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemMobileTypeClickListener(String mobileBrand, String mobileId) {
        try{
            mAdvertisingCreateViewModel.mobileBrandName.set(mobileBrand);
            mAdvertisingCreateViewModel.mobileBrandId.set(mobileId);
            advertisingRequest.setMobileBrandId(mobileId);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private void startPermissionsActivity(String[] permission) {
        PermissionsActivity.startActivityForResult(this, 0, permission);
    }

    public void openImageIntent(int pickImage)
    {
        try{
        if (checker.lacksPermissions(PERMISSIONS_READ_STORAGE)) {
            startPermissionsActivity(PERMISSIONS_READ_STORAGE);
        }else{
            // File System.
            final Intent galleryIntent = new Intent();
            galleryIntent.setType("image/*");
            galleryIntent.setAction(Intent.ACTION_PICK);

            // Chooser of file system options.
            final Intent chooserIntent = Intent.createChooser(galleryIntent, getString(R.string.string_choose_image));
            startActivityForResult(chooserIntent, pickImage);
        }
        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {

        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConstants.PICK_IMAGE
                && resultCode == RESULT_OK) {
            try {
                if (data == null) {
                    CommonUtils.showAlert(this,getString(R.string.title_warning),getString(R.string.unable_to_pick_image),null);
                    return;
                }

                progressDialog = new ProgressDialog(AdvertisingCreateActivity.this);
                progressDialog.setMessage(getString(R.string.title_upload_progressbar));
                progressDialog.show();

                Uri imageUri = data.getData();
                //reduse size image
                String imageUriSttr = compressImage(imageUri.toString());
                imageUri = Uri.parse(imageUriSttr);
                ///

                String filePath = getRealPathFromURIPath(imageUri, AdvertisingCreateActivity.this);
                File file = new File(filePath);

                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("upl", file.getName(), mFile);
                RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

                String imageNumber = AppConstants.PICK_IMAGE+"";
                mAdvertisingCreateViewModel.callUploadImage(this,fileToUpload, filename,iApiCall,imageNumber);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (requestCode == AppConstants.PICK_IMAGE1
                && resultCode == RESULT_OK) {
            try {
                if (data == null) {
                    CommonUtils.showAlert(this,getString(R.string.title_warning),getString(R.string.unable_to_pick_image),null);
                    return;
                }

                progressDialog = new ProgressDialog(AdvertisingCreateActivity.this);
                progressDialog.setMessage(getString(R.string.title_upload_progressbar));
                progressDialog.show();

                Uri imageUri = data.getData();
                //reduse size image
                String imageUriSttr = compressImage(imageUri.toString());
                imageUri = Uri.parse(imageUriSttr);
                ///
                String filePath = getRealPathFromURIPath(imageUri, AdvertisingCreateActivity.this);
                File file = new File(filePath);
                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("upl", file.getName(), mFile);
                RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

                String imageNumber = AppConstants.PICK_IMAGE1+"";
                mAdvertisingCreateViewModel.callUploadImage(this,fileToUpload, filename,iApiCall,imageNumber);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (requestCode == AppConstants.PICK_IMAGE3
                && resultCode == RESULT_OK) {
            try {
                if (data == null) {
                    CommonUtils.showAlert(this,getString(R.string.title_warning),getString(R.string.unable_to_pick_image),null);
                    return;
                }

                progressDialog = new ProgressDialog(AdvertisingCreateActivity.this);
                progressDialog.setMessage(getString(R.string.title_upload_progressbar));
                progressDialog.show();

                Uri imageUri = data.getData();
                //reduse size image
                String imageUriSttr = compressImage(imageUri.toString());
                imageUri = Uri.parse(imageUriSttr);
                ///
                String filePath = getRealPathFromURIPath(imageUri, AdvertisingCreateActivity.this);
                File file = new File(filePath);
                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("upl", file.getName(), mFile);
                RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

                String imageNumber = AppConstants.PICK_IMAGE3+"";
                mAdvertisingCreateViewModel.callUploadImage(this,fileToUpload, filename,iApiCall,imageNumber);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (requestCode == AppConstants.PICK_IMAGE4
                && resultCode == RESULT_OK) {
            try {
                if (data == null) {
                    CommonUtils.showAlert(this,getString(R.string.title_warning),getString(R.string.unable_to_pick_image),null);
                    return;
                }

                progressDialog = new ProgressDialog(AdvertisingCreateActivity.this);
                progressDialog.setMessage(getString(R.string.title_upload_progressbar));
                progressDialog.show();

                Uri imageUri = data.getData();
                //reduse size image
                String imageUriSttr = compressImage(imageUri.toString());
                imageUri = Uri.parse(imageUriSttr);
                ///
                String filePath = getRealPathFromURIPath(imageUri, AdvertisingCreateActivity.this);
                File file = new File(filePath);
                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("upl", file.getName(), mFile);
                RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

                String imageNumber = AppConstants.PICK_IMAGE4+"";
                mAdvertisingCreateViewModel.callUploadImage(this,fileToUpload, filename,iApiCall,imageNumber);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String compressImage(String imageUri) {

        String filePath = getRealPathFromURI(imageUri);
        Bitmap scaledBitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();

//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;

//      max Height and width values of the compressed image is taken as 816x612

        float maxHeight = 816.0f;
        float maxWidth = 612.0f;
        float imgRatio = actualWidth / actualHeight;
        float maxRatio = maxWidth / maxHeight;

//      width and height values are set maintaining the aspect ratio of the image

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;

            }
        }

//      setting inSampleSize value allows to load a scaled down version of the original image

        options.inSampleSize = calculateInSampleSize1(options, actualWidth, actualHeight);

//      inJustDecodeBounds set to false to load the actual bitmap
        options.inJustDecodeBounds = false;

//      this options allow android to claim the bitmap memory if it runs low on memory
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
//          load the bitmap from its path
            bmp = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();

        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

//      check the rotation of the image and display it properly
        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);

            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d("EXIF", "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                    scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream out = null;
        String filename = getFilename();
        try {
            out = new FileOutputStream(filename);

//          write the compressed bitmap at the destination specified by filename.
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return filename;

    }

    public String getFilename() {
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "Tajer/Images");
        if (!file.exists()) {
            file.mkdirs();
        }
        String uriSting = (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
        return uriSting;

    }

    private String getRealPathFromURI(String contentURI) {
        Uri contentUri = Uri.parse(contentURI);
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        if (cursor == null) {
            return contentUri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(index);
        }
    }

    public int calculateInSampleSize1(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }


    //    @Override
//    public AndroidInjector<Fragment> supportFragmentInjector() {
//        return fragmentDispatchingAndroidInjector;
//    }

}
