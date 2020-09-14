package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class 보물상자비밀번호_5658 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		Set<Integer> set = new TreeSet<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		});
		int testcase = Integer.parseInt(br.readLine()), n, k;
		for(int tc=1; tc<=testcase; tc++) {
			set.clear();
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); k = Integer.parseInt(st.nextToken());
			StringBuffer sbf = new StringBuffer(br.readLine());
			int size = n/4;
			for(int i=0; i<size; i++) {
				for(int j=0; j<n; j+=size) {
					set.add(Integer.parseInt(sbf.substring(j, j+size), 16));
				}
				
				sbf.append(sbf.charAt(0));
				sbf.deleteCharAt(0);
			}
			Iterator<Integer> it = set.iterator();
			for(int i=0; i<k-1; i++) it.next();
			sb.append("#").append(tc).append(" ").append(it.next()).append("\n");
		}
		System.out.println(sb.toString());
		
	}
}
