package com.blogposts.service;

import com.blogposts.entity.Blogpost;
import org.apache.logging.log4j.util.Strings;

import java.util.Comparator;

/**
 * A custom comparator to sort according to the given 'sortBy' and 'direction' argument
 */
public class BlogpostComparator implements Comparator<Blogpost> {

    private BlogpostCompareType blogpostCompareType;
    private String order;

    BlogpostComparator(BlogpostCompareType blogpostCompareType, String order) {
        this.blogpostCompareType = blogpostCompareType;
        this.order = order;
    }

    /**
     * Overridden compare method of the Comparator class
     * @param b1 : blogpost1
     * @param b2 : blogpost2
     * @return 0/-1/1 (int)
     */
    @Override
    public int compare(Blogpost b1, Blogpost b2) {
        if (!Strings.isEmpty(order)) {
            if (order.equals("desc")) {
                return helper(b2, b1);
            } else {
                return helper(b1, b2);
            }
        }
        return b1.getId() - b2.getId();
    }

    /**
     * Helper method for handling different types of comparisons
     * @param b1 : blogpost1
     * @param b2 : blogpost2
     * @return 0/-1/1 (int)
     */
    private int helper(Blogpost b1, Blogpost b2) {
        switch (blogpostCompareType) {
            case ID:
                return b1.getId() - b2.getId();
            case LIKES:
                return b1.getLikes() - b2.getLikes();
            case READS:
                return Integer.compare(Float.compare(b1.getReads(), b2.getReads()), 0);
            case POPULARITY:
                return Integer.compare(Float.compare(b1.getPopularity(), b2.getPopularity()), 0);
        }
        return b1.getId() - b2.getId();
    }
}
