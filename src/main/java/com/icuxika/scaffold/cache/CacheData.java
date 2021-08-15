package com.icuxika.scaffold.cache;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public interface CacheData {

    @JsonIgnore
    String getCacheKey();
}
