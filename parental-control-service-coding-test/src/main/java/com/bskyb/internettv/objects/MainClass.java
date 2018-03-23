package com.bskyb.internettv.objects;

import com.bskyb.internettv.parental_control_service.ParentalControlServiceImpl;

public class MainClass {

	public static void main(String[] args) {
		
		MovieServiceImplTest service = new MovieServiceImplTest();
		ParentalControlServiceImpl parentalCSImpl = new ParentalControlServiceImpl(service);
		try {
			System.out.println("Customer parental level: U, movie id 100(parental control = P18)");
			boolean canWatch = parentalCSImpl.canWatchMovie("U", "100");
			System.out.println(canWatch==true?"Enjoy your movie":"Sorry, you cannot watch this movie");
			
			System.out.println("Customer parental level: P15, movie id 200(parental control = P15)");
			canWatch = parentalCSImpl.canWatchMovie("P15", "200");
			System.out.println(canWatch==true?"Enjoy your movie":"Sorry, you cannot watch this movie");
			
			System.out.println("Customer parental level: P12, movie id 500(parental control = U)");
			canWatch = parentalCSImpl.canWatchMovie("P12", "500");
			System.out.println(canWatch==true?"Enjoy your movie":"Sorry, you cannot watch this movie");

			System.out.println("Customer parental level: PG, movie id 0(movie not found)");
			canWatch = parentalCSImpl.canWatchMovie("j", "500");
			System.out.println(canWatch==true?"Enjoy your movie":"Sorry, you cannot watch this movie");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
