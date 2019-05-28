package me.edicarlos.barcodescanner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

public class SolicitationListAdapter extends RecyclerView.Adapter<SolicitationListAdapter.WordViewHolder> {

    private final LinkedList<String> mSolicitationList;
    private final LayoutInflater mInflater;

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView solicitationItemView;
        final SolicitationListAdapter mAdapter;

        /**
         * Creates a new custom view holder to hold the view to display in the RecyclerView.
         *
         * @param itemView The view in which to display the data.
         * @param adapter The adapter that manages the the data and views for the RecyclerView.
         */
        public WordViewHolder(View itemView, SolicitationListAdapter adapter) {
            super(itemView);
            solicitationItemView = (TextView) itemView.findViewById(R.id.solicitation);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // All we do here is prepend "Clicked! " to the text in the view, to verify that
            // the correct item was clicked. The underlying data does not change.
            solicitationItemView.setText ("Clicked! "+ solicitationItemView.getText());
        }
    }

    public SolicitationListAdapter(Context context, LinkedList<String> solicitationList) {
        mInflater = LayoutInflater.from(context);
        this.mSolicitationList = solicitationList;
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
        View mItemView = mInflater.inflate(R.layout.solicitation_item, parent, false);
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
        String mCurrent = mSolicitationList.get(position);
        // Add the data to the view holder.
        holder.solicitationItemView.setText(mCurrent);
    }

    /**
     * Returns the size of the container that holds the data.
     *
     * @return Size of the list of data.
     */
    @Override
    public int getItemCount() {
        return mSolicitationList.size();
    }
}
