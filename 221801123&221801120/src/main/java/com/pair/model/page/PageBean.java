package com.pair.model.page;

import java.util.List;

/**
 * 封装分页相关的数据
 */
public class PageBean<T> {

	/**
	 * 当前页的数据
	 */
	private List<T> records;
	/**
	 * 每页记录数
	 */
	private int pageSize;
	
	/**
	 * 当前页
	 */
	private int currentPage;
	/**
	 * 总记录数
	 */
	private int recordCount;

	/**
	 * 显示页码的数量
	 * 默认是10
	 */
	private int pageNumber;
	
	/**
	 * 开始页号，用于下面的页面导航
	 */
	private int pageBeginIndex;
	/**
	 * 结束页号
	 */
	private int pageEndIndex;
	/**
	 * 总页数
	 */
	private int pageCount;
	
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageBeginIndex() {
		return pageBeginIndex;
	}
	public void setPageBeginIndex(int pageBeginIndex) {
		this.pageBeginIndex = pageBeginIndex;
	}
	public int getPageEndIndex() {
		return pageEndIndex;
	}
	public void setPageEndIndex(int pageEndIndex) {
		this.pageEndIndex = pageEndIndex;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	//用于测试
	@Override
	public String toString() {
		return "PageBean{" +
				", pageSize=" + pageSize +
				", currentPage=" + currentPage +
				", recordCount=" + recordCount +
				", pageNumber=" + pageNumber +
				", pageBeginIndex=" + pageBeginIndex +
				", pageEndIndex=" + pageEndIndex +
				", pageCount=" + pageCount +
				'}';
	}
}
