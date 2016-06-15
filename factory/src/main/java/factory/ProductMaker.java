package factory;

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
        boolean hasSupply = true;
        for (SupplyIterator supplyIterator : materials) {
            hasSupply = hasSupply && (supplyIterator.hasNext());
        }
        return hasSupply;
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
        long latestStartTime = Long.MIN_VALUE;
        SupplyIterator period = null;
        for (SupplyIterator supplyIterator : materials) {
            long startTime = supplyIterator.getCurrentPeriod().getStartTimeMsec();
            if (startTime > latestStartTime) {
                latestStartTime = startTime;
                period = supplyIterator;
            }
        }
        return period;
    }


    private SupplyIterator getEarliestEndTimePeriod() {
        long earliestEndTime = Long.MAX_VALUE;
        SupplyIterator period = null;
        for (SupplyIterator supplyIterator : materials) {
            long endTime = supplyIterator.getCurrentPeriod().getEndTimeMsec();
            if (endTime < earliestEndTime) {
                earliestEndTime = endTime;
                period = supplyIterator;
            }
        }
        return period;
    }

    private boolean isPeriodIntersected(SupplyIterator latestStart, SupplyIterator earliestEnd) {
        return latestStart.getStartTimeMsec() < earliestEnd.getEndTimeMsec();
    }


    private void createSupplyPeriod(SupplyIterator latestStartPeriod, SupplyIterator earliestEndPeriod, Supply productSupply) {
        long startTime = latestStartPeriod.getStartTimeMsec();
        long endTime = earliestEndPeriod.getEndTimeMsec();
        int produceNumber = getMaxProductNumber();
        productSupply.addNewSupplyPeriod(new SupplyPeriod(startTime, endTime, produceNumber));
    }

    private int getMaxProductNumber() {
        int minProductNumber = Integer.MAX_VALUE;
        for (SupplyIterator supplyIterator : materials) {
            int productNumber = supplyIterator.getMaxProductCanMake();
            if (minProductNumber > productNumber) {
                minProductNumber = productNumber;
            }
        }
        return minProductNumber;
    }


    private void skipEarliestSupply(SupplyIterator earliestEndPeriod) {
        earliestEndPeriod.nextPeriod();
    }


}
