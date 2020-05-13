import java.util.LinkedList;
import java.util.Queue;


public class WriteBackInstruction {
	
	public static Queue<String[]> writebackqueue = new LinkedList<String[]>();
	
	public static void writeBack() {
		String[] cashe4 = writebackqueue.poll();
		String instruction = Main.memory[Integer.parseInt(cashe4[5])];
		if (instruction == null) return;
		Main.writeregister = Integer.parseInt(cashe4[4]);
		Main.regwrite = Integer.parseInt(cashe4[0]);
		Main.memtoreg = Integer.parseInt(cashe4[1]);
		if (Integer.parseInt(cashe4[0]) == 1 && Integer.parseInt(cashe4[4])> -1 && Integer.parseInt(cashe4[4]) < Main.registers.length) {
			if (Integer.parseInt(cashe4[1]) == 0) {
				if(!cashe4[4].equals("0") ){
					Main.registers[	Integer.parseInt(cashe4[4])] = Integer.parseInt(cashe4[2]);	
				}
			}
			else {
				if(!cashe4[4].equals("0") ){
					Main.registers[Integer.parseInt(cashe4[4])] = Integer.parseInt(cashe4[3]);	
				}
			}
		}
//		System.out.println("innnn");
		Main.writeBack = instruction + " in WB Stage";
		
	}


}
