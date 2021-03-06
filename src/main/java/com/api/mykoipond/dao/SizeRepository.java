package com.api.mykoipond.dao;


import com.api.mykoipond.domain.BreedEntity;
import com.api.mykoipond.domain.SizeEntity;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(
        path="sizes",
        collectionResourceRel ="sizes"
)
public interface SizeRepository extends CrudRepository<SizeEntity, Long>{
}
