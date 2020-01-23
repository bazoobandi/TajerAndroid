package com.zbam.tajer.ui.option.createchannel;

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
import android.support.annotation.Nullable;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zbam.tajer.BR;
import com.zbam.tajer.R;
import com.zbam.tajer.data.model.api.notification.NotificationData;
import com.zbam.tajer.data.model.api.response.UploadImageResponse;
import com.zbam.tajer.databinding.ActivityChannelCreateBinding;
import com.zbam.tajer.ui.base.BaseActivity;
import com.zbam.tajer.ui.main.advertisingcreate.AdvertisingCreateActivity;
import com.zbam.tajer.ui.main.advertisingcreate.AdvertisingCreateInterface;
import com.zbam.tajer.ui.pemission.PermissionsActivity;
import com.zbam.tajer.ui.pemission.PermissionsChecker;
import com.zbam.tajer.utils.AppConstants;
import com.zbam.tajer.utils.CommonUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by z.bazoubandi on 11/12/2018.
 */

public class CreateChannelActivity extends BaseActivity<ActivityChannelCreateBinding,CreateChannelViewModel> implements
        CreateChannelInterface
{

    ActivityChannelCreateBinding mActivityChannelCreateBinding;
    PermissionsChecker checker;
    ProgressDialog progressDialog;

    public static CreateChannelInterface createInterface;

    /**
     * Permission List
     */
    private static final String[] PERMISSIONS_READ_STORAGE = new String[]
            {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Inject
    CreateChannelViewModel mCreateChannelViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityChannelCreateBinding = getViewDataBinding();
        mCreateChannelViewModel.setActivity(this);
        createInterface =this;
        initImage();
    }


    public void callChannelCreate() {
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put(AppConstants.REQ_KEY_CHANNEL_NAME, mActivityChannelCreateBinding.etxChannelCreateTitle.getText().toString());
            map.put(AppConstants.REQ_KEY_CHANNEL_ADDRESS, mActivityChannelCreateBinding.etxChannelCreateAddress.getText().toString());
            map.put(AppConstants.REQ_KEY_CHANNEL_DESCRIPTION, mActivityChannelCreateBinding.etxChannelCreateDescription.getText().toString());
            map.put(AppConstants.REQ_KEY_CHANNEL_EMAIL, mActivityChannelCreateBinding.etxChannelCreateEmail.getText().toString());
            map.put(AppConstants.REQ_KEY_CHANNEL_MOBILE, mActivityChannelCreateBinding.etxChannelCreateMobile.getText().toString());
            map.put(AppConstants.REQ_KEY_CHANNEL_TEL, mActivityChannelCreateBinding.etxChannelCreateTel.getText().toString());
            map.put(AppConstants.REQ_KEY_CHANNEL_WEBSITE, mActivityChannelCreateBinding.etxChannelCreateWebsite.getText().toString());
            map.put(AppConstants.REQ_KEY_CHANNEL_IMG, mCreateChannelViewModel.imgeUrlStr.get().toString());
            map.put(AppConstants.REQ_KEY_CHANNEL_CATEGORY, mCreateChannelViewModel.categoryId.get().toString());
            map.put(AppConstants.REQ_KEY_CHANNEL_CITY, mCreateChannelViewModel.cityId.get().toString());

            mCreateChannelViewModel.callChannelCreate(this,map,iApiCall);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initImage()
    {
        /**
         * Permission Checker Initialized
         */
        checker = new PermissionsChecker(this);
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
                    CommonUtils.showAlert(this, getString(R.string.title_warning), getString(R.string.unable_to_pick_image), null);
                    return;
                }

                progressDialog = new ProgressDialog(CreateChannelActivity.this);
                progressDialog.setMessage(getString(R.string.title_upload_progressbar));
                progressDialog.show();

                Uri imageUri = data.getData();
                //reduse size image
                String imageUriSttr = compressImage(imageUri.toString());
                imageUri = Uri.parse(imageUriSttr);
                ///

                String filePath = getRealPathFromURIPath(imageUri, CreateChannelActivity.this);
                File file = new File(filePath);

                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("upl", file.getName(), mFile);
                RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

                mCreateChannelViewModel.callUploadChannelImage(this, fileToUpload, filename, iApiCall);

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

    public String getFilename() {
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "Tajer/Images");
        if (!file.exists()) {
            file.mkdirs();
        }
        String uriSting = (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
        return uriSting;

    }


    @Override
    public void onNotificationReceived(NotificationData notificationData) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_channel_create;
    }

    @Override
    public CreateChannelViewModel getViewModel() {
        return mCreateChannelViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public void onItemClickListener(String category, String categoryId) {
        try{
            mCreateChannelViewModel.categoryName.set(category);
            mCreateChannelViewModel.categoryId.set(categoryId);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemCityClickListener(String city, String cityId) {
        try{
            mCreateChannelViewModel.cityName.set(city);
            mCreateChannelViewModel.cityId.set(cityId);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
