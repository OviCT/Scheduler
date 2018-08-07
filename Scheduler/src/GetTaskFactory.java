
public class GetTaskFactory{
	public Task getTask(String taskInfo) {
		if(taskInfo == null) {
			return null;
		}
		else if(taskInfo.equalsIgnoreCase("Serviciu")) {
			return new Work();
		}
		else if(taskInfo.equalsIgnoreCase("Acasa")) {
			return new Home();
		}
		else if(taskInfo.length() > 1) {
			System.out.println("Deocamdata, acel tip de task nu exista. Send a feedback, maybe we will add it!");
		}
		
		return null;
	}

}
