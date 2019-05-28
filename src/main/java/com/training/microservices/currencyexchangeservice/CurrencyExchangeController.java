package com.training.microservices.currencyexchangeservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	@Autowired
	private Environment env;
	
	@Autowired
	private ExchangeValueRepository repo;
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		ExchangeValue ev= repo.findByFromAndTo(from, to);
		ev.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		return ev;
	}
	
	@GetMapping("/currency-exchange/allExchangeValues")
	public List<ExchangeValue> retrieveAllExchangeValues() {
		
		return repo.findAll();
	}
	
	@PostMapping("/currency-exchange/addExchangeValue")
	public void postExchangeValue(@RequestBody ExchangeValue exchangeValue) throws Exception {
		
		if (!repo.findById(exchangeValue.getId()).isEmpty()
				|| repo.findByFromAndTo(exchangeValue.getFrom(), exchangeValue.getTo()) != null) {
			//TODO: CIC guys might have an error page or something we can direct this message to.
			throw new Exception("An exchangeValue with id: " + exchangeValue.getId() 
				+ " or from: " + exchangeValue.getFrom() + " and to: " + exchangeValue.getTo() + " already exists");
		}
		exchangeValue.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		repo.save(exchangeValue);
	}

}
