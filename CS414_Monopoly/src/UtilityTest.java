import static org.junit.Assert.*;

import java.awt.Image;

import org.junit.Before;
import org.junit.Test;

public class UtilityTest {

        private Utility util;
        private UtilityType utilType;
        private Player player;
        private Image image;
        private int xPos;
        private int yPos;
        private int price;
        private String name;
        private boolean isUtilityOwned;
        private boolean isUtilMortgaged;

        @Before
        public void setUp() throws Exception {
                utilType = UtilityType.COLLEGE;
                player = new Player("Player 1", 0, 1, 500);
                xPos = 0;
                yPos = 1;
                price = 100;
                name = "COllege";
                isUtilityOwned = false;
                isUtilMortgaged = false;
                util = new Utility(xPos, yPos, price, name, isUtilityOwned, isUtilMortgaged);
        }

        @Test
        public void utilityOwnedTest() {

                assertTrue(util.isUtilityOwned() == false);
                util.setUtilityOwned(true);
                assertTrue(util.isUtilityOwned() == true);
        }

        @Test
        public void utilityNameTest() {
                assertTrue(util.getUtilityName().equals("COllege"));
        }

        @Test
        public void utilityPosnTest() {
                assertTrue(util.getxPos() == 0);
                util.setxPos(1);
                assertTrue(util.getxPos() == 1);

                assertTrue(util.getyPos() == 1);
                util.setyPos(2);
                assertTrue(util.getyPos() == 2);
        }

        @Test
        public void utilityPriceTest() {
                assertTrue(util.getPrice() == 100);
                util.setPrice(200);
                assertTrue(util.getPrice() == 200);
        }

        @Test
        public void utilityTypeTest() {
        	util.setUtilType(utilType);
                assertEquals(util.getUtilType(),utilType);
                util.setUtilType( UtilityType.LUXURY);
                assertTrue(util.getUtilType().equals(UtilityType.LUXURY));

        }

        @Test
        public void utilityPlayerTest() {
                util.updateUtilityOwned(player);
                assertTrue(util.getPlayer().getAvailCash() == 500);
                player.deductCash(200);
                assertTrue(util.getPlayer().getAvailCash() == 300);
                player.addUtility(util);
                assertTrue(player.getUtilList().get(0).isUtilityOwned() == false);
                assertTrue(player.getUtilList().get(0).getUtilityName().equals("COllege"));
        }


}
