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

public class Speck extends Projectille
{
   public Speck(double x, double y, double speedx_in, double speedy_in, boolean playerCreated)
   {
      super(x,y,3,3, playerCreated ? Owner.PLAYER : Owner.ENEMY);
      
      speedx = speedx_in;
      speedy = speedy_in;
      damage = 1;
   }

   private double speedx;
   private double speedy;
   private static Random rand = new Random();
   
   public void run()
   {
      posX += /*Level.timeBetweenFrames*/speedx;  //once upone a time, i used the time between frames to determine how much movement was done. Looked aweful.
      posY += /*Level.timeBetweenFrames*/speedy;
      
      //if projectile is outside the bounds, then remove it.
      if(posX > 810 || posX < -10 || posY > 610 || posY < -10)
      {
         markToRemove();
      }
      //System.out.println(posX +" "+posY);
   }

   //draw a simple shape
   static Color c = new Color(1,1,1,1);

   public void drawMe(GraphicsContext gc)
   {
      gc.setFill(c);
      gc.fillRect(posX - 1,posY,3,1);
      gc.fillRect(posX,posY -1,1,3);
   }
}