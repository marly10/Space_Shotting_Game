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

public class Level {
   private static Canvas canvas;
   public static ArrayList < GameObject > objects = new ArrayList();

   public void setObjects(Drone d) {
      this.objects.add(d);
   }


   public static ArrayList<GameObject> getObjects() {
      return objects;
   }

   //probably should have just done the level as a singleton.
   public static boolean bl, br, bu, bd, fdown, rdown;
   public static Level theLevel;
   LinkedList llist =  new LinkedList();

   // instantiating Singleton class with variable x
   String filename;
   Drone d ;
   EnemyAiMovement eMove = new EnemyAiMovement();

   //static player
   Player playerFile = Player.getInstance();//implementation of Singlentoon


   HashMap<String,String> hMap = new HashMap<String,String>();

   DroneFactory droneFactory = new DroneFactory();


   public Level() {
      theLevel = this;
      read();
   }

   public void read() {
      try {
         Scanner scan = new Scanner(new File("level"));
         filename = scan.next();

         scan.next();

         int numOfShips = scan.nextInt();
         hMap.put("shipCount", String.valueOf(numOfShips));

         //ship
         for (int i = 0; i < numOfShips; i++) {
            String nameOfShip = scan.next();
            int size = scan.nextInt();
            hMap.put(nameOfShip + ".size", String.valueOf(size));
            String color = scan.next();
            hMap.put(nameOfShip + ".color", color);
            int hp = scan.nextInt();
            hMap.put(nameOfShip + ".hp", String.valueOf(hp));
            int moneyIfDestroyed = scan.nextInt();
            hMap.put(nameOfShip + ".moneyIfDestroyed", String.valueOf(moneyIfDestroyed));

            scan.next(); //weapon block

            int numweps = scan.nextInt();
            hMap.put(nameOfShip + ".numweps", String.valueOf(numweps));
            for (int j = 0; j < numweps; j++) {
               String namewep = scan.next();
               hMap.put(nameOfShip + ".namewep" + j, namewep);
               int xOffset = scan.nextInt();
               hMap.put(nameOfShip + ".xOffset" + j, String.valueOf(xOffset));
               int yOffset = scan.nextInt();
               hMap.put(nameOfShip + ".yOffset" + j, String.valueOf(yOffset));
            }

            scan.next(); //ai block
            int ainums = scan.nextInt();
            hMap.put(nameOfShip + ".ainums", String.valueOf(ainums));
            for (int j = 0; j < ainums; j++) {
               String direction = scan.next();
               hMap.put(nameOfShip + ".direction" + j, direction);
               int howManyFrames = scan.nextInt();
               hMap.put(nameOfShip + ".howManyFrames" + j, String.valueOf(howManyFrames));
               int speedOfMove = scan.nextInt();
               hMap.put(nameOfShip + ".speedOfMove" + j, String.valueOf(speedOfMove));
            }
         }

         String data = scan.next(); //data for level block

         String type = scan.next();

         //add our factory drone creation inside of this while statement to use the read in files
         while (!type.equals("end")) {
            float timeElapsed = scan.nextFloat();
            int x = scan.nextInt();
            type = scan.next();
         }

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   //on starting the game
   public void Start() {
      objects.add(playerFile);
      Random rand = new Random();
      int shipCount = Integer.parseInt(hMap.get("shipCount"));
      int numweps;

      //testing enemies
      for(int i = 0; i < shipCount; i++)
      {
         numweps = Integer.parseInt(hMap.get("ship"+ (i+1) + ".numweps"));
         //dd.setSpecks(Integer.parseInt(hMap.get("ship" + Integer.toString(i+1) + ".numweps")));
         //dd.setSpecks(Integer.parseInt(hMap.get("ship" + Integer.toString(i+1) + ".namewep")));
         for (int j = 0; j < numweps; j++)
         {
            d = droneFactory.createDrone(hMap.get("ship" + Integer.toString(i + 1) + ".namewep" + Integer.toString(j)),rand.nextInt(600)+100, rand.nextInt(100)+ 30, Integer.parseInt(hMap.get("ship" +(i + 1) + ".size")), 0, Color.web(hMap.get("ship" + Integer.toString(i + 1) + ".color")),
                    Integer.parseInt(hMap.get("ship" + Integer.toString(i + 1) + ".hp")));
            //llist.insert(d);
            objects.add(d);
            //setObjects(d);
         }

         //left, right, down, up, leftdown, leftup, rightdown, rightup
         switch (hMap.get("ship" + Integer.toString(i + 1) + ".direction" + Integer.toString(i)))
         {
            case "left":
               eMove.setEnemyAiMovement(new EnemyAiMovementLeft(d));
               break;

            case "right":
               eMove.setEnemyAiMovement(new EnemyAiMovementRight(d));
               break;

            case "down":
               eMove.setEnemyAiMovement(new EnemyAiMovementDown(d));
               break;

            case "up":
               eMove.setEnemyAiMovement(new EnemyAiMovementUp(d));
               break;

            case "leftdown":
               eMove.setEnemyAiMovement(new EnemyAiMovementLeftDown(d));
               break;

            case "leftup":
               eMove.setEnemyAiMovement(new EnemyAiMovementLeftUp(d));
               break;

            case "rightdown":
               eMove.setEnemyAiMovement(new EnemyAiMovementRightDown(d));
               break;

            case "rightup":
               eMove.setEnemyAiMovement(new EnemyAiMovementRightUp(d));
               break;
         }

      }
      for(Map.Entry m:hMap.entrySet()) {
         System.out.println(m.getKey()+" "+m.getValue());
      }
   }

   //on stopping the game
   public void Stop() {
      objects.clear();
      GameObject.clearCollision();
   }

   public boolean end() {
      return playerFile.getHp() <= 0;
   }

   public static Canvas getCanvas()
   {
      return canvas;
   }


   //main method the game loops over
   public static double timeBetweenFrames = 0;
   public void Update(Canvas theCanvas, double time) {
      timeBetweenFrames = time;

       this.canvas = theCanvas;

      for (int i = 0; i < objects.size(); i++) {
         objects.get(i).run();
      }


      for(int i = 0; i < llist.getSize(); i++) //llist version of above
      {
         //llist.getNode(i).d.run();
         ListNode testNode = llist.getNode(i);
         Drone testDrone = null;
         //System.out.println("this is test node: " + testNode); //testline
         //System.out.println("linkedlist size: " + llist.getSize() + "\n This is i: " + i);
         testDrone = testNode.d;
         testDrone.run();
      }

      GameObject.RunCollisions();



/*
for(int i = 0; i < llist.getSize(); i++) //llist version of below
      {
         if(llist.getNode(i).d.checkIfNoMore())
         {
            if(llist.getNode(i).d.doesCollide())
            {
               llist.getNode(i).d.unregisterCollisions();
            }

            llist.deleteNode(llist.getNode(i).d);
            i--;
         }
      }
*/

      for (int i = 0; i < objects.size(); i++) {
         if (objects.get(i).checkIfNoMore()) {
            if (objects.get(i).doesCollide()) {
               objects.get(i).unregisterCollisions();
            }

            objects.remove(i);
            i--;
         }
      }

      GraphicsContext gc = theCanvas.getGraphicsContext2D();
      gc.setFill(Color.GRAY);
      gc.fillRect(0, 0, 800, 700);

      for (GameObject object : objects) {
         object.drawMe(gc);
      }

      for(int i = 0; i < llist.getSize(); i++) //llist version
      {
         llist.getNode(i).d.drawMe(gc);
      }

      //only get the rdown for the first frame it happens
      Level.rdown = false;

      gc.setFill(Color.WHITE);

      gc.fillText("Money "+playerFile.getMoney(),40,40);
      if( playerFile.getSideShootMoneySpeck()<0 || playerFile.getSideShootMoneySide() < 0 || playerFile.getSideShootMoneySpeck()<0  && playerFile.getSideShootMoneySide() < 0)
      {
         int x = 0;
         gc.fillText("Speck Ammo: "+ x,40,60);//Prints the current ammo on FX
         gc.fillText("Side Ammo: " + x,40,80);//Prints the current
      }
      else
      {
         gc.fillText("Speck Ammo: "+ playerFile.getSideShootMoneySpeck(),40,60);//Prints the current ammo on FX
         gc.fillText("Side Ammo: "+ playerFile.getSideShootMoneySide(),40,80);//Prints the current ammo on FX
         gc.fillText("Track Ammo: "+ playerFile.getSideShootMoneyTrack(),40,100);//Prints the current am
      }

      gc.fillText("Enemy HP: "+ playerFile.getHp(),650,60);//Prints the current ammo on FX

   }

   //helper methods for creating the weps
   public void createSpeck(double x, double y, double speedx, double speedy, boolean playerCreated) {
      Speck s = new Speck(x, y, speedx, speedy, playerCreated);
      objects.add(s);
   }

   public void createSides(double x, double y, double speedx, double speedy, boolean playerCreated, boolean isLeft) {
      Sides s = new Sides(x, y, speedx, speedy, playerCreated, isLeft);

      objects.add(s);
   }
}