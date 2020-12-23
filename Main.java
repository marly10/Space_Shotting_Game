/*Project created by Spencer, Ricky and Chris*/
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


public class Main extends Application
{
   VBox root = new VBox();

    // instantiating Singleton class with variable x
    static Player x = Player.getInstance();

    Text money= new Text("");

    Text speckAmmo = new Text("");
    Text sideAmmo = new Text("");

   Stage theStage;

   Button start = new Button("Start");
   Button shop = new Button("Shop");
   Button back = new Button("Back");

   Scene menu;
   Scene game;
   Scene shopScene;

   private Canvas gameCanvas = new Canvas(800,600);

   public void setMenu(Stage stage)
   {
      stage.setScene(menu);
      stage.setTitle("Final");
      stage.show();
   }

   public void setShop(Stage stage)
   {
      stage.setScene(shopScene);
      stage.show();
   }


   public void start(Stage stage)
   {
   //creating the menu
         Label title = new Label("Raptor");
      title.setFont(Font.font ("Verdana", 34));

            Label subtitle = new Label("Its like the Dinosauce. Only not.");
      subtitle.setFont(Font.font ("Verdana", 18));

      Label spacer1 = new Label(" ");
      spacer1.setFont(Font.font ("Verdana", 52));

            Label spacer2 = new Label(" ");
      spacer2.setFont(Font.font ("Verdana", 10));


      start.setFont(Font.font ("Verdana", 14));
      shop.setFont(Font.font ("Verdana", 14));


      start.setPrefSize(150,30);
      shop.setPrefSize(150,30);



      root.getChildren().add(title);
      root.getChildren().add(subtitle);
      root.getChildren().add(spacer1);
      root.getChildren().add(start);
      root.getChildren().add(shop);


      root.setAlignment(Pos.TOP_CENTER);


      start.setOnAction(new ButtonListener());
      shop.setOnAction(new ButtonListener());

      theStage = stage;
      menu = new Scene(root,800,600);
      setMenu(stage);

      //creating the canvas
      VBox empty = new VBox();

      empty.getChildren().add(gameCanvas);

      game = new Scene(empty, 800, 600);

   //creating the shop
       ShopPane file = new ShopPane();

      HBox empty3 = new HBox();

      empty3.getChildren().add(new Label("                   "));
      empty3.getChildren().add(money);
      empty3.getChildren().add(new Label("                   "));
      empty3.getChildren().add(new ShopPane("Specks",100));
      empty3.getChildren().add(new Label("                   "));
      empty3.getChildren().add(new ShopPane("Track",10000));
      empty3.getChildren().add(new Label("                   "));
      empty3.getChildren().add(new ShopPane("Sides",1000));
      empty3.getChildren().add(new Label("                   "));

      empty3.getChildren().add(back);
      back.setOnAction(new ButtonListener());


      shopScene = new Scene(empty3, 800, 600);
      (new AnimationHandler()).start();

      gameCanvas.setOnKeyPressed(new KeyListenerDown());
      gameCanvas.setOnKeyReleased(new KeyListenerUp());
   }



   public static void main(String [] args)
   {
      launch(args);
   }


   //handler for the buttons
   public class ButtonListener implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {
         if(e.getSource() == start)
         {
            theStage.setScene(game);
            gameCanvas.requestFocus();

            current = new Level();
            current.Start();
         }
         if(e.getSource() == shop)
         {
             setShop(theStage);
         }
         if(e.getSource() == back)
         {
            setMenu(theStage);
         }
      }
   }



   public static Level current=null; // could have been a singleton.
   public class AnimationHandler extends AnimationTimer
   {
      long lastTime=-1;

      public void handle(long currentTimeInNanoSeconds)
      {

         if(lastTime != -1)
         {
            //frameStats.addFrame(currentTimeInNanoSeconds-lastTime);
            long t = (currentTimeInNanoSeconds-lastTime)/1000l;
            double time = t*1.0/100000;
            if(current!= null)
               current.Update(gameCanvas,time);
               money.setText("Current cash: "+ x.getInstance().getMoney());//changes the cash player has at Run time
               speckAmmo.setText("Speck Ammo: "+ x.getInstance().getSideShootMoneySpeck());//changes the cash player has at Run time
               sideAmmo.setText("Side Ammo: "+ x.getInstance().getSideShootMoneySide());//changes the cash player has at Run time
               sideAmmo.setText("Track Ammo: "+ x.getInstance().getSideShootMoneyTrack());//changes the cash player has at Run time


         }
         lastTime = currentTimeInNanoSeconds;

         if(current != null && current.end())
         {
               if(current != null)
              {
                  current.Stop();
              }
              setMenu(theStage);
              current = null;
         }
      }
   }


   //listeners to keep track of whether a key is up or down
   public class KeyListenerDown implements EventHandler<KeyEvent>
   {
      public void handle(KeyEvent event)
      {
          if (event.getCode() == KeyCode.UP)
          {
              Level.bu = true;
          }
          if (event.getCode() == KeyCode.DOWN)
          {
              Level.bd = true;
          }
          if (event.getCode() == KeyCode.LEFT)
          {
              Level.bl = true;
          }
          if (event.getCode() == KeyCode.RIGHT)
          {
              Level.br = true;
          }
          if (event.getCode() == KeyCode.F)
          {
              Level.fdown = true;
          }
          if (event.getCode() == KeyCode.R)
          {
              Level.rdown = true;
          }
          if (event.getCode() == KeyCode.Q)
          {
              if(current != null)
              {
                  current.Stop();
              }
              setMenu(theStage);
          }
      }
   }
   public class KeyListenerUp implements EventHandler<KeyEvent>
   {
      public void handle(KeyEvent event)
      {
          if (event.getCode() == KeyCode.UP)
          {
              Level.bu = false;
          }
          if (event.getCode() == KeyCode.DOWN)
          {
              Level.bd = false;
          }
          if (event.getCode() == KeyCode.LEFT)
          {
              Level.bl = false;
          }
          if (event.getCode() == KeyCode.RIGHT)
          {
              Level.br = false;
          }
          if (event.getCode() == KeyCode.F)
          {
              Level.fdown = false;
          }
          if (event.getCode() == KeyCode.R)
          {
              Level.rdown = false;
          }
      }
   }
}