package edu.neu.madcourse.NUMAD21Fa_YuluYang;

public class LinkData {
    private final String mName;
    private final String mUrl;

    public LinkData(String name, String url) {
        mName = name;
        mUrl = url;
    }

    public String getName() {
        return mName;
    }

    public String getUrl() {
        return mUrl;
    }
}
