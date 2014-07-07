/*
 * 系统名称：新闻发布系统
 * 
 * 类名：NewsTypeService
 * 
 * 创建日期：2014-06-20
 */
package org.news.service;

import java.util.ArrayList;
import java.util.List;

import org.news.dao.NewsTypeDAO;
import org.news.model.NewsType;
import org.news.utils.Logger;

/**
 * 文章类型服务类
 * @author tt
 * @version 14.6.18
 */
public class NewsTypeService {
	
	private NewsTypeDAO newsTypeDAO = new NewsTypeDAO(); //引入新闻消息DAO
	
	/**
	 * 添加频道，每新增一个种类，将原来的名字都加上该种类，再添加入集合中
	 * @param type
	 * @param description
	 * @return 操作是否成功
	 */
	public boolean addNewsType(String type, String description){

		boolean flag = true;//标志
		List<NewsType> typeSet = getAllNewsType();//获取当前所有频道
	
		//单个种类的算一个新频道
		int newsTypeId;//频道ID
		if (typeSet.size() == 0){
			newsTypeId = 1;
		}else{
			newsTypeId = typeSet.get(typeSet.size()-1).getNewsTypeId()+1;
		}
		NewsType newstype = new NewsType(newsTypeId, type+",", description);
		if (!newsTypeDAO.addNewsType(newstype)){
			flag = false;
		}
		
		for (NewsType newsType: typeSet){
			Logger.log(newsType.getNewsTypeName(), Logger.DEBUG);
			newsType.setNewsTypeId(++newsTypeId);
			newsType.setNewsTypeName(newsType.getNewsTypeName()+type+",");//将频道加上当前名字
			newsType.setNewsTypeDescripe("");
			if (!newsTypeDAO.addNewsType(newsType)){
				flag = false;
			}
		}
		
		return flag;
	}

	/**
	 * 批量删除
	 * @param index
	 * @return 是否成功
	 */
	public boolean deleteNewsType(int index) {
		ArrayList<Integer> newsTypeIds = new ArrayList<Integer>(); //要删除的频道ID
		String type = getNewsTypeById(index).getNewsTypeName();//获取该频道名字
		List<NewsType> typeSet = getAllNewsType();//获取当前所有频道
		
		for (NewsType newsType: typeSet){
			if (newsType.getNewsTypeName().contains(type)){//如果名字中包含该种类
				Logger.log(newsType.getNewsTypeName(), Logger.DEBUG);
				newsTypeIds.add(new Integer(newsType.getNewsTypeId()));//加入到批量删除当中
			}
		}
		return newsTypeDAO.deleteNewsType(newsTypeIds);
	}
	
	/**
	 * 修改频道信息
	 * @param NewsType
	 * @return 是否成功
	 */
	public boolean updateNewsType(NewsType newsType) {
		if (newsTypeDAO.updateNewsType(newsType) == null){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	* 查询所有的频道信息
	* @return 频道集合
	*/
	 public List<NewsType> getAllNewsType(){
		 return newsTypeDAO.getAllNewsType();
	}
	 
	/**
	* 查询所有的频道种类
	* @return 频道集合
	*/
	public List<NewsType> getAllTypes(){
		List<NewsType> typeSet = getAllNewsType();//获取当前所有频道

		for (int i = 0; i<typeSet.size();++i){
			NewsType newsType = new NewsType(typeSet.get(i));
			String[] set = newsType.getNewsTypeName().split(",");
			Logger.log(set.length, Logger.DEBUG);
			if (set.length > 1){//多个种类，则删除
				Logger.log("del"+i, Logger.DEBUG);
				typeSet.remove(i);
				i--;//指针前移
			}
		}
		
		return typeSet;
	}	 
	
	/**
	 * 根据ID号找到对应的类别
	 * @param typeid
	 * @return
	 */
	public NewsType getNewsTypeById(int typeid){
		return newsTypeDAO.findNewsTypeById(typeid);
	}
	
	/**
	 * 根据名称找ID
	 * @param names
	 * @return
	 */
	public int getTypeIdByName(String[] names){
		int ID = 0; 
		List<NewsType> typeSet = getAllNewsType();//获取当前所有频道
		int length = names.length;
		
		for (NewsType newsType: typeSet){
			String newsName = newsType.getNewsTypeName();
			int i = 0;
			for (; i<length; ++i){
				if (!newsName.contains(names[i])){
					break;//有一个不包含则退出
				}
			}
			if ((i == length)&&(newsName.split(",").length == length)){//找到匹配的类型
				return newsType.getNewsTypeId();
			}
		}
		
		return ID;
	}
	
	/**
	 * 根据ID找名称
	 * @param typeid
	 * @return
	 */
	public String [] getNamesByTypeId(int typeid){
		String typesName = getNewsTypeById(typeid).getNewsTypeName();//获取新闻类型
		String[] names = typesName.split(",");
		return names;
	}
}
