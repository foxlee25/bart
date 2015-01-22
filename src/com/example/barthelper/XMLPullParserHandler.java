package com.example.barthelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
 
public class XMLPullParserHandler {
    List<BartItem> bartItems;
    private BartItem bartItem;
    private String text;
 
    public XMLPullParserHandler() {
        bartItems = new ArrayList<BartItem>();
    }
 
    public List<BartItem> getbartItems() {
        return bartItems;
    }
 
    public List<BartItem> parse(InputStream is) {
        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;
        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();
 
            parser.setInput(is, null);
 
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                case XmlPullParser.START_TAG:
                    if (tagname.equalsIgnoreCase("etd")) {
                        // create a new instance of bartItem
                        bartItem = new BartItem();
                    }
                    break;
 
                case XmlPullParser.TEXT:
                    text = parser.getText();
                    break;
 
                case XmlPullParser.END_TAG:
                    if (tagname.equalsIgnoreCase("etd")) {
                        // add bartItem object to list
                        bartItems.add(bartItem);
                        // get name 
                    } /*else if (tagname.equalsIgnoreCase("name")) {
                        bartItem.setName(text);
                    }*else if (tagname.equalsIgnoreCase("abbr")) {
                        bartItem.setAbbr(text);
                    }*/ else if (tagname.equalsIgnoreCase("destination")) {
                        bartItem.setDestination(text);
                    } else if (tagname.equalsIgnoreCase("abbreviation")) {
                        bartItem.setAbbreviation(text);
                    } else if (tagname.equalsIgnoreCase("minutes")) {
                        bartItem.setMinutes(text);
                    } else if (tagname.equalsIgnoreCase("platform")) {
                        bartItem.setPlatform(text);
                    } else if (tagname.equalsIgnoreCase("direction")) {
                        bartItem.setDirection(text);
                    } else if (tagname.equalsIgnoreCase("length")) {
                        bartItem.setLength(text);
                    } else if (tagname.equalsIgnoreCase("color")) {
                        bartItem.setColor(text);
                    } else if (tagname.equalsIgnoreCase("hexcolor")) {
                        bartItem.setHexcolor(text);
                    } else if (tagname.equalsIgnoreCase("bikeflag")) {
                        bartItem.setBikeflag(text);
                    }
                    
                    
                    break;
 
                default:
                    break;
                }
                eventType = parser.next();
            }
 
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        return bartItems;
    }
}