import javafx.animation.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;

import java.util.Random;

public class Sides extends Projectille
{
   double goalXSpeed;
   double goalYSpeed;

   public Sides(double x, double y, double speedx_in, double speedy_in, boolean playerCreated, boolean leftOrRight)
   {
      super(x,y,3,5, playerCreated? Owner.PLAYER : Owner.ENEMY);
      goalXSpeed = speedx_in;
      goalYSpeed = speedy_in;
      speedx = leftOrRight ? 5 : -5;
      speedy = 0;
      damage = 5;
   }

   private double speedx;
   private double speedy;
   private static Random rand = new Random();
   
   public void run()
   {
      //way this works: starts firing one direction, then, by running average, moves it to only up.
      posX += /*Level.timeBetweenFrames*/speedx;
      posY += /*Level.timeBetweenFrames*/speedy;
      
      speedx = speedx*.95 + goalXSpeed*.05;
      speedy = speedy*.95 + goalYSpeed*.05;
      
      if(posX > 810 || posX < -10 || posY > 610 || posY < -10)
      {
         markToRemove();
      }
   }

   //drawing the Sides weapon
   static Color c = new Color(1,1,1,1);

   public void drawMe(GraphicsContext gc)
   {

         gc.setFill(c);
         gc.fillRect(posX - 1,posY,3,1);
         gc.fillRect(posX,posY -2.5,1,5);

   }
}