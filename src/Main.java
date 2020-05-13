import java.io.File;
import java.util.Scanner;

public class Main {
	// Threads
	String name;
	Thread t;
	File file;
	public static boolean out=false;
	
	//for main method
	public static int countInstructions = 0;
	public static String fetch;
	public static String decode;
	public static String execute;
	public static String mem;
	public static String writeBack;
	
	//
	public static int pc = 0;
	public static String[] memory = new String[1024];
	public static int[] registers = new int[15];
	// for mem component
	public static int address = 0;
	public static String readdatamem = "";
	public static String readinstructionmem = "";
	public static String writedatamem = "";
	// -----------
	// for reg file component
	public static int readregister1 = 0;
	public static int readregister2 = 0;
	public static int writeregister = 0;
	public static int readdata1 = 0;
	public static int readdata2 = 0;
	public static int writedatareg = 0;
	// -----------
	// signals
	public static int memread = 0;
	public static int memwrite = 0;
	public static int regwrite = 0;
	public static int pcsrc = 0;
	public static int regdst = 0;
	public static int memtoreg = 0;
	public static int Alusrc = 0;
	public static int op = 0;
	public static int jump=0;
	// -----------
	// cashes
	public static String[] cashe1 = new String[2];
	public static String[] cashe2 = new String[14];
	public static String[] cashe3 = new String[10];
	public static String[] cashe4 = new String[6];
	
	
	
	
	public static  void setout(){
		out=true;
	}
	

	public static void loadinstructions() {
		Scanner sc = new Scanner(System.in);
		int i = 0;
		while (true) {
			String instruction = sc.nextLine();
			if (instruction.equals("-1")) {
				break;
			}
			countInstructions++;
			memory[i] = instruction;
			i++;
		}
		for(;i<memory.length;i++) {
			memory[i]="10";
		}
		
	}
	
	public static String prepend(String number, int n) {
		while(number.length() < n)
			number = '0' + number;
		return number;
	}
	
	public static void main(String[] args) {
		
		for(int i=0;i<registers.length;i++) {
			registers[i]=i;
		}
		System.out.println("Please enter each instruction as a binary number and terminate instruction list with -1, written as a decimal number.");
		loadinstructions();
		
		int i = 0;
		while (i++ < countInstructions+4) {
			
//			System.out.println("inbigloop");
			if (!WriteBackInstruction.writebackqueue.isEmpty()) {
				WriteBackInstruction.writeBack();
//				System.out.println("in");
			} else writeBack = "";
			
			if (!Memory.queue.isEmpty()) {
				Memory.memToWriteBack(Memory.queue.remove());
			} else mem = "";
			
			if (!ExecuteInstruction.executequeue.isEmpty()) {
				System.out.println("execute hena aho ");
				String[] arr = ExecuteInstruction.executequeue.remove();
				String opcode = arr[10];
				int readdata1 = Integer.parseInt(arr[8]);
				int readdata2 = Integer.parseInt(arr[9]);
				String extended = arr[11];
				int pc = Integer.parseInt(arr[7]);
				String regdst=arr[4];
				ExecuteInstruction.Execute(opcode, readdata1, readdata2, extended, pc,regdst);
				if(ExecuteInstruction.add ) {
					Memory.queue.add(Main.cashe3);
					if(ExecuteInstruction.Branch==1) {
					ExecuteInstruction.afterBranch=true;
					ExecuteInstruction.Branch=0;
					}
				}
			} else execute = "";
			
			if (!DecodeInstruction.decodequeue.isEmpty()) {
				String[] removed= DecodeInstruction.decodequeue.remove(); 
				ExecuteInstruction.executequeue.add(DecodeInstruction.InstDecode(removed[1],removed[0]));
			} else decode = "";
			
			if (pc < countInstructions) {
				String instruction = FetchInstruction.InstFetch(Main.pc);
				if(!instruction.equals("-1")) DecodeInstruction.decodequeue.add(Main.cashe1);
			} else fetch = "";
			
			if ((fetch != null && !fetch.equals("")) || (decode != null && !decode.equals("")) || (execute != null && !execute.equals("")) || (mem != null && !mem.equals("")) || (writeBack != null && !writeBack.equals(""))) 
				System.out.println("IN CYCLE: " + i);
			
			if (fetch != null && !fetch.equals("")) {
				System.out.println(fetch);
				System.out.println();
			}
			
			if (decode != null && !decode.equals("")) {
				System.out.println(decode);
				System.out.println();
			} 
			
			if (execute != null && !execute.equals("")) {
				System.out.println(execute);
				System.out.println();
			}
			
			
			if (mem != null && !mem.equals("")) {
				System.out.println(mem);
				System.out.println();
				//zawedt dy
				mem="";
			}
			
			if (writeBack != null && !writeBack.equals("")) {
				System.out.println(writeBack);
				System.out.println();
				//zawedt dy
				writeBack="";
			}
		}
		
		
	}
	
	
	
	
	
	
	
}