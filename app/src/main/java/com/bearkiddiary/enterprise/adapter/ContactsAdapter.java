package com.bearkiddiary.enterprise.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bearkiddiary.enterprise.R;
import com.bearkiddiary.enterprise.model.bean.Contact;
import com.bearkiddiary.enterprise.ui.view.CircleImageview;

import java.util.List;

/**
 * Created by YarenChoi on 2016/8/8.
 * 学生列表适配器
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    private List<Contact> dataList;
    private Context mContext;
    private View.OnClickListener onItemClickListener;

    public ContactsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_contacts, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.root.setTag(position);
        if (onItemClickListener != null) {
            holder.root.setOnClickListener(onItemClickListener);
        }

        //根据position获取分类的首字母的Char ascii值
        int section = getSectionForPosition(position);

        //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (position == getPositionForSection(section)) {
            holder.tv_letter.setVisibility(View.VISIBLE);
            holder.tv_letter.setText(dataList.get(position).getPingyin());
        } else {
            holder.tv_letter.setVisibility(View.GONE);
        }

        holder.tv_name.setText(this.dataList.get(position).getName());
        holder.iv_avatar.setImageResource(R.drawable.avatar);
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (dataList != null) {
            count = dataList.size();
        }
        return count;
    }

    /**
     * 刷新数据
     *
     * @param dataList 新的数据列表
     */
    public void refreshData(List<Contact> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的Char ascii值
     */
    public int getSectionForPosition(int position) {
        return dataList.get(position).getPingyin().charAt(0);
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getItemCount(); i++) {
            String sortStr = dataList.get(i).getPingyin();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout root;
        TextView tv_letter;
        TextView tv_name;
        CircleImageview iv_avatar;

        public ViewHolder(View view) {
            super(view);
            root = (RelativeLayout) view.findViewById(R.id.rl_contacts_root);
            tv_letter = (TextView) view.findViewById(R.id.tv_contacts_letter_title);
            tv_name = (TextView) view.findViewById(R.id.tv_contacts_name);
            iv_avatar = (CircleImageview) view.findViewById(R.id.iv_contacts_avatar);
        }
    }

}
