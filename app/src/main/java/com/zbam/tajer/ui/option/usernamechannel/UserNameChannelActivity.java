package com.zbam.tajer.ui.option.usernamechannel;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;

import com.zbam.tajer.BR;
import com.zbam.tajer.R;
import com.zbam.tajer.data.model.api.notification.NotificationData;
import com.zbam.tajer.data.model.api.response.ContactPhoneResponse;
import com.zbam.tajer.databinding.ActivityChannelCreateBinding;
import com.zbam.tajer.databinding.ActivityChannelUsernameBinding;
import com.zbam.tajer.ui.base.BaseActivity;
import com.zbam.tajer.ui.option.createchannel.CreateChannelActivity;
import com.zbam.tajer.ui.option.createchannel.CreateChannelViewModel;
import com.zbam.tajer.ui.pemission.PermissionsActivity;
import com.zbam.tajer.ui.pemission.PermissionsChecker;
import com.zbam.tajer.utils.AppConstants;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

/**
 * Created by z.bazoubandi on 11/13/2018.
 */

public class UserNameChannelActivity extends BaseActivity<ActivityChannelUsernameBinding,UserNameChannelViewModel> {

    private static final String TAG = "ContactNumber";
    @Inject
    UserNameChannelViewModel mUserNameChannelViewModel;

    ActivityChannelUsernameBinding mActivityChannelUsernameBinding;
    ArrayList<ContactPhoneResponse> mContactPhoneResponse = new ArrayList<>();

    ProgressDialog progressDialog;

    PermissionsChecker checker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityChannelUsernameBinding = getViewDataBinding();
        mUserNameChannelViewModel.setActivity(this);
        initContact();
    }

    public void initContact()
    {
        /**
         * Permission Checker Initialized
         */
        checker = new PermissionsChecker(this);
    }

    /**
     * Permission List
     */
    private static final String[] PERMISSIONS_READ_CONTACT = new String[]
            {Manifest.permission.WRITE_CONTACTS,Manifest.permission.READ_CONTACTS};


    private void startPermissionsActivity(String[] permission) {
        PermissionsActivity.startActivityForResult(this, 0, permission);
    }

    public ArrayList<ContactPhoneResponse>  openContactIntent()
    {
        try{
//            if (checker.lacksPermissions(PERMISSIONS_READ_CONTACT)) {
//                startPermissionsActivity(PERMISSIONS_READ_CONTACT);
//            }else{
//                String myData = "content://contacts/people/";
//                Intent myActivity2 = new Intent(Intent.ACTION_VIEW, Uri.parse( myData) );
//                startActivity(myActivity2);
//            }

//            progressDialog = new ProgressDialog(UserNameChannelActivity.this);
//            progressDialog.setMessage(getString(R.string.title_upload_progressbar_contact));
//            progressDialog.show();
//                ContentResolver cr = getContentResolver();
//                Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
//                        null, null, null, null);
//
//
//                if ((cur != null ? cur.getCount() : 0) > 0) {
//                    while (cur != null && cur.moveToNext()) {
//                        String id = cur.getString(
//                                cur.getColumnIndex(ContactsContract.Contacts._ID));
//                        String name = cur.getString(cur.getColumnIndex(
//                                ContactsContract.Contacts.DISPLAY_NAME));
//
//                        if (cur.getInt(cur.getColumnIndex(
//                                ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
//                            Cursor pCur = cr.query(
//                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                                    null,
//                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
//                                    new String[]{id}, null);
//                            while (pCur.moveToNext()) {
//                                String phoneNo = pCur.getString(pCur.getColumnIndex(
//                                        ContactsContract.CommonDataKinds.Phone.NUMBER));
//                                ContactPhoneResponse contactPhoneResponse = new ContactPhoneResponse();
//                                contactPhoneResponse.setContactName(name);
//                                contactPhoneResponse.setContactNumber(phoneNo);
//                                mContactPhoneResponse.add(contactPhoneResponse);
//                            }
//                            pCur.close();
//                        }
//                    }
//                }
//                if(cur!=null){
//                    cur.close();
//                }
//



            ContentResolver cr = this.getContentResolver(); //Activity/Application android.content.Context
            Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
            if(cursor.moveToFirst())
            {
               // ArrayList<ContactPhoneResponse> alContacts = new ArrayList<ContactPhoneResponse>();
                do
                {
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                    if(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
                    {
                        Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",new String[]{ id }, null);
                        while (pCur.moveToNext())
                        {
                            String contactNumber = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                            ContactPhoneResponse contactPhoneResponse = new ContactPhoneResponse();
                            contactPhoneResponse.setContactNumber(contactNumber);
                            mContactPhoneResponse.add(contactPhoneResponse);

                            break;
                        }
                        pCur.close();
                    }

                } while (cursor.moveToNext()) ;
            }


        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return mContactPhoneResponse;
    }

    public void callChannelUserNameCreate() {
        try {
            HashMap<String, String> map = new HashMap<>();
            map.put(AppConstants.REQ_KEY_CHANNEL_USER_NAME, mActivityChannelUsernameBinding.etxChannelUsername.getText().toString());

            mUserNameChannelViewModel.callChannelUserNameCreate(this,map,iApiCall);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNotificationReceived(NotificationData notificationData) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_channel_username;
    }

    @Override
    public UserNameChannelViewModel getViewModel() {
        return mUserNameChannelViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }
}
