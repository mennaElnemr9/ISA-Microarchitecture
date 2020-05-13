
import java.util.LinkedList;
import java.util.Queue;


public class FetchInstruction {
	public static Queue<String[]> fetchqueue = new LinkedList<String[]>();
	
	
	
	public static String InstFetch(int instructionAdd) { // take the adress from the PC
		String instruction = Main.memory[instructionAdd];
		if (instruction == null) return "";
		
//	if(instruction==null) {
//			
//			System.out.println( "-1");
//		}
		if(instruction.charAt(0)=='0') {
			Main.cashe1[1]=Main.memory[instructionAdd];	
		} 
		
//		fetchqueue.remove();
		Main.cashe1[0]=Main.pc+"";	
//		System.out.println(Main.pc + "before");
		ProgCount(instructionAdd);
//		System.out.println(Main.pc + "after");
		Main.fetch = instruction + " in Fetch Stage." + "\nNext PC: " + Main.prepend(Integer.toBinaryString(Main.pc), 32) + "\nInstruction: " + instruction;
		if(instruction.charAt(0)=='0')
			return instruction;
		else
			return "-1";
	}
	public static void ProgCount(int instructionAdd) {
		Main.pc = instructionAdd+1;
	}
	
	
}