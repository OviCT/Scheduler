import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Scheduler extends Thread {
	private static ArrayList<Event> workEvents = new ArrayList<Event>();
	private static ArrayList<Event> personalEvents = new ArrayList<Event>();
	private static GetTaskFactory event = new GetTaskFactory();
	
	public static void main (String args[]) throws IOException {
		Scanner reader = new Scanner(System.in);
		int option = 0;
		
		while (option != 4) {
		
			Scheduler scheduler = new Scheduler();
			scheduler.start();
			
			System.out.println("Welcome to your own Scheduler!");
			System.out.println("What do you want to do today? \n 1.See your event list. \n 2.Add an event. \n 3.Delete an event. \n 4.Exit");
			option = reader.nextInt();
			if(option == 1) {
				System.out.println("~~Work Events:");
				for(Event task : workEvents)
					task.getInfo();
				System.out.println("~~Personal Events:");
				for(Event task : personalEvents)
					task.getInfo();
				
				System.out.println("Press enter to return to the menu.");
				System.in.read();
			}
			else if(option == 2) {
				System.out.println("What kind of evemt do you want to add? [Work] or [Personal]? ~Type 'back' to go back~");
				while (true) {
					String choice = reader.nextLine();
					if(choice.equalsIgnoreCase("Back"))
						break;
					if(choice.equalsIgnoreCase("Work")) {
						addWorkEvent(choice);
					}
					else if(choice.equalsIgnoreCase("Personal")) {
						addPersonalEvent(choice);
					}
					else if(choice.length() > 1){
						System.out.println("This option does not exist.");
					}
						
				}
			}
			else if(option == 3) {
				System.out.println("What event do you want to delete?");
				option = reader.nextInt();
				//String name = reader.nextLine();
				if(workEvents.contains(option)) {
					removeWorkEvent(option);
				}
				if(workEvents.contains(option)) {
					removePersonalEvent(option);
				}
				
			}
			else if(option != 4) {
				System.out.println("This option does not exist.");
			}

		}
		reader.close();
	}
	public static void addWorkEvent(String type) {
		Event temp_ = event.getEvent(type);
		if(temp_ != null)
			workEvents.add(temp_);
	}
	public static void addPersonalEvent(String type) {
		Event temp_ = event.getEvent(type);
		if(temp_ != null)
			personalEvents.add(temp_);
	}
	public static void removeWorkEvent(int x) {
		workEvents.remove(x);
	}
	public static void removePersonalEvent(int x) {
		personalEvents.remove(x);
	}
	public static int DayNumber(String DayName) {	
		
		if(DayName.equalsIgnoreCase("Monday")) {
			return 2;
		}
		else if(DayName.equalsIgnoreCase("Tuesday")) {
			return 3;
		}
		else if(DayName.equalsIgnoreCase("Wednesday")) {
			return 4;
		}
		else if(DayName.equalsIgnoreCase("Thursday")) {
			return 5;
		}
		else if(DayName.equalsIgnoreCase("Friday")) {
			return 6;
		}
		else if(DayName.equalsIgnoreCase("Saturday")) {
			return 7;
		}
		else if(DayName.equalsIgnoreCase("Sunday")) {
			return 1;
		}
		else 
			return 0;
	}
	
	Calendar c = Calendar.getInstance();
	int currentHour = c.get(Calendar.HOUR_OF_DAY);
	int currentMinute = c.get(Calendar.MINUTE);
	int currentDay = c.get(Calendar.DAY_OF_WEEK);
	
	
	public void run() {
		if(workEvents.size() == 0) {
			System.out.println("");
		}
		else {
			for(Event list : workEvents) {
					int EventHour = Integer.parseInt(list.getHour());
					int EventMinute = Integer.parseInt(list.getMinute());
					
					if(currentDay == DayNumber(list.getDay())) {
						if((EventHour - currentHour == 1) && (currentMinute >= 30) ) {
							
							if(EventMinute == 00) {
								System.out.print("It's " + currentHour + ":" + currentMinute + ". There are " + (60 - currentMinute) + " minutes left until the " + list.getName() + " event," + ", scheduled for hour " + list.getHour() + ":" + list.getMinute() + " . \n");
							}			
							else if( (EventMinute > currentMinute) && (60 - currentMinute + EventMinute) <= 30 ) {
								System.out.print("It's " + currentHour + ":" + currentMinute + ". There are "  + (EventMinute - currentMinute) + " minutes left until the " + list.getName() + " event," + ", scheduled for hour " + list.getHour() + ":" + list.getMinute() + " . \n");
							}
							else if((60 - currentMinute) + EventMinute <= 30) {
								System.out.print("It's " + currentHour + ":" + currentMinute + ". There are " + ((60 - currentMinute) + EventMinute) + " minutes left until the " + list.getName() + " event," + " scheduled for hour " + list.getHour() + ":" + list.getMinute() + " . \n");
							}
						}
						else if((EventHour - currentHour == 0) && (EventMinute - currentMinute <= 30) && (EventMinute - currentMinute > 0)) {
								System.out.print("It's " + currentHour + ":" + currentMinute + ". There are " + (EventMinute - currentMinute) + " minutes left until the Event named " + list.getName() + " event,"  + " scheduled for hour " + list.getHour() + ":" + list.getMinute() + " . \n");
							}
						}
					}
			}
		if(personalEvents.size() == 0) {
			System.out.println("");
		}
		else {
			for(Event list : personalEvents) {
					int EventHour = Integer.parseInt(list.getHour());
					int EventMinute = Integer.parseInt(list.getMinute());
					
					if(currentDay == DayNumber(list.getDay())) {
						if((EventHour - currentHour == 1) && (currentMinute >= 30) ) {
							
							if(EventMinute == 00) {
								System.out.print("It's " + currentHour + ":" + currentMinute + ". There are " + (60 - currentMinute) + " minutes left until the " + list.getName() + " event," + ", scheduled for hour " + list.getHour() + ":" + list.getMinute() + " . \n");
							}			
							else if( (EventMinute > currentMinute) && (60 - currentMinute + EventMinute) <= 30 ) {
								System.out.print("It's " + currentHour + ":" + currentMinute + ". There are "  + (EventMinute - currentMinute) + " minutes left until the " + list.getName() + " event," + ", scheduled for hour " + list.getHour() + ":" + list.getMinute() + " . \n");
							}
							else if((60 - currentMinute) + EventMinute <= 30) {
								System.out.print("It's " + currentHour + ":" + currentMinute + ". There are " + ((60 - currentMinute) + EventMinute) + " minutes left until the " + list.getName() + " event," + " scheduled for hour " + list.getHour() + ":" + list.getMinute() + " . \n");
							}
						}
						else if((EventHour - currentHour == 0) && (EventMinute - currentMinute <= 30) && (EventMinute - currentMinute > 0)) {
								System.out.print("It's " + currentHour + ":" + currentMinute + ". There are " + (EventMinute - currentMinute) + " minutes left until the Event named " + list.getName() + " event,"  + " scheduled for hour " + list.getHour() + ":" + list.getMinute() + " . \n");
							}
						}
					}
			}
		try {
			sleep(60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
}
