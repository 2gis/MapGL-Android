package ru.dublgis.dgismobile.sdktestapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.dublgis.dgismobile.sdktestapp.menu.GroupButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonsList.addView(GroupButton(this, "Markers"))
        buttonsList.addView(GroupButton(this, "Clustering"))
        buttonsList.addView(GroupButton(this, "Object selection"))
    }
}