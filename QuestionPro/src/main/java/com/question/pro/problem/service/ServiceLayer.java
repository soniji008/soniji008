package com.question.pro.problem.service;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.question.pro.problem.entity.Items;
import com.question.pro.problem.entity.SpecificData;

@Service
@CacheConfig(cacheNames = {"example"})
public class ServiceLayer {
	
	public  RestTemplate restTemplate;
    SpecificData first,second;
    Integer maxid1;
	Integer maxid2;
	
	@Autowired
	public ServiceLayer(RestTemplate restTemplate) {
		super();
		this.restTemplate = restTemplate;
	}

	
	public ServiceLayer() {
		super();
		
	}
	
	
	


	@Cacheable
	public ArrayList<Items> consumeTopStories() {
		
		
		first = restTemplate.getForObject("https://hacker-news.firebaseio.com/v0/maxitem.json", SpecificData.class);
		 ArrayList<Items> result = new ArrayList<>();
		 maxid1 = first.getId();
		 for(Integer i = maxid1; i>maxid1-10;i--) {
			  
			 result.add(restTemplate.getForObject("https://hacker-news.firebaseio.com/v0/item/"+i+".json", Items.class));
		 }
		 
		return result;
		
	}


	public ArrayList<Items> consumePastStories() {
		
		 second = restTemplate.getForObject("https://hacker-news.firebaseio.com/v0/maxitem.json", SpecificData.class);
		 maxid2 = second.getId();
		 ArrayList<Items> result2 = new ArrayList<>();
		 for(Integer i = maxid2; i>maxid1-10;i--) {
			  
			 result2.add(restTemplate.getForObject("https://hacker-news.firebaseio.com/v0/item/"+i+".json", Items.class));
		 }
		 
		return result2;
		
	}


	public String consumeComments(long id) {
		
		Items temp =restTemplate.getForObject("https://hacker-news.firebaseio.com/v0/item/"+id+".json", Items.class);
		
		
		
		
		return ("Comments: "+temp.getText());
	}


	

}
