package com.latico.example.springboot.service.springbatch.writer;


import com.latico.example.springboot.service.springbatch.model.User;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class MyWriter implements ItemWriter<User> {

	@Override
	public void write(List<? extends User> items) throws Exception {
		for(User user : items){
			System.out.println(user);
		}
	}

}
