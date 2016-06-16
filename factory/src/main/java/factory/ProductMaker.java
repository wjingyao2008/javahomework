package factory;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;


public class ProductMaker {

    private Stream<SupplyIterator> materials;

    public ProductMaker(List<SupplyIterator> materialSupplies) {
        materials = materialSupplies.stream();
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
        return materials.allMatch(i->i.hasNext());
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
        return materials.max(Comparator.comparingLong(SupplyIterator::getStartTimeMsec)).get();
    }


    private SupplyIterator getEarliestEndTimePeriod() {
        return materials.min(Comparator.comparingLong(SupplyIterator::getEndTimeMsec)).get();
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
        return materials.min(Comparator.comparing(SupplyIterator::getMaxProductCanMake)).get().getMaxProductCanMake();
    }


    private void skipEarliestSupply(SupplyIterator earliestEndPeriod) {
        earliestEndPeriod.nextPeriod();
    }


}
