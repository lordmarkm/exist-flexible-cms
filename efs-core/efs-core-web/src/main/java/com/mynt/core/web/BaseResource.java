package com.mynt.core.web;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.efs.core.dto.BaseInfo;
import com.mynt.core.jpa.model.BaseEntity;
import com.mynt.core.jpa.service.ExistJpaServiceCustom;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiImplicitParams;

/**
 *
 * @author mbmartinez, Sep 27, 2017
 *
 * @param <D> - DTO
 * @param <S> - Service
 */
public abstract class BaseResource<D extends BaseInfo, S extends ExistJpaServiceCustom<? extends BaseEntity, D>> {

    private static final Logger LOG = LoggerFactory.getLogger(BaseResource.class);

    @Autowired
    protected S service;

    @ApiOperation(value = "Perform a parametrized search for this entity")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", dataType = "int", paramType = "query",
                value = "Results page you want to retrieve (0..N)"),
        @ApiImplicitParam(name = "size", dataType = "int", paramType = "query",
                value = "Number of records per page."),
        @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                value = "Sorting criteria in the format: property(,asc|desc). "
                        + "Default sort order is ascending. "
                        + "Multiple sort criteria are supported.")
    })
    @RequestMapping(method = GET)
    public ResponseEntity<Page<D>> rqlSearch(
            @RequestParam(required = false) @ApiParam(value = "The search term, example: name==test;deleted==false") String term,
            Pageable page) {
        LOG.debug("{}::rqlSearch({}, {})", this.getClass().getSimpleName(), term, page);
        return new ResponseEntity<>(service.rqlSearch(term, service.getFieldMapping(), page), OK);
    }

    //TODO doesn't work
    //http://stackoverflow.com/questions/27170298/spring-reponsebody-requestbody-with-abstract-class
//    @RequestMapping(method = POST)
//    public ResponseEntity<D> save(@RequestBody D toSave) {
//        return new ResponseEntity<>(service.saveInfo(toSave), OK);
//    }

    @ApiOperation(value = "Perform a soft delete on this entity")
    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity<D> delete(@PathVariable @ApiParam(value = "The id of the entity to delete") Long id) {
        LOG.debug("{}::delete({})", this.getClass().getSimpleName(), id);
        return new ResponseEntity<>(service.deleteInfo(id), OK);
    }

    @ApiOperation(value = "Find a particular entity by ID")
    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<D> findOne(@PathVariable @ApiParam(value = "The id of the entity to query") Long id) {
        LOG.debug("{}::findOne({})", this.getClass().getSimpleName(), id);
        return new ResponseEntity<>(service.findOneInfo(id), OK);
    }

}