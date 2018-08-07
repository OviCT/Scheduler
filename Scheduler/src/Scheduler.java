import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Scheduler extends Thread {
	private static ArrayList<Task> tasksWork = new ArrayList<Task>();
	private static ArrayList<Task> tasksHome = new ArrayList<Task>();
	private static GetTaskFactory task = new GetTaskFactory();
	
	public static void main (String args[]) throws IOException {
		Scanner reader = new Scanner(System.in);
		int optiune = 0;
		
		while (optiune != 4) {
		
			Scheduler scheduler = new Scheduler();
			scheduler.start();
			
			System.out.println("Bun venit la propriul tau Scheduler!");
			System.out.println("Ce doresti sa faci? \n 1.Vezi toate evenimentele \n 2.Adauga un eveniment. \n 3.Sterge un eveniment \n 4.Exit");
			optiune = reader.nextInt();
			if(optiune == 1) {
				System.out.println("~~Evenimentele pentru Serviciu:");
				for(Task task : tasksWork)
					task.getInfo();
				System.out.println("~~Evenimentele pentru Acasa:");
				for(Task task : tasksHome)
					task.getInfo();
				
				System.in.read();
			}
			else if(optiune == 2) {
				System.out.println("Ce fel de task vrei sa adaugi? Pentru [Serviciu] sau [Acasa]? ~Tasteaza 'Inapoi' pentru a te intoarce~");
				while (true) {
					String alegere = reader.nextLine();
					if(alegere.equalsIgnoreCase("Inapoi"))
						break;
					if(alegere.equalsIgnoreCase("Serviciu")) {
						addTaskWork(alegere);
					}
					else if(alegere.equalsIgnoreCase("Acasa")) {
						addTaskHome(alegere);
					}
					else if(alegere.length() > 1){
						System.out.println("Aceasta optiune nu exista");
					}
						
				}
			}
			else if(optiune == 3) {
				System.out.println("Ce task doriti sa stergeti?");
				optiune = reader.nextInt();
				;
			}
			else if(optiune != 4) {
				System.out.println("Optiunea dorita nu exista.");
			}

		}
		reader.close();
	}
	public static void addTaskWork(String tip) {
		Task temp_ = task.getTask(tip);
		if(temp_ != null)
			tasksWork.add(temp_);
	}
	public static void addTaskHome(String tip) {
		Task temp_ = task.getTask(tip);
		if(temp_ != null)
			tasksHome.add(temp_);
	}
	public static void removeTaskWork(int x) {
		tasksWork.remove(x);
	}
	public static void removeTaskHome(int x) {
		tasksHome.remove(x);
	}
	public static int DayNumber(String DayName) {	
		
		if(DayName.equalsIgnoreCase("Luni")) {
			return 2;
		}
		else if(DayName.equalsIgnoreCase("Marti")) {
			return 3;
		}
		else if(DayName.equalsIgnoreCase("Miercuri")) {
			return 4;
		}
		else if(DayName.equalsIgnoreCase("Joi")) {
			return 5;
		}
		else if(DayName.equalsIgnoreCase("Vineri")) {
			return 6;
		}
		else if(DayName.equalsIgnoreCase("Sambata")) {
			return 7;
		}
		else if(DayName.equalsIgnoreCase("Duminica")) {
			return 1;
		}
		else 
			return 0;
	}
	
	Calendar c = Calendar.getInstance();
	int hour = c.get(Calendar.HOUR_OF_DAY);
	int minute = c.get(Calendar.MINUTE);
	int day = c.get(Calendar.DAY_OF_WEEK);
	
	
	public void run() {
		if(tasksWork.size() == 0) {
			System.out.println("");
		}
		else {
			for(Task list : tasksWork) {
					int EventHour = Integer.parseInt(list.getHour());
					int EventMinute = Integer.parseInt(list.getMinute());
					
					if(day == DayNumber(list.getDay())) {
						if((EventHour - hour <=1) && (EventMinute - minute <= 30)) {
							if(EventMinute > minute) {
								System.out.print("Atentie! Mai sunt " + (EventMinute - minute) + " de miunte pana la Evenimentul " + list.getNume() + " setat pentru ora " + list.getHour() + ":" + list.getMinute() + " . \n");
							}
							else {
								System.out.print( "Atentie! Mai sunt " + (minute - EventMinute) + " de miunte pana la Evenimentul " + list.getNume() + " setat pentru ora " + list.getHour() + ":" + list.getMinute() + " . \n");
							}
						}
					}
			}
		if(tasksHome.size() == 0) {
			System.out.println("");
		}
		else {
			for(Task list : tasksHome) {
					int EventHour = Integer.parseInt(list.getHour());
					int EventMinute = Integer.parseInt(list.getMinute());
					if(day == DayNumber(list.getDay())) {
					if((EventHour - hour <=1) && (EventMinute - minute <= 30)) {
						if(EventMinute > minute) {
							System.out.print( "Atentie! Mai sunt " + (EventMinute - minute) + " de miunte pana la Evenimentul " + list.getNume() + " setat pentru ora " + list.getHour() + ":" + list.getMinute() + " . \n");
						}
						else {
							System.out.print( "Atentie! Mai sunt " + (minute - EventMinute) + " de miunte pana la Evenimentul " + list.getNume() + " setat pentru ora " + list.getHour() + ":" + list.getMinute() + " . \n");
						}
					}
					}
				}	
		}
		try {
			sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		}
	}
}
