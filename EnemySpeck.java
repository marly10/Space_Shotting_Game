public class EnemySpeck extends Enemy
{
    public EnemySpeck(Drone dDrone)
    {
        this.dDrone = dDrone;
    }
    @Override
    public void shootTarget()
    {
        this.dDrone.setWeapon(new WeaponSpeck(5,0,dDrone,false));
    }
}


