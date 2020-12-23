import javafx.event.*;
import javafx.stage.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.scene.text.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.geometry.*;
import javafx.application.Application;
import java.io.*;
import java.util.*;
import java.text.*;

public class Iterator
{
   //various variables
   LinkedList llist;
   ListNode currentNode;
   
   public Iterator(LinkedList llist_in) //constructor
   {
      llist = llist_in;
      currentNode = llist.getHead();
   }
   
   public boolean hasNext() //checks if next node
   {
      if(currentNode.getNext() == null)
      {
         return false;
      }
      else
      {
         return true;
      }
   }
   
   public void next() //iterates through list
   {
      currentNode = currentNode.getNext();
   }
   
   
   public void remove() //removes currentNode and fixes links
   {
      boolean hasPrevious = false, hasNext = false;
      
      //setting flags
      if(currentNode.hasNext())
      {
         hasNext = true;
      }
      if(currentNode.hasPrevious())
      {
         hasPrevious = true;
      }
      
      
      //if next, but no previous (AKA, first node in the list; |A --> B -->...)
      if(hasNext == true && hasPrevious == false)
      {
         ListNode temp = currentNode.getNext();
         ListNode nothing = null;
         
         temp.setPrevious(null);
         llist.changeHead(nothing);
         llist.changeHead(temp); 
      }
      
      
      //if no next, but previous (AKA, last node in the list; ...--> A --> B|)
      if(hasNext == false && hasPrevious == true)
      {
         ListNode temp = currentNode.getPrevious();
      }
   }
}