package com.leavessilent.mylibrary.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.test.TouchUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义的一个通用RecyclerAdapter
 * Created by Leavessilent on 2016/8/29.
 */
public abstract class UniversalAdapter<T> extends RecyclerView.Adapter<UniversalAdapter.RecyclerViewHolder> implements View.OnClickListener {

    private static final String TAG = UniversalAdapter.class.getSimpleName();
    protected List<T> mData;
    private LayoutInflater mInflater;
    private RecyclerView mRecyclerView;
    private View mItemView;

    public UniversalAdapter(Context context, List<T> data) {
        if (data == null) {
            mData = new ArrayList<>();
        } else {
            mData = data;
        }
        mInflater = LayoutInflater.from(context);
    }

    public UniversalAdapter(Context context) {
        this(context, null);
    }


    public void updateRes(List<T> data) {
        if (data != null) {
            mData.clear();
            mData.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void addDataList(List<T> data) {
        if (data != null) {
            mData.addAll(data);
            notifyDataSetChanged();
        }
    }


    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    public void add(T t, int positon) {
        if (t != null) {
            mData.add(t);
            notifyItemInserted(positon);
        }
    }

    public void remove(int positon) {
        mData.remove(positon);
        notifyItemRemoved(positon);
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(getItemLayoutID(viewType), parent, false);
        itemView.setOnClickListener(this);
        return new RecyclerViewHolder(itemView);
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        onBindDataToView(holder, mData.get(position), position);
    }

    public View getItemView() {
        return mItemView;
    }

    public T getItem(int position) {
        return mData.get(position);
    }

    /**
     * 通过viewType获取layoutId
     *
     * @param viewType
     * @return
     */
    public abstract int getItemLayoutID(int viewType);


    /**
     * 绑定数据到Item的空间中去
     *
     * @param holder
     * @param t
     */
    protected abstract void onBindDataToView(RecyclerViewHolder holder, T t, int position);

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onClick(View v) {
        mItemView = v;
        int position = mRecyclerView.getChildAdapterPosition(v);
        if (position >= 0 && position < mData.size()) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(position);
            }
        }
    }


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private View mItemView;
        private Map<Integer, View> mViewsCache;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mViewsCache = new HashMap<>();
        }

        public <T extends View> T getView(int viewId) {
            View view;
            if (mViewsCache.containsKey(viewId)) {
                view = mViewsCache.get(viewId);
            } else {
                view = mItemView.findViewById(viewId);
                mViewsCache.put(viewId, view);
            }
            return (T) view;
        }

        public TextView getTextView(int viewId) {
            return getView(viewId);
        }

        public ImageView getImageView(int viewId) {
            return getView(viewId);
        }


    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
