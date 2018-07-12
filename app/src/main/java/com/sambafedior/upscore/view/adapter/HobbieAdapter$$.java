package com.sambafedior.upscore.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sambafedior.upscore.R;
import com.sambafedior.upscore.model.Hobbie;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

import static com.sambafedior.upscore.model.Hobbie.SECTION_TYPE;
import static com.sambafedior.upscore.model.Hobbie.TAG_TYPE;

public class HobbieAdapter$$ extends StatelessSection {
    private SectionedRecyclerViewAdapter sectionAdapter;
    String hobbie;
    String tag;
    List<String> list;
    Context context;
    private ItemlisteClickListener itemlisteClickListener = null;

    public HobbieAdapter$$(SectionParameters sectionParameters, String hobbie, List<String> list, Context context, ItemlisteClickListener itemlisteClickListener) {
        super(new SectionParameters.Builder(R.layout.item_section_hobbie)
                .headerResourceId(R.layout.item_tag_hobbie)
                .build());
        this.hobbie = hobbie;
        this.list = list;
        this.context = context;
        this.itemlisteClickListener = itemlisteClickListener;
    }

    @Override
    public int getContentItemsTotal() {
        return list.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ItemViewHolder itemHolder = (ItemViewHolder) holder;
        tag = list.get(position);
        itemHolder.tvTagHobbie.setText(tag);
        itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, String.format("Clicked on position #%s of Section %s",
                        sectionAdapter.getPositionInSection(itemHolder.getAdapterPosition()), hobbie),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
        headerHolder.tvSectionHobbie.setText(hobbie);
    }


    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvSectionHobbie;

        HeaderViewHolder(View view) {
            super(view);
            tvSectionHobbie = view.findViewById(R.id.tvSectionHobbie);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final View rootView;
        private final TextView tvTagHobbie;

        ItemViewHolder(View view) {
            super(view);
            rootView = view;
            tvTagHobbie = view.findViewById(R.id.tvTagHobbie);
            tvTagHobbie.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v == tvTagHobbie) {
                if (itemlisteClickListener != null) {
                    itemlisteClickListener.onItemClick(v, getAdapterPosition(), tag, hobbie);
                }
            }
        }
    }

    public interface ItemlisteClickListener {
        void onItemClick(View v, int adapterPosition, String tag, String hobbie);
    }
}
