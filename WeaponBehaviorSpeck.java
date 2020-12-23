public class WeaponBehaviorSpeck extends WeaponBehavior
{
   public WeaponBehaviorSpeck(boolean isPlayer_in)
   {
      super(isPlayer_in);
   }

   public void fire(double x, double y)
   {
      Level.theLevel.createSpeck(x,y,0,isPlayer ? -10 : 10,isPlayer);
   }
}
