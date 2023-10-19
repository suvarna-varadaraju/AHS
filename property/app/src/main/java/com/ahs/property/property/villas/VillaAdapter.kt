package com.ahs.property.property.villas

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.ahs.property.databinding.ResidentItemBinding
import com.ahs.property.property.Model.ResidentModel
import com.ahs.property.property.resident.ResidentAdapter
import kotlinx.android.synthetic.main.resident_item.view.image

class VillaAdapter (private val heroList: ArrayList<ResidentModel>, val context: Context,
                    private val listener: OnItemClickListener
) : RecyclerView.Adapter<VillaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = ResidentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(heroList[position])
        if(position==(getItemCount()-1)){
            //holder.itemView.logo.visibility = View.GONE
        }

/*
        holder.itemView.logo.setOnClickListener {
            listener.onItemClick(
                position,1
            )
        }
*/

        holder.itemView.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://ahs-properties.com/project/amara/"))
            context.startActivity(browserIntent)
        }

        val animZoomIn1 = AnimationUtils.loadAnimation(context,
            com.ahs.property.R.anim.zoomin_out)
        holder.itemView.image.startAnimation(animZoomIn1)
    }

    override fun getItemCount(): Int {
        return heroList.size
    }

    interface OnItemClickListener {
        fun onItemClick(position:Int,whichview : Int)
    }

    class ViewHolder(var itemBinding: ResidentItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(hero: ResidentModel) {
            itemBinding.image.setImageResource(hero.image)
        }
    }
}