package parental;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bskyb.internettv.objects.MovieServiceImplTest;
import com.bskyb.internettv.parental_control_service.ParentalControlServiceImpl;
import com.bskyb.internettv.thirdparty.MovieService;

public class ParentalControlServiceImplTest {
	private ParentalControlServiceImpl service;

	private MovieService movieService;

	@Before
	public void setup() {
		movieService = new MovieServiceImplTest();
		service = new ParentalControlServiceImpl(movieService);
	}
	/*			
			System.out.println("Customer parental level: P12, movie id 500(parental control = U)");
			canWatch = parentalCSImpl.canWatchMovie("P12", "500");
			System.out.println(canWatch==true?"Enjoy your movie":"Sorry, you cannot watch this movie");

			System.out.println("Customer parental level: PG, movie id 0(movie not found)");
			canWatch = parentalCSImpl.canWatchMovie("j", "500");
			System.out.println(canWatch==true?"Enjoy your movie":"Sorry, you cannot watch this movie");
	*/
	@Test
	public void canWatchMoviesNOTAllows() {
		try {
			//Customer parental level: U, movie id 100(parental control = P18)
			boolean result = service.canWatchMovie("U", "100");
			Assert.assertFalse(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	@Test
	public void canWatchMoviesAllows() {
		try {
			//Customer parental level: P15, movie id 200(parental control = P15)
			boolean result = service.canWatchMovie("P15", "200");
			Assert.assertTrue(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	//Customer parental level: P12, movie id 500(parental control = U))
	
	@Test
	public void canWatchMoviesAllows2() {
		try {
			//Customer parental level: P15, movie id 200(parental control = P15)
			boolean result = service.canWatchMovie("P12", "500");
			Assert.assertTrue(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void throwsTitleNotFoundException() {
		try {
			service.canWatchMovie("P12", "0");
			Assert.fail("Expected exception to be thrown");
		} catch (Exception e) {
			assertEquals(e.getMessage(), "The given movie could not be found.");
		}
	}

	@Test
	public void throwsTechnicalFailureException() {
		try {
			service.canWatchMovie("P12", "785");
			Assert.fail("Expected exception to be thrown");
		} catch (Exception e) {
			assertEquals(e.getMessage(), "System Error - for the moment you cannot watch this movie.");
		}
	}

	@Test
	public void throwsIllegalArgumentException() {
		try {
			service.canWatchMovie("12", "100");
			Assert.fail("Expected exception to be thrown");
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Customer parental control level incorrect.");
		}
	}
}