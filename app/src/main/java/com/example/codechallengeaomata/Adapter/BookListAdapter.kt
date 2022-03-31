package com.example.codechallengeaomata.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.codechallengeaomata.Activity.FullScreenActivity
import com.example.codechallengeaomata.Model.VolumeInfo
import com.example.codechallengeaomata.R
import kotlinx.android.synthetic.main.recycler_list_row.view.*


class BookListAdapter(var context: Context) : RecyclerView.Adapter<BookListAdapter.MyViewHolder>() {

    var bookListData = ArrayList<VolumeInfo>()

    var mContext =context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListAdapter.MyViewHolder {
        val inflater =   LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_list_row,
            parent,
            false
        )
        return MyViewHolder(inflater)
    }


    override fun onBindViewHolder(holder: BookListAdapter.MyViewHolder, position: Int) {
        holder.bind(mContext, bookListData[position])

    }

    override fun getItemCount(): Int {
        return bookListData.size
    }


    class MyViewHolder(view: View) :RecyclerView.ViewHolder(view){

        val thumbImageView =view.thumbImageView

        fun bind(context: Context, data: VolumeInfo){
            val imageThumbnail =data.volumeInfo.imageLinks.smallThumbnail

            try {

                Glide.with(context)
                    .load(imageThumbnail)
                    .dontAnimate()
                    .into(thumbImageView)
            }
            catch (e: Exception){

            }

            thumbImageView.setOnClickListener {
                AlertDialog.Builder(context)
                    .setTitle("Open Picture")
                    .setMessage("Do you want to see the picture in full screen")
                    .setPositiveButton("Yes",
                        DialogInterface.OnClickListener { dialog, which ->
                            val intent = Intent(context, FullScreenActivity::class.java)
                            intent.putExtra("image", imageThumbnail)
                            context.startActivity(intent)
                        })
                    .setNegativeButton("No", null)
                    .show()
            }
        }
    }
}