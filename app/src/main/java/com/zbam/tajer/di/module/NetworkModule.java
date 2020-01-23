package com.zbam.tajer.di.module;

import android.app.Application;
import android.util.Base64;

import com.zbam.tajer.api.IApiCall;
import com.zbam.tajer.data.pref.AppPreferencesHelper;
import com.zbam.tajer.utils.AppConstants;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by z.bazoubandi on 7/16/2018.
 */

@Module
public class NetworkModule {

    private static final String NAME_BASE_URL = "NAME_BASE_URL";
    private static final String NAME_HEADER_ACCEPT = AppConstants.NAME_ACCEPT;
    private static final String NAME_HEADER_CONTENT_TYPE = AppConstants.NAME_CONTENT_TYPE;
    private static final String NAME_HEADER_SECURITY_KEY = AppConstants.NAME_SECURITY_KEY;
    private static final String NAME_HEADER_PASSENGER_ID = AppConstants.NAME_PASSENGER_ID;
    private static final String NAME_HEADER_AUTHORIZATION = AppConstants.NAME_AUTHORIZATION;

    @Provides
    @Named(NAME_HEADER_ACCEPT)
    String provideNameHeaderAccept() {
        return AppConstants.HEADER_ACCEPT;
    }

    @Provides
    @Named(NAME_HEADER_AUTHORIZATION)
    String provideHeaderAuthorization() {
        return AppConstants.HEADER_AUTHORIZATION_KEY;
    }

    @Provides
    @Named(NAME_BASE_URL)
    String provideBaseUrlString() {
        return AppConstants.BASE_URL;
    }

    @Provides
    @Named(NAME_HEADER_CONTENT_TYPE)
    String provideHeaderContentType() {
        return AppConstants.HEADER_CONTENT_TYPE;
    }

    @Provides
    @Named(NAME_HEADER_SECURITY_KEY)
    String provideHeaderSecurityKey() {
        return AppConstants.HEADER_SECURITY_KEY;
    }

    @Provides
    @Named(NAME_HEADER_PASSENGER_ID)
    String provideHeaderPassengerID() {
        return AppConstants.HEADER_PASSENGER_ID;
    }

    @Provides
    Cache provideOkHttpClientCache(Application application){
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    TrustManager[] provideTrusManagerCertificates(){
        final TrustManager[] certs = new TrustManager[] { new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }
        }
        };

        return certs ;
    }

    @Provides
    HostnameVerifier provideHostnameVerifier(){
        return new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true ;
            }
        } ;
    }

    @Provides
    SSLContext provideSSLContext(TrustManager[] cert) {
        SSLContext ctx = null ;

        try {
            ctx = SSLContext.getInstance("SSL");
            ctx.init(null , cert, new SecureRandom() );
        } catch (final java.security.GeneralSecurityException ex){
        }
        return ctx ;

    }

    @Provides
    HttpLoggingInterceptor provideClientInterceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @Singleton
    Interceptor provideInterceptor(final AppPreferencesHelper appPreferencesHelper ,
                                   @Named(NAME_HEADER_CONTENT_TYPE) final String contentType ,
                                   @Named(NAME_HEADER_SECURITY_KEY) final String securityKey,
                                   @Named(NAME_HEADER_ACCEPT) final String accept,
                                   @Named(NAME_HEADER_AUTHORIZATION) final String authorizationKey){
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request() ;
                Request.Builder requestBuilder = original.newBuilder()
                        .header(contentType, "application/json")
                        .header(accept, "application/json")
                        .header(authorizationKey, "Basic " + Base64.encodeToString("09126716594:alireza".getBytes(), Base64.NO_WRAP));
                        ///.header(securityKey, appPreferencesHelper.getSecurityKey())
                       // .header("lang" , appPreferencesHelper.getDefaultLanguage());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };


        return  interceptor ;
    }

    @Provides
    OkHttpClient provideOkHttpClient(Cache cache ,
                                     HttpLoggingInterceptor httpLoggingInterceptor ,
                                     SSLContext sslContext ,
                                     TrustManager[] trustManagers,
                                     HostnameVerifier hostnameVerifier ,
                                     final AppPreferencesHelper appPreferencesHelper ,
                                     @Named(NAME_HEADER_CONTENT_TYPE) final String contentType ,
                                     @Named(NAME_HEADER_ACCEPT) final String accept,
                                     @Named(NAME_HEADER_SECURITY_KEY) final String securityKey,
                                     @Named(NAME_HEADER_AUTHORIZATION) final String authorizationKey){
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .readTimeout(120  , TimeUnit.SECONDS)
                .writeTimeout(120 , TimeUnit.SECONDS)
                .connectTimeout(120 , TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request() ;
                        Request.Builder requestBuilder = original.newBuilder()
                                .header(contentType, "application/json")
                                .header(accept, "application/json")
                               // .header(securityKey, appPreferencesHelper.getSecurityKey())
                                .header(authorizationKey, "Basic " +Base64.encodeToString("09126716594:alireza".getBytes(), Base64.NO_WRAP))
                                //.header("lang" , appPreferencesHelper.getDefaultLanguage())
                                ;
                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                })
                .sslSocketFactory(sslContext.getSocketFactory() , (X509TrustManager) trustManagers[0])
                .hostnameVerifier(hostnameVerifier)
                .build();
        return  client;
    }

    @Provides
    GsonConverterFactory provideGsonConverter(){
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(GsonConverterFactory converter , OkHttpClient client , @Named(NAME_BASE_URL) String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converter)
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    IApiCall provideCallApi(Retrofit retrofit) {
        return retrofit.create(IApiCall.class);
    }
}
