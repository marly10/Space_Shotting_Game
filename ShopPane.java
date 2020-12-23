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

public class ShopPane extends VBox
{
   Button plus = new Button("+");;
   Button minus = new Button("-");
   Label amt = new Label("0");
   Label title = new Label("");
   Label costLabel = new Label("");
   public static boolean trackSwitch = false; //tracker switch

   int intAmt=0;
   public static int count = 0;//code to  change the player cash
   public static String overStr;
   public static String id;

   //static player
   Player x = Player.getInstance();

   public ShopPane()
   {

   }
   //setup the shop
   public ShopPane(String name, int cost)
   {
      count++;//code to  change the player cash
      title.setText(name);
      costLabel.setText(""+cost);
      amt.setText(""+0);
      
      HBox row = new HBox();

      getChildren().add(title);
      getChildren().add(costLabel);


      row.getChildren().add(minus);
      row.getChildren().add(amt);
      row.getChildren().add(plus);

      getChildren().add(row);
      
      minus.setOnAction(new ButtonListener());  
      plus.setOnAction(new ButtonListener());

      overStr = Integer.toString(count);//code to  change the player cash

      minus.setId("minus_"+overStr);//code to  change the player cash
      plus.setId("plus_"+overStr);//code to  change the player cash
      
      setAlignment(Pos.TOP_CENTER);
   }
   //methods for changing and getting the track weapon in the button 
   public void setTrackTrue()
   {
      trackSwitch = true;
   }
   public void setTrackFalse()
   {
      trackSwitch = false;
   }
   public boolean getTrack()
   {
      return trackSwitch;
   }
   //handlder for the buttons
   public class ButtonListener implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)      
      {
         id = ((Node)e.getSource()).getId();

         if(e.getSource() == plus)
         {
            intAmt++;
            amt.setText(""+intAmt);
         }
         if(e.getSource() == minus)
         {
            intAmt--;
            amt.setText(""+intAmt);
            //x.deductMoney(10); //figure out differentiating costs
         }

         //reduces & add money for buying guns.
         if(id.equals("minus_1"))//minus_1 button is pressed
         {
            x.increaseMoneySpeck(50); //figure out differentiating costs
            System.out.println(x.getMoney()+" Button: "+id+" Made: "+count);//used to check and make sure it works

            System.out.println("Ammo Cash Speck: "+x.getSideShootMoneySpeck());
            System.out.println("Ammo Cash Sides: "+x.getSideShootMoneySide());
            System.out.println("Ammo Cash Sides: "+x.getSideShootMoneyTrack());
         }
         if(id.equals("minus_2"))//minus_2 button is pressed
         {
            //weapon for the track weapon possible bugs here 
            x.increaseMoneyTrack(5000); //figure out differentiating costs
            System.out.println(x.getMoney()+" Button: "+id+" Made: "+count);//used to check and make sure it works

            System.out.println("Ammo Cash Speck: "+x.getSideShootMoneySpeck());
            System.out.println("Ammo Cash Sides: "+x.getSideShootMoneySide());
            System.out.println("Ammo Cash Sides: "+x.getSideShootMoneyTrack());
            //System.out.println(x.getMoney()+" Button: "+id+" Made: "+count);//used to check and make sure it works
            //System.out.println("Ammo Cash Speck:"+x.getSideShootMoneySpeck());
         }

         if(id.equals("minus_3"))//minus_3 button is pressed
         {
            x.increaseMoneySide(500); //figure out differentiating costs
            System.out.println(x.getMoney()+" Button: "+id+" Made: "+count);//used to check and make sure it works

            System.out.println("Ammo Cash Speck: "+x.getSideShootMoneySpeck());
            System.out.println("Ammo Cash Sides: "+x.getSideShootMoneySide());
            System.out.println("Ammo Cash Sides: "+x.getSideShootMoneyTrack());

         }

         if(id.equals("plus_1"))//minus_1 button is pressed
         {
            x.deductMoneySpeck(100); //figure out differentiating costs
            System.out.println(x.getMoney()+" Button: "+id+" Made: "+count);//used to check and make sure it works
            System.out.println("Ammo Cash Speck: "+x.getSideShootMoneySpeck());
            System.out.println("Ammo Cash Sides: "+x.getSideShootMoneySide());
            System.out.println("Ammo Cash Sides: "+x.getSideShootMoneyTrack());
         }
         if(id.equals("plus_2"))//plus_2 button is pressed
         {
            //weapons intergration for the track weapon
            x.deductMoneyTrack(10000); //figure out differentiating costs

            System.out.println("Ammo Cash Speck: "+x.getSideShootMoneySpeck());
            System.out.println("Ammo Cash Sides: "+x.getSideShootMoneySide());
            System.out.println("Ammo Cash Sides: "+x.getSideShootMoneyTrack());
         }
         if(id.equals("plus_3"))//minus_3 button is pressed
         {
            x.deductMoneySide(1000); //figure out differentiating costs
            System.out.println(x.getMoney() + " Button: " + id + " Made: " + count);//used to check and make sure it works

            System.out.println("Ammo Cash Speck: " + x.getSideShootMoneySpeck());
            System.out.println("Ammo Cash Sides: " + x.getSideShootMoneySide());
            System.out.println("Ammo Cash Sides: " + x.getSideShootMoneyTrack());
         }
         
      }
   }
   
}