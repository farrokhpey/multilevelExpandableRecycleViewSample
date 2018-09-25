package ir.mahfa.multilevelexpandablerecycleviewsample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
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

public class categoriesAdapter extends RecyclerView.Adapter<categoriesAdapter.categoriesViewHolder> {

    private Context mContext;
    private ArrayList<Categories> mCategoriesList;
    private int mLevel;

    categoriesAdapter(Context context, ArrayList<Categories> categoriesList, int level) {
        this.mContext = context;
        this.mCategoriesList = categoriesList;
        this.mLevel = level;
    }

    @NonNull
    @Override
    public categoriesAdapter.categoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
    public void onBindViewHolder(@NonNull categoriesAdapter.categoriesViewHolder holder, int position) {
        holder.bind(mContext, mCategoriesList.get(position), mLevel);
    }

    @Override
    public int getItemCount() {
        return mCategoriesList.size();
    }

    static class categoriesViewHolder extends RecyclerView.ViewHolder {
        TextView text_title;
        RecyclerView inner_recyclerview;
        AppCompatImageView image_expandCollapse;

        categoriesViewHolder(View itemView) {
            super(itemView);
            text_title = itemView.findViewById(R.id.text_title);
            inner_recyclerview = itemView.findViewById(R.id.inner_recyclerview);
            image_expandCollapse = itemView.findViewById(R.id.image_expandCollapse);
        }

        void bind(final Context context, Categories item, int level) {
            text_title.setText(item.name);
            switch (level) {
                case 0:
                    if (item.categories != null) {
                        categoriesAdapter adapter = new categoriesAdapter(context, item.categories, 1);
                        inner_recyclerview.setLayoutManager(new LinearLayoutManager(context));
                        inner_recyclerview.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
                        inner_recyclerview.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                    break;
                case 1:
                    if (item.categories != null) {
                        categoriesAdapter adapter = new categoriesAdapter(context, item.categories, 2);
                        inner_recyclerview.setLayoutManager(new GridLayoutManager(context, 2));
                        inner_recyclerview.setAdapter(adapter);
                        if (item.expanded) {
                            inner_recyclerview.setVisibility(View.VISIBLE);
                            image_expandCollapse.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_arrow_drop_up_black_24dp));
                        } else {
                            inner_recyclerview.setVisibility(View.GONE);
                            image_expandCollapse.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_arrow_drop_down_black_24dp));
                        }
                        adapter.notifyDataSetChanged();
                        image_expandCollapse.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (inner_recyclerview.getVisibility() == View.VISIBLE) {
                                    inner_recyclerview.setVisibility(View.GONE);
                                    image_expandCollapse.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_arrow_drop_down_black_24dp));
                                } else {
                                    inner_recyclerview.setVisibility(View.VISIBLE);
                                    image_expandCollapse.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_arrow_drop_up_black_24dp));
                                }
                            }
                        });
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public void expandGroup(int groupPosition, int childPosition) {
        if (mCategoriesList != null &&
                mCategoriesList.size() > groupPosition &&
                mCategoriesList.get(groupPosition).categories != null &&
                mCategoriesList.get(groupPosition).categories.size() > childPosition) {
            mCategoriesList.get(groupPosition).categories.get(childPosition).expanded = true;
            notifyItemChanged(groupPosition);
        }
    }
}
