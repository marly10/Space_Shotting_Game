import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.Random;

public class Drone extends Ship
{
   Weapon weps;
   float speed;
   Color color;
   String weaponItem;
   Player o;
   double hp = 20;
   Drone d;
   DroneFactory dd = new DroneFactory();


   boolean newEnemy;
   ShopPane sp = new ShopPane();

   double moveX=0;
   double moveY=0, randomInt = 0, x1,y1;

   int droneX = 0, droneY = 0;
   //static player
   Player mm = Player.getInstance();

   int speck , sides, sides1, sides2;
   private Object Drone;

   public void setSpecks(int speck)
   {
      this.speck = speck;
   }

   public void setSides(int sides)
   {
      this.sides = sides;
   }

   public void setWeaponItem(String weaponItem)
   {
      this.weaponItem = weaponItem;
   }


   public int getSides()
   {
      return sides;
   }

   public int getSpecks()
   {
      return speck;
   }

   public String getWeaponItem()
   {
      return weaponItem;
   }

   public Drone(double x, double y, int size, float speed, Color color)
   {
      super(x,y,20,20, 100, Projectille.Owner.ENEMY,true); //true in this case is if it collides
      this.speed = speed;
      this.color = color;
      this.sides1 = size;
      this.sides2 = size;
      this.x1 = x;
      this.y1 = y;
      o = Player.getInstance();
   }

   public void setMoveX(int droneX)
   {
      this.droneX = droneX;
   }

   public void setMoveY(int droneY)
   {
      this.droneY = droneY;
   }
   Weapon rightW, leftW;
   public void theWeapon(Weapon rightW, Weapon leftW)
   {
      this.rightW = rightW;
      this.leftW = leftW;
   }
   public void setWeapon(Weapon w)
   {
      weps = w;
   }

   int framecount = 0;

   public void run()
   {
      boolean ds = false;
      framecount += 1;
      posX += droneX;
      posY += droneY;
      //observer pattern implementation
      //checking to see if button pushed in shop pane
      if(mm.getSideShootMoneyTrack() > 0)
      {
         if (posX > o.getPosX() - 100 && posX < o.getPosX() + 100)
         {
            if (posY > o.getPosY() - 100 && posY < o.getPosY() + 100)
            {
               System.out.println("Close to player "+getHp());
               //damage reduction to match  per second
               deductHp(2);
               mm.decreaseTrack(1);

               if(getHp() <= 0 )
               {
                  ds = true;
                  markToRemove();
                  //new Drone( droneX,  droneY,  speed,  color);
                  setHp(100);
               }
            }
         }
      }


      if(weps != null)
      {
         weps.run();
      }


      //posX+=moveX;//*Level.timeBetweenFrames;
      //posY+=moveY;//*Level.timeBetweenFrames;
      Random rand = new Random();

      int n = rand.nextInt(50);
      int nd = rand.nextInt(540);

      double m = 0, h = 0;
      m = x1+posX;
      h = y1+posY;

      if(posX < 10)
      {
         droneX = droneX * -1;
         m = 10;
      }
      if(posX > 790)
      {
         droneX = droneX * -1;
         m = 790;
      }
      if(posY > 590)
      {
         droneY = droneY * -1;
         h = 590;
      }
      if(posY < 10)
      {
         droneY = droneY * -1;
         h = 10;
      }

      if(getHp() <= 5)
      {

         Canvas canvas = new Canvas();
         GraphicsContext gc;
         Level l;
         gc = Level.getCanvas().getGraphicsContext2D();
         Drone yu = new Drone( m,  h,  sides1,  speed,  color);
         Enemy e;

         Level.getObjects().add(yu);

         mm.increaseMoneyTrack(dd.getHps());
      }

   }


   //draw a simple shape for the player
public void drawMe(GraphicsContext gc)
   {
      gc.setFill(color);
      gc.fillRect(posX - 10, posY-10,sides1*2,sides2*2);
   }
}