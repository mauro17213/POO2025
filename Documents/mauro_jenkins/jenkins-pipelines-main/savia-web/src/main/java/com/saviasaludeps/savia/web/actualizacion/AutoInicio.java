package com.saviasaludeps.savia.web.actualizacion;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class AutoInicio {

    ExecutorService executorService = Executors.newFixedThreadPool(3);

    @PostConstruct
    public void init() {
        try {
            executorService.submit(new HiloConfiguracion());
            executorService.submit(new HiloUbicacion());
            executorService.submit(new HiloMaestro());
        } catch (Exception ex) {
        }
    }

    @PreDestroy
    public void doShutdown() {
        executorService.shutdownNow();
    }

}
