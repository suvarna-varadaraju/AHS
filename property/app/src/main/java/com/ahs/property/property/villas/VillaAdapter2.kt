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

class VillaAdapter2 (private val heroList: ArrayList<ResidentModel>, val context: Context,
                    private val listener: OnItemClickListener
) : RecyclerView.Adapter<VillaAdapter2.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = ResidentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(heroList[position])
        if(position==(getItemCount()-1)){
            //holder.itemView.logo.visibility = View.GONE
        }

       /* holder.itemView.logo.setOnClickListener {
            listener.onItemClick(
                position,3
            )
        }*/
        holder.itemView.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://ahs-properties.com/project/sun-rays/"))
            context.startActivity(browserIntent)
        }
        val animZoomIn1 = AnimationUtils.loadAnimation(context,
            com.ahs.property.R.anim.zoomin_out)
        holder.itemView.image.startAnimation(animZoomIn1)

        /* holder.itemBinding.logo.setOnClickListener{
             val mBuilder = MaterialAlertDialogBuilder(context,
                 R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_Centered
             )
             mBuilder.setView(
                 LayoutInflater.from(context).inflate(com.ahs.property.R.layout.dailog, null)
             )

             val mDialog = mBuilder.create()
             mDialog.show()

             val download = mDialog.findViewById<TextView>(com.ahs.property.R.id.download)
             val viewproject = mDialog.findViewById<TextView>(com.ahs.property.R.id.viewproject)
             val enquire = mDialog.findViewById<MaterialButton>(com.ahs.property.R.id.enquire)

             mDialog.setCanceledOnTouchOutside(true)
             download?.setOnClickListener {
                 if(position == 0){
                     var download= context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                     var PdfUri = Uri.parse("https://ahs-properties.com/wp-content/uploads/2023/05/Amara-L22-EMRHLS-AHS-Properties-1_compressed.pdf")
                     var getPdf = DownloadManager.Request(PdfUri)
                     getPdf.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                     download.enqueue(getPdf)
                     Toast.makeText(context,"Download Started", Toast.LENGTH_LONG).show()
                 }
                 if(position == 1){
                     var download= context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                     var PdfUri = Uri.parse("https://ahs-properties.com/wp-content/uploads/2023/05/One-Canal-Brochure_compressed.pdf")
                     var getPdf = DownloadManager.Request(PdfUri)
                     getPdf.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                     download.enqueue(getPdf)
                     Toast.makeText(context,"Download Started", Toast.LENGTH_LONG).show()
                 }
                 if(position == 2){
                     var download= context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                     var PdfUri = Uri.parse("https://ahs-properties.com/wp-content/uploads/2023/05/One-Crescent-Brochure_compressed.pdf")
                     var getPdf = DownloadManager.Request(PdfUri)
                     getPdf.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                     download.enqueue(getPdf)
                     Toast.makeText(context,"Download Started", Toast.LENGTH_LONG).show()
                 }
                 mDialog.dismiss()
             }

             viewproject?.setOnClickListener {
                 if(position == 0){
                     val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ahs-properties.com/project/amara/"))
                     context.startActivity(browserIntent)
                 }
                 if(position == 1){
                     val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ahs-properties.com/project/one-canal/"))
                     context.startActivity(browserIntent)
                 }
                 if(position == 2){
                     val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://1onecrescent.com/"))
                     context.startActivity(browserIntent)
                 }

                 mDialog.dismiss()
             }

             enquire?.setOnClickListener {
                 mDialog.dismiss()
             }
         }*/
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