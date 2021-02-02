package com.movie.directory.movies.demo.businessLogic;
import java.util.ArrayList;

public class AllFilters implements Filter{
	 ArrayList<Filter> filters;
	    
	    public AllFilters() {
	        filters = new ArrayList<Filter>();
	    }

	    public void addFilter(Filter f) {
	        filters.add(f);
	    }

	    @Override
	    public boolean satisfies(int id) {
	        for(Filter f : filters) {
	            if (! f.satisfies(id)) {
	                return false;
	            }
	        }
	        
	        return true;
	    }
}
