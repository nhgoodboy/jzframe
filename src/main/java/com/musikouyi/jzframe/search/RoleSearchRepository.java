package com.musikouyi.jzframe.search;

import com.musikouyi.jzframe.domain.entity.Role;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface RoleSearchRepository extends ElasticsearchRepository<Role, Integer> {
}
