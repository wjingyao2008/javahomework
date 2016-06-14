package factory;

import junit.framework.TestCase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class SupplyFactoryTest extends TestCase {
    SupplyFactory supplyFactory;
    DateFormat format = new SimpleDateFormat("yyyy–MM–dd HH:mm:ss");

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        supplyFactory=new SupplyFactory();
        supplyFactory.addNewSupply("RAW_EUCALYPTUS_001","2014–02–04 00:00:00","2014–11–30 00:00:00",6000);
        supplyFactory.addNewSupply("RAW_EUCALYPTUS_001","2015–02–01 00:00:00","2038–01–19 00:00:00",6000);
        supplyFactory.addNewSupply("RAW_ROSE_005","2014–10–01 00:00:00","2014–10–31 00:00:00",18);
        supplyFactory.addNewSupply("RAW_ROSE_005","2015–01–01 00:00:00","2015–01–31 00:00:00",666);
        supplyFactory.addNewSupply("CAPACITY","2014–02–04 00:00:00","2015–01–15 00:00:00",999);
        supplyFactory.sortAllSupplyWithStartTime();
    }




    public void testForProduct_98100201() throws Exception {
        Product product=new Product("98100201");
        product.addNeededMaterial(new Material("RAW_ROSE_005",14));
        product.addNeededMaterial(new Material("CAPACITY", 1));
        Supply productSupply = supplyFactory.getValidProductSupply(product);


        assertEquals("98100201", productSupply.getSupplyName());
        assertEquals(2, productSupply.getList().size());

        assertEquals(format.parse("2014–10–01 00:00:00"), productSupply.getList().get(0).getStartTime());
        assertEquals(format.parse("2014–10–31 00:00:00"), productSupply.getList().get(0).getEndTime());
        assertEquals(1, productSupply.getList().get(0).getMaterialNumber());

        assertEquals(format.parse("2015–01–01 00:00:00"), productSupply.getList().get(1).getStartTime());
        assertEquals(format.parse("2015–01–15 00:00:00"), productSupply.getList().get(1).getEndTime());
        assertEquals(47, productSupply.getList().get(1).getMaterialNumber());

    }

    public void testForProduct_98102601() throws Exception {
        Product product=new Product("98102601");
        product.addNeededMaterial(new Material("RAW_EUCALYPTUS_001",4));
        product.addNeededMaterial(new Material("RAW_ROSE_005",12));
        product.addNeededMaterial(new Material("CAPACITY", 1));
        Supply productSupply = supplyFactory.getValidProductSupply(product);


        assertEquals("98102601", productSupply.getSupplyName());
        assertEquals(1, productSupply.getList().size());

        assertEquals(format.parse("2014–10–01 00:00:00"), productSupply.getList().get(0).getStartTime());
        assertEquals(format.parse("2014–10–31 00:00:00"), productSupply.getList().get(0).getEndTime());
        assertEquals(1, productSupply.getList().get(0).getMaterialNumber());


    }


}