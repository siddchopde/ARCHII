/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author siddharth
 */

//CREATE OBJECTS OF THIS CLASS TO CREATE NEW PUBLISHERS
//A OBJECT OF SERVER IS REQUIRED OR THIS CLASS WILL NOT RUN
public class publisher {
    private String name;
    private int topicCount;
    private List<String> myPublishedNotes;//CAN BE MODIFIED TO KEEP TRACK OF TOPICS AND RESPECTIVE NOTES
    publisher()
    {
        name = "UNKNOWN_PUBLISHER_" + Integer.toString(resources.PUBLISHER_COUNT);
        topicCount = 0;
        myPublishedNotes = new LinkedList<>();
        resources.PUBLISHER_COUNT++;
    }
    
    public void newNote(String topic, String note)
    {
        try{
            server.addNoteToTopic(topic, note);
            myPublishedNotes.add(note);
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }        
    }
    
    //REGISTER THIS PUBLISHER TO A TOPIC
    public void registerToTopic(String topic)
    {
        server.addPublisherToTopic(topic, this);
        this.topicCount++;
    }
    
    //UNREGISTER THIS PUBLISHER FROM A TOPIC
    public void unregisterFromTopic(String topic)
    {
        //Just Additional Check to make system run much faster
        if(this.topicCount > 0)
        {
            server.removePublisherFromTopic(topic, this);
            this.topicCount--;
        }
        
    }
    
    //SET publosher name
    public void setName(String name)
    {
        this.name = name;
    }
    
    //get publisher name
    public String getName()
    {
        return this.name;
    }
    
}
