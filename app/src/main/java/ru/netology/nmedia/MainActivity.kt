package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false,
            sharedByMe = true
        )
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content

            if (post.likedByMe) {
                imageLikes?.setImageResource(R.drawable.ic_baseline_favorite_24)
            }
            likesAmount?.text = post.likes.toString()

            //root.setOnClickListener {
            //    Log.d("stuff", "stuff")
            //}

            //avatar.setOnClickListener {
            //    Log.d("stuff", "avatar")
            //}

            imageLikes?.setOnClickListener {
                //Log.d("stuff", "like")
                post.likedByMe = !post.likedByMe
                imageLikes.setImageResource(
                    if (post.likedByMe) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24
                )

                if (post.likedByMe) post.likes++ else post.likes--
                likesAmount?.text = post.likes.toString()
            }

            sharesAmount.text = post.shared.toString()

            imageShare?.setOnClickListener {
                if (post.sharedByMe) post.shared++
                sharesAmount?.text = ToConvert(post.shared)

            }

        }
    }
    data class Post(
        val id: Int,
        val author: String,
        val content: String,
        val published: String,
        var likedByMe: Boolean,
        var sharedByMe: Boolean,
        var likes: Int = 0,
        var shared: Int = 995,

    )


    fun ToConvert(count: Int): String  {
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
