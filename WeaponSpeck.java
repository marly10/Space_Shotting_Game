public class WeaponSpeck extends Weapon
{
   public WeaponSpeck(int x, int y, Ship o, boolean isPlayer)
   {
      super(x,y, o,2);
      weps = new WeaponBehaviorSpeck(isPlayer);
   }

}