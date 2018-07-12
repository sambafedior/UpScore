package com.sambafedior.upscore.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sambafedior.upscore.R;
import com.sambafedior.upscore.model.Hobbie;

import java.util.Collections;
import java.util.List;

import static com.sambafedior.upscore.model.Hobbie.SECTION_TYPE;
import static com.sambafedior.upscore.model.Hobbie.TAG_TYPE;

public class HobbieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Hobbie> hobbieList = Collections.emptyList();

    public HobbieAdapter(Context context, List<Hobbie> hobbieList) {
        this.context = context;
        this.hobbieList = hobbieList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case SECTION_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section_hobbie, parent, false);
                return new SectionHobbieHolder(view);

            case TAG_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag_hobbie, parent, false);
                return new SectionTagHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Hobbie hobbie = hobbieList.get(position);
        if (hobbie != null) {
            switch (hobbie.getType()) {
                case SECTION_TYPE:
                    ((SectionHobbieHolder) holder).tvSectionHobbie.setText(hobbie.getSection());
                    break;
                case TAG_TYPE:
                    ((SectionTagHolder) holder).tvTagHobbie.setText((CharSequence) hobbie.getTag());
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (hobbieList == null)
            return 0;
        return hobbieList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (hobbieList != null) {
            Hobbie hobbie = hobbieList.get(position);
            if (hobbie != null) {
                return hobbie.getType();
            }
        }
        return 0;
    }

    public static class SectionHobbieHolder extends RecyclerView.ViewHolder {
        TextView tvSectionHobbie;

        public SectionHobbieHolder(View itemView) {
            super(itemView);
            tvSectionHobbie = itemView.findViewById(R.id.tvSectionHobbie);
        }
    }

    public static class SectionTagHolder extends RecyclerView.ViewHolder {
        TextView tvTagHobbie;

        public SectionTagHolder(View itemView) {
            super(itemView);
            tvTagHobbie = itemView.findViewById(R.id.tvTagHobbie);
        }
    }
}
