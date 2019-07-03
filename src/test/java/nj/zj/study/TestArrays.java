package nj.zj.study;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**

* <p>Description: Arrays.asList()使用

 * Arrays.asList()将数组转换为集合后,底层其实还是数组
 * 传递的数组必须是对象数组，而不是基本类型。
 * 使用集合的修改方法:add()、remove()、clear()会抛出异常。
 *
 * </p>

* @author ZTY  

* @date 2019年6月25日  

*/
public class TestArrays {
	public static void main(String[] args) {

		/**
		 * 如何正确的将数组转换为ArrayList?
		 */

		//1.最简便的方法(推荐)
		ArrayList<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));

		//2.使用 Java8 的Stream(推荐)
		Integer [] myarr={1,2,3};
		List<Integer> list1 = Arrays.stream(myarr).collect(Collectors.toList());
		//基本类型也可以实现转换（依赖boxed的装箱操作）
		int [] arr={1,2,3};
		List<Integer> collect = Arrays.stream(arr).boxed().collect(Collectors.toList());

		//使用 Apache Commons Collections
		String  [] str={"aa", "安监上的"};
		List<String> mylist = new ArrayList<>();
		CollectionUtils.addAll(mylist,str);
		System.out.println(mylist);

	}
}
