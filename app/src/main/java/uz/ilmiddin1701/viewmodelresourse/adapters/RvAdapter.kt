package uz.ilmiddin1701.viewmodelresourse.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.ilmiddin1701.viewmodelresourse.databinding.ItemRvBinding
import uz.ilmiddin1701.viewmodelresourse.models.MyMoney

class RvAdapter(var list: ArrayList<MyMoney>): Adapter<RvAdapter.Vh>() {

    inner class Vh(var itemRvBinding: ItemRvBinding): ViewHolder(itemRvBinding.root){
        @SuppressLint("SetTextI18n")
        fun onBind(money: MyMoney){
            itemRvBinding.currencyName.text = money.CcyNm_UZ
            itemRvBinding.currencyAmount1.text = "${money.Nominal} ${money.Ccy}"
            itemRvBinding.currencyDate.text = money.Date
            itemRvBinding.currencyAmount2.text = "${money.Rate} so'm"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }
}