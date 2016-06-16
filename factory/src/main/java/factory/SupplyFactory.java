package factory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class SupplyFactory {
    private Map<String, Supply> supplyMap = new HashMap<String, Supply>();


    public void addNewSupply(String materialName, Date startTime, Date endTime, int supplyNumber) {
        if (!supplyMap.containsKey(materialName)) {
            supplyMap.put(materialName, new Supply(materialName));
        }
        supplyMap.get(materialName).addNewSupplyPeriod(new SupplyPeriod(startTime, endTime, supplyNumber));
    }

    public void addNewSupply(String materialName, String startTime, String endTime, int supplyNumber) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy–MM–dd HH:mm:ss");
        Date startDate = format.parse(startTime);
        Date endDate = format.parse(endTime);
        addNewSupply(materialName, startDate, endDate, supplyNumber);
    }

    public void postPrepareSupply() {
        for (Supply supply : supplyMap.values()) {
            supply.sortPeriodsByStartTime();
        }
    }

    public Supply getValidProductSupply(Product product) {
        List<Material> requiredMaterials = product.getRequiredMaterials();
        if (requiredMaterials.size() == 0) {
            throw new ProduceFailedException("the product of " + product.getId() + " did not require any material.");
        }

        List<SupplyIterator> materialSupplyIterators = getMaterialSuppliesIterator(requiredMaterials);
        ProductMaker productMaker = new ProductMaker(materialSupplyIterators);
        return productMaker.createProductSupply(product);
    }


    private Supply getSupplyByName(String materialName) {
        Supply supply = supplyMap.get(materialName);
        if (supply == null)
            throw new ProduceFailedException("can't find any material supply for " + materialName);
        return supply;
    }

    private List<SupplyIterator> getMaterialSuppliesIterator(List<Material> requiredMaterials) {
        List<SupplyIterator> materialSupplies = new ArrayList<SupplyIterator>();
        for (Material material : requiredMaterials) {
            Supply materialSupply = this.getSupplyByName(material.getName());
            materialSupplies.add(new SupplyIterator(materialSupply, material));
        }

        return materialSupplies;
    }
}
