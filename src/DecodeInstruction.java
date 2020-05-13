import java.util.LinkedList;
import java.util.Queue;


public class DecodeInstruction {
	private static final char[] Time = null;
	public static Queue<String[]> decodequeue = new LinkedList<String[]>();
	
	
	public static int fromBtoD(String x){
		int s=0;
		for(int i=0;i<x.length()-1;i++) {
			if(x.charAt(x.length()-i-1)=='1') {
				s+=Math.pow(2, i);		
			}}
			return s;}
	
	
	public static String fromDtoB(int x) {
		String s="";
		if(x>0) {
		while(x>1) {
			s=(x%2)+s;
			x/=2;
		}
		s=1+s;}
		else s="0";
		for(int i=s.length();i<32;i++) {
			s=0+s;
		}
		return s;}
	
	

	public static String[]	InstDecode(String instruction,String pc) {
	  if (instruction == null) return null;

	  String[] s=new String[14];
	  String[] signals= ContUnit(instruction.substring(1,5));
	  for(int i=0;i<7;i++)
		  s[i]=signals[i];
	  
	  s[7]=pc;
	  s[8]=Main.registers[Integer.parseInt(instruction.substring(24, 28),2)]+"";
	 
	  s[9]=Main.registers[Integer.parseInt(instruction.substring(28, 32),2)]+"";
	  s[10]=instruction.substring(1, 5);
	  s[11]=SignExtend(instruction.substring(5, 20));
	  s[12]=instruction.substring(28);
	  s[13]=instruction.substring(20, 24);
	  //controlSignals,pc,readdata1,readdata2,opcode,rt,rd
	  Main.cashe2=s;
	  
	  Main.memread=Integer.parseInt(s[0]);
	  Main.memwrite=Integer.parseInt(s[1]);
	  Main.regwrite=Integer.parseInt(s[2]);
	  Main.pcsrc=Integer.parseInt(s[3]);
	  Main.regdst=Integer.parseInt(s[4]);
	  Main.memtoreg=Integer.parseInt(s[5]);
	  Main.Alusrc=Integer.parseInt(s[6]);
	  Main.op=fromBtoD(s[10]);
	  Main.readdata1=Integer.parseInt(s[8]);
	  Main.readdata2=Integer.parseInt(s[9]);
	  Main.writeregister=fromBtoD(s[13]);
	  Main.readregister1=fromBtoD(instruction.substring(24, 28));
	  Main.readregister2=fromBtoD(s[12]);
	  
	  Main.decode = instruction + " in Decode Stage." + "\nread data 1: " + Main.prepend(Integer.toBinaryString(Main.readdata1), 32) + 
			  "\nread data 2: " + Main.prepend(Integer.toBinaryString(Main.readdata2), 32) + "\nsign-extend: " + s[11] +"\nNext PC: "+fromDtoB(Integer.parseInt(pc)+1) +"\nrt: " 
			  + s[12] + "\nrd: " + s[13] + "\nWB Controls: MemtoReg: " + Main.memtoreg + " RegWrite: " + Main.regwrite + "\nMEM Controls: MemRead: "
			  + Main.memread + " MemWrite: " + Main.memwrite + " Branch: " + Main.pcsrc + "\nEX Controls: RegDst: " + Main.regdst
			  + " ALUOp: " + s[10] + "ALUSrc: " + Main.Alusrc;
	
	  return s;
	}
	public static String SignExtend(String s){
		//first bit should not be used in calculations
	  if(s.charAt(0)=='1')
		 return "111111111111111111"+s;
	  return "10000000000000000"+s;
	}
	public static String[] ContUnit(String op){
	   switch(op){
	   //memRead,memWrite,RegWrite,pcSrc,RegDst,MemtoReg,AluSrc
	   case "0000": case "0001": case "0010": case "0100":String[] a= {"0","0","1","0","1","0","0"}; return a;
	   case"0011": case "0101": case "1100": case "0110": case "0111": String[] b= {"0","0","1","0","1","0","1"}; return b;
	   case "1000":String[] c= {"1","0","1","0","1","1","1"}; return c;
	   case "1001":String[] d= {"0","1","0","0","0","0","1"}; return d;
	   case "1010": case "1011": case "1101": String[] e= {"0","0","0","1","0","0","0"};return e;
	   }
	   return null;
	}

	
}
//import java.sql.Time;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.LinkedList;
//import java.util.Queue;
//
//
//public class DecodeInstruction {
//	private static final char[] Time = null;
//	public static Queue<String[]> decodequeue = new LinkedList<String[]>();
//	
//
//	public static String[]	InstDecode(String instruction,String pc) {
//	  if (instruction == null) return null;
//		
//	  String[] s=new String[14];
//	  String[] signals= ContUnit(instruction.substring(1,5));
//	  for(int i=0;i<7;i++)
//		  s[i]=signals[i];
//	  s[7]=pc;
//	  s[8]=Main.registers[Integer.parseInt(instruction.substring(24, 28),2)]+"";
//	 
//	  s[9]=Main.registers[Integer.parseInt(instruction.substring(28, 32),2)]+"";
//	  s[10]=instruction.substring(1, 5);
//	  s[11]=SignExtend(instruction.substring(5, 20));
//	  s[12]=instruction.substring(28);
//	  s[13]=instruction.substring(20, 24);
//	  //controlSignals,pc,readdata1,readdata2,opcode,rt,rd
//	  Main.cashe2=s;
//	  
//	  Main.memread=Integer.parseInt(s[0]);
//	  Main.memwrite=Integer.parseInt(s[1]);
//	  Main.regwrite=Integer.parseInt(s[2]);
//	  Main.pcsrc=Integer.parseInt(s[3]);
//	  Main.regdst=Integer.parseInt(s[4]);
//	  Main.memtoreg=Integer.parseInt(s[5]);
//	  Main.Alusrc=Integer.parseInt(s[6]);
//	  Main.op=Integer.parseInt(s[10],2);
//	  Main.readdata1=Integer.parseInt(s[8]);
//	  Main.readdata2=Integer.parseInt(s[9]);
//	  Main.writeregister=Integer.parseInt(s[13]);
//	  Main.readregister1=Integer.parseInt(instruction.substring(24, 28));
//	  Main.readregister2=Integer.parseInt(s[12]);
//	  
//	  Main.decode = instruction + " in Decode Stage." + "\nread data 1: " + Main.prepend(Integer.toBinaryString(Main.readdata1), 32) + 
//			  "\nread data 2: " + Main.prepend(Integer.toBinaryString(Main.readdata2), 32) + "\nsign-extend: " + s[11] + "\nrt: " 
//			  + s[12] + "\nrd: " + s[13] + "\nWB Controls: MemtoReg: " + Main.memtoreg + " RegWrite: " + Main.regwrite + "\nMEM Controls: MemRead: "
//			  + Main.memread + " MemWrite: " + Main.memwrite + " Branch: " + Main.pcsrc + "\nEX Controls: RegDst: " + Main.regdst
//			  + " ALUOp: " + Main.op + "ALUSrc: " + Main.Alusrc;
//	  
//	  return s;
//	}
//	public static String SignExtend(String s){
//		//first bit should not be used in calculations
//	  if(s.charAt(0)=='1')
//		 return "111111111111111111"+s;
//	  return "10000000000000000"+s;
//	}
//	public static String[] ContUnit(String op){
//	   switch(op){
//	   //memRead,memWrite,RegWrite,pcSrc,RegDst,MemtoReg,AluSrc
//	   case "0000": case "0001": case "0010": case "0100":String[] a= {"0","0","1","0","1","0","0"}; return a;
//	   case"0011": case "0101": case "1100": case "0110": case "0111": String[] b= {"0","0","1","0","1","0","1"}; return b;
//	   case "1000":String[] c= {"1","0","1","0","1","1","1"}; return c;
//	   case "1001":String[] d= {"0","1","0","0","0","0","1"}; return d;
//	   case "1010": case "1011": case "1101": String[] e= {"0","0","0","1","0","0","0"};return e;
//	   }
//	   return null;
//	}
//
//	
//}
