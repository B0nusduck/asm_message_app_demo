import objects.*;
public class mainApp {

	public static void main(String[] args) {
		deviceQueue device = new deviceQueue();
		device.offer(new device("device a",1));
		device.offer(new device("device b",2));
		device.offer(new device("device c",3));
		device.offer(new device("device d",4));
		//device.showDeviceQueue();
		try{
			device.getDeviceByName("device a").setSentMessage("hello this is device a",3);
			//device.getDeviceByName("device a").getQueueSent(device);
			device.sentMSG("device a");
			//device.getDeviceByName("device a").getQueueSent(device);
		}
		catch(Exception e) {
			System.out.print("\nfailed to call a function");
		}
		try{
			device.getDeviceByName("device b").setSentMessage("hello this is device b",3);
			//device.getDeviceByName("device b").getQueueSent(device);
			device.sentMSG("device b");
		}
		catch(Exception e) {
			System.out.print("failed to call a function");
		}
		try {
			device.getDeviceByName("device d").setSentMessage("hello this is device d",3);
			//device.getDeviceByName("device d").getQueueSent(device);
			device.sentMSG("device d");
		}
		catch(Exception e) {
			System.out.print("failed to call a function");
		}
		/*try {
			//device.getDeviceByName("device c").getQueueSent(device);
			device.getDeviceByName("device c").getQueueRecive(device);
		}
		catch(Exception e) {
			System.out.print("failed to call a function");
		}*/
		System.out.println("\n\n\n--message that device c recive--");
		do {
			try {
				System.out.print("\n"+device.getDeviceByName("device c").assembleMSG(device));
			}
			catch(Exception e) {
				System.out.print("failed to call a function");
			}
		}while(device.getDeviceByName("device c").getReciveLength()!=0);
	}

}
//note: app use mostly linear search
//err hanlder 1 location sent msg function in device queue, input hanlder, happen when put wrong id into set msg
//err hanlder 2 location assemble msg function in device, output handler, happen when asemble msg when there are no msg in the 
//reciving queue
//err hablder 3 location try catch in main to handle the inability to call function properly when invalid data is inputed