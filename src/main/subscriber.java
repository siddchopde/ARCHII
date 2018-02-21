/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import static java.lang.Integer.max;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author siddharth
 */

//CREATE OBJECTS OF THIS CLASS TO CREATE NEW SUBSCRIBERS
//A OBJECT OF SERVER IS REQUIRED OR THIS CLASS WILL NOT RUN
public class subscriber {
    private HashMap<String,Integer> notesFetched; //Count of notes fetched from each topic
    private List<String> myNotes; //LIST OF ALL THE NOTES THAT ARE AVAILABLE IN MY DEVICE OR CACHE
    private String name; //NAME OF THE SUBSCRIBER
    subscriber() //ADD PARAMETERIZED CONSTRUCTOR ALSO
    {
        name = "UNKNOWM" + Integer.toString(resources.SUBSCRIBER_COUNT);
        resources.SUBSCRIBER_COUNT++;        
        notesFetched = new HashMap<>();
        myNotes = new LinkedList<>();
        //server.addMeToSubscriber(this);
    } 
    
    public void newNoteAvailable(topic currTopic)
    {
        System.out.print("NEW NOTE AVAILABLE FROM TOPIC: " + currTopic.topicName);
        this.fetchAvailableNote(currTopic);
    }
    
    //This method is based on observer pattern, so there is no need to provide a topic check.
    private void fetchAvailableNote(topic currTopic)
    {
        //To prevent null pointer exception i.e. if no notes of this topic are present it will give null value
        if(notesFetched.get(currTopic.topicName) == null)
            notesFetched.put(currTopic.topicName, 0);
        
        //GET ALL THE NEW NOTES AVAILABLE IN THE TOPIC
        List<String> temp = currTopic.getNotes(notesFetched.get(currTopic.topicName));
        
        //STACK THESE NOTES IN SUBSCRIBERS FEED
        myNotes.addAll(temp);
        //Update the size of notes fetched from a topic
        notesFetched.replace(currTopic.topicName, temp.size() + notesFetched.get(currTopic.topicName));        
    }
    
    //As this method is called only if you are subscribed to topic, it is safe to go without topic check
    public List<String> getNotesFromIndex(int fromIndex)
    {
        fromIndex = max(0,fromIndex);
        List<String> temp = new LinkedList<>();
        if(fromIndex > this.myNotes.size())
        {
            temp.add("FAILED TO FETCH MY NOTES");
            return temp;
        }
        else 
            return this.myNotes.subList(fromIndex, this.myNotes.size()); //REMEBER TO ADD TOTAL COUNT        
    }
    
    public void subscribeTopic(String topicName)
    {
        server.addSubscriberToTopic(topicName, this); //RETURNS STRING
    }
    
    public void removeSubscriptionFromTopic(String topicName)
    {
        server.removeSubscriptionToTopic(topicName, this); //RETURN STRING
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return this.name;
    }
}
