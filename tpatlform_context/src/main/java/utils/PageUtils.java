package utils;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;


/**
 * 分页工具类
 * 
 * @author Lanvo
 * @date 2018-11-27
 */
public class PageUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	//总记录数
	private int totalCount;
	//每页记录数
	private int pageSize;
	//总页数
	private int totalPage;
	//当前页数
	private int currPage;
	//列表数据
	private List<?> list;
	//
	private int page;
	
	private int limit;
	
//	/**
//	 * 分页
//	 * @param list        列表数据
//	 * @param totalCount  总记录数
//	 * @param pageSize    每页记录数
//	 * @param currPage    当前页数
//	 */
//	public PageUtils(List<?> list, int totalCount, int pageSize, int currPage) {
//		this.list = list;
//		this.totalCount = totalCount;
//		this.pageSize = pageSize;
//		this.currPage = currPage;
//		this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
//	}

	

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}



	public int getPage() {
		return page;
	}



	public void setPage(int page) {
		this.page = page;
	}



	public int getLimit() {
		return limit;
	}



	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public PageUtils(){
		
	}
	
	/**
	 * 分页
	 */
	public PageUtils(Page<?> page) {
		this.list = page.getResult();
		this.totalCount = (int)page.getTotal();
//		this.pageSize = page.getPageSize();
//		this.currPage = page.getStartRow();
//		this.totalPage = (int)page.getPages();
	}
	
	
	public PageUtils(List<?> list, int total) {
		this.list = list;
		this.totalCount = total;
	}

	public static PageUtils getPageInfo(Map<String, Object> params){
		if(params!=null&&params.containsKey("page")&&params.containsKey("limit")){
			PageUtils page =new PageUtils();
			page.setPage(Integer.valueOf((String) params.get("page")));
			page.setLimit(Integer.valueOf((String) params.get("limit")));
			return page;
		}else{
			return null;
		}
		
	}
	
}
