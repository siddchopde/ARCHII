/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author siddharth
 */
//INSATANTIATE THIS OBJECT FIRST BEFORE ANY OTHER CLASSES IN THIS PACKAGE
public class server {
    private static List<subscriber> subscriberList; //List of all pressent subscriber
    private static List<publisher> publisherList; //List of all publishers
    //private List<topic> topicList;    //List of all Topics
    private static HashMap<topic, List<publisher>> topicToPublisher;
    private static HashMap<String,topic> findTopic;
    private static HashMap<topic, List<subscriber>> topicToSubscriber; //Mapping subscribers to their chosen topics.
    private static HashMap<String, String> noteForTopic;
    server()
    {
        subscriberList = new LinkedList<>();
        publisherList = new LinkedList<>();
        //topicList = new LinkedList<>();
        topicToSubscriber = new HashMap<>();
        topicToPublisher = new HashMap<>();
        noteForTopic = new HashMap<>();
        findTopic = new HashMap<>();
    }
    
    public static void main(String args[])
    {
        System.out.println("Starting");
        //START BY CREATING A OBJECT OF SERVER HERE OR CREATE A NEW CLASS and instantiate there
        // ONCE THE SERVER IS ON 
        //CREATE GO ON BY CREATING MULTIPLE SUBSCRIBERS AND PUBLISHERS
    }
    
    public void addTopic(String newTopic) throws Exception
    {        
        findTopic.put(newTopic, new topic(newTopic));
        //topicList.add(new topic(newTopic));
    }
    
    public static void notifySubscribers(topic currTopic)
    {
        for(subscriber s : topicToSubscriber.get(currTopic))
        {
            s.newNoteAvailable(currTopic);
        }
    }
    
    public static void addNoteToTopic(String currentTopic, String note) throws Exception
    {
        //If topic is not present add a new entry for topic
        if(!findTopic.containsKey(currentTopic))
            findTopic.put(currentTopic, new topic(currentTopic));     
        
        //add the new note to the respective topic
        findTopic.get(currentTopic).addNote(currentTopic);
        
        //Notify the subscribers that there are new notes available
        notifySubscribers(findTopic.get(currentTopic));
    }   
     
    
    public static String addSubscriberToTopic(String topicToSubscribe, subscriber subscriberToAdd)
    {
        //CHECK IF TOPIC IS PRESENT OR NOT
        if(findTopic.get(topicToSubscribe) != null)
        {
            topicToSubscriber.get(findTopic.get(topicToSubscribe)).add(subscriberToAdd);
            return "ADDED TO THE TOPIC" + topicToSubscribe;
        }
        return "NO SUCH TOPIC PRESENT";
    }
    
    public static String removeSubscriptionToTopic(String topicToSubscribe, subscriber subscriberToRemove)
    {
        //CHECK IF TOPIC IS PRESENT OR NOT
        if(findTopic.get(topicToSubscribe) != null)
        {
            topicToSubscriber.get(findTopic.get(topicToSubscribe)).remove(subscriberToRemove);
        }        
        return "NO SUCH TOPIC PRESENT";
    }    
    
    public static void addPublisherToTopic(String topicName, publisher publisherObject)
    {
        if(!topicToPublisher.get(findTopic.get(topicName)).contains(publisherObject))
            topicToPublisher.get(findTopic.get(topicName)).add(publisherObject);
    }
    
    public static boolean removePublisherFromTopic(String topicName, publisher publisherObject)
    {
        return topicToPublisher.get(findTopic.get(topicName)).remove(publisherObject);        
    }
    
    //MAY HAVE TO REMOVE THIS
    public static void addMeToSubscriber(subscriber newSubscriber)
    {
        subscriberList.add(newSubscriber);
    }
}
