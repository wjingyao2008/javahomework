package factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Supply {

    private String supplyName;
    private List<SupplyPeriod> list=new ArrayList<SupplyPeriod>();


    public Supply(String supplyName) {
        this.supplyName = supplyName;
    }


    public String getSupplyName() {
        return supplyName;
    }

    public void addNewSupplyPeriod(SupplyPeriod supplyPeriod){
        list.add(supplyPeriod);
    }

    public List<SupplyPeriod> getList(){
        return list;
    }

    public void sortPeriodsByStartTime(){
        Collections.sort(list);
    }
}
