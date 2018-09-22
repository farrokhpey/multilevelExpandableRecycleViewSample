package ir.mahfa.multilevelexpandablerecycleviewsample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Farrokh on 22/09/2018.
 */

public class lastCategoriesAdapter extends RecyclerView.Adapter<lastCategoriesAdapter.categoriesViewHolder> {

    Context mContext;
    ArrayList<Categories> mCategoriesList;
    int mLevel;

    public lastCategoriesAdapter(Context context, ArrayList<Categories> categoriesList, int level) {
        this.mContext = context;
        this.mCategoriesList = categoriesList;
        this.mLevel = level;
    }

    @NonNull
    @Override
    public lastCategoriesAdapter.categoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        switch (mLevel) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_level_0, parent, false);
                break;
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_level_1, parent, false);
                break;
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_level_2, parent, false);
                break;
            default:
                break;
        }
        return new categoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull lastCategoriesAdapter.categoriesViewHolder holder, int position) {
        holder.bind(mContext, mCategoriesList.get(position), mLevel);
    }

    @Override
    public int getItemCount() {
        return mCategoriesList.size();
    }

    static class categoriesViewHolder extends RecyclerView.ViewHolder {
        TextView text_title;
        RecyclerView inner_recyclerview;

        categoriesViewHolder(View itemView) {
            super(itemView);
            text_title = itemView.findViewById(R.id.text_title);
            inner_recyclerview = itemView.findViewById(R.id.inner_recyclerview);
        }

        void bind(Context context, Categories item, int level) {
            text_title.setText(item.name);
            switch (level) {
                case 0:
                    if (item.categories != null) {
                        lastCategoriesAdapter adapter=new lastCategoriesAdapter(context, item.categories, 1);
                        inner_recyclerview.setLayoutManager(new LinearLayoutManager(context));
                        inner_recyclerview.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
                        inner_recyclerview.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                    break;
                case 1:
                    if (item.categories != null) {
//                        inner_recyclerview.setAdapter(new categoriesAdapter(context, item.subCategories, 2));
                    }
                    break;
                case 2:
                    break;
                default:
                    break;
            }
        }
    }
}
