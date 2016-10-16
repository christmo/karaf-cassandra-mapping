package io.twim.persister;

import com.datastax.driver.core.*;
import com.datastax.driver.core.policies.ConstantReconnectionPolicy;
import com.datastax.driver.core.policies.LatencyAwarePolicy;
import com.datastax.driver.core.policies.RoundRobinPolicy;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import io.twim.model.UserSubject;
import io.twim.model.UserSubjectCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * Created by christmo on 15/10/16.
 */
public class CassandraCRUD implements CRUD {

    private static final Logger log = LoggerFactory.getLogger(CassandraCRUD.class);

    private int port;
    private String clusterName;
    private String keyspace;
    private String ip;

    private Cluster cluster = null;
    private Session session = null;
    private MappingManager manager = null;


    public void init() throws Exception {

        Cluster.Builder builder = Cluster.builder();
        builder.withLoadBalancingPolicy(LatencyAwarePolicy.builder(new RoundRobinPolicy()).build());
        builder.withReconnectionPolicy(new ConstantReconnectionPolicy(1000L));

        if (Objects.nonNull(ip)) {
            builder.addContactPoint(ip);
        } else {
            log.error("Cassandra cluster IP is null");
        }
        if (port > 1) {
            builder.withPort(port);
        } else {
            log.error("Cassandra cluster PORT is < 0");
        }
        if (Objects.nonNull(clusterName)) {
            builder.withClusterName(clusterName);
        } else {
            log.error("Cassandra cluster NAME is null");
        }

        cluster = builder.build();

        registryCodecs(keyspace);
    }

    private void registryCodecs(String keyspace) {
        CodecRegistry codecRegistry = cluster.getConfiguration().getCodecRegistry();

        UserType userSubjectType = cluster.getMetadata().getKeyspace(keyspace).getUserType("user_subject");
        TypeCodec<UDTValue> userSubjectTypeCodec = codecRegistry.codecFor(userSubjectType);
        UserSubjectCodec codec = new UserSubjectCodec(userSubjectTypeCodec, UserSubject.class);
        codecRegistry.register(codec);
    }

    @Override
    public <E> void save(E entity, Class<E> clazz) {
        log.info("Starting save");
        Mapper mapper = manager.mapper(clazz);
        mapper.save(entity);
        log.info("Ending save");
    }


    public void setPort(int port) {
        this.port = port;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public void setKeyspace(String keyspace) {
        this.keyspace = keyspace;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
