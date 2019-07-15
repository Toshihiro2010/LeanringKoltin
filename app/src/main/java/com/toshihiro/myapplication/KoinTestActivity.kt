package com.toshihiro.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.toshihiro.myapplication.myDagger.UserPreference
import kotlinx.android.synthetic.main.activity_koin_test.*
import org.koin.android.ext.android.inject

class KoinTestActivity : AppCompatActivity() {

    private val repo : UserPreference by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_koin_test)

        repo.saveUserName("Toshihiro")

        btnKoinGet.setOnClickListener{
            var name = repo.getUserName()
            Toast.makeText(this,"text : " + name , Toast.LENGTH_LONG).show()

        }

        btnKoinSave.setOnClickListener{
//            repo.saveUserName("Tv")
        }
    }
}
