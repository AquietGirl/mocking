package sales;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {SalesApp.class,EcmService.class})
public class SalesAppTest {

	@Test
	public void testGenerateReport() throws Exception {
		SalesDao salesDao = mock(SalesDao.class);
		Sales sales = new Sales();
		sales.setActive(true);
		when(salesDao.getSalesBySalesId(anyString())).thenReturn(sales);
		PowerMockito.whenNew(SalesDao.class).withNoArguments().thenReturn(salesDao);

		SalesReportDao salesReportDao = mock(SalesReportDao.class);
		ArrayList<SalesReportData> salesReportData = new ArrayList<>();
		when(salesReportDao.getReportData(anyObject())).thenReturn(salesReportData);
		PowerMockito.whenNew(SalesReportDao.class).withNoArguments().thenReturn(salesReportDao);


		mockStatic(EcmService.class);
		when(EcmService.uploadDocument(anyString())).thenReturn("1");



		SalesApp salesApp = spy(new SalesApp());
		salesApp.generateSalesActivityReport("DUMMY", 1000, false, false);
		PowerMockito.verifyStatic(times(1));
		EcmService.uploadDocument(anyString());

	}

	@Test
	public void mockStatic_test() throws Exception {
		mockStatic(EcmService.class);
		when(EcmService.uploadDocument(anyString())).thenReturn("1");
		String result = EcmService.uploadDocument("");
		assertEquals(result,"1");
	}
}
