package ru.netology.nmedia

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.ActivityEditPostBinding

class EditPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEditPostBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_edit_post)
        binding.edit.requestFocus()
        binding.edit.setOnClickListener {
            intent?.let {
                if (it.action != Intent.ACTION_SEND) {
                    return@let
                }
                val text = intent?.getStringExtra(Intent.EXTRA_TEXT)
            }
        }
    }

}


