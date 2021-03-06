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
		int option = -1;
		
		while (option != 0) {
		
			Scheduler scheduler = new Scheduler();
			scheduler.start();
			
			System.out.println("Welcome to your own Scheduler!");
			System.out.println("What do you want to do today? \n 1.See your event list. \n 2.Add an event. \n 3.Delete an event. \n 0.Exit");
			option = reader.nextInt();
			if(option == 1) {
				System.out.println("~~Work Events:");
				for(Event task : workEvents)
					task.getInfo();
				System.out.println("~~Personal Events:");
				for(Event task : personalEvents)
					task.getInfo();
				
				System.out.println(" \nPress enter to return to the menu.");
				System.in.read();
			}
			else if(option == 2) {
				System.out.println("What kind of event do you want to add? [Work] or [Personal]? ~Type 'back' to return to the menu.~");
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
						System.out.println("This kind of event does not exist. Send a feedback, maybe we will add it!");
					}		
				}
			}
			else if(option == 3) {
				System.out.println("What event do you want to delete? [1, 2, 3, ...]");
				option = reader.nextInt();
				
				Event foundEvent = null;
				for(Event e : workEvents) {
						if(e.getID() == option) {
							foundEvent = e;
							break;
					}
				}	
				if (foundEvent != null) {
					removeWorkEvent(foundEvent);
		        }
				
				for(Event e : personalEvents) {
						if(e.getID() == option) {
							foundEvent = e;
							break;
					}
				}	
				if (foundEvent != null) {
					removePersonalEvent(foundEvent);
		        }
			}
			else if(option != 0) {
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
	public static void removeWorkEvent(Event x) {
		workEvents.remove(x);
	}
	public static void removePersonalEvent(Event x) {
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
			System.out.println();
		}
		else {
			for(Event list : workEvents) {
				
				String hour = list.getHour();
				String[] partsOfhour = hour.split(":");
				
				int EventHour = Integer.parseInt(partsOfhour[0]);
				int	EventMinute = Integer.parseInt(partsOfhour[1]);		
					
				if(currentDay == DayNumber(list.getDay())) {
					if((EventHour - currentHour == 1) && (currentMinute >= 30) ) {
						
						if(EventMinute == 00) {
							System.out.println("It's " + currentHour + ":" + currentMinute + ". There are " + (60 - currentMinute) + " minutes left until the " + list.getName() + " (work) event" + ", scheduled for hour " + partsOfhour[0] + ":" + partsOfhour[1] + " .");
						}			
						else if( (EventMinute > currentMinute) && (60 - currentMinute + EventMinute) <= 30 ) {
							System.out.println("It's " + currentHour + ":" + currentMinute + ". There are "  + (EventMinute - currentMinute) + " minutes left until the " + list.getName() + " (work) event" + ", scheduled for hour " + partsOfhour[0] + ":" + partsOfhour[1] + " .");
						}
						else if((60 - currentMinute) + EventMinute <= 30) {
							System.out.println("It's " + currentHour + ":" + currentMinute + ". There are " + ((60 - currentMinute) + EventMinute) + " minutes left until the " + list.getName() + " (work) event" + ", scheduled for hour " + partsOfhour[0] + ":" + partsOfhour[1] + " .");
						}
					}
					else if((EventHour - currentHour == 0) && (EventMinute - currentMinute <= 30) && (EventMinute - currentMinute > 0)) {
							System.out.println("It's " + currentHour + ":" + currentMinute + ". There are " + (EventMinute - currentMinute) + " minutes left until the " + list.getName() + " (work) event"  + ", scheduled for hour " + partsOfhour[0] + ":" + partsOfhour[1] + " .");
						}
					}
				}
			}
		if(personalEvents.size() == 0) {
			System.out.println();
		}
		else {
			for(Event list : personalEvents) {
				
				String hour = list.getHour();
				String[] partsOfhour = hour.split(":");
				
				int EventHour = Integer.parseInt(partsOfhour[0]);
				int EventMinute = Integer.parseInt(partsOfhour[1]);
				
				if(currentDay == DayNumber(list.getDay())) {
					if((EventHour - currentHour == 1) && (currentMinute >= 30) ) {
						if(EventMinute == 00) {
							System.out.println("It's " + currentHour + ":" + currentMinute + ". There are " + (60 - currentMinute) + " minutes left until the " + list.getName() + " (personal) event" + ", scheduled for hour " + partsOfhour[0] + ":" + partsOfhour[1] + " .");
						}			
						else if( (EventMinute > currentMinute) && (60 - currentMinute + EventMinute) <= 30 ) {
							System.out.println("It's " + currentHour + ":" + currentMinute + ". There are "  + (EventMinute - currentMinute) + " minutes left until the " + list.getName() + " (personal) event" + ", scheduled for hour " + partsOfhour[0] + ":" + partsOfhour[1] + " .");
						}
						else if((60 - currentMinute) + EventMinute <= 30) {
							System.out.println("It's " + currentHour + ":" + currentMinute + ". There are " + ((60 - currentMinute) + EventMinute) + " minutes left until the " + list.getName() + " (personal) event" + ", scheduled for hour " + partsOfhour[0] + ":" + partsOfhour[1] + " .");
						}
					}
					else if((EventHour - currentHour == 0) && (EventMinute - currentMinute <= 30) && (EventMinute - currentMinute > 0)) {
							System.out.println("It's " + currentHour + ":" + currentMinute + ". There are " + (EventMinute - currentMinute) + " minutes left until the " + list.getName() + " (personal) event"  + ", scheduled for hour " + partsOfhour[0] + ":" + partsOfhour[1] + " .");
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
