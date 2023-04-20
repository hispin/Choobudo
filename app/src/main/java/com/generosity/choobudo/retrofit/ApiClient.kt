package com.generosity.choobudo.retrofit

import com.generosity.choobudo.common.common
import com.generosity.choobudo.retrofit.ApiClient.Constant.cookie
import com.generosity.choobudo.retrofit.ApiClient.Constant.token
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class ApiClient {



    object Constant {
        var token:String?=null
        var cookie:String?=null
    }



    object ApiClient {
        var mHttpLoggingInterceptor=
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


        var mOkHttpClient=OkHttpClient.Builder().addInterceptor(mHttpLoggingInterceptor)
            .addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    return chain.proceed(chain.request())
                }
            }).addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    //this is where we will add whatever we want to our request headers.
                    val request: Request=
                        chain.request().newBuilder().addHeader("Accept", "application/json")
                            .addHeader("Content-Type", "application/json").addHeader(
                                "Authorization",
                                "Basic QXBwQEFQSV91c2VyMSE6NiExMzU2OSRFNEI2NCEwNDdFQDRBQ0RGMV41QTIxODU3JCRFNTI2OTc2RjU2NkUhODQxQ0BANCMzNjIjIzhEQjc2IzM2QyE5OTYyNTVFOQ=="
                            ).build()
                    return chain.proceed(request)
                }
            }).build()


        var mRetrofit: Retrofit?=null


        val client: Retrofit?
            get() {
                if (mRetrofit == null) {
                    mRetrofit=Retrofit.Builder().baseUrl(common.Constant.BASE_URL)
                        //("Authorization",finalToken)
                        .client(mOkHttpClient).addConverterFactory(GsonConverterFactory.create())
                        .build()
                }

                return mRetrofit
            }
    }

    object ApiClientWithCookie {
        var mHttpLoggingInterceptor=
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


        var mOkHttpClient=OkHttpClient.Builder().addInterceptor(mHttpLoggingInterceptor)
            .addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    return chain.proceed(chain.request())
                }
            }).addInterceptor(object : Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    //this is where we will add whatever we want to our request headers.
                    val request: Request=
                        chain.request().newBuilder().addHeader("Accept", "application/json")
                            .addHeader("Content-Type", "application/json").addHeader(
                                "Authorization",
                                "Basic QXBwQEFQSV91c2VyMSE6NiExMzU2OSRFNEI2NCEwNDdFQDRBQ0RGMV41QTIxODU3JCRFNTI2OTc2RjU2NkUhODQxQ0BANCMzNjIjIzhEQjc2IzM2QyE5OTYyNTVFOQ=="
                            )
                            .addHeader(
                                "Set-Cookie", cookie!!
                            )
                            .addHeader(
                                "token_key", token!!
                            )
                            .build()
                    return chain.proceed(request)
                }
            }).build()


        var mRetrofit: Retrofit?=null


        val client: Retrofit?
            get() {
                if (mRetrofit == null) {
                    mRetrofit=Retrofit.Builder().baseUrl(common.Constant.BASE_URL)
                        //("Authorization",finalToken)
                        .client(mOkHttpClient).addConverterFactory(GsonConverterFactory.create())
                        .build()
                }

                return mRetrofit
            }
    }

//    object ApiClient {
//        var mHttpLoggingInterceptor=
//            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//
//
//        var mOkHttpClient=OkHttpClient.Builder().addInterceptor(mHttpLoggingInterceptor)
//            .addInterceptor(object : Interceptor {
//                @Throws(IOException::class)
//                override fun intercept(chain: Interceptor.Chain): Response {
//                    return chain.proceed(chain.request())
//                }
//            }).addInterceptor(object : Interceptor {
//                @Throws(IOException::class)
//                override fun intercept(chain: Interceptor.Chain): Response {
//                    //this is where we will add whatever we want to our request headers.
//                    val request: Request=
//                        chain.request().newBuilder().addHeader("Accept", "application/json")
//                            .addHeader("Content-Type", "application/json").addHeader(
//                                "Authorization",
//                                "Basic QXBwQEFQSV91c2VyMSE6NiExMzU2OSRFNEI2NCEwNDdFQDRBQ0RGMV41QTIxODU3JCRFNTI2OTc2RjU2NkUhODQxQ0BANCMzNjIjIzhEQjc2IzM2QyE5OTYyNTVFOQ=="
//                            ).build()
//                    return chain.proceed(request)
//                }
//            }).build()
//
//
//        var mRetrofit: Retrofit?=null
//
//
//        val client: Retrofit?
//            get() {
//                if (mRetrofit == null) {
//                    mRetrofit=Retrofit.Builder().baseUrl(common.Constant.BASE_URL)
//                        //("Authorization",finalToken)
//                        .client(mOkHttpClient).addConverterFactory(GsonConverterFactory.create())
//                        .build()
//                }
//
//                return mRetrofit
//            }
//    }


}