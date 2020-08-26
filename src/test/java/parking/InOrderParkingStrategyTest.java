package parking;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


public class InOrderParkingStrategyTest {

    private InOrderParkingStrategy inOrderParkingStrategy;

    @Before
    public void setUp() {
        inOrderParkingStrategy = new InOrderParkingStrategy();
    }

    @Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
         * With using Mockito to mock the input parameter */

        //given
        String carName = "car1";
        String parkingLotName = "parkingLot1";
        Car car = createMockCar(carName);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(createMockParkingLot("parkingLot1", 10));
        Receipt receipt = new Receipt();
        receipt.setParkingLotName(parkingLotName);
        receipt.setCarName(carName);

        //when
        Receipt result = inOrderParkingStrategy.park(parkingLotList, car);

        //then
        assertEquals(receipt.getCarName(), result.getCarName());
        assertEquals(receipt.getParkingLotName(), result.getParkingLotName());
    }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
         * With using Mockito to mock the input parameter */

        //given
        String carName = "car1";
        Car car = createMockCar(carName);
        Receipt receipt = new Receipt();
        receipt.setCarName(carName);
        receipt.setParkingLotName("No Parking Lot");

        //when
        Receipt result = inOrderParkingStrategy.park(null, car);

        //then
        assertEquals(receipt.getCarName(), result.getCarName());
        assertEquals(receipt.getParkingLotName(), result.getParkingLotName());

    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt() {

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */

        //given
        String carName = "car1";
        Car car = createMockCar(carName);
        ParkingLot parkingLot = createMockParkingLot("parkingLot1", 10);
        when(parkingLot.isFull()).thenReturn(true);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        Receipt receipt = new Receipt();
        receipt.setParkingLotName("No Parking Lot");
        receipt.setCarName(carName);

        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());

        //when
        Receipt result = inOrderParkingStrategy.park(parkingLotList, car);

        //then
        assertEquals(receipt.getCarName(), result.getCarName());
        assertEquals(receipt.getParkingLotName(), result.getParkingLotName());
        verify(inOrderParkingStrategy, times(1)).park(anyList(), any());
    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt() {

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */

        //given
        String carName = "car1";
        String parkingLotName = "parkingLot1";
        Car car = createMockCar(carName);
        ParkingLot parkingLot = createMockParkingLot(parkingLotName, 10);
        when(parkingLot.isFull()).thenReturn(false);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        Receipt receipt = new Receipt();
        receipt.setCarName(carName);
        receipt.setParkingLotName(parkingLotName);

        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());

        //when
        Receipt result = inOrderParkingStrategy.park(parkingLotList, car);

        //then
        assertEquals(receipt.getCarName(), result.getCarName());
        assertEquals(receipt.getParkingLotName(), result.getParkingLotName());
        verify(inOrderParkingStrategy, times(1)).park(anyList(), any());
    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt() {

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */

        //given
        Car car = createMockCar("car1");
        ParkingLot parkingLot = createMockParkingLot("parkingLot1", 10);
        when(parkingLot.isFull()).thenReturn(true);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());

        //when
        inOrderParkingStrategy.park(parkingLotList, car);

        //then
        verify(inOrderParkingStrategy, times(1)).park(anyList(), any());
    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot() {

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */

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
