package utils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeUtils<T>  implements Serializable {

	private String id;           // 节点id
    private String parentId;    // 父节点
    private String name;         // 节点名称 ,返回给前台的是一个装有TreeUtils的集合的数据，所以在前台显示数据的时候，el-tree的lable的名字的和这个一样
    private boolean disabled = false;
    private String icon;//图标
    private List<TreeUtils> childList;  // 父节点中存放子节点的集合
    private T data;              //  节点数据

    /**
     * 将传入的数据转换成树形结构
     * @param listData 从数据库中查询的数据
     * @param id 树形关系的属性名称（例如nodeid）
     * @param parentId 上级属性名称
     * @param categoryName 节点展示用的属性
     * @param disabledFN 禁用选择的属性名
     * @param iconFn 图标的属性名
     * @return
     * @throws Exception
     */
    public static List<TreeUtils> getTreeList(List<?> listData ,String id,String parentId,String categoryName, String disabledFN, String iconFn) throws Exception{

        List<TreeUtils> resultList = new ArrayList<TreeUtils>();  // 最终返回的结果
       Map<String ,Object> map = new HashMap<String,Object>();

        for(int i =0;i<listData.size() && !listData.isEmpty();i++){

            // 写一个与该方法差不多的方法，将得到TreeUtils的代码抽取出来
            // 也可以将listData集合整个转换成装有TreeUtils的集合x，然后再循环x
            TreeUtils treeUtils = new TreeUtils();
            //treeUtils.setId(Integer.parseInt(TreeUtils.getFileValue(listData.get(i),id).toString())); // id              // 返回值为Object无法直接转换成Integer,先toString，再转换成Integer。这里的返回值写成Object是因为多种类型字段的值都可以用该方法
            treeUtils.setId(TreeUtils.getFileValue(listData.get(i),id).toString()); // id  
            //treeUtils.setParentId(Integer.parseInt(TreeUtils.getFileValue(listData.get(i),parentId).toString())); // 父id
            treeUtils.setParentId(TreeUtils.getFileValue(listData.get(i),parentId).toString()); 
            treeUtils.setName(TreeUtils.getFileValue(listData.get(i),categoryName).toString());  // 节点名
            if(disabledFN != null) {
            	treeUtils.setDisabled((boolean) TreeUtils.getFileValue(listData.get(i),disabledFN));
            }
            if(iconFn != null) {
            	treeUtils.setIcon(TreeUtils.getFileValue(listData.get(i),iconFn).toString());
            }
            //System.out.println("节点名为+"+TreeUtils.getFileValue(listData.get(i),categoryName).toString());
            treeUtils.setData(listData.get(i));   // data:原对象中的所有属性，无children

            // 通过反射得到每条数据的id将数据封装的map集合中，id为键，元素本身为值
            map.put(treeUtils.getId(),treeUtils);


            // 将所有顶层元素添加到resultList集合中
            //if( 0 == treeUtils.getParentId()){
             //   resultList.add(treeUtils);
           // }
        }
// 得到所有的顶层节点，游离节点也算作顶层节点
// 优点一，不用知道等级节点的id
// 优点而，只查询了一次数据库
        for(int i =0;i<listData.size();i++){
            if(!map.containsKey(TreeUtils.getFileValue(listData.get(i),parentId).toString())){
                resultList.add((TreeUtils) map.get(TreeUtils.getFileValue(listData.get(i),id).toString()));
            }
        }



        for(int i =0;i<listData.size() && !listData.isEmpty();i++){
            TreeUtils obj = (TreeUtils)map.get(TreeUtils.getFileValue(listData.get(i), parentId).toString());
            if(obj != null){
                if(obj.getChildList() == null){
                    obj.setChildList(new ArrayList());
                }
                obj.getChildList().add(map.get(TreeUtils.getFileValue(listData.get(i),id).toString()));
            }
        }
        return resultList;
    }

    public static List<TreeUtils> getTreeListMap(List<Map<String, Object>> listData ,String id,String parentId,String categoryName, String disabledFN, String iconFn) throws Exception{
    	List<TreeUtils> resultList = new ArrayList<TreeUtils>();  // 最终返回的结果
    	Map<String ,Object> map = new HashMap<String,Object>();
    	for(int i =0;i<listData.size() && !listData.isEmpty();i++){

            // 写一个与该方法差不多的方法，将得到TreeUtils的代码抽取出来
            // 也可以将listData集合整个转换成装有TreeUtils的集合x，然后再循环x
            TreeUtils treeUtils = new TreeUtils();
            //treeUtils.setId(Integer.parseInt(TreeUtils.getFileValue(listData.get(i),id).toString())); // id              // 返回值为Object无法直接转换成Integer,先toString，再转换成Integer。这里的返回值写成Object是因为多种类型字段的值都可以用该方法
            treeUtils.setId(listData.get(i).get(id).toString()); // id  
            //treeUtils.setParentId(Integer.parseInt(TreeUtils.getFileValue(listData.get(i),parentId).toString())); // 父id
            treeUtils.setParentId(listData.get(i).get(parentId).toString()); 
            treeUtils.setName(listData.get(i).get(categoryName).toString());  // 节点名
            if(disabledFN != null) {
            	if("true".equals(listData.get(i).get(disabledFN).toString())){
            		treeUtils.setDisabled(true);
            	}else {
            		treeUtils.setDisabled(false);
            	}
            }
            if(iconFn != null) {
            	treeUtils.setIcon(listData.get(i).get(iconFn).toString());
            }
            //System.out.println("节点名为+"+TreeUtils.getFileValue(listData.get(i),categoryName).toString());
            treeUtils.setData(listData.get(i));   // data:原对象中的所有属性，无children

            // 通过反射得到每条数据的id将数据封装的map集合中，id为键，元素本身为值
            map.put(treeUtils.getId(),treeUtils);


            // 将所有顶层元素添加到resultList集合中
            //if( 0 == treeUtils.getParentId()){
             //   resultList.add(treeUtils);
           // }
         }
    	 for(int i =0;i<listData.size();i++){
            if(!map.containsKey(listData.get(i).get(parentId).toString())){
                resultList.add((TreeUtils) map.get(listData.get(i).get(id).toString()));
            }
         }



        for(int i =0;i<listData.size() && !listData.isEmpty();i++){
            TreeUtils obj = (TreeUtils)map.get(listData.get(i).get(parentId).toString());
            if(obj != null){
                if(obj.getChildList() == null){
                    obj.setChildList(new ArrayList());
                }
                obj.getChildList().add(map.get(listData.get(i).get(id).toString()));
            }
        }
    	return resultList;
    }
    /**
     * 通过反射得到的数据类型的也是不一定的，所以这里我们返回值为Object
     * Object是无法直接转为Integer,现将Object转为String，然后再将String转为Integer
     * @param item
     * @param fileName
     * @return
     * @throws Exception
     */
    public static Object  getFileValue(Object item,String fileName) throws Exception {
        Class<?> aClass = item.getClass();
        Field file = aClass.getDeclaredField(fileName); // 得到所有字段包括私有字段
        file.setAccessible(true); // 取消访问限制
        return file.get(item);    // 这里就体现出反射的意思了，我们通常都是通过对象拿到字段，这里是通过字段，将类的字节码对象为参数传入，来得到值
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TreeUtils> getChildList() {
		return childList;
	}

	public void setChildList(List<TreeUtils> childList) {
		this.childList = childList;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}
