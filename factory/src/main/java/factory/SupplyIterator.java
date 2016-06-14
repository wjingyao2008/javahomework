package factory;

/**
 * Created by Administrator on 2016/6/14 0014.
 */
public class SupplyIterator {

    private Supply supply;
    private int periodIndex=0;
    private Material requiredMaterial;
    public SupplyIterator(Supply supply, Material material){

        this.supply = supply;
        requiredMaterial=material;
    }


    public int getMaxProductCanMake(){
        int requredMaterialNum=requiredMaterial.getRequireNumber();
        return getCurrentPeriod().getMaterialNumber()/requredMaterialNum;
    }

    public SupplyPeriod getCurrentPeriod(){
        return supply.getList().get(periodIndex);
    }

    public long getStartTimeMsec(){
        return getCurrentPeriod().getStartTimeMsec();
    }

    public long getEndTimeMsec(){
        return getCurrentPeriod().getEndTimeMsec();
    }

    public void nextPeriod(){
        periodIndex++;
    }

    public boolean hasNext(){
        return periodIndex<supply.getList().size();
    }
}
