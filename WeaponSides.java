public class WeaponSides extends Weapon
{
   public WeaponSides(int x, int y, Ship o,boolean isPlayer)
   {
      super(x,y, o,3);
      weps = new WeaponBehaviorSides(isPlayer,x > 0);
   }
}