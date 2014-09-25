import java.util.HashMap;
import java.util.Map;


/**
 * program OneIn64 ask the user to think of a number between 1 and 64 then
 * prints out 32 numbers between 1 and 64 and asks the user if his/her number
 * is amongst those 32. Based on the user's response, the program again prints out
 * 32 numbers and asks the same thing. this will repeat 6 times for a list of 64 numbers. 
 * At the end of 6 times the program will correctly tell the user their number. 
 * 
 * requires: TextIO.java
 * Date: Nov 12,2013
 * @author Joe Bisesto 
 *
 */

public class OneIn64 {
	public static void main(String[] agrs) {

		// create a map of all possible values that the users number could be
		HashMap<Integer, Boolean> possible = new HashMap<Integer, Boolean>();
		
		//map of numbers that the program will ask the user 
		HashMap<Integer, Boolean> mapToAsk = new HashMap<Integer, Boolean>(); 
		
		//map of eliminated numbers
		HashMap<Integer, Boolean> eliminated = new HashMap<Integer, Boolean>(); 

		// add 64 numbers to possible map all set to true

		for (int i = 1; i <= 64; i++) {
			possible.put(i, true);
		}

		int numberOfTrue = 64; // represents the current number of true in the list
		String list; //a list that will be output to the user

		System.out
				.println("Think of a number between 1 and 64, hit enter when you are ready AND DON'T LIE TO ME!");
		TextIO.getln();
		
		Boolean isIn = true; //user input if their number is contained in the list 
		
		while (numberOfTrue % 2 != 1) {

			list = "";
			mapToAsk.clear(); //clear mapToAsk for next mapToAsk

			// add half of possible numbers to mapToShow and put the key into
			// list
			
			while (mapToAsk.size() < numberOfTrue / 2) {

				int number = (int) (1 + Math.random() * 64); //random number between 1 and 64

				//put number into mapToAsk only if it is not already in it, and if it in possible map
				if (!mapToAsk.containsKey(number)
						&& possible.containsKey(number)) {
					possible.remove(number); // remove number from possible list
					mapToAsk.put(number, true);
					list += number + "\t";
					
					//set the line breaks
					if(mapToAsk.size()==4 || mapToAsk.size()==8 || mapToAsk.size()==12 || mapToAsk.size()==16 || mapToAsk.size()==20|| mapToAsk.size()==24 || mapToAsk.size()==28){
						list+="\n";
					}
					
				}


			
			}
			// pad list with numbers
			
			
				while(mapToAsk.size()<32){
					int padNumber = (int) (1 + Math.random() * 64); 
					//a pad number is a random number between 1 and 64 that is not already contained in mapToAsk 
					//and that is not contained in possible 
					
					if(!mapToAsk.containsKey(padNumber) && !possible.containsKey(padNumber) ){
						mapToAsk.put(padNumber, false);
						list+= padNumber + "\t";
						
						//add line breaks
						if(mapToAsk.size()==4 || mapToAsk.size()==8 || mapToAsk.size()==12 || mapToAsk.size()==16 || mapToAsk.size()==20|| mapToAsk.size()==24 || mapToAsk.size()==28){
							list+="\n";
						}
					}
				}
				
				
			
			
		
			//ask user if their number is in the list
			System.out.println("Is your number in this list? (y/n)");

			// show the list to user, asking if their number is in the list
			System.out.println(list);

			isIn = TextIO.getlnBoolean(); //get user input

			// if isIn = true
			if (isIn == true) {
				// move all in possible to eliminated list
				for (Map.Entry<Integer, Boolean> moveElim : possible.entrySet()) {

					eliminated.put(moveElim.getKey(), false);

				}

				possible.clear();

				// move all in mapToAsk to possible if they are not false
				for (Map.Entry<Integer, Boolean> movePoss : mapToAsk.entrySet()) {
					
						if(movePoss.getValue().equals(false)){
							mapToAsk.remove(movePoss);
						}else{
							possible.put(movePoss.getKey(), true);
						}

				}
			}
			// if isIn is false
			else {
				// move all in mapToAsk to eliminated
				for (Map.Entry<Integer, Boolean> moveElim : mapToAsk.entrySet()) {

					eliminated.put(moveElim.getKey(), false);

				}
				// keep items in possible

			}

			// cut numberOfTrue in half
			numberOfTrue = numberOfTrue / 2;
			
			//true list for testing purposes only (Commented out)
			//for(Map.Entry<Integer, Boolean>trueList:possible.entrySet() ){
				//System.out.println(trueList);
			//}
			
			//add some interaction just for fun :oD!
			if(isIn && numberOfTrue >1){
			System.out.println("hmm... Okay... I am getting close... thinking...");
			}
			
			if(numberOfTrue == 1){
				System.out.println("Okay! I think I have an answer...");
				pause(1000);
			}
			
		}
		
		//At this point only 1 item should be in possible... this is the answer! (hopefully :-\)
		System.out.println("Your number was: ");

		for (Map.Entry<Integer, Boolean> number : possible.entrySet()) {
			System.out.println(number.getKey());
		}

	}
	//end main 
	
	//a pause method just for fun! 
	public static void pause(int mills) {
		if (mills > 0) {
			try {
				Thread.sleep(mills);
			} catch (InterruptedException e) {
			}
		}
	}
	
	//end program
}

