import javafx.scene.paint.*;

public class DroneFactory
{
     String weps;
     double x,y;
     int size,hp;
     float speed;
     Color color;


    static double xData = 0;
    public  Drone createDrone(String weps, double x, double y, int size, float speed, Color color, int hp)
    {
        this.weps = weps;
        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = speed;
        this.color = color;
        this.hp = hp;


        xData = x;
        Player xx = Player.getInstance();

        Drone d = new Drone(x,y,size,0,color);
        Enemy e;

        if(weps.equals("speck")) {
            e = new EnemySpeck(d);
            e.shootTarget();

        }
        if(weps.equals("sides")) {
            e = new EnemySides(d);

            e.shootTarget();

        }
        return d;
    }

    public  int getHps() {
        return hp;
    }

    void setHp()
    {
        this.hp = 100;
    }

    public String getWeps() {
        return weps;
    }

    public  double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public  int getSize() {
        return size;
    }

    public  float getSpeed() {
        return speed;
    }

    public  Color getColor() {
        return color;
    }

    double getFile(){
        return xData;
    }
}
