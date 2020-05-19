import com.mysql.jdbc.StringUtils;

public class Timer extends Thread{
	user user;
	
	String[] TimeString;// = {"00", "00", "00"};
	int[] TimeInt = new int[3];
	
	String[] userString;
	public Timer(String[] userString) {
		this.userString = userString;
	}
	
	public void Timer(String Time, user user) {
		System.out.println("timer");
		this.user = user;
		this.TimeString = Time.split(":");
		for(int i=0; i<TimeString.length; i++) {
//			System.out.println(Integer.parseInt(TimeString[i]));
			TimeInt[i] = Integer.parseInt(TimeString[i]);
		}
//		start();
	}
	
	public void run() {
		try {
			while(true) {
				user.time.setText("시간 : " + TimeInt[0] + ":" + TimeInt[1] + ":" + TimeInt[2]);
//				TimeInt[2] -= 1;
				if(TimeInt[2] == 0) {
					if(TimeInt[1] == 0) {
						if(TimeInt[0] == 0) {
							user.time.setText("시간 : " + TimeInt[0] + ":" + TimeInt[1] + ":" + TimeInt[2]);
							break;
						}
						TimeInt[0] -= 1;
						TimeInt[1] = 60;
					}
					TimeInt[1] -= 1;
					TimeInt[2] = 60;
				}
				TimeInt[2] -= 1;
				userString[2] = TimeInt[0] + ":" + TimeInt[1] + ":" + TimeInt[2];
				Main.pm.PCrepaint(userString);
				
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}
}
