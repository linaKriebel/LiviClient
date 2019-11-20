public class World {

    public Coordinate playerPosition = new Coordinate(100, 100);
    public Coordinate player2Position = new Coordinate(100, 100);
    public Coordinate[] obstacles;

    public World(){
        obstacles = new Coordinate[10];

        for(int i=0; i<10; i++){
            obstacles[i] = new Coordinate(i*30, 10);
        }
    }
}
