package com.bskyb.internettv.parental_control_service;

import com.bskyb.internettv.objects.ParentalControlLevels;
import com.bskyb.internettv.thirdparty.MovieService;
import com.bskyb.internettv.thirdparty.TechnicalFailureException;
import com.bskyb.internettv.thirdparty.TitleNotFoundException;

public class ParentalControlServiceImpl implements ParentalControlService {
	private final MovieService movieService;

	public ParentalControlServiceImpl(MovieService service) {
		movieService = service;
	}

	public boolean canWatchMovie(String customerParentalControlLevel, String movieId) throws Exception {
		try {
			String movieParentalControlLvl = movieService.getParentalControlLevel(movieId);
			return compareLevel(customerParentalControlLevel, movieParentalControlLvl);
		} catch (TitleNotFoundException ex) {
			throw new Exception(ex.getMessage());
		} catch (TechnicalFailureException ex) {
			throw new Exception(ex.getMessage());
		} catch (IllegalArgumentException e) {
			throw new Exception("Customer parental control level incorrect.");
		}
	}

	private boolean compareLevel(String customerParentalControlLevel, String movieParentalControlLvl) {
		if (ParentalControlLevels.valueOf(movieParentalControlLvl.toUpperCase())
				.compareTo(ParentalControlLevels.valueOf(customerParentalControlLevel.toUpperCase())) <= 0)
			return true;
		else
			return false;

	}

}
