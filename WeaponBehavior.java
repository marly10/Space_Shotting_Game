public abstract class WeaponBehavior
{
   protected boolean isPlayer;
   
   public WeaponBehavior(boolean isPlayer_in)
   {
      isPlayer = isPlayer_in;
   }

   public abstract void fire(double x, double y);
}