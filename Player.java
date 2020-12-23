import javafx.scene.canvas.*;
import javafx.scene.paint.*;

public class Player extends Ship
{

   Weapon newGun;//instantiated newGun mood
   //Weapon.WeaponShoot md;

   double moveX=0;
   double moveY=0;

   public static int money = 100000;
   public static int sideShootMoney = 0, speckShootMoney = 0, trackShootMoney = 0;

   private static Player instance = null;

   //singleton implementation
   public static Player getInstance()
   {
      if (instance == null)
      {
         instance = new Player();
      }
      return instance;
   }//getInstance ends

   public void deductMoneySpeck(int weaponCost_in) //for spending money at shop - deducts/buys ammo money for Speck
   {
      money = money - weaponCost_in;
      speckShootMoney += weaponCost_in;
      
      if(money < 0)
      {
         money = 0;
      }
   }

   public void increaseMoneySpeck(int weaponCost_in) //for spending money at shop - increases/sells ammo money for Speck
   {
      money = money + weaponCost_in;
      speckShootMoney -= weaponCost_in;
      if(money < 0)
      {
         money = 0;
      }
   }

   public void deductMoneySide(int weaponCost_in) //for spending money at shop - deducts/buys ammo money for Sides
   {
      money = money - weaponCost_in;
      sideShootMoney += weaponCost_in;
      
      if(money < 0)
      {
         money = 0;
      }
   }

   public void increaseMoneySide(int weaponCost_in) //for spending money at shop - increases/sells ammo money for Sides
   {
      money = money + weaponCost_in;
      sideShootMoney -= weaponCost_in;
      
      if(money < 0)
      {
         money = 0;
      }
   }

   public void deductMoneyTrack(int weaponCost_in) //for spending money at shop - deducts/buys ammo money for Sides
   {
      money = money - weaponCost_in;
      trackShootMoney += weaponCost_in;

      if(money < 0)
      {
         money = 0;
      }
   }

   public void increaseMoneyTrack(int weaponCost_in) //for spending money at shop - increases/sells ammo money for Sides
   {
      money = money + weaponCost_in;
      trackShootMoney -= weaponCost_in;

      if(money < 0)
      {
         money = 0;
      }
   }

   public void decreaseTrack(int weaponCost_in) //for spending money at shop - increases/sells ammo money for Sides
   {
      trackShootMoney--;
   }

   public int getSideShootMoneySpeck()
   {
      return speckShootMoney;
   }
   public int getSideShootMoneySide()
   {
      return sideShootMoney;
   }
   public int getSideShootMoneyTrack()
   {
      return trackShootMoney;
   }

   public Player()
   {
      super(400,500,20,20, 100, Projectille.Owner.PLAYER);
   }

   public int getMoney()
   {
      return money;
   }

   public int getMoveY()
   {
      return (int)moveY;
   }

   public int getMoveX()
   {
      return (int)moveX;
   }

   public void run()
   {
      //movement code
      double lr=0;
      double ud=0;

      if(Level.bl)
      {
         lr -= 10;
      }
      if(Level.br)
      {
         lr += 10;
      }   
      if(Level.bu)
      {
         ud -= 10;
      }
      if(Level.bd)
      {
         ud += 10;
      }   

      moveX = moveX*.95 + lr*.05; //this is a "running average". smooths the movement... kind of.
      moveY = moveY*.95 + ud*.05;
      posX+=moveX;//*Level.timeBetweenFrames;
      posY+=moveY;//*Level.timeBetweenFrames;

      /*System.out.println("Number1: "+moveX);
      System.out.println("Number2: "+moveY);*/

      if(posX < 10)
      {
         posX = 10;
      }
      if(posX > 790)
      {
         posX = 790;
      }
      if(posY > 590)
      {
         posY = 590;
      }
      if(posY < 10)
      {
         posY = 10;
      }
      
      //weapon code
      if(Level.rdown)
      {
        selectedWeapon = (selectedWeapon+1)%2;
      }
      
      if(Level.fdown)
      {
         if(selectedWeapon==0)
         {
            framesCoolDown--;
            if(framesCoolDown <= 0)
            {
               //Level.theLevel.createSpeck(posX+5,posY,0,-10,true);leaving/adding safety code
               //Level.theLevel.createSpeck(posX-5,posY,0,-10,true);leaving/adding safety code
               if(getSideShootMoneySpeck() > 0) {
                  speckShootMoney--;//decreases the current money for ammo speckShootMoney
                  newGun = new WeaponSpeck(0, 0,this,true);
                  newGun.weps.fire(posX+5,posY);
                  newGun.weps.fire(posX-5,posY);

               /* •	Create a series of weapons for the player (Create a weapon class).
               •	Each weapon will have a behavior. A weapon also needs a position (offset from the ship’s position). Each weapon should have its only class.
               •	Each weapon should represent ONE projectile, not both that currently comes out of the player. This means you need two Speck weapons and two Sides weapons.
               •	You should be able to change which weapon is active by R. */
               }
               else{
                  System.out.println("Buy more Speck Ammo");//prints the need of ammo on terminal
               }
               framesCoolDown = framesCoolDownMaxSpeck; //firing rate?
            }
         }
         else
         {
            framesCoolDown--;
            if(framesCoolDown <= 0)
            {  
               //Level.theLevel.createSides(posX+3,posY,0,-10,true,true); leaving/adding safety code
               //Level.theLevel.createSides(posX-3,posY,0,-10,true,false); leaving/adding safety code
               if(getSideShootMoneySide() > 0) {
                  sideShootMoney--;//decreases the current money for ammo sideShootMoney

                  newGun = new WeaponSides(0, 0,this, true);//dr moods implementation
                  newGun.weps.fire(posX-3,posY);//dr moods implementation
                  newGun = new WeaponSides(10, 0,this,true);//dr moods implementation
                  newGun.weps.fire(posX+3,posY);//dr moods implementation

               }
               else{
                  System.out.println("Buy more Side Ammo");//prints the need of ammo on terminal
               }
               framesCoolDown = framesCoolDownMaxSides;
            }       
         }
      }//  if(Level.fdown)
   }

   @Override
   public double getPosX(){//overwrittem/Override abstract class(gameobject posX)
      return this.posX;
   }

   @Override
   public double getPosY(){//overwrittem/Override abstract class(gameobject posY)
      return this.posY;
   }

   int selectedWeapon = 0;
   
   int framesCoolDown = 0;
   int framesCoolDownMaxSpeck = 2;
   int framesCoolDownMaxSides = 3;
   int framesCoolDownMaxTrack =3;

   //draw a simple shape for the player
   static Color c = new Color(1,0,1,1);
   public void drawMe(GraphicsContext gc)
   {
      gc.setFill(c);
      gc.fillRect(posX - 10, posY,20,1);
      gc.fillRect(posX, posY -10,1,20);
   }
}