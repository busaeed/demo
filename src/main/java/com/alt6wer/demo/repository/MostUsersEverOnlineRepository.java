package com.alt6wer.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.alt6wer.demo.model.MostUsersEverOnline;
import com.alt6wer.demo.model.MostUsersEverOnlineId;

public interface MostUsersEverOnlineRepository extends CrudRepository<MostUsersEverOnline, MostUsersEverOnlineId> {
	
	MostUsersEverOnline findFirstByOrderByNumberOfUsersDesc();
	
}
