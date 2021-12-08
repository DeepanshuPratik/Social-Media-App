package com.example.chatapp

import android.app.DownloadManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.Daos.PostDao
import com.example.chatapp.models.Post
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.core.Query
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: postAdapter
    private lateinit var postDao: PostDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addtask.setOnClickListener{
            val intent = Intent(this,createPost::class.java)
            startActivity(intent)
        }
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        postDao = PostDao()
        val postCollections = postDao.postCollections
        val query = postCollections.orderBy("createdAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
        val recyclerViewOptions = FirestoreRecyclerOptions.Builder<Post>().setQuery(query, Post::class.java).build()
        adapter = postAdapter(recyclerViewOptions)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}