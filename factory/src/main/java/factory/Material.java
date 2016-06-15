package factory;


public class Material {

    private String name;
    private int requireNumber;

    public Material(String name, int requireNumber) {
        this.name = name;
        this.requireNumber = requireNumber;
    }


    public String getName() {
        return name;
    }

    public int getRequireNumber() {
        return requireNumber;
    }

}
