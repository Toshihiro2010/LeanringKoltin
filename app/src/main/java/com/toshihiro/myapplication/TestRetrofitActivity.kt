package com.toshihiro.myapplication

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.toshihiro.myapplication.model.User
import com.toshihiro.myapplication.service.GithubAPI
import com.toshihiro.myapplication.service.RetrofitClient
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_test_retrofit.*
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.Exception
import org.reactivestreams.Subscriber
import android.os.AsyncTask.execute
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import okhttp3.OkHttpClient
import org.reactivestreams.Subscription
import android.os.AsyncTask.execute





class TestRetrofitActivity : AppCompatActivity() {

    var retrofit: Retrofit? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_retrofit)
        retrofit = RetrofitClient().getRetrofitClient()
//        testOkHttp()

        testokhttpRX()
//        testRetrofit()
    }


    fun testOkHttp(){
        var myOkHttps = OkHttpClient()
        var request : Request = Request.Builder()
            .url("https://api.github.com/users/Toshihiro2010")
            .get()
            .build()

        Flowable.fromCallable {
            myOkHttps.newCall(request).execute()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                if (it.isSuccessful) {
                    var jsonaa = it.body
                    var data = Gson().fromJson(jsonaa?.charStream(), User::class.java)
                    Log.d("Response :", data.toString())

//                    setHeader(data.login + " / " + data.id)
                }
            },{
                Log.d("Res error :", it.toString())
            })

//        return myObserve

//        myOkHttps.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
////                Toast.makeText(applicationContext, "error : " + e.toString(), Toast.LENGTH_SHORT).show()
//
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                if(response.code == 200){
//                    var jsonaa = response.body
//                    var data = Gson().fromJson(jsonaa?.charStream(), User::class.java)
//                    Log.d("Response :", data.login + " / " + data.email)
//
//                    setHeader(data.login + " / " + data.id)
//
//                }
//            }
//
//        })

    }

    fun setHeader(text : String){
        textHeaderRetrofit.text = text
    }

    fun testRetrofit(){

        var dataAPI: GithubAPI = retrofit!!.create(GithubAPI::class.java)
        dataAPI.getUser("toshihiro2010")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.isSuccessful) {
                    var data = it.body()
                    textHeaderRetrofit.text = data?.login
                    Toast.makeText(this, " is data : " + data!!.login, Toast.LENGTH_SHORT).show()
                }
            }
    }

    @SuppressLint("CheckResult")
    fun testokhttpRX(){
        var myOkHttps = OkHttpClient()
        var request : Request = Request.Builder()
            .url("https://api.github.com/users/Toshihiro2010")
            .get()
            .build()

        var observable = Observable.create<Response> { emitter ->
            Log.d("RxJava", "received on thread: " + Thread.currentThread().getName())
//            myOkHttps.newCall(request).enqueue(object : Callback{
//                override fun onFailure(call: Call, e: IOException) {
//                    emitter.onError(e)
//                }
//
//                override fun onResponse(call: Call, response: Response) {
//                    Log.d("RxJava", "received on thread: " + Thread.currentThread().getName())
//                    if(response.isSuccessful){
//                        emitter.onNext(response)
//                        emitter.onComplete()
//                    }
//                }
//
//            })

            try {
                var response = myOkHttps.newCall(request).execute()
                emitter.onNext(response)
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }

        var textTest = ""



        observable
            .subscribeOn(Schedulers.io())
            .doOnNext {
                Log.d("RxJava", "received on thread: " + Thread.currentThread().getName())
                var jsonaa = it.body
                var data = Gson().fromJson(jsonaa?.charStream(), User::class.java)
                Log.d("Rx Response :", data.login + " / " + data.id)
                textTest = data.login
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete{
                Log.d("RxJava :", "complete")
                Toast.makeText(this,"complete",Toast.LENGTH_SHORT).show()
                textHeaderRetrofit.text = textTest
            }
            .subscribe({
//                Log.d("RxJava", "received on thread: " + Thread.currentThread().getName())
//                var jsonaa = it.body
//                var data = Gson().fromJson(jsonaa?.charStream(), User::class.java)
//                Log.d("Rx Response :", data.login + " / " + data.id)
//                textTest = data.login
//                textHeaderRetrofit.text = textTest
            },{
                Log.d("Rx eer :", it.toString())
            })


    }
}
