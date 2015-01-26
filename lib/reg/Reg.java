package reg;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reg {
	public static List<String> matches(String reg_str, CharSequence str){
		List<String> matchList = new ArrayList<String>();
		Pattern pattern = Pattern.compile(reg_str);
		Matcher matcher = pattern.matcher(str);
		
		int index = 0;
		while(matcher.find(index)){
			matchList.add(matcher.group());
			index = matcher.end();
		}
		return matchList;
	}
}
