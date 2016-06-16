package factory;

import junit.framework.TestCase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;


public class SupplyFactoryTest extends TestCase {
    private SupplyFactory supplyFactory;
    private DateFormat format = new SimpleDateFormat("yyyy–MM–dd HH:mm:ss");

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        supplyFactory = new SupplyFactory();
        supplyFactory.addNewSupply("RAW_EUCALYPTUS_001", "2014–02–04 00:00:00", "2014–11–30 00:00:00", 6000);
        supplyFactory.addNewSupply("RAW_EUCALYPTUS_001", "2015–02–01 00:00:00", "2038–01–19 00:00:00", 6000);
        supplyFactory.addNewSupply("RAW_ROSE_005", "2014–10–01 00:00:00", "2014–10–31 00:00:00", 18);
        supplyFactory.addNewSupply("RAW_ROSE_005", "2015–01–01 00:00:00", "2015–01–31 00:00:00", 666);
        supplyFactory.addNewSupply("CAPACITY", "2014–02–04 00:00:00", "2015–01–15 00:00:00", 999);
        supplyFactory.postPrepareSupply();
    }


    public void testForProduct_98100201() throws Exception {
        Product product = new Product("98100201");
        product.addNeededMaterial(new Material("RAW_ROSE_005", 14));
        product.addNeededMaterial(new Material("CAPACITY", 1));
        Supply productSupply = supplyFactory.getValidProductSupply(product);


        assertEquals("98100201", productSupply.getSupplyName());
        assertEquals(2, productSupply.getList().size());

        assertSupplyPeriod(productSupply.getList().get(0), "2014–10–01 00:00:00", "2014–10–31 00:00:00", 1);
        assertSupplyPeriod(productSupply.getList().get(1), "2015–01–01 00:00:00", "2015–01–15 00:00:00", 47);


    }

    private void assertSupplyPeriod(SupplyPeriod productSupplyPeriod,
                                    String expectStartTime,
                                    String expectEndTime,
                                    int expectProductNum) throws ParseException {
        assertEquals(format.parse(expectStartTime), productSupplyPeriod.getStartTime());
        assertEquals(format.parse(expectEndTime), productSupplyPeriod.getEndTime());
        assertEquals(expectProductNum, productSupplyPeriod.providedNum());

    }



    public void testForProduct_98102601() throws Exception {
        Product product = new Product("98102601");
        product.addNeededMaterial(new Material("RAW_EUCALYPTUS_001", 4));
        product.addNeededMaterial(new Material("RAW_ROSE_005", 12));
        product.addNeededMaterial(new Material("CAPACITY", 1));
        Supply productSupply = supplyFactory.getValidProductSupply(product);


        assertEquals("98102601", productSupply.getSupplyName());
        assertEquals(1, productSupply.getList().size());

        assertSupplyPeriod(productSupply.getList().get(0), "2014–10–01 00:00:00", "2014–10–31 00:00:00",1);


    }


    public void testSort() throws Exception {
        String[] words={"1","a","d","5","A","B"};
        Arrays.sort(words,(first, second)->first.compareTo(second));
        System.out.println(Arrays.toString(words));
    }


}