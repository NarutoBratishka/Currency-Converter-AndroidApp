package ru.alexeysekatskiy.currencyconverter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Aleksey Sekatskiy
 */
public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {

    private final int itemsCount = CurrencyList.size;
//    private final Context parent;

    public CurrencyAdapter(/*Context parent*/) {
//        this.parent = parent;
    }

    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.currency_list_item_view;

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);

        CurrencyViewHolder viewHolder = new CurrencyViewHolder(view);

        /*Здесь можно задать повторяющиеся значения, которые будут присвоены ViewHolder константно*/

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CurrencyViewHolder holder, int position) {
        holder.bind(CurrencyList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemsCount;
    }

    class CurrencyViewHolder extends RecyclerView.ViewHolder {

        TextView listItemNameView;
        TextView listItemCharCodeView;

        public CurrencyViewHolder(View itemView) {
            super(itemView);

            listItemNameView = itemView.findViewById(R.id.tv_item_currency_name);
            listItemCharCodeView = itemView.findViewById(R.id.tv_item_currency_charcode);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
//                    @SuppressLint("DefaultLocale") String message = String.format("%.2f руб.", CurrencyList.get(position).getValue());
//
//                    Toast toast =  Toast.makeText(parent,
//                            message, Toast.LENGTH_SHORT);
//                    toast.show();

                    if (MainActivity.isRightActivity()) {
                        MainActivity.secondValute = CurrencyList.get(position);
                    } else {
                        MainActivity.firstValute = CurrencyList.get(position);
                    }

//                    changeCheckSym();
                }
            });
        }

        void bind(CurrencyBucket bucket) {
            listItemNameView.setText(bucket.getName());
            listItemCharCodeView.setText(bucket.getCharCode());
        }
    }
}
