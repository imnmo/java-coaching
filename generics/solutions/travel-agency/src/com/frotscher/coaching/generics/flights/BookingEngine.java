package com.frotscher.coaching.generics.flights;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class BookingEngine {

//	public <P, R> List<R> executeTasksSync(List<Task<P, R>> tasks) {
//		List<R> results = new ArrayList<>();
//		for (Task<P, R> task : tasks) {
//			R result = task.execute();
//			results.add(result);
//		}
//		return results;
//	}
	
	public List<?> executeTasksSync(List<Task<?, ?>> tasks) {
		
//		List<Object> results = new ArrayList<Object>();
//		for (Task<?, ?> task : tasks) {
//			Object result = task.execute();
//			results.add(result);
//		}
//		
		List<?> results = tasks.stream()
                               .map(task -> task.execute())
                               .collect(Collectors.toList());
		return results;
	}

	public <P, R> List<Future<R>> executeTasksAsync(List<Task<P, R>> tasks) {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		
		List<Future<R>> futures = new ArrayList<>();
		for (Task<P, R> task : tasks) {
			futures.add(executor.submit(task::execute));
		}
		return futures;
	}
}
