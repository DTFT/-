package com.msg.model;

public class SystemContext {
	private static ThreadLocal<Integer> pageSize=new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> pageIndex=new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> pageOffset=new ThreadLocal<Integer>();
	
	public static int getPageOffset() {
		return pageOffset.get();
	}
	public static void setPageOffset(int _pageOffset) {
		pageOffset.set(_pageOffset);
	}
	public static void removepageOffset(){
		pageOffset.remove();
	}
	
	public static void setPageSize(int _pageSize){
		pageSize.set(_pageSize);
	}
	public static int getPageSize(){
		return pageSize.get();
	}
	public static void removePageSize(){
		pageSize.remove();
	}
	
	public static void setpageIndex(int _pageIndex){
		pageIndex.set(_pageIndex);
	}
	public static int getpageIndex(){
		return pageIndex.get();
	}
	public static void removepageIndex(){
		pageIndex.remove();
	}
}
