package ru.alexeysekatskiy.currencyconverter;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.util.ArrayList;
import java.io.StringReader;

public class XMLParser {

    private ArrayList<Post> posts;

    public XMLParser(){
        posts = new ArrayList<>();
    }

    public ArrayList<Post> getUsers(){
        return  posts;
    }

    public boolean parse(String xmlData){
        boolean status = true;
        Post currentPost = null;
        boolean inEntry = false;
        String textValue = "";

        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(new StringReader(xmlData));
            int eventType = xpp.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){

                String tagName = xpp.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        if("user".equalsIgnoreCase(tagName)){
                            inEntry = true;
                            currentPost = new Post();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(inEntry){
                            if("Valute".equals(tagName)){
                                posts.add(currentPost);
                                inEntry = false;
                            } else if("NumCode".equals(tagName)){
                                currentPost.setNumCode(textValue);
                            } else if("CharCode".equals(tagName)){
                                currentPost.setCharCode(textValue);
                            } else if("Nominal".equals(tagName)){
                                currentPost.setNominal(textValue);
                            } else if("Name".equals(tagName)){
                                currentPost.setName(textValue);
                            } else if("Value".equals(tagName)){
                                currentPost.setValue(textValue);
                            }
                        }
                        break;
                    default:
                }
                eventType = xpp.next();
            }
        }
        catch (Exception e){
            status = false;
            e.printStackTrace();
            Log.e("XMLParser", e.getMessage()); /////
        }
        return  status;
    }
}
