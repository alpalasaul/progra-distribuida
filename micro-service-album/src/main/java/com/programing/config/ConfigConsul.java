package com.programing.config;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.model.agent.ImmutableRegCheck;
import com.orbitz.consul.model.agent.ImmutableRegistration;
import com.orbitz.consul.model.agent.Registration;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Destroyed;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.UUID;

@ApplicationScoped
public class ConfigConsul {

    public static final String NAME = "mp-album";
    public static String ID;

    @Inject
    @ConfigProperty(name = "port", defaultValue = "9080")
    private Integer puerto;

    @Inject
    @ConfigProperty(name = "consul.ip", defaultValue = "127.0.0.1")
    private String consulIp;

    @PostConstruct
    public void init() {
        System.out.println("***inicializar");
        ID = UUID.randomUUID().toString();
    }

    public void init(@Observes @Initialized(ApplicationScoped.class) Object obj) {
        System.out.printf("[%s] App inicializada: %s, puerto: %d\n", NAME, ID, puerto);

        System.out.printf("*****CONSUL: %s\n", consulIp);

        Consul client = Consul.builder()
                .withHostAndPort(HostAndPort.fromParts("localhost", 8500))
                .build();

        String urlCheck = String.format("http://127.0.0.1:%d/health", puerto);

        ImmutableRegCheck check = ImmutableRegCheck.builder()
                .http(urlCheck)
                .interval("10s")
                .deregisterCriticalServiceAfter("5s")
                .build();

        AgentClient agentClient = client.agentClient();
        Registration service = ImmutableRegistration.builder()
                .id(ID)
                .name(NAME)
                .address("127.0.0.1")
                .port(puerto)
                .putMeta("ip", "127.0.0.1")
                .putMeta("puerto", puerto.toString())
                .check(check)
                .build();

        agentClient.register(service);

    }

    public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object object) {
        System.out.println("App destruida");

        Consul client = Consul.builder()
                .withHostAndPort(HostAndPort.fromParts(consulIp, 8500))
                .build();
        AgentClient agentClient = client.agentClient();
        agentClient.deregister(ID);
    }

}
