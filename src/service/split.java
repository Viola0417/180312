package service;

import java.util.Vector;

public class split {
//把字符串变成vector
//eg.string s = ['a','b','c']--> vector[a,b,c]
	public Vector splitStr(String s) {
		//System.out.println("过来了");
		Vector<String> res = new Vector<String>();
		Vector<Integer> list = new Vector<Integer>();
		String substr = new String();
		int i = 0;
		while(i<s.length()) {
			//System.out.println(s.charAt(i));
			if(s.charAt(i)=='\'') {
				//System.out.println(i);
				list.add(i);
			}
			i++;
		}
		//System.out.println(list);
		i=0;
		while(i<(list.size()-1)) {
			//System.out.println(list.get(i));
		
			substr = s.substring(list.get(i)+1,list.get(i+1));
			//System.out.println(substr);
			res.add(substr);
			i=i+2;
		}
		System.out.println(res);
		return res;
	}

}

