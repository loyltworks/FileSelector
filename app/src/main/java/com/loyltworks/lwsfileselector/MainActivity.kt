package com.loyltworks.lwsfileselector

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.loyltworks.fileselector.FileSelector
import com.loyltworks.fileselector.FileSelectorCallBack
import com.loyltworks.fileselector.FileSelectorData
import com.loyltworks.fileselector.FileType
import com.loyltworks.lwsfileselector.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private  var binding:ActivityMainBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        //        ProgressDialogue.showDialog(this)

        binding!!.fileSelector.setOnClickListener {
            FileSelector.imageSize(512)
                .requiredFileTypes(FileType.ALL) // Optional
                .open(this, object : FileSelectorCallBack {
                    override fun onResponse(fileSelectorData: FileSelectorData) {


                        binding!!.image.setImageBitmap(fileSelectorData.thumbnail)
                        binding!!.fileName.text = fileSelectorData.fileName

                    }
                })
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }
}