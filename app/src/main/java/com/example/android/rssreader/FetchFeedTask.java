package com.example.android.rssreader;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Xml;

import com.example.android.rssreader.model.FeedModel;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class FetchFeedTask extends AsyncTask<Void,Void,Boolean> {
    private String urlLink;
    private RecyclerView rvNews;
    private rvNewsAdapter rvNewsAdapter;
    private List<FeedModel> feedModelList;

    public FetchFeedTask(String urlLink, RecyclerView rvNews, rvNewsAdapter adapter) {
        this.urlLink = urlLink;
        this.rvNews = rvNews;
        this.rvNewsAdapter = adapter;
    }

    //TODO:Recordar colocar el swipelayoute true
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Boolean success) {
        if(success){

            rvNewsAdapter.insert(feedModelList);
            rvNews.setAdapter(rvNewsAdapter);
        }
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        if(TextUtils.isEmpty(urlLink)) return false;

        try {
            if(!urlLink.startsWith("http://") && !urlLink.startsWith("https://"))
                urlLink = "http://" + urlLink;

            URL url = new URL(urlLink);
            InputStream inputStream = url.openConnection().getInputStream();
            feedModelList = parseFeed(inputStream);
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return false;
    }

    private List<FeedModel> parseFeed(InputStream inputStream) throws XmlPullParserException, IOException {
        boolean isItem = false;
        String title = null,description = null,link =null;
        List<FeedModel> items = new ArrayList<>();
        try {
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
            xmlPullParser.setInput(inputStream,null);

            xmlPullParser.nextTag();

            while (xmlPullParser.next() != XmlPullParser.END_DOCUMENT){
                int eventType = xmlPullParser.getEventType();

                String name = xmlPullParser.getName();
                if(name==null) continue;

                if(eventType == XmlPullParser.END_TAG){
                    if(name.equalsIgnoreCase("item")){
                        isItem = false;
                    }
                    continue;
                }


                if(eventType == xmlPullParser.START_TAG){
                    if(name.equalsIgnoreCase("item")){
                        isItem = true;
                        continue;
                    }
                }

                String result = "";

                if(xmlPullParser.next() == XmlPullParser.TEXT){
                    result = xmlPullParser.getText();
                    xmlPullParser.nextTag();
                }

                if(name.equalsIgnoreCase("title")){
                    title = result;
                }else if(name.equalsIgnoreCase("link")){
                    link = result;
                }else if(name.equalsIgnoreCase("description")){
                    description = result;
                }

                if(title != null && link != null && description != null){
                    if(isItem){
                        FeedModel item = new FeedModel(link,title,description);
                        items.add(item);
                    }
                }

                title = null;
                description=null;
                link=null;
                isItem=false;

            }
        } finally {
            inputStream.close();
        }
        return items;

    }

}
