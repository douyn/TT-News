package com.doudou.toutiao.ui.home.model;

/**
 * Created by Administrator on 2017/2/25.
 */

public class NewsChannelModel {
    /************************TYPE****************************/
    // 头条TYPE
    public static final String HEADLINE_TYPE = "headline";
    // 房产TYPE
    public static final String HOUSE_TYPE = "house";
    // 其他TYPE
    public static final String OTHER_TYPE = "list";
    /************************TYPE****************************/

    /************************NAME****************************/
    // NAME 保存在/res/value/new_channel
    /************************NAME****************************/

    /************************TYPE****************************/
    // NAME 保存在/res/value/new_channel
    /************************TYPE****************************/

    private String newsChannelName;
    private String newsChannelId;
    private String newsChannelType;

    private String newsChannelSelect;
    private int newsChannelIndex;
    private String newsChannelFixed;

    public NewsChannelModel () {
        super();
    }

    public NewsChannelModel (String newsChannelName) {
        this.newsChannelName = newsChannelName;
    }

    public NewsChannelModel (String newsChannelName, String newsChannelId, String newsChannelType, String newsChannelSelect, int newsChannelIndex, String newsChannelFixed) {
        this.newsChannelName = newsChannelName;
        this.newsChannelId = newsChannelId;
        this.newsChannelType = newsChannelType;
        this.newsChannelSelect = newsChannelSelect;
        this.newsChannelIndex = newsChannelIndex;
        this.newsChannelFixed = newsChannelFixed;
    }

    public String getNewsChannelName() {
        return newsChannelName;
    }

    public void setNewsChannelName(String newsChannelName) {
        this.newsChannelName = newsChannelName;
    }

    public String getNewsChannelId() {
        return newsChannelId;
    }

    public void setNewsChannelId(String newsChannelId) {
        this.newsChannelId = newsChannelId;
    }

    public String getNewsChannelSelect() {
        return newsChannelSelect;
    }

    public void setNewsChannelSelect(String newsChannelSelect) {
        this.newsChannelSelect = newsChannelSelect;
    }

    public int getNewsChannelIndex() {
        return newsChannelIndex;
    }

    public void setNewsChannelIndex(int newsChannelIndex) {
        this.newsChannelIndex = newsChannelIndex;
    }

    public String getNewsChannelFixed() {
        return newsChannelFixed;
    }

    public void setNewsChannelFixed(String newsChannelFixed) {
        this.newsChannelFixed = newsChannelFixed;
    }

    public String getNewsChannelType() {
        return newsChannelType;
    }

    public void setNewsChannelType(String newsChannelType) {
        this.newsChannelType = newsChannelType;
    }

    public static String getType (String id) {
        switch (id) {
            case "T1348647909107": // 头条
                return HEADLINE_TYPE;
            case "5YyX5Lqs": // 房产
                return HOUSE_TYPE;
            default: // 其他类型
                return OTHER_TYPE;
        }
    }
}
