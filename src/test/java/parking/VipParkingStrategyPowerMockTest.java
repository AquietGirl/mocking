package parking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Calendar;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value={ParkingLot.class , Calendar.class})
public class VipParkingStrategyPowerMockTest {

    @Test
    public void testCalculateHourlyPrice_givenSunday_thenPriceIsDoubleOfSundayPrice(){

        /* Exercise 6: Write test case for VipParkingStrategy calculateHourlyPrice
        * by using PowerMock to mock static method */

        //given
        VipParkingStrategy vipParkingStrategy = new VipParkingStrategy();
        mockStatic(ParkingLot.class , Calendar.class);
        PowerMockito.when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)).thenReturn(Calendar.SUNDAY);
        PowerMockito.when(ParkingLot.getBasicHourlyPrice()).thenCallRealMethod();

        //when
        int result = vipParkingStrategy.calculateHourlyPrice();

        //then
        assertEquals(50, result);

    }

    @Test
    public void testCalculateHourlyPrice_givenNotSunday_thenPriceIsDoubleOfNonSundayPrice(){

        /* Exercise 6: Write test case for VipParkingStrategy calculateHourlyPrice
         * by using PowerMock to mock static method */
        //given
        VipParkingStrategy vipParkingStrategy = new VipParkingStrategy();
        mockStatic(ParkingLot.class , Calendar.class);
        PowerMockito.when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)).thenReturn(Calendar.SATURDAY);
        PowerMockito.when(ParkingLot.getBasicHourlyPrice()).thenCallRealMethod();

        //when
        int result = vipParkingStrategy.calculateHourlyPrice();

        //then
        assertEquals(40, result);

    }
}
