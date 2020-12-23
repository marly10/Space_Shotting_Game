public class Weapon
{
/*
   in my player...
   Weapon wepSpeckLeft = new WeaponSpeck(-5,0,this,true); - this is the object that owns a particular weapon
   Weapon wepSpeckRight = new WeaponSpeck(5,0,this,true);
   
   Weapon wepSidesLeft = new WeaponSides(-3,0,this,true);
   Weapon wepSidesRight = new WeaponSides(3,0,this,true);
   
*/

   protected int currentCoolDown;
   protected int maxCoolDown;
   
   WeaponBehavior weps;// weps is the variable for the Behavior

   private final int xoffset;
   private final int yoffset;
   
   Ship owner;

   public Weapon(int xoffset_in, int yoffset_in, Ship owner_in, int mcdi)
   {
      xoffset = xoffset_in;
      yoffset = yoffset_in;
      owner = owner_in;
      maxCoolDown = mcdi;
   }

   public Ship getOwner()
   {
      return owner;
   }

   public void run()
   {
      currentCoolDown--;
      if(currentCoolDown <= 0)
      {
         currentCoolDown = maxCoolDown;
         weps.fire(xoffset+owner.getPosX(),yoffset+owner.getPosY());
      }
   }
}