import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Memory {

	public static Queue<String[]> queue = new LinkedList<String[]>();

	public static String temp = "";
	public static String addRes = "";

	public static String instruct = "";

	static int[][] cashe = new int[512][3]; // [valid,tag,data]

	public static void memToWriteBack(String[] cashe3) {
//		System.out.println(cashe3[4]+" mennaaa");
		String instruction = Main.memory[Integer.parseInt(cashe3[4])];
		if (instruction == null)
			return;
		String data = "";

		String reg1 = cashe3[0];
		String reg2 = cashe3[1];

		String memRead = cashe3[2];
		String memWrite = cashe3[3];

		String a = cashe3[4];
		String zero = cashe3[5];
		String ALUResult = cashe3[6]; // read data then add to cache 4
		String writeData = cashe3[7];
		String direct = cashe3[8];

		Main.cashe4[0] = reg1;
		Main.cashe4[1] = reg2;

		if (memRead.equals("1")) {

			int indx = Integer.parseInt(ALUResult) % 512;
			int tag = Integer.parseInt(ALUResult) / 512;
			if (cashe[indx][0] == 0) {
				cashe[indx][0] = 1;
				cashe[indx][1] = tag;
				cashe[indx][2] = Integer.parseInt(Main.memory[Integer.parseInt(ALUResult)].substring(1));
				
				
				data = Main.memory[Integer.parseInt(ALUResult)].substring(1);
			} else {
				if (cashe[indx][1] == tag) {
					// System.out.println(cashe[indx][2]);
					data = cashe[indx][2] + "";
				} else {
					cashe[indx][1] = tag;
					cashe[indx][2] = Integer.parseInt(Main.memory[Integer.parseInt(ALUResult)].substring(1));
					data = Main.memory[Integer.parseInt(ALUResult)].substring(1);
				}
			}
			// read from read data and go to cache 4
			Main.cashe4[3] = data;

		} else if (memWrite.equals("1")) {
			int indx = Integer.parseInt(ALUResult) % 512;
			int tag = Integer.parseInt(ALUResult) / 512;

			cashe[indx][0] = 1;
			cashe[indx][1] = tag;
			cashe[indx][2] = Integer.parseInt(writeData);
			
			
			Main.memory[Integer.parseInt(ALUResult)] = "1" + writeData;
			Main.writedatamem = writeData;
		} else {

			Main.cashe4[2] = ALUResult;
		}

		Main.cashe4[4] = direct;
		Main.cashe4[5] = cashe3[4];

		// ALU result: 0000 0000 0000 0000 0000 0000 0000 0000
		// memory word read: 1111 1111 1111 1111 0000 0000 0000 0000
		// rt/rd field: 01001
		// WB controls: MemToReg: 0, RegWrite: 1
		Main.mem = (memRead.equals("1"))
				? (instruction + " in Memory Stage." + "\nmem variables " + "\nALU result: " + ALUResult
						+ "\nmemory word read: " + Main.memory[Integer.parseInt(ALUResult)].substring(1)
						+ "\nrt/rd field: " + cashe3[8] + "WB controls: MemToReg: " + cashe3[1] + ", " + "RegWrite: "
						+ cashe3[0])
				: (instruction + " in Memory Stage." + "\nmem variables " + "\nALU result: " + ALUResult
						+ "\nmemory word read: " + "don't care" + "\nrt/rd field: " + cashe3[8]
						+ "WB controls: MemToReg: " + cashe3[1] + ", " + "RegWrite: " + cashe3[0]);
		// Main.mem = instruction + " in Memory Stage." + "\nmem variables";
		//System.out.println(Main.memory[Integer.parseInt(ALUResult)]);
		WriteBackInstruction.writebackqueue.add(Main.cashe4);

	}

	public String mux(String a, String b, String select) {
		if (select.equals("0"))
			return a;
		return b;
	}

	public String add(String a, String b) {
		return (2 << (Integer.parseInt(a, 2)) + Integer.parseInt(b, 2)) + "";
	}
}
