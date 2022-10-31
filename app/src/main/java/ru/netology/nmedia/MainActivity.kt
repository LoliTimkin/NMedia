package ru.netology.nmedia

import android.nfc.cardemulation.CardEmulation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.CardPostBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter (
            onLikeListener = {
                viewModel.likeById(it.id)
            },
            onShareListener = {
                viewModel.shareById(it.id)
            }
        )
        binding.list.adapter = adapter
        viewModel.data.observe(this ) { posts ->
            adapter.submitList(posts)
        }
   //                 imageLikes.setOnClickListener {
   //                     viewModel.likeById(post.id)
    //                }
   //                 imageShare.setOnClickListener {
    //                    viewModel.share()
    //                }
//
      //              likesAmount.text = toConvert(post.likes)
      //              sharesAmount.text = toConvert(post.shared)



    }
            //root.setOnClickListener {
            //    Log.d("stuff", "stuff")
            //}

            //avatar.setOnClickListener {
            //    Log.d("stuff", "avatar")
            //}

            //imageLikes?.setOnClickListener {
                //Log.d("stuff", "like")
            //}

}
