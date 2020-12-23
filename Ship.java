import javafx.scene.canvas.GraphicsContext;

public abstract class Ship extends GameObject
{
   protected double hp;
   protected double data;
   Player op;
   //demonstrating how to use "inner" definitions (if you used public inner classes)
   private Projectille.Owner o;
   
   public Ship(double  x, double y, double w, double h, double hp_in, Projectille.Owner o_in)
   {
      super(x,y,w,h,true);
      hp = hp_in;
      o = o_in;
   }

   public Ship(double  x, double y, double w, double h, double hp_in, Projectille.Owner o_in, boolean b) {
      super(x,y,w,h,true);
      hp = hp_in;
      o = o_in;
   }


   public double getHp()
   {
      return hp;
   }

   public void deductHp(double hpP) //sends mass message to the enemy /
   {
      hp -= hpP;
   }

   public void setHp(double v) //sends mass message to the enemy /
   {
      hp = v;
   }

   //overiding the onTriggerCollide method. The idea is that I have the ships do soething if they hit a projectile. Damages the ship and removes the projectile.
   public void onTriggerCollide(GameObject other)
   {
      if(other instanceof Projectille)
      {
         Projectille p = (Projectille) other;
         if(p.getOwner() != o)
         {
            hp -= p.getDamage();
            if(hp <= 0)
            {
               markToRemove();
            }
            p.markToRemove();
         }
      }
   }
}