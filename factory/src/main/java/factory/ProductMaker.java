package factory;

import java.util.Comparator;
import java.util.List;


public class ProductMaker {

    private List<SupplyIterator> materials;

    public ProductMaker(List<SupplyIterator> materialSupplies) {
        materials = materialSupplies;
    }

    public Supply createProductSupply(Product product) {
        Supply productSupply = new Supply(product.getId());
        while (hasAllKindSupplies()) {
            tryToMakeProduct(productSupply);
        }
        productSupply.sortPeriodsByStartTime();
        return productSupply;
    }

    private boolean hasAllKindSupplies() {
        return materials.stream().allMatch(i->i.hasNext());
    }

    private void tryToMakeProduct(Supply productSupply) {

        SupplyIterator latestStartPeriod = getLatestStartTimePeriod();
        SupplyIterator earliestEndPeriod = getEarliestEndTimePeriod();
        if (isPeriodIntersected(latestStartPeriod, earliestEndPeriod)) {
            createSupplyPeriod(latestStartPeriod, earliestEndPeriod, productSupply);
        }
        skipEarliestSupply(earliestEndPeriod);

    }

    private SupplyIterator getLatestStartTimePeriod() {
        return materials.stream().max(Comparator.comparingLong(i->i.getStartTimeMsec())).get();
    }


    private SupplyIterator getEarliestEndTimePeriod() {
        return materials.stream().min(Comparator.comparingLong(i->i.getEndTimeMsec())).get();
    }

    private boolean isPeriodIntersected(SupplyIterator latestStart, SupplyIterator earliestEnd) {
        return latestStart.getStartTimeMsec() < earliestEnd.getEndTimeMsec();
    }


    private void createSupplyPeriod(SupplyIterator latestStartPeriod, SupplyIterator earliestEndPeriod, Supply productSupply) {
        long startTime = latestStartPeriod.getStartTimeMsec();
        long endTime = earliestEndPeriod.getEndTimeMsec();
        int produceNumber = getMinProductNumber();
        productSupply.addNewSupplyPeriod(new SupplyPeriod(startTime, endTime, produceNumber));
    }

    private int getMinProductNumber() {
        return materials.stream().min(Comparator.comparing(i->i.getMaxProductCanMake())).get().getMaxProductCanMake();
    }


    private void skipEarliestSupply(SupplyIterator earliestEndPeriod) {
        earliestEndPeriod.nextPeriod();
    }


}
