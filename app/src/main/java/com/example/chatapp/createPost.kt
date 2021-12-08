package com.example.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.chatapp.Daos.PostDao
import kotlinx.android.synthetic.main.activity_create_post.*

class createPost : AppCompatActivity() {

    private lateinit var postDao : PostDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
        postDao = PostDao()
        submit.setOnClickListener{
            val input = post.text.toString().trim()
            if(input.isNotEmpty()){
                postDao.addPost(input)
                Toast.makeText(this,"$input added as post!",Toast.LENGTH_LONG).show()
                finish()
            }

        }
    }
}