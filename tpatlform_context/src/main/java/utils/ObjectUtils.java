package utils;

public class ObjectUtils {
	
	public static boolean isNotNull(Object obj) {
		if(obj == null||"".equals(obj.toString())) {
			return false;
		}else {
			return true;
		}
		
	}

}
