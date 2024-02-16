package com.boonreal.aopdemo;

import com.boonreal.aopdemo.dao.AccountDAO;
import com.boonreal.aopdemo.dao.MembershipDAO;
import com.boonreal.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public  CommandLineRunner commandLineRunner(AccountDAO accountDAO, MembershipDAO membershipDAO, TrafficFortuneService trafficFortuneService) {
		return runner -> {
			demoAroundAdviceHandleException(trafficFortuneService);

		};
	}

	private void demoAroundAdviceHandleException(TrafficFortuneService trafficFortuneService) {
		System.out.println("\nMain Program: demoAroundAdviceHandleException");
		System.out.println("Calling getFortune()");

		boolean tripWire=true;

		String data=trafficFortuneService.getFortune(tripWire);
		System.out.println(data);
	}

	private void demoAroundAdvice(TrafficFortuneService trafficFortuneService) {
		System.out.println("\nMain Program: demoAroundAdvice");
		System.out.println("Calling getFortune()");

		String data=trafficFortuneService.getFortune();
		System.out.println(data);
	}

	private void demoAfterAdvice(AccountDAO accountDAO) {
		List<Account> accounts=null;

		try {
			boolean tripWire=true;
			accounts=accountDAO.findAccounts(tripWire);
		}
		catch (Exception exc) {
			System.out.println("\n\nMain Program: ....caugth exception: "+exc);

		}

		System.out.println("\n\nMain Program: demoAfterThrowingAdvice");
		System.out.println("----");
		System.out.println(accounts);
	}

	private void demoAfterThrowingAdvice(AccountDAO accountDAO) {
		List<Account> accounts=null;

		try {
			boolean tripWire=true;
			accounts=accountDAO.findAccounts(tripWire);
		}
		catch (Exception exc) {
			System.out.println("\n\nMain Program: ....caugth exception: "+exc);

		}

		System.out.println("\n\nMain Program: demoAfterThrowingAdvice");
		System.out.println("----");
		System.out.println(accounts);
	}

	private void demoAfterReturningAdvice(AccountDAO accountDAO) {
		List<Account> accounts=accountDAO.findAccounts();

		System.out.println("\n\nMain Program: demoAfterReturningAdvice");
		System.out.println("----");
		System.out.println(accounts);
	}

	private void demoTheBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {

		Account account=new Account();
		accountDAO.addAccount(account,true);
		membershipDAO.addAccount();
	}

}
