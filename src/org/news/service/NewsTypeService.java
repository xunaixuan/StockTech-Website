/*
 * ϵͳ���ƣ����ŷ���ϵͳ
 * 
 * ������NewsTypeService
 * 
 * �������ڣ�2014-06-20
 */
package org.news.service;

import java.util.ArrayList;
import java.util.List;

import org.news.dao.NewsTypeDAO;
import org.news.model.NewsType;
import org.news.utils.Logger;

/**
 * �������ͷ�����
 * @author tt
 * @version 14.6.18
 */
public class NewsTypeService {
	
	private NewsTypeDAO newsTypeDAO = new NewsTypeDAO(); //����������ϢDAO
	
	/**
	 * ���Ƶ����ÿ����һ�����࣬��ԭ�������ֶ����ϸ����࣬������뼯����
	 * @param type
	 * @param description
	 * @return �����Ƿ�ɹ�
	 */
	public boolean addNewsType(String type, String description){

		boolean flag = true;//��־
		List<NewsType> typeSet = getAllNewsType();//��ȡ��ǰ����Ƶ��
	
		//�����������һ����Ƶ��
		int newsTypeId;//Ƶ��ID
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
			newsType.setNewsTypeName(newsType.getNewsTypeName()+type+",");//��Ƶ�����ϵ�ǰ����
			newsType.setNewsTypeDescripe("");
			if (!newsTypeDAO.addNewsType(newsType)){
				flag = false;
			}
		}
		
		return flag;
	}

	/**
	 * ����ɾ��
	 * @param index
	 * @return �Ƿ�ɹ�
	 */
	public boolean deleteNewsType(int index) {
		ArrayList<Integer> newsTypeIds = new ArrayList<Integer>(); //Ҫɾ����Ƶ��ID
		String type = getNewsTypeById(index).getNewsTypeName();//��ȡ��Ƶ������
		List<NewsType> typeSet = getAllNewsType();//��ȡ��ǰ����Ƶ��
		
		for (NewsType newsType: typeSet){
			if (newsType.getNewsTypeName().contains(type)){//��������а���������
				Logger.log(newsType.getNewsTypeName(), Logger.DEBUG);
				newsTypeIds.add(new Integer(newsType.getNewsTypeId()));//���뵽����ɾ������
			}
		}
		return newsTypeDAO.deleteNewsType(newsTypeIds);
	}
	
	/**
	 * �޸�Ƶ����Ϣ
	 * @param NewsType
	 * @return �Ƿ�ɹ�
	 */
	public boolean updateNewsType(NewsType newsType) {
		if (newsTypeDAO.updateNewsType(newsType) == null){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	* ��ѯ���е�Ƶ����Ϣ
	* @return Ƶ������
	*/
	 public List<NewsType> getAllNewsType(){
		 return newsTypeDAO.getAllNewsType();
	}
	 
	/**
	* ��ѯ���е�Ƶ������
	* @return Ƶ������
	*/
	public List<NewsType> getAllTypes(){
		List<NewsType> typeSet = getAllNewsType();//��ȡ��ǰ����Ƶ��

		for (int i = 0; i<typeSet.size();++i){
			NewsType newsType = new NewsType(typeSet.get(i));
			String[] set = newsType.getNewsTypeName().split(",");
			Logger.log(set.length, Logger.DEBUG);
			if (set.length > 1){//������࣬��ɾ��
				Logger.log("del"+i, Logger.DEBUG);
				typeSet.remove(i);
				i--;//ָ��ǰ��
			}
		}
		
		return typeSet;
	}	 
	
	/**
	 * ����ID���ҵ���Ӧ�����
	 * @param typeid
	 * @return
	 */
	public NewsType getNewsTypeById(int typeid){
		return newsTypeDAO.findNewsTypeById(typeid);
	}
	
	/**
	 * ����������ID
	 * @param names
	 * @return
	 */
	public int getTypeIdByName(String[] names){
		int ID = 0; 
		List<NewsType> typeSet = getAllNewsType();//��ȡ��ǰ����Ƶ��
		int length = names.length;
		
		for (NewsType newsType: typeSet){
			String newsName = newsType.getNewsTypeName();
			int i = 0;
			for (; i<length; ++i){
				if (!newsName.contains(names[i])){
					break;//��һ�����������˳�
				}
			}
			if ((i == length)&&(newsName.split(",").length == length)){//�ҵ�ƥ�������
				return newsType.getNewsTypeId();
			}
		}
		
		return ID;
	}
	
	/**
	 * ����ID������
	 * @param typeid
	 * @return
	 */
	public String [] getNamesByTypeId(int typeid){
		String typesName = getNewsTypeById(typeid).getNewsTypeName();//��ȡ��������
		String[] names = typesName.split(",");
		return names;
	}
}
