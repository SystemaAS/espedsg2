

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
/**
 * @author oscardelatorre
 *
 */
public class JavaReflexionTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JavaReflexionTester tester = new JavaReflexionTester();
		
		try{
			//tester.run();
			tester.runUrlStore();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	/**
	 oscardelatorre
	 Dec 1, 2012
	 */
	private void run() throws Exception{
		/*SearchFilterTdsExportTopicList searchFilter = new SearchFilterTdsExportTopicList();
		searchFilter.setStatus("S");
		Class cl = Class.forName(searchFilter.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		for(Field field : list){
			field.setAccessible(true);
			try{
				String value = (String)field.get(searchFilter);
				if(value!=null && !"".equals(value)){
					System.out.println(field.getName() + " Value:" + value);
				}
				
			}catch(Exception e){
				continue;
			}
		}
		*/
	}
	
	private void runUrlStore() throws Exception{
		/*TrorUrlDataStore store = new TrorUrlDataStore();
		Class cl = Class.forName(store.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		for(Field field : list){
			try{
				field.setAccessible(true);
				String value = (String)field.get(store);
				if(value!=null && !"".equals(value)){
					System.out.println(field.getName() + " Value:" + value);
				}
			}catch(Exception e){
				continue;
			}
		}*/
	}
	
}
