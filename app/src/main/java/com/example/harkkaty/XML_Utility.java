package com.example.harkkaty;

import android.provider.DocumentsContract;
import android.util.EventLog;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

public class XML_Utility {

    private File file;
    private static XML_Utility xml = new XML_Utility();
    private DateC datec;

    //proxy strings
    private String Time, Amount;

    //string used for creating new xml instance
    String timeString, receiverString, amountString, messageString, entityString, rootString, event;

    //xml-accociated
    private Document dom;
    private Element ele, rootEle, childEle;
    private  DocumentBuilder db;

    private XML_Utility(){
        Time="";
        Amount="";
        ele = null;
        rootEle = null;
        timeString = "Time of event";
        receiverString = "Receiver";
        amountString = "Money";
        messageString = "Message";
        entityString = "The entity";
        rootString = "Events";
        event="Event";
        childEle=null;
    }

    public static XML_Utility getInstance(){return xml;}

    //used to add an event to xml
    public void addEvent(Date time, String receiver, double amount, String message, String entity) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            Time = datec.getSimpleDate(time);
            Amount = Double.toString(amount);
            Document document=null;
            db = dbf.newDocumentBuilder();
            file = new File("events.xml");
            rootEle = document.createElement(rootString);
            if(file.createNewFile()){
                document = db.parse(file);
            }else{
                document=createEventXml(document);//, Time, receiver, Amount, message, entity);

            }
            ele = document.createElement(event);
            childEle = document.createElement(timeString);
            childEle.appendChild(document.createTextNode(Time));
            ele.appendChild(childEle);
            childEle = document.createElement(receiverString);
            childEle.appendChild(document.createTextNode(receiver));
            ele.appendChild(childEle);
            childEle = document.createElement(amountString);
            childEle.appendChild(document.createTextNode(Amount));
            ele.appendChild(childEle);
            childEle = document.createElement(messageString);
            childEle.appendChild(document.createTextNode(message));
            ele.appendChild(childEle);
            childEle = document.createElement(entityString);
            childEle.appendChild(document.createTextNode(entity));

            rootEle.appendChild(ele);
            document.appendChild(rootEle);

        }catch (ParserConfigurationException e){

        }catch (TransformerException e){

        }catch (Exception e){

        }
    }

    //creates new xml.file
    private Document createEventXml(Document document){//, String time, String receiver, String amount, String message, String entity ){
        document = db.newDocument();
        document.appendChild(rootEle);
        
        return document;
    }


    //gets evetns based on accountID and returns them as a Accountevent list
    public ArrayList<AccountEvents> getEvents(String ID){
        ArrayList<AccountEvents> accEvents = new ArrayList<>();
        accEvents=null;

        return  accEvents;
    }
}
