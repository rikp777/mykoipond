package com.api.mykoipond.projections;

import com.api.mykoipond.domain.BreedEntity;
import com.api.mykoipond.domain.KoiEntity;
import com.api.mykoipond.domain.PondEntity;
import com.api.mykoipond.domain.SubBreedEntity;
import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

@Projection(name = "inlineSubBreeds", types = { BreedEntity.class })
public interface BreedProjection {
    String getName();
    String getDescription();
    Set<SubBreedProjection> getSubBreeds();
}
