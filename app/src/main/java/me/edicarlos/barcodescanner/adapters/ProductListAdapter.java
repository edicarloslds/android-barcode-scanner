package me.edicarlos.barcodescanner.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

import me.edicarlos.barcodescanner.R;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.WordViewHolder> {

    private final LinkedList<String> mProductList;
    private final LayoutInflater mInflater;

    class WordViewHolder extends RecyclerView.ViewHolder{

        public final TextView productItemView;
        final ProductListAdapter mAdapter;

        /**
         * Creates a new custom view holder to hold the view to display in the RecyclerView.
         *
         * @param itemView The view in which to display the data.
         * @param adapter The adapter that manages the the data and views for the RecyclerView.
         */
        public WordViewHolder(View itemView, ProductListAdapter adapter) {
            super(itemView);
            productItemView = (TextView) itemView.findViewById(R.id.product);
            this.mAdapter = adapter;
        }
    }

    public ProductListAdapter(Context context, LinkedList<String> solicitationList) {
        mInflater = LayoutInflater.from(context);
        this.mProductList = solicitationList;
    }

    /**
     * Inflates an item view and returns a new view holder that contains it.
     * Called when the RecyclerView needs a new view holder to represent an item.
     *
     * @param parent The view group that holds the item views.
     * @param viewType Used to distinguish views, if more than one type of item view is used.
     * @return a view holder.
     */
    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate an item view.
        View mItemView = mInflater.inflate(R.layout.product_item, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    /**
     * Sets the contents of an item at a given position in the RecyclerView.
     * Called by RecyclerView to display the data at a specificed position.
     *
     * @param holder The view holder for that position in the RecyclerView.
     * @param position The position of the item in the RecycerView.
     */
    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        // Retrieve the data for that position.
        String mCurrent = mProductList.get(position);
        // Add the data to the view holder.
        holder.productItemView.setText(mCurrent);
    }

    /**
     * Returns the size of the container that holds the data.
     *
     * @return Size of the list of data.
     */
    @Override
    public int getItemCount() {
        return mProductList.size();
    }
}
