package com.alt6wer.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alt6wer.demo.model.MostUsersEverOnline;
import com.alt6wer.demo.repository.MostUsersEverOnlineRepository;

@Service
public class MostUsersEverOnlineService {
	
	@Autowired
	private MostUsersEverOnlineRepository mostUsersEverOnlineRepository;
	
	public MostUsersEverOnline findMostUsersEverOnline(MostUsersEverOnline currentMostUsersEverOnline) {
		MostUsersEverOnline mostUsersEverOnline = mostUsersEverOnlineRepository.findFirstByOrderByNumberOfUsersDesc();
		if (mostUsersEverOnline == null || mostUsersEverOnline.getNumberOfUsers() <= currentMostUsersEverOnline.getNumberOfUsers()) {
			mostUsersEverOnlineRepository.save(currentMostUsersEverOnline);
			mostUsersEverOnlineRepository.delete(mostUsersEverOnline);
			return currentMostUsersEverOnline;
		}
		return mostUsersEverOnline;
	}

}
