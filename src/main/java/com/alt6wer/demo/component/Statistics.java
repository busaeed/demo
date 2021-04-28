package com.alt6wer.demo.component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alt6wer.demo.model.Member;
import com.alt6wer.demo.model.MostUsersEverOnline;
import com.alt6wer.demo.service.MemberService;
import com.alt6wer.demo.service.MostUsersEverOnlineService;
import com.alt6wer.demo.service.ReplyService;
import com.alt6wer.demo.service.SessionService;
import com.alt6wer.demo.service.TopicService;

import lombok.Getter;

@Getter
@Component
public class Statistics {
	
	private List<Member> onlineMemberList;
	
	private int numberOfOnlineGuests;
	private int numberOfOnlineMembers;
	private int numberOfOnlineVisitors;
	
	private MostUsersEverOnline mostUsersEverOnline;
	
	private int totalNumberOfMembers;
	private int totalNumberOfActiveMembers;
	
	private int totalNumberOfTopics;
	private int totalNumberOfReplies;
	
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MostUsersEverOnlineService mostUsersEverOnlineService;
	
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private ReplyService replyService;
	
	@PostConstruct
	public void init() {
		this.numberOfOnlineGuests = this.fetchNumberOfOnlineGuests();
		this.onlineMemberList = this.fetchOnlineMemberList();
		this.numberOfOnlineMembers = this.fetchNumberOfOnlineMembers();
		this.numberOfOnlineVisitors = this.fetchNumberOfOnlineVisitors();
		this.mostUsersEverOnline = this.fetchMostUsersEverOnline();
		this.totalNumberOfMembers = this.fetchTotalNumberOfMembers();
		this.totalNumberOfActiveMembers = this.fetchTotalNumberOfActiveMembers();
		this.totalNumberOfTopics = this.fetchTotalNumberOfTopics();
		this.totalNumberOfReplies = this.fetchTotalNumberOfReplies();
	}

	private int fetchTotalNumberOfReplies() {
		return replyService.findTotalNumberOfReplies();
	}

	private int fetchTotalNumberOfTopics() {
		return topicService.findTotalNumberOfTopics();
	}

	private int fetchTotalNumberOfActiveMembers() {
		long activeSince = (Instant.now().getEpochSecond()*1000)-2592000000L;
		return memberService.findTotalNumberOfActiveMembers(activeSince);
	}

	private int fetchTotalNumberOfMembers() {
		return memberService.findTotalNumberOfMembers();
	}

	private MostUsersEverOnline fetchMostUsersEverOnline() {
		MostUsersEverOnline currentMostUsersEverOnline = new MostUsersEverOnline(LocalDateTime.now(), this.numberOfOnlineVisitors);
		return mostUsersEverOnlineService.findMostUsersEverOnline(currentMostUsersEverOnline);
	}

	private List<Member> fetchOnlineMemberList() {
		return memberService.findOnlineMembers();
	}

	@Override
	public String toString() {
		return "Statistics [numberOfOnlineGuests=" + numberOfOnlineGuests + ", numberOfOnlineMembers="
				+ numberOfOnlineMembers + ", numberOfOnlineVisitors=" + numberOfOnlineVisitors + "]";
	}

	private int fetchNumberOfOnlineGuests() {
		return sessionService.findNumberOfOnlineGuests();
	}

	private int fetchNumberOfOnlineMembers() {
		return this.onlineMemberList.size();
	}

	private int fetchNumberOfOnlineVisitors() {
		return this.numberOfOnlineGuests + this.numberOfOnlineMembers;
	}
	
}