public class weaponTemp
{
    private String wepName;
    private int x;
    private int y;

    public weaponTemp(String newName, int newX, int newY)
    {
        this.wepName = newName;
        this.x = newX;
        this.y = newY;
    }

    public String getName() {
        return wepName;
    }

    public float getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
