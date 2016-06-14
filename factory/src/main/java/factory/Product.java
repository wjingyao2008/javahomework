package factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by y28yang on 6/14/2016.
 */
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
