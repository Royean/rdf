package utils;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtils {

/**

	 * 

	 * @param list  外面传进来的list集合

	 * @param clazz 为了获取属性名，传进来一个class

	 * @return       返回JSONArray

	 */

	public static <T> JSONArray formatRsToJsonArray(List<T> list,Class<T> clazz ) {

		try {

 

			//首先创建一个JSONArray对象			

			JSONArray jsonArray = new JSONArray();

			//获取List迭代器

			Iterator it = list.iterator();

			while(it.hasNext()){

				//新建一个JSONOBject

				//先将获取到的一条记录存入JSONObject中

				JSONObject o = new JSONObject();	

				

				Field [] fs = clazz.getDeclaredFields();//获取传进来的class的所有属性	

				Object object = it.next();         //记录迭代器的下一个记录

				for (int i = 0; i < fs.length; i++) {

					fs[i].setAccessible(true);//强暴遍历到的属性，以获取私有属性

					Field f = object.getClass().getDeclaredField(fs[i].getName()); //根据强暴到的属性名再次获取属性

					f.setAccessible(true);   //强暴，获取私有属性

					String name = fs[i].getName();  //得到属性名

					Object value = f.get(object);	//得到属性名对应的属性值			 

					o.put(name, value);    //将属性名和属性值分别作为Key、value存入JSONObject中

				}

				jsonArray.add(o);  //最后将JSONObject加入JSONArray中

			}

			return jsonArray;   //返回JSONArray

		} catch (Exception e) {

			throw new RuntimeException(e);

		}

 

	}

}
