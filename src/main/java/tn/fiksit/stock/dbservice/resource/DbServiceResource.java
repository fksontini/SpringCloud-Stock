package tn.fiksit.stock.dbservice.resource;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.fiksit.stock.dbservice.model.Quote;
import tn.fiksit.stock.dbservice.model.Quotes;
import tn.fiksit.stock.dbservice.repository.QuotesRepository;


@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {

	private QuotesRepository quotesRespository;
	
	public DbServiceResource(QuotesRepository quotesRespository) {
		this.quotesRespository = quotesRespository;
	}
	@GetMapping("/{username}")
	public List<String> getQuotesByUserName(@PathVariable("username")
									final String username)
	{
		return 
		 quotesRespository.findByUserName(username)
		.stream()
		.map(Quote::getQuote)
		.collect(Collectors.toList());

	}
	@PostMapping("/add")
	public List<String> add(@RequestBody final Quotes quotes)
	{
		quotes.getQuotes()
		.stream()
		.map(quote-> new Quote(quotes.getUserName(),quote))
		.forEach(quote-> quotesRespository.save(quote));
		return getQuotesByUserName(quotes.getUserName());
	}
	
			 
	

	
}
