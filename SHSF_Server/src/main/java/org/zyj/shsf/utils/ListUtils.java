package org.zyj.shsf.utils;

import java.util.ArrayList;
import java.util.List;


public class ListUtils extends org.apache.commons.collections.ListUtils {

	public ListUtils(){
		
	}
	public static boolean isBlank(List<?> list) {
        return list == null || list.isEmpty();
    }

    public static boolean isNotBlank(List<?> list) {
        return list != null && !list.isEmpty();
    }
    
    public static <T> List<T> newArrayList(int size){
        return new ArrayList<T>(size);
    }
    
    public static <T> List<T> newArrayList(){
        return new ArrayList<T>();
    }
}
