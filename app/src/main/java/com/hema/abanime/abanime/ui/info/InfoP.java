package com.hema.abanime.abanime.ui.info;

import com.hema.abanime.abanime.MVP.presenter.PMlistener;
import com.hema.abanime.abanime.MVP.presenter.Plistener;
import com.hema.abanime.abanime.MVP.ui.Vlistener;
import com.hema.abanime.abanime.utils.Md5;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.hema.abanime.abanime.net.api.BaseUrl.SECRETKEY_MINILOADER;

/**
 * Created by Homa on 2017/9/14.
 */

public class InfoP implements Plistener, PMlistener {

    private static final String TAG = "fanjuP";
    private Vlistener vlistener;
    private InfoM mlistener;

    private String FlowStr;
    private Map<String, String> map;
    private ArrayList<String> urls;
    private RxFragmentActivity aty;


    public InfoP(Vlistener viewListener) {
        this.vlistener = viewListener;
        mlistener=new InfoM(this);
    }

    @Override
    public void startPost(RxFragmentActivity rxFragmentActivity) {
        mlistener.startPost(rxFragmentActivity);
    }

    public void startPostVideoTag(RxFragmentActivity rxAppCompatActivity,String aid) {
        mlistener.startPostVideoTag(rxAppCompatActivity,aid);
    }
    public void startPostAVSearchHtml(RxFragmentActivity rxAppCompatActivity,String aid) {
        mlistener.startPostAVSearchHtml(rxAppCompatActivity,aid);
        aty=rxAppCompatActivity;
    }

    public void startPostAVVideoHtml(RxFragmentActivity rxAppCompatActivity,Map<String, String> map) {
        mlistener.startPostAVVideoHtml(rxAppCompatActivity,map);
    }

    @Override
    public void onNext(String resulte, final String mothead) {

        if (mothead.equals("getVideoTag")){
            FlowStr=resulte;
            vlistener.onNext("getVideoTag");
        }else if (mothead.equals("getBiliAVSearchHtml")){
            map =parseSearchUrl(resulte);
            startPostAVVideoHtml(aty,map);
        }else {
            urls=parseVideoUrl(resulte);
            vlistener.onNext("getBiliAVVideoHtml");
        }
    }

    @Override
    public void onError(ApiException e) {
        vlistener.onError(e);
    }

    public Map<String, String> parseSearchUrl(String body) {
        // 创建 Pattern 对象
        Pattern r = Pattern.compile("cid=([^&]+)");
        // 现在创建 matcher 对象
        Matcher m = r.matcher(body);
        String cid;
        if (m.find()) {
            cid = m.group(1);
        } else {
//        ToastUtil.ShortToast("未找到cid");
            return null;
        }

//    LogUtil.test("cid = " + cid );
        String sign = Md5.strToMd5Low32("cid=" + cid + "&from=miniplay&player=1" + SECRETKEY_MINILOADER);
        Map<String, String> map = new HashMap<>();
        map.put("cid", cid);
        map.put("from", "miniplay");
        map.put("player", "1");
        map.put("sign", sign);

        // 真实的视频源地址请求url
        return map;
    }

    public ArrayList<String> parseVideoUrl(String xml) {
        org.dom4j.Document document = null;
        try {
            document = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        assert document != null;
        Element rootElement = document.getRootElement();
        List<Element> elementList = rootElement.elements("durl");

        ArrayList<String> urlList = new ArrayList<>();

        for (Element element : elementList) {
            Element urlElement = element.element("url");
            String url = urlElement.getText();
            urlList.add(url);
        }

        return urlList;
    }

    public String getFlowStr() {
        return FlowStr;
    }

    public ArrayList<String> getUrls() {
        return urls;
    }
}
