package co.com.monkeymobile.product_searcher.presentation.product_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.com.monkeymobile.product_searcher.R
import co.com.monkeymobile.product_searcher.databinding.ProductItemAdapterBinding
import co.com.monkeymobile.product_searcher.domain.model.Item
import com.bumptech.glide.Glide

class ItemAdapter(private val listener: ItemAdapterListener) :
    ListAdapter<Item, ItemAdapter.ItemViewHolder>(
        DiffCallback()
    ) {

    private class DiffCallback : DiffUtil.ItemCallback<Item>() {

        override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
    }

    interface ItemAdapterListener {
        fun onItemClicked(item: Item)
    }

    class ItemViewHolder(private val binding: ProductItemAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item, listener: ItemAdapterListener) {
            val context = binding.root.context

            binding.productId.text = context.getString(R.string.text_id_placeholder, item.id)
            binding.productTitle.text = item.title

            Glide.with(context)
                .load(item.imageUrl)
                .into(binding.thumbnail)

            binding.root.setOnClickListener { listener.onItemClicked(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
        ProductItemAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(getItem(position), listener)
}
