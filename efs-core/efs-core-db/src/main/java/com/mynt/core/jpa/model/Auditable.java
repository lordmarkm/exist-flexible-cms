package com.mynt.core.jpa.model;

import java.util.List;
import java.util.Map;

/**
 * Created by hkmalabanan on 7/26/17.
 */
public interface Auditable {

    String getAuditName();

    /**
     * Stores List inside Entity that can't be fetched using the
     * existing Javers object
     * e.g
     * List<String> - this can be diff by javers
     * but once we get the changes between these two List<String>
     * a ListChange will be created on the diff (if a item/object was being added)
     * and currently upon checking the ListChange there is no existing prorperty
     * in order to fetch the changed value.
     * */
    default Map<String, List<?>> getSpecialHandlingAudit() {
        return null;
    }

    default String getHTMLString() {
        return  "N/A";
    }
}
