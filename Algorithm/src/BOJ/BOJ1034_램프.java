package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ1034_램프 {
	static int n, m, k, max;
	static Map<String, Integer> map;
	public static void main(String[] args) throws Exception{
		input();
		solve();
		System.out.println(max);
	}

	private static void solve() {
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			String removeOne = key.replace("1", "");
			int howZero = removeOne.length();
			if(k-howZero < 0 || (k-howZero)%2 != 0) continue;
			max = Math.max(max, map.get(key));
		}
	}

	private static void input() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new HashMap<String, Integer>();
		for(int i=0; i<n; i++) {
			String input = br.readLine();
			if(map.containsKey(input)) {
				map.replace(input, map.get(input)+1);
			}else {
				map.put(input, 1);
			}
		}
		k = Integer.parseInt(br.readLine());
		
		max = 0;
	}
}
