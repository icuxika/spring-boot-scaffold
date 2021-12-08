package com.icuxika.scaffold.module.blog.mapper;

import com.icuxika.scaffold.module.blog.entity.BikeGeo;
import com.icuxika.scaffold.module.blog.entity.Blog;
import com.icuxika.scaffold.module.blog.entity.BlogContent;
import com.icuxika.scaffold.module.blog.entity.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class BlogMapperTest {

    @Autowired
    @Qualifier("tBlogMapper")
    private BlogMapper blogMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void insert() {
        Blog blog = new Blog();
        blog.setUserId(2L);
        blog.setCreateTime(LocalDateTime.now());
        blog.setUpdateTime(LocalDateTime.now());
        blog.insert();
        System.out.println(blog.getId());

        BlogContent blogContent = new BlogContent();
        blogContent.setBlogId(blog.getId());
        blogContent.setContent("ABCDEFG");
        mongoTemplate.insert(blogContent);
        System.out.println(blogContent.getId());
    }

    @Test
    void select() {
        BlogContent blogContent = mongoTemplate.findOne(Query.query(Criteria.where("blogId").is(2L)), BlogContent.class);
        System.out.println(blogContent);
    }

    @Test
    void createBikeGeoData() {

        List<Double[]> doubles = new ArrayList<>();
        doubles.add(new Double[]{118.692543, 32.111296});
        doubles.add(new Double[]{118.691429, 32.110261});
        doubles.add(new Double[]{118.675943, 32.10959});
        doubles.add(new Double[]{118.669296, 32.113627});
        doubles.add(new Double[]{118.705264, 32.114422});
        doubles.add(new Double[]{118.710797, 32.105767});
        doubles.add(new Double[]{118.690855, 32.114361});
        doubles.add(new Double[]{118.682555, 32.117618});
        doubles.add(new Double[]{118.691717, 32.112365});
        doubles.add(new Double[]{118.688313, 32.113505});

        List<BikeGeo> bikeGeoList = doubles.stream().map(point -> {
            BikeGeo bikeGeo = new BikeGeo();
            Location location = new Location();
            location.setCoordinates(point);
            bikeGeo.setLocation(location);
            return bikeGeo;
        }).collect(Collectors.toList());

        mongoTemplate.insertAll(bikeGeoList);

    }

    @Test
    void aroundBike() {
        GeoResults<BikeGeo> bikeGeoGeoResults = mongoTemplate.geoNear(
                NearQuery.near(new Point(118.688313, 32.113505))
                        .spherical(true).inKilometers().maxDistance(0.3), BikeGeo.class, "bikeGeo"
        );
        List<GeoResult<BikeGeo>> geoResultList = bikeGeoGeoResults.getContent();
        for (GeoResult<BikeGeo> bikeGeoGeoResult : geoResultList) {
            System.out.println(bikeGeoGeoResult);
        }
    }
}