public class shottingData
{
    String gun, xoFF, YoFF;

    String weaponItem;

    int speck, sides;

    public void setSpecks(int speck)
    {
        this.speck = speck;
    }

    public void setSides(int sides)
    {
        this.sides = sides;
    }

    public void setWeaponItem(String weaponItem)
    {
        this.weaponItem = weaponItem;
    }


    public int getSides()
    {
        return sides;
    }

    public int getSpecks()
    {
        return speck;
    }

    public String getWeaponItem()
    {
        return weaponItem;
    }
}
