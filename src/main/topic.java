/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author siddharth
 */

//THIS CLASS DOES NOT NEED TO BE INSTANTIATED IT IS DONE BY THE SERVER
public class topic {
    List<String> notes;
    private int notesCount = 0;
    String topicName = resources.TOPIC_NOT_DEFINED;
    topic(String newTopic) throws Exception
    {
        this.topicName = newTopic;
        if(resources.TOPIC_COUNT < Integer.MAX_VALUE)
            resources.TOPIC_COUNT = min(0,resources.TOPIC_COUNT++);
        else
            throw new Exception("MAX TOPIC LIMIT REACHED");
    }    
    public void addNote(String newNote)
    {
        notes.add(newNote);
        notesCount++;
    }   
    
    public int getNotesCount()
    {
        return this.notesCount;
    }
    
    public List<String> getNotes(int fromIndex)
    {
        fromIndex = max(0,fromIndex); //To Prevent negative indexing
        List<String> tempList = new LinkedList<>();        
        if(fromIndex > this.notesCount)
        {
            tempList.add(topicName + ": OOPS FAILED TO FETCH");
            return tempList;
        }
        else 
            return this.notes.subList(fromIndex, notesCount);         
    }
}
