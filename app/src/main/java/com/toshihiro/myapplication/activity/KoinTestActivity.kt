package com.toshihiro.myapplication.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.toshihiro.myapplication.R
import com.toshihiro.myapplication.myDagger.UserPreference
import com.toshihiro.myapplication.service.GithubAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_koin_test.*
import org.koin.android.ext.android.inject
import retrofit2.Retrofit

class KoinTestActivity : AppCompatActivity() {

    private val repo : UserPreference by inject()
    private val retrofitClient : Retrofit by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_koin_test)

        repo.saveUserName("Toshihiro")

        btnKoinGet.setOnClickListener{
            var name = repo.getUserName()
            Toast.makeText(this,"text : " + name , Toast.LENGTH_LONG).show()

        }

        btnKoinSave.setOnClickListener{
            var dataAPI: GithubAPI = retrofitClient.create(GithubAPI::class.java)

            dataAPI.getUser("toshihiro2010")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if(it.isSuccessful){
                        var myData = it.body()?.name
                        Log.d("bent" , "Ok :" + myData)
                    }
                },{
                    Log.d("bent" , "error :" + it.message)
                })
        }
    }
}
