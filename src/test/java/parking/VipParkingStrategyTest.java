package parking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.smartcardio.Card;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VipParkingStrategyTest {

    @Mock
    private CarDao carDao;

    @InjectMocks
    private VipParkingStrategy vipParkingStrategy;

	@Test
    public void testPark_givenAVipCarAndAFullParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 4, Write a test case on VipParkingStrategy.park()
	    * With using Mockito spy, verify and doReturn */

	    //given
        String carName = "car1";
        String parkingLotName = "parkingLot1";
        Car car = createMockCar(carName);
        ParkingLot parkingLot = createMockParkingLot(parkingLotName, 10);
        when(parkingLot.isFull()).thenReturn(true);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        doReturn(true).when(vipParkingStrategy).isAllowOverPark(car);
        Receipt receipt = new Receipt();
        receipt.setCarName(carName);
        receipt.setParkingLotName(parkingLotName);

        // when
        Receipt result = vipParkingStrategy.park(parkingLotList, car);

        //then
        verify(vipParkingStrategy,times(1)).park(anyList(),any());
        verify(parkingLot , times(1)).isFull();
        assertEquals(receipt.getCarName(),result.getCarName());
        assertEquals(receipt.getParkingLotName(),result.getParkingLotName());

    }

    @Test
    public void testPark_givenCarIsNotVipAndAFullParkingLog_thenGiveNoSpaceReceipt() {

        /* Exercise 4, Write a test case on VipParkingStrategy.park()
         * With using Mockito spy, verify and doReturn */

        //given
        String carName = "car1";
        String parkingLotName = "No Parking Lot";
        Car car = createMockCar(carName);
        ParkingLot parkingLot = createMockParkingLot(parkingLotName, 10);
        when(parkingLot.isFull()).thenReturn(true);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        doReturn(false).when(vipParkingStrategy).isAllowOverPark(car);
        Receipt receipt = new Receipt();
        receipt.setCarName(carName);
        receipt.setParkingLotName(parkingLotName);

        // when
        Receipt result = vipParkingStrategy.park(parkingLotList, car);

        //then
        verify(vipParkingStrategy,times(1)).park(anyList(),any());
        verify(parkingLot , times(1)).isFull();
        assertEquals(receipt.getCarName(),result.getCarName());
        assertEquals(receipt.getParkingLotName(),result.getParkingLotName());

    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */

        //given
        Car car = createMockCar("Acar1");

        when(carDao.isVip(anyString())).thenReturn(true);

        //when
        boolean result = vipParkingStrategy.isAllowOverPark(car);

        //then
        assertTrue(result);

    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */

        //given
        Car car = createMockCar("car1");

        when(carDao.isVip(anyString())).thenReturn(true);

        //when
        boolean result = vipParkingStrategy.isAllowOverPark(car);

        //then
        assertFalse(result);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse(){
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */

        //given
        Car car = createMockCar("Acar1");

        when(carDao.isVip(anyString())).thenReturn(false);

        //when
        boolean result = vipParkingStrategy.isAllowOverPark(car);

        //then
        assertFalse(result);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */

        //given
        Car car = createMockCar("car1");

        when(carDao.isVip(anyString())).thenReturn(false);

        //when
        boolean result = vipParkingStrategy.isAllowOverPark(car);

        //then
        assertFalse(result);
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }

    private ParkingLot createMockParkingLot(String parkingLotName, int maxCapacity) {
        ParkingLot parkingLot = mock(ParkingLot.class);
        when(parkingLot.getName()).thenReturn(parkingLotName);
        when(parkingLot.getMaxCapacity()).thenReturn(maxCapacity);
        return parkingLot;
    }
}
