package com.bskyb.internettv.objects;

import com.bskyb.internettv.thirdparty.MovieService;
import com.bskyb.internettv.thirdparty.TechnicalFailureException;
import com.bskyb.internettv.thirdparty.TitleNotFoundException;

public class MovieServiceImplTest implements MovieService{

	public String getParentalControlLevel(String titleId) throws TitleNotFoundException, TechnicalFailureException {
		 switch (titleId) {
         case "100":
             return "P18";
         case "200":
             return "P15";
         case "500":
        	 return "U";
         case "0":
             throw new TitleNotFoundException("The given movie could not be found.");
         default:
             throw new TechnicalFailureException("System Error - for the moment you cannot watch this movie.");
     }
	}

}
