package factory;

import java.util.ArrayList;
import java.util.List;


public class Product {
    private String id;
    private List<Material> materials = new ArrayList<Material>();

    public Product(String id)
        {
            this.id = id;
        }


    public String getId()
        {
            return id;
        }

    public void addNeededMaterial(Material material)
        {
            this.materials.add(material);
        }

     public List<Material> getRequiredMaterials(){
         return materials;
     }


}
