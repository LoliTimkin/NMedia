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




    fun toConvert(count: Int): String  {
        return when (count) {
            in 0..999 -> count.toString()
            in 1_000..10_000 ->  convert1K(count)
            in 10_000..1000_000 ->  convert1M(count)
            else ->  "значение вне диапозона"
        }
    }

    fun convert1K(count: Int): String {
        val thousands =  count / 1_000
        if (count % 1_000 > 0) {
            val hundredth = count - thousands * 1_000
            val decimal = when (hundredth) {
                100 -> 1
                200 -> 2
                300 -> 3
                400 -> 4
                500 -> 5
                600 -> 6
                700 -> 7
                800 -> 8
                900 -> 9
                else ->  0
            }

        return if (decimal != 0) "$thousands,$hundredth K" else "$thousands K"

        }
        else return "$thousands K"

    }

    fun convert1M (count: Int): String {
        val millions: Int = count / 1_000_000
        return "$millions K"
    }

}
