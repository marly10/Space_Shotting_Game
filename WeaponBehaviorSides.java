public class WeaponBehaviorSides extends WeaponBehavior////actual Behavior implementation by extends weapon
{
   protected boolean isLeft;
   
   public WeaponBehaviorSides(boolean isPlayer_in, boolean isLeft_in)
   {
      super(isPlayer_in);
      isLeft = isLeft_in;
   }

   public void fire(double x, double y)
   {
      Level.theLevel.createSides(x,y,0,isPlayer ? -10 : 10,isPlayer,isLeft);
   }
}

