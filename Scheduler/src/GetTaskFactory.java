
public class GetTaskFactory{
	public Event getEvent(String eventInfo) {
		if(eventInfo == null) {
			return null;
		}
		else if(eventInfo.length() < 1) {
			System.out.println("This kind of event does not exist. Send a feedback, maybe we will add it!");
		}
		else if(eventInfo.equalsIgnoreCase("Work")) {
			return new Work();
		}
		else if(eventInfo.equalsIgnoreCase("Personal")) {
			return new Personal();
		}
		
		return null;
	}

}
