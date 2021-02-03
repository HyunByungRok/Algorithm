package BOJ;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ3425_고스택 {
	private static final long MAX = (long) Math.pow(10, 9);
	private static String command, result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		while(true) {
			List<String> cmdList;
			command = new String(br.readLine());
			if("QUIT".equals(command)) break;
			else {
				cmdList = new ArrayList<String>();
				cmdList.add(command);
			}
			if(!"END".equals(command)) {
				do {
					cmdList.add(command=br.readLine());
				}while(!"END".equals(command));
			}
			cmdList.remove(cmdList.size()-1);
			int size = cmdList.size();
			int loop = Integer.parseInt(br.readLine());
			
			
			for(int l=0; l<loop; l++) {
				Stack<Long> stack = new Stack<Long>();
				
				stack.push(Long.parseLong(br.readLine()));
				
				for(int i=0; i<size; i++) {
					StringTokenizer st = new StringTokenizer(cmdList.get(i));
					command = st.nextToken();
					result = null;
					if("NUM".equals(command)) {
						stack.push(Long.parseLong(st.nextToken()));
					}else {
						if(stack.isEmpty()) {
							result = "ERROR";
							break;
						}
						if("POP".equals(command)) {
							stack.pop();
						}else if("INV".equals(command)) {
							stack.push(stack.pop()*-1);
						}else if("DUP".equals(command)) {
							stack.push(stack.peek());
						}else {
							long num1 = stack.pop();
							if(stack.isEmpty()) {
								result = "ERROR";
								break;
							}
							long num2 = stack.pop();
							if("SWP".equals(command)) {
								stack.push(num1);
								stack.push(num2);
							}else if("ADD".equals(command)) {
								if(isOver(num1+num2)) {
									result = "ERROR";
									break;
								}
								stack.push(num1+num2);
							}else if("SUB".equals(command)) {
								if(isOver(num2-num1)) {
									result = "ERROR";
									break;
								}
								stack.push(num2-num1);
							}else if("MUL".equals(command)) {
								if(isOver(num1*num2)) {
									result = "ERROR";
									break;
								}
								stack.push(num1*num2);
							}else if("DIV".equals(command) || "MOD".equals(command)) {
								if(num1 == 0) {
									result = "ERROR";
									break;
								}
								if("DIV".equals(command)) {
									stack.push(num2/num1);
								}else {
									stack.push(num2%num1);
								}
							}
						}
					}
					
				}
				if("ERROR".equals(result)) {
					sb.append("ERROR").append("\n");
				}else {
					if(stack.size()!=1) {
						sb.append("ERROR").append("\n");
					}else {
						sb.append(stack.pop()).append("\n");
					}
				}
			}
			br.readLine();
			sb.append("\n");
		}
		System.out.println(sb.toString());
		
	}
	private static boolean isOver(Long integer) {
		return Math.abs(integer) > MAX;
	}
	
	
}
