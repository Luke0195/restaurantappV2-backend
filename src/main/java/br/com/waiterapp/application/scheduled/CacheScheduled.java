package br.com.waiterapp.application.scheduled;


import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Log4j2
public class CacheScheduled {

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.HOURS)
    @CacheEvict("historic")
    public void clearHistoricCache(){
        log.info("Cache removido com sucesso!");
    }
}
