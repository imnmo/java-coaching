package com.frotscher.coaching.generics.flights;

public class FlightSearchTask implements Task<FlightSearch, FlightDetails>{

	private FlightSearch searchCriteria;
	
	public FlightSearchTask(FlightSearch searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	
	@Override
	public FlightSearch getParameters() {
		return searchCriteria;
	}

	@Override
	public FlightDetails execute() {
		FlightDetails details = 
				new FlightDetails(
						searchCriteria.getOrigin(),
						searchCriteria.getDestination(), 
						"LH123", 
						250.00);
		return details;
	}
}
