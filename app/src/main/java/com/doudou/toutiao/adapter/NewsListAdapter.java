package com.doudou.toutiao.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.aspsine.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.doudou.toutiao.R;
import com.doudou.toutiao.app.Appcontext;
import com.doudou.toutiao.ui.home.news_list.NewsListModel;
import com.jaydenxiao.common.commonutils.DisplayUtil;

import java.util.List;

import static android.R.attr.type;

/**
 * Created by Administrator on 2017/2/25.
 */

public class NewsListAdapter extends MultiItemRecycleViewAdapter<NewsListModel> {

    public static final int TYPE_ITEM = 0;
    public static final int TYPE_PHOTO_ITEM =1;

    public NewsListAdapter (Context context, List<NewsListModel> list) {
        super(context, new MultiItemTypeSupport<NewsListModel>() {
            @Override
            public int getLayoutId(int itemType) {
                if(type == TYPE_PHOTO_ITEM){
                    return R.layout.item_news_photo;
                }else{
                    return R.layout.item_news;
                }
            }

            @Override
            public int getItemViewType(int position, NewsListModel msg) {
                if (!TextUtils.isEmpty(msg.getDigest()))
                {
                    return TYPE_ITEM;
                }
                return TYPE_PHOTO_ITEM;
            }
        });
    }

    @Override
    public void convert(ViewHolderHelper holder, NewsListModel newsSummary) {
        switch (holder.getLayoutId())
        {
            case R.layout.item_news:
                setItemValues(holder,newsSummary,getPosition(holder));
                break;
            case R.layout.item_news_photo:
                setPhotoItemValues(holder,newsSummary,getPosition(holder));
                break;
        }
    }

    /**
     * 普通样式
     * @param holder
     * @param newsSummary
     * @param position
     */
    private void setItemValues(final ViewHolderHelper holder, final NewsListModel newsSummary, final int position) {
        String title = newsSummary.getLtitle();
        if (title == null) {
            title = newsSummary.getTitle();
        }
        String ptime = newsSummary.getPtime();
        String digest = newsSummary.getDigest();
        String imgSrc = newsSummary.getImgsrc();

        holder.setText(R.id.news_summary_title_tv,title);
        holder.setText(R.id.news_summary_ptime_tv,ptime);
        holder.setText(R.id.news_summary_digest_tv,digest);
        holder.setImageUrl(R.id.news_summary_photo_iv,imgSrc);
        holder.setOnClickListener(R.id.rl_root, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
            }
        });
    }


    /**
     * 图文样式
     * @param holder
     * @param position
     */
    private void setPhotoItemValues(ViewHolderHelper holder, final NewsListModel newsSummary, int position) {
        String title = newsSummary.getTitle();
        String ptime = newsSummary.getPtime();
        holder.setText(R.id.news_summary_title_tv,title);
        holder.setText(R.id.news_summary_ptime_tv,ptime);
        setImageView(holder, newsSummary);
        holder.setOnClickListener(R.id.ll_root, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 查看照跳
                // TODO 跳转到 看照片
            }
        });
    }

    private void setImageView(ViewHolderHelper holder, NewsListModel newsSummary) {
        int PhotoThreeHeight = (int) DisplayUtil.dip2px(90);
        int PhotoTwoHeight = (int) DisplayUtil.dip2px(120);
        int PhotoOneHeight = (int)DisplayUtil.dip2px(150);

        String imgSrcLeft = null;
        String imgSrcMiddle = null;
        String imgSrcRight = null;
        LinearLayout news_summary_photo_iv_group=holder.getView(R.id.news_summary_photo_iv_group);
        ViewGroup.LayoutParams layoutParams = news_summary_photo_iv_group.getLayoutParams();

        if (newsSummary.getAds() != null) {
            List<NewsListModel.AdsBean> adsBeanList = newsSummary.getAds();
            int size = adsBeanList.size();
            if (size >= 3) {
                imgSrcLeft = adsBeanList.get(0).getImgsrc();
                imgSrcMiddle = adsBeanList.get(1).getImgsrc();
                imgSrcRight = adsBeanList.get(2).getImgsrc();
                layoutParams.height = PhotoThreeHeight;
                holder.setText(R.id.news_summary_title_tv, Appcontext.getInstance().getResources()
                        .getString(R.string.photo_collections, adsBeanList.get(0).getTitle()));
            } else if (size >= 2) {
                imgSrcLeft = adsBeanList.get(0).getImgsrc();
                imgSrcMiddle = adsBeanList.get(1).getImgsrc();

                layoutParams.height = PhotoTwoHeight;
            } else if (size >= 1) {
                imgSrcLeft = adsBeanList.get(0).getImgsrc();

                layoutParams.height = PhotoOneHeight;
            }
        } else if (newsSummary.getImgextra() != null) {
            int size = newsSummary.getImgextra().size();
            if (size >= 3) {
                imgSrcLeft = newsSummary.getImgextra().get(0).getImgsrc();
                imgSrcMiddle = newsSummary.getImgextra().get(1).getImgsrc();
                imgSrcRight = newsSummary.getImgextra().get(2).getImgsrc();

                layoutParams.height = PhotoThreeHeight;
            } else if (size >= 2) {
                imgSrcLeft = newsSummary.getImgextra().get(0).getImgsrc();
                imgSrcMiddle = newsSummary.getImgextra().get(1).getImgsrc();

                layoutParams.height = PhotoTwoHeight;
            } else if (size >= 1) {
                imgSrcLeft = newsSummary.getImgextra().get(0).getImgsrc();

                layoutParams.height = PhotoOneHeight;
            }
        } else {
            imgSrcLeft = newsSummary.getImgsrc();

            layoutParams.height = PhotoOneHeight;
        }

        setPhotoImageView(holder, imgSrcLeft, imgSrcMiddle, imgSrcRight);
        news_summary_photo_iv_group.setLayoutParams(layoutParams);
    }

    private void setPhotoImageView(ViewHolderHelper holder, String imgSrcLeft, String imgSrcMiddle, String imgSrcRight) {
        if (imgSrcLeft != null) {
            holder.setVisible(R.id.news_summary_photo_iv_left,true);
            holder.setImageUrl(R.id.news_summary_photo_iv_left,imgSrcLeft);
        } else {
            holder.setVisible(R.id.news_summary_photo_iv_left,false);
        }
        if (imgSrcMiddle != null) {
            holder.setVisible(R.id.news_summary_photo_iv_middle,true);
            holder.setImageUrl(R.id.news_summary_photo_iv_middle,imgSrcMiddle);
        } else {
            holder.setVisible(R.id.news_summary_photo_iv_middle,false);
        }
        if (imgSrcRight != null) {
            holder.setVisible(R.id.news_summary_photo_iv_right,true);
            holder.setImageUrl(R.id.news_summary_photo_iv_right,imgSrcRight);
        } else {
            holder.setVisible(R.id.news_summary_photo_iv_right,false);
        }
    }
}
