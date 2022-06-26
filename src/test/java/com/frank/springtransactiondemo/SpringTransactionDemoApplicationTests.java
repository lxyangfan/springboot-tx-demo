package com.frank.springtransactiondemo;

import com.frank.springtransactiondemo.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringTransactionDemoApplication.class})
class SpringTransactionDemoApplicationTests {

	@Autowired
	private OrderService orderService;

	/**
	 * 外部事务和内部事务互相隔离。外部事务回滚不影响内部事务提交。
	 */
	@Test
	void givenOutDefaultSubRequiresNew_whenOutMeetException_thenOutRollbackSubCommits() {
		orderService.makeAnOrderSubRequiresNew1("item_1", 10);
	}

	/**
	 * 内部事务抛异常回滚，如果外部事务可以不处理，异常会向上抛，导致外部事务回滚。
	 */
	@Test
	void givenOutDefaultSubRequiresNew_whenSubMeetException_thenOutRollbackSubRollback() {
		orderService.makeAnOrderSubRequiresNew2("item_2", 5);
	}

}
