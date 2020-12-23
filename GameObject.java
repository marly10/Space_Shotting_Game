
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


public abstract class GameObject
{
   private static ArrayList<GameObject> collisions = new ArrayList<GameObject>();

   protected double posX,posY;
   protected double width,height;
   protected boolean doesCollide=false;
   private boolean removeMe=false;

   public double getPosX()
   {
      return posX;
   }

   public double getPosY()
   {
      return posY;
   }

   public GameObject(){}
   
   //posx,posy are the center of object
   public GameObject(double x, double y, double w, double h, boolean doesCollide_in)
   {
      posX = x-w/2;
      posY = y-h/2;
      width = w;
      height = h;
      doesCollide = doesCollide_in;
      if(doesCollide)
      {
         registerCollisionObject();
      }
   }
   
   //call if you want to mark something to be removed. Does not immediately happen
   public void markToRemove()
   {
      removeMe = true;
   }

   //method for determining if already to be removed
   public boolean checkIfNoMore()
   {
      return removeMe;
   }
   
   //method for returning if the object is collideable
   public boolean doesCollide()
   {
      return doesCollide;
   }

   //each GO should have registerCollision called if they care about collision
   public void registerCollisionObject()
   {
      doesCollide = true;
      collisions.add(this);
   }
   public void unregisterCollisions()
   {
      doesCollide = false;
      collisions.remove(this);
   }
   
   
   //probably should use kd-tree for colillision so its ~O(N*Log(N)) and not O(N^2).
   public static void RunCollisions()
   {
      for(int i=0;i<collisions.size();i++)
      {
         for(int k=i+1;k<collisions.size();k++)
         {
            if(collisions.get(i).collidesWith(collisions.get(k)))
            {
               GameObject a = collisions.get(i);
               GameObject b = collisions.get(k);
            
               a.onTriggerCollide(b);
               b.onTriggerCollide(a);    
            }
         }
      }
   }
   public static void clearCollision()
   {
      collisions.clear();
   }

   public abstract void run();
   public abstract void drawMe(GraphicsContext gc);
   public void onTriggerCollide(GameObject other)
   {
      
   }
   
   //implemention of the 2d square collision algorithm
   public boolean collidesWith(GameObject other)
   { 
      double topThis =  other.posY-other.height/2;
      double bottomThis = other.posY+other.height/2;
      double leftThis = other.posX-other.width/2;
      double rightThis = other.posX+other.width/2;
      double topOther = posY-height/2;
      double bottomOther = posY+height/2;
      double leftOther = posX-width/2;
      double rightOther = posX+width/2;
      
      return !(bottomThis < topOther ||
               topThis > bottomOther ||
               leftThis > rightOther ||
               rightThis < leftOther);
   }
}