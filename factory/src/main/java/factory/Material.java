package factory;

/**
 * Created by y28yang on 6/14/2016.
 */
public class Material {

    private String name;
    private int requireNumber;

    public Material(String name,int requireNumber){
        this.name=name;
        this.requireNumber=requireNumber;
    }


    public String getName() {
        return name;
    }

    public int getRequireNumber() {
        return requireNumber;
    }

}
