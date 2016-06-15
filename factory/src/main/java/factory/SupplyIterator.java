package factory;


public class SupplyIterator {

    private Supply supply;
    private int periodIndex = 0;
    private Material requiredMaterial;

    public SupplyIterator(Supply supply, Material material) {

        this.supply = supply;
        requiredMaterial = material;
    }


    public int getMaxProductCanMake() {
        int requiredNum = requiredMaterial.getRequireNumber();
        int providedNum = getCurrentPeriod().providedNum();
        return providedNum / requiredNum;
    }

    public SupplyPeriod getCurrentPeriod() {
        return supply.getList().get(periodIndex);
    }

    public long getStartTimeMsec() {
        return getCurrentPeriod().getStartTimeMsec();
    }

    public long getEndTimeMsec() {
        return getCurrentPeriod().getEndTimeMsec();
    }

    public void nextPeriod() {
        periodIndex++;
    }

    public boolean hasNext() {
        return periodIndex < supply.getList().size();
    }
}
