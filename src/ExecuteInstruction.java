import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ExecuteInstruction {
	public static Queue<String[]> executequeue = new LinkedList<String[]>();
	// zawedt hena
	static int Branch = 0;
	static boolean afterBranch = false;
	static boolean add = true;

	public static int Execute(String OPcode, int Readdata1, int Readdata2, String extended, int pc, String regdst) {
		// dy ziada
		if (afterBranch) {
			afterBranch = false;
			add = false;
			Main.execute = "";
			return 0;
		} else
			add = true;
		String instruction1 = Main.memory[pc];
		if (instruction1 == null)
			return 0;

		int ALUresult = -1;
		int immediate = Integer.parseInt(extended.substring(16), 2);
		int branch = 0;
		if (OPcode.equals("0000")) { // add
			ALUresult = Readdata1 + Readdata2;
			Main.cashe3[0] = 1 + "";
			Main.cashe3[1] = 0 + "";
			Main.cashe3[2] = 0 + "";
			Main.cashe3[3] = 0 + "";

		} else if (OPcode.equals("0001")) { // sub

			ALUresult = (Readdata1 > Readdata2) ? Readdata1 - Readdata2 : Readdata2 - Readdata1;
			Main.cashe3[0] = 1 + "";
			Main.cashe3[1] = 0 + "";
			Main.cashe3[2] = 0 + "";
			Main.cashe3[3] = 0 + "";
		} else if (OPcode.equals("0010")) { // mult
			ALUresult = Readdata1 * Readdata2;
			Main.cashe3[0] = 1 + "";
			Main.cashe3[1] = 0 + "";
			Main.cashe3[2] = 0 + "";
			Main.cashe3[3] = 0 + "";
		} else if (OPcode.equals("0011")) { // addim
			ALUresult = Readdata1 + immediate;
			System.out.println("aluresultttt" + Readdata1);
			Main.cashe3[0] = 1 + "";
			Main.cashe3[1] = 0 + "";
			Main.cashe3[2] = 0 + "";
			Main.cashe3[3] = 0 + "";
		} else if (OPcode.equals("0100")) { // or
			ALUresult = Readdata1 | Readdata2;
			Main.cashe3[0] = 1 + "";
			Main.cashe3[1] = 0 + "";
			Main.cashe3[2] = 0 + "";
			Main.cashe3[3] = 0 + "";
		} else if (OPcode.equals("0101")) { // andim
			ALUresult = Readdata1 & immediate;
			Main.cashe3[0] = 1 + "";
			Main.cashe3[1] = 0 + "";
			Main.cashe3[2] = 0 + "";
			Main.cashe3[3] = 0 + "";
		} else if (OPcode.equals("0110")) { // shiftr
			ALUresult = Readdata1 >> immediate;
			Main.cashe3[0] = 1 + "";
			Main.cashe3[1] = 0 + "";
			Main.cashe3[2] = 0 + "";
			Main.cashe3[3] = 0 + "";
		} else if (OPcode.equals("0111")) { // shiftl
			ALUresult = Readdata1 << immediate;
			Main.cashe3[0] = 1 + "";
			Main.cashe3[1] = 0 + "";
			Main.cashe3[2] = 0 + "";
			Main.cashe3[3] = 0 + "";
		} else if (OPcode.equals("1000")) { // lw
			ALUresult = Readdata1 + immediate;
			Main.cashe3[0] = 1 + "";
			Main.cashe3[1] = 1 + "";
			Main.cashe3[2] = 1 + "";
			Main.cashe3[3] = 0 + "";
		} else if (OPcode.equals("1001")) { // sw
			System.out.println(Readdata1 + " leqaaa " + immediate);
			ALUresult = Readdata1 + immediate;
			Main.cashe3[0] = 0 + "";
			Main.cashe3[1] = 0 + "";
			Main.cashe3[2] = 0 + "";
			Main.cashe3[3] = 1 + "";
		} else if (OPcode.equals("1010")) { // eq
			ALUresult = Readdata1 - Readdata2;
			Main.cashe3[0] = 0 + "";
			Main.cashe3[1] = 0 + "";
			Main.cashe3[2] = 0 + "";
			Main.cashe3[3] = 0 + "";
			if (ALUresult == 0) {
				branch = immediate;
			}
		} else if (OPcode.equals("1011")) {
			// less than
			ALUresult = Readdata1 - Readdata2;
			Main.cashe3[0] = 0 + "";
			Main.cashe3[1] = 0 + "";
			Main.cashe3[2] = 0 + "";
			Main.cashe3[3] = 0 + "";
			if (ALUresult < 0) {
				branch = immediate;
			}
		} else if (OPcode.equals("1100")) { // setonless
			ALUresult = (Readdata1 < immediate) ? 1 : 0;
			Main.cashe3[0] = 1 + "";
			Main.cashe3[1] = 0 + "";
			Main.cashe3[2] = 0 + "";
			Main.cashe3[3] = 0 + "";
		} else { // jump
			Main.pc = Main.registers[Integer.parseInt(Main.memory[pc].substring(24, 28), 2)];
			Main.cashe3[0] = 0 + "";
			Main.cashe3[1] = 0 + "";
			Main.cashe3[2] = 0 + "";
			Main.cashe3[3] = 0 + "";
		}
		String instruction = Main.memory[pc];
		if (branch != 0) {
			Branch = 1;
			Main.pc = pc + branch + 1;
		}
		Main.cashe3[4] = pc + "";
		// haghayar fe dy

		// khaletha keda

		// putting el 7agat fe el cahce
		// sheleha
		System.out.println("pc after branch " + Main.pc);
		Main.cashe3[5] = ALUresult <= 0 ? 1 + "" : 0 + "";
		Main.cashe3[6] = ALUresult + "";
		Main.cashe3[7] = Readdata2 + "";
		Main.cashe3[8]=Branch+"";
		if (Main.cashe3[5] .equals("1") & Branch==1) {
			Main.pcsrc = 1;
		} else {
			Main.pcsrc = 0;
		}
//		System.out.println(pc+ " leqaa");
//		System.out.println(instruction+" leqaaa");
		Main.cashe3[8] = Integer.parseInt(regdst) == 0 ? Integer.parseInt(instruction.substring(28), 2) + ""
				: Integer.parseInt(instruction.substring(20, 24), 2) + "";

//		zero flag: 0
//		branch address: 0000 0000 0000 0000 0000 0000 0001 1000
//		ALU result/address: 0000 0000 0000 0000 0000 0000 0000 0101
//		register value to write to memory: 0000 0000 0000 0000 0000 0000 0000 0000
//		rt/rd register: 01000
//		WB controls: MemToReg: 1, RegWrite: 1
//		MEM controls: MemRead: 0, MemWrite: 0, Branch: 0

		Main.execute = instruction1 + " in Execute Stage." + "\n" + "zero flag: " + Main.cashe3[5] + "branch address: "
				+ Integer.toBinaryString(branch) + "\n" + "ALU result/address: " + Integer.toBinaryString(ALUresult);
		if (OPcode.equals("1001"))
			Main.execute += "\n" + "register value to write to memory:  " + Readdata2;
		else
			Main.execute += "\n" + "register value to write to memory:  " + "0000 0000 0000 0000 0000 0000 0000 0000";
		Main.execute += "\n" + "rt/rd register: " + Main.cashe3[8] + "\n" + "WB controls: MemToReg: " + Main.cashe3[1]
				+ "RegWrite:" + Main.cashe3[0] + "\n" + "MEM controls: " + "MemRead: " + Main.cashe3[2]
				+ ", MemWrite:  " + Main.cashe3[3] + " ,Branch: " +Branch+" ,pcsrc:"+ Main.pcsrc;
		// Main.execute+= "\nNext PC: " + Main.pc;

		// sending the cache to the memo stage

		return ALUresult;
	}

}
